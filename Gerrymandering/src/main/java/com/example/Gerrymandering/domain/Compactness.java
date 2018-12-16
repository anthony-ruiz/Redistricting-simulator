package com.example.Gerrymandering.domain;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Compactness {

    public List<Precinct> compactness(State state, Set<Precinct> possibleNeighnors, District currentDistrict, Precinct seedPrecinct){
        List<Precinct> possibleNeighborsList = new ArrayList<>(possibleNeighnors);                 //list to be returned
        List<District> allDistrictList = new ArrayList<>(state.getDistricts());
        List<Integer> distanceToPrecincts = new ArrayList<>();     //contains the distance from the seed precint to all the neighbors

        String[] cordiantes = seedPrecinct.getCoordinates().split(" ");
        Double seedLatitude = Double.valueOf(cordiantes[0]);
        Double seedLongitude = Double.parseDouble(cordiantes[1]);

        for(Precinct p: possibleNeighborsList){
            String[] Cordiantes = p.getCoordinates().split(" ");
            Double latitude = Double.parseDouble(Cordiantes[0]);
            Double longitude = Double.parseDouble(Cordiantes[1]);
            p.setDistanceToSeed(computeDistance(seedLatitude,seedLongitude,latitude,longitude));
        }
        Collections.sort(possibleNeighborsList, Precinct.DESCENDING_DISTANCE);
        for(Precinct p : possibleNeighborsList){

        }
        return possibleNeighborsList;
    }






    private int computeDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the earth
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters
        return (int) distance;
    }
}
