package com.example.Gerrymandering.domain;

import java.util.HashMap;

public class ObjectiveFunction {
    private Double politicalFairness;
    private Double compactness;
    private Double populationEquality;
    private HashMap<String, Double> objective;

    public ObjectiveFunction(Double politicalFairness, Double compactness, Double populationEquality) {
        this.politicalFairness = politicalFairness;
        this.compactness = compactness;
        this.populationEquality = populationEquality;
        objective = new HashMap<>();
        objective.put("Political Fairness", politicalFairness);
        objective.put("Compactness", compactness);
        objective.put("Population Equality", populationEquality);
    }

    public Double getPoliticalFairness() {
        return politicalFairness;
    }

    public void setPoliticalFairness(Double politicalFairness) {
        this.politicalFairness = politicalFairness;
    }

    public Double getCompactness() {
        return compactness;
    }

    public void setCompactness(Double compactness) {
        this.compactness = compactness;
    }

    public Double getPopulationEquality() {
        return populationEquality;
    }

    public void setPopulationEquality(Double populationEquality) {
        this.populationEquality = populationEquality;
    }

    public HashMap<String, Double> getObjective() {
        return objective;
    }

    public void setObjective(HashMap<String, Double> objective) {
        this.objective = objective;
    }

    public void setObjective(Double politicalFairness, Double compactness, Double populationEquality) {
        objective = new HashMap<>();
        objective.put("Political Fairness", politicalFairness);
        objective.put("Compactness", compactness);
        objective.put("Population Equality", populationEquality);
    }
}
