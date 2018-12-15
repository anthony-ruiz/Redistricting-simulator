package com.example.Gerrymandering.controller;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import redistricting.domain.District;
import redistricting.enums.AlgorithmType;
import redistricting.domain.ObjectiveFunction;
import redistricting.domain.Precinct;
import redistricting.domain.State;
import redistricting.persistence.PersistenceUnit;

public interface Algorithm {

    public void setWeights(String politicalFairness, String compactness, String populationEquality);

    public void setState(String state);

    abstract public void beginAlgorithm();
}
