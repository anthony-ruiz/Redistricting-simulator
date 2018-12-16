package com.example.Gerrymandering.controller;

import java.util.*;

import com.example.Gerrymandering.domain.*;

public class RegionGrowingAlgorithm implements Algorithm {

    private HashMap<String, Double> objectiveValues;
    private State currentState;
    private String stateName;
    private ArrayList<String> moves;
    public MovesBuffer movesBuffer;

    public RegionGrowingAlgorithm(String state) {
//        currentState = new PersistenceUnit().getRGState(state);
        stateName = state;
        movesBuffer = new MovesBuffer();
        objectiveValues = new HashMap<>();
        objectiveValues.put("politicalFairness", 0.0);
        objectiveValues.put("compactness", 0.0);
        objectiveValues.put("populationEquality", 0.0);
    }

    @Override
    public void setWeights(String politicalFairness, String compactness, String populationEquality) {
        objectiveValues.put("politicalFairness", Double.parseDouble(politicalFairness));
        objectiveValues.put("compactness", Double.parseDouble(compactness));
        objectiveValues.put("populationEquality", Double.parseDouble(populationEquality));
    }

    @Override
    public void setState(State state) {
        currentState = state;
    }

    @Override
    public String getStateName() {
        return this.stateName;
    }

    @Override
    public State getState() {
        return this.currentState;
    }

    @Override
    public void beginAlgorithm() {
        Set<Precinct> seeds = currentState.getSeedPrecincts();
        Set<District> districts = currentState.getDistricts();
//        int districtAmt = currentState.getDistrictAmount();
//        for (int i = 0; i < districtAmt; i++) {
//            districts.add(new District());
//        }
        Iterator<Precinct> iter = seeds.iterator();
        for (District dists : districts) {
            dists.addPrecinct(iter.next());
        }
        while(currentState.getUsedCount() < currentState.getPrecincts().size()) {
            for (District dists : districts) {
                if(dists.isFinished()) {
                    continue;
                }
                Set<Precinct> borders = dists.getBorders();
                Set<Precinct> currNeighbors = dists.getCurrentNeighbors();
                currNeighbors.clear();
                for (Precinct borderPrecinct : borders) {
                    currNeighbors.addAll(borderPrecinct.getEligibleNeighbors());
                }
                //Precinct precinctToAdd = getBestPrecinct(objectiveFunction, currNeighbors);
                //currently random precinct choice approach
                if(currNeighbors.size() != 0) {
                    PopulationEquality c = new PopulationEquality();
                    Precinct mySeed = new Precinct();
                    for(Precinct p : seeds){
                        if(p.getDistrict().getId() == dists.getId()){
                            mySeed = p;
                            break;
                        }
                    }

                    List<Precinct> mainList = c.populationEquality(currentState,currNeighbors,dists,mySeed);

//                for(Precinct p : currNeighbors) {
//                    Precinct precinctToAdd = p;
//                    dists.addPrecinct(precinctToAdd);
//                    break;
//                }
                    Iterator<Precinct> iter2 = mainList.iterator();
                    Precinct precinctToAdd = iter2.next();
                    dists.addPrecinct(precinctToAdd);
                } else {
                    dists.setFinished(true);
                }

            }
        }
        MovesBuffer movesBuffer = new MovesBuffer();
        movesBuffer.constructJson("finished", 0);

        for(District d : currentState.getDistricts()){
            int dvotes = 0;
            int rvotes = 0;
            System.out.print("Distrct: "+ d.getId());
            for(Precinct p : d.getPrecincts()){
                dvotes = dvotes +p.getDemVotes();
                rvotes = rvotes + p.getRepVotes();

            }
            if(dvotes > rvotes){
                System.out.println(" is a Democrat!");
            }
            else{
                System.out.println(" is a Republican!");
            }
        }

        System.out.println("Finished!");
    }

    private Precinct getBestPrecinct(ObjectiveFunction objectiveFunction, Set<Precinct> currentNeighbors) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
