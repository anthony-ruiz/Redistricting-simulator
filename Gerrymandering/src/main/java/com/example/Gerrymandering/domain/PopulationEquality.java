package com.example.Gerrymandering.domain;

import java.util.*;

/*the purpose of this class is to get get a list of
ranked precincts which adding them will make it more
close to the median*/

public class PopulationEquality {

    public List<Precinct> populationEquality(State state, Set<Precinct> possibleNeighnors, District currentDistrict, Precinct seedOfDistrict){
        List<Precinct> possibleNeighborsList = new ArrayList<>(possibleNeighnors);                 //list to be returned
        List<District> allDistrictList = new ArrayList<>(state.getDistricts());
        List<Integer> districtPopulation = new ArrayList<>();     //contains the population of all the districts

        for(District district : allDistrictList){                   //gets the population of all districts
            int population = 0;
            for(Precinct p : district.getPrecincts()){
                population = population + p.getPopulation();
            }
            districtPopulation.add(population);
        }

        double average = calculateAverage(districtPopulation);

        if(currentDistrict.getPopulation() < average){
            Collections.sort(possibleNeighborsList, Precinct.DESCENDING_COMPARATOR);
        }
        else{
            Collections.sort(possibleNeighborsList, Precinct.ACENDING_COMPARATOR);
        }
        return  possibleNeighborsList;
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
}

