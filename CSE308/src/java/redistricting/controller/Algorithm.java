/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redistricting.controller;

import java.util.Set;
import redistricting.enums.AlgorithmType;
import redistricting.domain.ObjectiveFunction;
import redistricting.domain.Precinct;
import redistricting.domain.State;

/**
 *
 * @author Tom Biscardi
 */
public class Algorithm {
    private AlgorithmType algorithmType;
    private ObjectiveFunction objectiveFunction;
    private Set<Precinct> currentNeighbors;
    private State currentState;

    public void setWeight(String weight) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setState(String state) {
        currentState = new State(state);
    }

    public void setAlgorithm(String algorithm) {
        algorithmType = algorithmType.stringConvert(algorithm);
    }
    
    
    
    
    
    
    
}
