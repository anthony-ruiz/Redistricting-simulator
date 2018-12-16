package com.example.Gerrymandering.domain;


import com.example.Gerrymandering.controller.MovesBuffer;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
public class Precinct implements Serializable, Comparable<Precinct> {
    private District district;
    private String ID;
    private boolean used;
    private Set<Precinct> neighbors;
    private double volume;
    private boolean onDistrictBorder;
    private int population;
    private String coordinates;
    private int distanceToSeed = 1;
    private  int repVotes;
    private int demVotes;




    public Precinct() {
        used = false;
    }

    public void  setDistanceToSeed(int distance){
        this.distanceToSeed = distance;
    }
    public int getDistanceToSeed(){
        return distanceToSeed;
    }
    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
    public void setRepVotes(int repVotes){
        this.repVotes = repVotes;
    }
    public void setDemVotes(int demVotes) {
        this.demVotes = demVotes;
    }
    public int getDemVotes() {
        return demVotes;
    }
    public int getRepVotes() {
        return repVotes;
    }
    public String getCoordinates() {
        return coordinates;
    }
    public District getDistrict() {
        return district;
    }
    public void setPopulation(int population){
        this.population = population;
    }
    public int getPopulation(){
        return population;
    }
    public void setDistrict(District district) {
        used = true;
        this.district = district;
        MovesBuffer movesBuffer = new MovesBuffer();
        movesBuffer.constructJson(ID, district.getId());
        // create json string for update controller
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Set<Precinct> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Set<Precinct> neighbors) {
        this.neighbors = neighbors;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public boolean isOnDistrictBorder() {
        checkBorder();
        return onDistrictBorder;
    }

    private void checkBorder() {
        onDistrictBorder = false;
        for(Precinct neighbor: neighbors) {
            District neighborDist = neighbor.getDistrict();
            if(neighborDist == null || neighborDist != this.district) {
                onDistrictBorder = true;
            }
        }
    }

    public void setOnDistrictBorder(boolean onDistrictBorder) {
        this.onDistrictBorder = onDistrictBorder;
    }

    public Set<Precinct> getEligibleNeighbors() {
        Set<Precinct> eligibleNeighbors = new HashSet<>();
        for(Precinct neighborPrecincts : neighbors) {
            if(!neighborPrecincts.isUsed()) {
                eligibleNeighbors.add(neighborPrecincts);
            }
        }
        return eligibleNeighbors;
    }

    @Override
    public int compareTo(Precinct d) {
        return (this.getID()).compareTo(d.getID());
    }


    public static final Comparator<Precinct> DESCENDING_COMPARATOR = new Comparator<Precinct>() { //greatest population first
        // Overriding the compare method to sort the population
        public int compare(Precinct d, Precinct d1) {
            return d.getPopulation() - d1.getPopulation();
        }
    };

    public static final Comparator<Precinct> ACENDING_COMPARATOR = new Comparator<Precinct>() {
        // Overriding the compare method to sort the population
        public int compare(Precinct d, Precinct d1) {
            return d1.getPopulation() - d.getPopulation();
        }
    };


    public static final Comparator<Precinct> DESCENDING_DISTANCE = new Comparator<Precinct>() { //greatest population first
        // Overriding the compare method to sort the distance
        public int compare(Precinct d, Precinct d1) {
            return d.getDistanceToSeed() - d1.getDistanceToSeed();
        }
    };

    public static final Comparator<Precinct> ACENDING_DISTANCE = new Comparator<Precinct>() {
        // Overriding the compare method to sort the distance
        public int compare(Precinct d, Precinct d1) {
            return d1.getDistanceToSeed() - d.getDistanceToSeed();
        }
    };

}
