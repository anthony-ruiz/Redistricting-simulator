package com.example.Gerrymandering.controller;

import java.util.*;

import com.example.Gerrymandering.domain.*;

public class RegionGrowingAlgorithm implements Algorithm {

    private HashMap<String, Double> objectiveValues;
    private State currentState;
    private String stateName;
    private ArrayList<String> moves;
    public MovesBuffer movesBuffer;
    private Set<Precinct> seeds;
    private List<String> tempSeeds;
    private String strategy;

    public RegionGrowingAlgorithm(String state) {
//        currentState = new PersistenceUnit().getRGState(state);
        stateName = state;
        movesBuffer = new MovesBuffer();
        objectiveValues = new HashMap<>();
    }

    @Override
    public void setWeights(String politicalFairness, String compactness, String populationEquality) {
        objectiveValues = new HashMap<>();
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
    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    @Override
    public void setTempSeeds(List<String> seeds) {
        tempSeeds = seeds;
    }

    @Override
    public void beginAlgorithm() {
        MovesBuffer.tempResults.clear();
        if(strategy.equals("SELECT_SEED")) {
            this.seeds = new HashSet<>();
            for(String s : tempSeeds) {
                for(Precinct p : currentState.getPrecincts()) {
                    if(p.getID().equals(s)) {
                        this.seeds.add(p);
                        break;
                    }
                }
            }
        } else {
            seeds = currentState.getSeedPrecincts(strategy);
        }
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
                    Compactness c = new Compactness();
                    Precinct mySeed = new Precinct();
                    for(Precinct p : seeds){
                        if(p.getDistrict().getId() == dists.getId()){
                            mySeed = p;
                            break;
                        }
                    }
                    List<Precinct> mainList = c.compactness(currentState,currNeighbors,dists,mySeed);
//                for(Precinct p : currNeighbors) {
//                    Precinct precinctToAdd = p;
//                    dists.addPrecinct(precinctToAdd);
//                    break;
//                }
//                    Iterator<Precinct> iter2 = mainList.iterator();
//                    Precinct precinctToAdd = iter2.next();
                    MetricsManager metricsManager = new MetricsManager();
                    Precinct precinctToAdd = metricsManager.getBestPrecinct(currentState,currNeighbors,dists,mySeed,objectiveValues);
                    dists.addPrecinct(precinctToAdd);
                } else {
                    dists.setFinished(true);
                }

            }
        }
        MovesBuffer movesBuffer = new MovesBuffer();
        for(District d: currentState.getDistricts()) {
            movesBuffer.addPopulationResult(d.getId(), d.getPopulationT());
        }
        for(District d: currentState.getDistricts()) {
            movesBuffer.addPoliticalResult(d.getId(), d.getPoliticalAlignment());
            System.out.println("District ID: " + d.getId() + " Dem Votes: " + d.getDemVotes() + " Rep  Votes: " + d.getRepVotes());
            System.out.println("District ID: " + d.getId() + " Dem Precs: " + d.getDemocratic() + " Rep  Precs: " + d.getRepublican());
        }
        movesBuffer.combineResults();
        movesBuffer.constructJson("finished", 0);

        System.out.println("Finished!");
    }

}
