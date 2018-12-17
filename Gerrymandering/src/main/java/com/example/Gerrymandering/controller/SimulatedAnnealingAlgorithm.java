package com.example.Gerrymandering.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.example.Gerrymandering.domain.State;
//import com.example.Gerrymandering.persistence.PersistenceUnit;

public class SimulatedAnnealingAlgorithm implements Algorithm{
    private Set<String> objectiveValues;
    private State currentState;

    public SimulatedAnnealingAlgorithm(String state) {
//        currentState = new PersistenceUnit().getSAState(state);
    }

    @Override
    public void setWeights(String politicalFairness, String compactness, String populationEquality) {
        objectiveValues.add(politicalFairness);
        objectiveValues.add(compactness);
        objectiveValues.add(populationEquality);
    }

    @Override
    public void setState(State state) {
//        currentState = new PersistenceUnit().getSAState(state);
    }

    @Override
    public void beginAlgorithm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getStateName() {
        return null;
    }

    @Override
    public State getState() {
        return null;
    }

    @Override
    public void setStrategy(String strategy) {

    }

    @Override
    public void setTempSeeds(List<String> seeds) {

    }

}
