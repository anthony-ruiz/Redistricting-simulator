package com.example.Gerrymandering.controller;

import java.util.Set;
import redistricting.domain.State;
import redistricting.persistence.PersistenceUnit;

public class SimulatedAnnealingAlgorithm implements Algorithm{
    private Set<String> objectiveValues;
    private State currentState;

    public SimulatedAnnealingAlgorithm(String state) {
        currentState = new PersistenceUnit().getSAState(state);
    }

    @Override
    public void setWeights(String politicalFairness, String compactness, String populationEquality) {
        objectiveValues.add(politicalFairness);
        objectiveValues.add(compactness);
        objectiveValues.add(populationEquality);
    }

    @Override
    public void setState(String state) {
        currentState = new PersistenceUnit().getSAState(state);
    }

    @Override
    public void beginAlgorithm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
