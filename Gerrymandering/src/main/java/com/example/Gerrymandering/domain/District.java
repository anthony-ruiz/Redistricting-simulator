package com.example.Gerrymandering.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
public class District implements Serializable{

    private State state;
    private int Id;
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
        borders = new HashSet<>();
        for (Precinct precinct : precincts) {
            if (precinct.isOnDistrictBorder()) {
                borders.add(precinct);
            }
        }
        return borders;
    }

    public Set<Precinct> getCurrentNeighbors() {
        currentNeighbors = new HashSet<>();
        return currentNeighbors;
    }

    public int getId() {
        return Id;
    }

    public void setPrecincts(Set<Precinct> precincts) {
        this.precincts = precincts;
    }

    public void setID(int id ){
        this.Id = id;
    }

    public void setState(State state){
        this.state = state;
    }

    public void setPopulation(int population){
        this.population = population;
    }

    public State getState(){
        return state;
    }

    public int getPopulation(){
        return population;
    }

    public Set<Precinct> getPrecincts() {
        return precincts;
    }
}
