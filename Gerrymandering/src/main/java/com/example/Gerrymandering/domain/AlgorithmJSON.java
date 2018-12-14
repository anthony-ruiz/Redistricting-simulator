package com.example.Gerrymandering.domain;

import com.example.Gerrymandering.controller.Algorithm;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.Gerrymandering.controller.RegionGrowingAlgorithm;
import com.example.Gerrymandering.controller.SimulatedAnnealingAlgorithm;

public class AlgorithmJSON {
    @JsonProperty("state")
    public String state;
    @JsonProperty("politicalFairness")
    public String politicalFairness;
    @JsonProperty("compactness")
    public String compactness;
    @JsonProperty("populationEquality")
    public String populationEquality;
    @JsonProperty("algorithm")
    public String algorithm;
    
    public Algorithm create() {
        Algorithm newAlgorithm;
        if(algorithm.equals("REGION_GROWING")) {
            newAlgorithm = new RegionGrowingAlgorithm(state);
        } else {
            newAlgorithm = new SimulatedAnnealingAlgorithm(state);
        }
        newAlgorithm.setWeights(politicalFairness, compactness, populationEquality);
        return newAlgorithm;                
    }
}
