package com.example.Gerrymandering.controller;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import com.example.Gerrymandering.domain.District;
//import com.example.Gerrymandering.enums.AlgorithmType;
import com.example.Gerrymandering.domain.Precinct;
import com.example.Gerrymandering.domain.State;
//import com.example.Gerrymandering.persistence.PersistenceUnit;

public interface Algorithm {

    public void setWeights(String politicalFairness, String compactness, String populationEquality);

    public void setState(State state);

    public void beginAlgorithm();

    public String getStateName();

    public State getState();

}
