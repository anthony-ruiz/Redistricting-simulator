package com.example.Gerrymandering.controller;

import java.util.*;

import com.example.Gerrymandering.domain.District;
import com.example.Gerrymandering.domain.ObjectiveFunction;
import com.example.Gerrymandering.domain.Precinct;
import com.example.Gerrymandering.domain.State;
//import com.example.Gerrymandering.persistence.PersistenceUnit;

public class RegionGrowingAlgorithm implements Algorithm {

    private Set<String> objectiveValues;
    private State currentState;
    private ArrayList<String> moves;

    public RegionGrowingAlgorithm(String state) {
//        currentState = new PersistenceUnit().getRGState(state);
    }

    @Override
    public void setWeights(String politicalFairness, String compactness, String populationEquality) {
        objectiveValues.add(politicalFairness);
        objectiveValues.add(compactness);
        objectiveValues.add(populationEquality);
    }
    
    @Override
    public void setState(String state) {
//        currentState = new PersistenceUnit().getRGState(state);
    }

    @Override
    public void beginAlgorithm() {
        {
            Set<Precinct> seeds = currentState.getSeedPrecincts();
            Set<District> districts = currentState.getDistricts();
            int districtAmt = currentState.getDistrictAmount();
            for (int i = 0; i < districtAmt; i++) {
                districts.add(new District());
            }
            Iterator<Precinct> iter = seeds.iterator();
            for (District dists : districts) {
                dists.addPrecinct(iter.next());
            }
            while (true) { // change "true" to loop through all districts in round robin fashion until all precincts are assigned to a district
                for (District dists : districts) {
                    Set<Precinct> borders = dists.getBorders();
                    Set<Precinct> currNeighbors = dists.getCurrentNeighbors();
                    currNeighbors.clear();
                    for (Precinct borderPrecinct : borders) {
                        currNeighbors.addAll(borderPrecinct.getEligibleNeighbors());
                    }
                    //Precinct precinctToAdd = getBestPrecinct(objectiveFunction, currNeighbors);
                    //currently random precinct choice approach
                    Collections.shuffle((List<?>) currNeighbors);
                    Iterator<Precinct> iter2 = currNeighbors.iterator();
                    Precinct precinctToAdd = iter2.next();
                    dists.addPrecinct(precinctToAdd);
                }
            }
        }
    }
    
    private Precinct getBestPrecinct(ObjectiveFunction objectiveFunction, Set<Precinct> currentNeighbors) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
