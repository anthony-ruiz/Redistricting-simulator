package com.example.Gerrymandering.domain;

import java.io.Serializable;
import java.util.Set;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;

//@Entity
//@Table(name="districts")
public class District implements Serializable{

    private State state;
//    @Id
    private String Id;
    private int population;
    private double volume;
    private Representative representative;
    private Set<Precinct> precincts;
    private Set<Precinct> borders;
    private Set<Precinct> currentNeighbors;

    public void addPrecinct(Precinct precinctToAdd) {
        precincts.add(precinctToAdd);
        precinctToAdd.setDistrict(this);
    }

    public Set<Precinct> getBorders() {
        for (Precinct precinct : precincts) {
            if (precinct.isOnDistrictBorder()) {
                borders.add(precinct);
            }
        }
        return borders;
    }

    public Set<Precinct> getCurrentNeighbors() {
        return currentNeighbors;
    }

    public String getId() {
        return Id;
    }

    public void setPrecincts(Set<Precinct> precincts) {
        this.precincts = precincts;
    }
}
