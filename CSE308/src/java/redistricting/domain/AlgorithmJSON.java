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
    @JsonProperty("weight")
    public String weight;
    @JsonProperty("algorithm")
    public String algorithm;
    
    public Algorithm create() {
        Algorithm newAlgorithm = new Algorithm();
        newAlgorithm.setState(state);
        newAlgorithm.setWeight(weight);
        newAlgorithm.setAlgorithm(algorithm);
        return newAlgorithm;                
    }
}
