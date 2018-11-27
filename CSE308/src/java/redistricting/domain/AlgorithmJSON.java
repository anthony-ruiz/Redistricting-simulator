package redistricting.domain;

import redistricting.controller.Algorithm;
import com.fasterxml.jackson.annotation.JsonProperty;
import redistricting.controller.RegionGrowingAlgorithm;
import redistricting.controller.SimulatedAnnealingAlgorithm;

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
