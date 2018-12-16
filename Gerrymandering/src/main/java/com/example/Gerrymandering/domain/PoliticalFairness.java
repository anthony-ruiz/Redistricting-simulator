package com.example.Gerrymandering.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class PoliticalFairness {

    public List<Precinct> fairness(State state, Set<Precinct> possibleNeighnors, District currentDistrict, Precinct seedPrecinct){

        double  totalVotesInState = 0;
        double totalDemocratVotes = 0;
        double totalRepublicanVotes =0;
        double democratRatio = 0;
        int demDistrictGoal;
        int repDistrictGoal;

        Set<Precinct> allPrecincts = state.getPrecincts();
        for(Precinct p : allPrecincts){
            totalVotesInState = totalVotesInState + p.getRepVotes() +p.getDemVotes();
            totalDemocratVotes += p.getDemVotes();
            totalRepublicanVotes += p.getRepVotes();
        }

        democratRatio = totalDemocratVotes / totalVotesInState;
        democratRatio = round(democratRatio, 2 );


        demDistrictGoal = (int) democratRatio * state.getDistrictAmount();
        repDistrictGoal = state.getDistrictAmount() - demDistrictGoal;


        List<Precinct> possibleNeighborsList = new ArrayList<>(possibleNeighnors);                 //list to be returned
        List<District> allDistrictList = new ArrayList<>(state.getDistricts());
        List<Integer> districtRepublicanVote  = new ArrayList<>();     //contains the population of all the districts
        List<Integer> districtDemocratVote  = new ArrayList<>();     //contains the population of all the districts

        for(District district : allDistrictList){
            int repVotes = 0;
            for(Precinct precinct : district.getPrecincts()){
                repVotes = repVotes + precinct.getRepVotes();
            }
            district.setRepVotes(repVotes);
            districtRepublicanVote.add(repVotes);
        }

        for(District district : allDistrictList){
            int demVotes = 0;
            for(Precinct precinct : district.getPrecincts()){
                demVotes = demVotes + precinct.getRepVotes();
            }
            district.setDemVotes(demVotes);
            districtDemocratVote.add(demVotes);
        }
        if(currentDistrict.getId() <= democratRatio){
            Collections.sort(possibleNeighborsList, Precinct.DEMOCRAT_VOTES);
        }
        else{
            Collections.sort(possibleNeighborsList, Precinct.REPUBLICAN_VOTES);
        }
        return possibleNeighborsList;

    }



    private double calculateAverage(List <Integer> marks) {
        Integer sum = 0;
        if(!marks.isEmpty()) {
            for (Integer mark : marks) {
                sum += mark;
            }
            return sum.doubleValue() / marks.size();
        }
        return sum;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
