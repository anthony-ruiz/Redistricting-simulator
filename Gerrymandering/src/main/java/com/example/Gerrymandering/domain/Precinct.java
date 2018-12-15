package com.example.Gerrymandering.domain;


import com.example.Gerrymandering.controller.MovesBuffer;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
public class Precinct implements Serializable {
    private District district;
    private String ID;
    private boolean used;
    private Set<Precinct> neighbors;
    private double volume;
    private boolean onDistrictBorder;
    
    public Precinct() {
        used = false;
    }
    
    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        used = true;
        this.district = district;
//        System.out.println("Added Precinct " + this.ID + " to District " + district.getId());
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
}
