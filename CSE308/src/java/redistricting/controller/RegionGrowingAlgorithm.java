package redistricting.controller;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import redistricting.domain.District;
import redistricting.domain.ObjectiveFunction;
import redistricting.domain.Precinct;
import redistricting.domain.State;
import redistricting.persistence.PersistenceUnit;

public class RegionGrowingAlgorithm implements Algorithm {

    private Set<String> objectiveValues;
    private State currentState;

    public RegionGrowingAlgorithm(String state) {
        currentState = new PersistenceUnit().getRGState(state);
    }

    @Override
    public void setWeights(String politicalFairness, String compactness, String populationEquality) {
        objectiveValues.add(politicalFairness);
        objectiveValues.add(compactness);
        objectiveValues.add(populationEquality);
    }
    
    @Override
    public void setState(String state) {
        currentState = new PersistenceUnit().getRGState(state);
    }

    @Override
    public void beginAlgorithm() {
        {
            Set<Precinct> seeds = currentState.getSeedPrecincts();
            Set<District> districts = currentState.getDistricts();
            int districtAmt = currentState.getDistrictAmount();
            for (int i = 0; i < districtAmt; i++) {
                districts.add(new District());
            }
            Iterator<Precinct> iter = seeds.iterator();
            for (District dists : districts) {
                dists.addPrecinct(iter.next());
            }
            while (true) { // change "true" to loop through all districts in round robin fashion until all precincts are assigned to a district
                for (District dists : districts) {
                    Set<Precinct> borders = dists.getBorders();
                    Set<Precinct> currNeighbors = dists.getCurrentNeighbors();
                    currNeighbors.clear();
                    for (Precinct borderPrecinct : borders) {
                        currNeighbors.addAll(borderPrecinct.getEligibleNeighbors());
                    }
                    //Precinct precinctToAdd = getBestPrecinct(objectiveFunction, currNeighbors);
                    //currently random precinct choice approach
                    Collections.shuffle((List<?>) currNeighbors);
                    Iterator<Precinct> iter2 = currNeighbors.iterator();
                    Precinct precinctToAdd = iter2.next();
                    dists.addPrecinct(precinctToAdd);
                }
            }
        }
    }
    
    private Precinct getBestPrecinct(ObjectiveFunction objectiveFunction, Set<Precinct> currentNeighbors) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
