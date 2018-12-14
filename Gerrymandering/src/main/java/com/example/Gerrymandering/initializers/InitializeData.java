package com.example.Gerrymandering.initializers;

import com.example.Gerrymandering.domain.District;
import com.example.Gerrymandering.domain.Precinct;
import com.example.Gerrymandering.domain.State;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class InitializeData {

    private String algorithmType;

    public InitializeData(String algorithmType) {
        this.algorithmType = algorithmType;
    }

    public State initialize(String stateName) {
        PopulateStates ps = new PopulateStates();
        PopulateDistricts pd = new PopulateDistricts();
        PopulatePrecincts pp = new PopulatePrecincts();
        List statesList = null;
        List districtsList = null;
        List precinctsList = null;
        try {
            statesList = ps.populate();
            districtsList = pd.populate(statesList);
            precinctsList = pp.populate(districtsList,algorithmType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<State> states = new HashSet<State>(statesList);
        Set<District> districts = new HashSet<District>(districtsList);
        Set<Precinct> precincts = new HashSet<Precinct>(precinctsList);

        ps.addDistricts(districts, precincts);
        pd.addPrecinctSet(precincts);

        if(algorithmType.equals("SIMULATED_ANNEALING")) {

        }

        State state;
        for(Iterator<State> it = states.iterator(); it.hasNext();) {
            state = it.next();
            if (state.getName().equals(stateName)) {
                return state;
            }
        }
        return null;
    }

}