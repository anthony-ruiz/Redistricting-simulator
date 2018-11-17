/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redistricting.domain;

import redistricting.controller.Algorithm;
import com.fasterxml.jackson.annotation.JsonProperty;
import redistricting.enums.AlgorithmType;

/**
 *
 * @author Tom Biscardi
 */
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
        Algorithm newAlgorithm = new Algorithm();
        newAlgorithm.setState(state);
        newAlgorithm.setWeights(politicalFairness, compactness, populationEquality);
        newAlgorithm.setAlgorithm(algorithm);
        return newAlgorithm;                
    }
}
