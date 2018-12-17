package com.example.Gerrymandering.domain;

import com.example.Gerrymandering.controller.Algorithm;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.Gerrymandering.controller.RegionGrowingAlgorithm;
import com.example.Gerrymandering.controller.SimulatedAnnealingAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmJSON {
    @JsonProperty("state")
    private String state;
    @JsonProperty("politicalFairness")
    private String politicalFairness;
    @JsonProperty("compactness")
    private String compactness;
    @JsonProperty("populationEquality")
    private String populationEquality;
    @JsonProperty("algorithm")
    private String algorithm;
    @JsonProperty("strategy")
    private String strategy;
    @JsonProperty("seeds")
    private List<String> seeds;

    
    public Algorithm create() {
        Algorithm newAlgorithm;
        if(algorithm.equals("REGION_GROWING")) {
            newAlgorithm = new RegionGrowingAlgorithm(state);
            switch (strategy) {
                case "RANDOM_SEED":
                    newAlgorithm.setStrategy(strategy);
                    break;
                case "REP_SEED":
                    newAlgorithm.setStrategy(strategy);
                    break;
                case "SELECT_SEED":
                    newAlgorithm.setStrategy(strategy);
//                    ArrayList<String> listdata = new ArrayList<>();
//                    if (seeds != null) {
//                        for (int i = 0;i < seeds.length(); i ++){
//                            listdata.add(seeds.getString(i));
//                        }
//                    }
                    newAlgorithm.setTempSeeds((ArrayList<String>) seeds);
                    break;
                default:
                    newAlgorithm.setStrategy("RANDOM_SEED");
                    break;
            }
        } else {
            newAlgorithm = new SimulatedAnnealingAlgorithm(state);
        }
        newAlgorithm.setWeights(politicalFairness, compactness, populationEquality);
        return newAlgorithm;                
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPoliticalFairness() {
        return politicalFairness;
    }

    public void setPoliticalFairness(String politicalFairness) {
        this.politicalFairness = politicalFairness;
    }

    public String getCompactness() {
        return compactness;
    }

    public void setCompactness(String compactness) {
        this.compactness = compactness;
    }

    public String getPopulationEquality() {
        return populationEquality;
    }

    public void setPopulationEquality(String populationEquality) {
        this.populationEquality = populationEquality;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}
