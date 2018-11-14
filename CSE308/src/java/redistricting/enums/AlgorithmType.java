/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redistricting.enums;

/**
 *
 * @author Tom Biscardi
 */
public enum AlgorithmType {
    REGION_GROWING,
    SIMULATED_ANNEALING;

    public AlgorithmType stringConvert(String algorithm) {
        switch(algorithm) {
            case "REGION_GROWING":
                return REGION_GROWING;
            case "SIMULATED_ANNEALING":
                return SIMULATED_ANNEALING;
            default:
                return REGION_GROWING;
        }
    }

}
