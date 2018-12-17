package com.example.Gerrymandering.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
public class District implements Serializable{

    private State state;
    private int Id;
    private int population;
    private int populationT;
    private double volume;
    private Representative representative;
    private Set<Precinct> precincts;
    private Set<Precinct> borders;
    private Set<Precinct> currentNeighbors;
    private int repVotes;
    private int demVotes;
    private int republican;
    private int democratic;
    private boolean finished = false;

    public void addPrecinct(Precinct precinctToAdd) {
        precincts.add(precinctToAdd);
        state.incrementUsedCount();
        this.populationT += precinctToAdd.getPopulation();
        if(precinctToAdd.getDemVotes() > precinctToAdd.getRepVotes()) {
            democratic++;
        } else {
            republican++;
        }
        precinctToAdd.setDistrict(this);
    }

    public String getPoliticalAlignment() {
        if(democratic > republican) {
            return "Democratic";
        } else {
            return "Republican";
        }
    }

    public int getRepublican() {
        return republican;
    }

    public void setRepublican(int republican) {
        this.republican = republican;
    }

    public int getDemocratic() {
        return democratic;
    }

    public void setDemocratic(int democratic) {
        this.democratic = democratic;
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

    public void setDemVotes(int demVotes) {
        this.demVotes = demVotes;
    }

    public int getDemVotes() {
        return demVotes;
    }

    public int getRepVotes() {
        return repVotes;
    }

    public void setRepVotes(int repVotes) {
        this.repVotes = repVotes;
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

    public int getPopulationT(){
        return populationT;
    }

    public Set<Precinct> getPrecincts() {
        return precincts;
    }

    public void setFinished(boolean b) {
        this.finished = b;
    }

    public boolean isFinished() {
        return this.finished;
    }
}
