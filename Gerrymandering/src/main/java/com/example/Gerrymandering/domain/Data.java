package com.example.Gerrymandering.domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Data {

    private List <State>stateList = new ArrayList<>();
    private List<District> districtList = new ArrayList<>();
    private List<Precinct> precinctList = new ArrayList<>();
    private Set<State> states = new HashSet<State>();
    private Set<District> districts = new HashSet<District>();
    private Set<Precinct> precincts = new HashSet<Precinct>();
    private String algorithmType = null;


    public void populateAll(String algorithmType){
        this.algorithmType = algorithmType;
        try {
            initializeState();         //initializes the basic values of State,Distric,Precinct
            initializeDistrict();
            initializePrecincts();
            states = new HashSet<State>(stateList);
            districts = new HashSet<District>(districtList);
            precincts = new HashSet<Precinct>(precinctList);
            finishInitializing();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeState() throws IOException {
        String csvFileToRead = "States.csv";
        BufferedReader br = null;
        String line = "";
        String splitBy = ",";
        br = new BufferedReader(new FileReader(csvFileToRead));
        while ((line = br.readLine()) != null) {
            String[] stateLine = line.split(splitBy);
            State state = new State();
            state.setId(Integer.parseInt(stateLine[0]));                                                    //sets stateID
            state.setName(stateLine[1]);                                                                    //sets state name
            state.setDistricAmount(Integer.parseInt(stateLine[2]));                                         // sets amount of districts that a state has
            stateList.add(state);
        }
    }

    private void initializeDistrict() throws IOException {
        String csvFileToRead = "Districts.csv";
        BufferedReader br = null;
        String line = "";
        String splitBy = ",";
        br = new BufferedReader(new FileReader(csvFileToRead));
        while ((line = br.readLine()) != null) {
            String[] districtLine = line.split(splitBy);
            District district = new District();
            State currentState = (State) stateList.get(Integer.parseInt(districtLine[0])-1);                //sets the state that the distric belongs to
            district.setState(currentState);
            district.setID(Integer.parseInt(districtLine[1]));                                              //sets District ID
            if(algorithmType.equals("SIMULATED_ANNEALING")){
                district.setPopulation(Integer.parseInt(districtLine[2]));                              // if its simulated annealing sets district population
            }
            districtList.add(district);
        }
    }

    private void initializePrecincts() throws IOException {
        String csvFileToRead = "Precinct.csv";
        BufferedReader br = null;
        String line = "";
        String splitBy = ",";
        br = new BufferedReader(new FileReader(csvFileToRead));
        while ((line = br.readLine()) != null) {
            String[] precinctLine = line.split(splitBy);
            Precinct precinct = new Precinct();
            if(algorithmType.equals("SIMULATED_ANNEALING")){
                District currentDistrict = (District) districtList.get(Integer.parseInt(precinctLine[0])-1);
                precinct.setDistrict(currentDistrict);                                       //sets the district that precinct belong to if Simulated Annealing
            }
            precinct.setID(precinctLine[1]);                                                                    //sets precinct ID
            precinct.setVolume(Double.parseDouble(precinctLine[2]));                                            //sets volume of precinct
            precinct.setPopulation(Integer.parseInt(precinctLine[3]));
            precinct.setCoordinates(precinctLine[4]);
            precinct.setRepVotes(Integer.parseInt(precinctLine[5]));
            precinct.setDemVotes(Integer.parseInt(precinctLine[6]));
            precinctList.add(precinct);
        }
        setNeighbors();
    }

    private void setNeighbors(){                                                                                //sets the neighbors of all of the precincts
        Neighbors neighbors = new Neighbors();
        neighbors.populateNeigbors();
        for (Precinct aPrecinctList : precinctList) {
            Set<Precinct> setForCurrent = new HashSet<>();
            List<String> neighborsOfCurrentList = neighbors.getNeighbors(aPrecinctList.getID());
            for(String s : neighborsOfCurrentList) {
                for(Precinct p : precinctList) {
                    if(p.getID().equals(s)) {
                        setForCurrent.add(p);
                    }
                }
            }
            aPrecinctList.setNeighbors(setForCurrent);
        }
    }

    private void finishInitializing(){
        State myState = (State) stateList.get(0);//get az since its the only state
        myState.setDistricts(districts);                                                                        //gives the state the set of district
        myState.setPrecincts(precincts);                                                                        //gives the state its set of precincts
        if(algorithmType.equals("SIMULATED_ANNEALING")){
            for(District d : districtList){                                                  //gives the districts its set of precincts if simulated annealing
                Set<Precinct> currentSet = new HashSet<>();
                int districtID = d.getId();
                for(Precinct p : precincts){
                    if(p.getDistrict().getId() == districtID){
                        currentSet.add(p);
                    }
                }
                d.setPrecincts(currentSet);
            }
        } else {
            for(District d : districtList){
                Set<Precinct> currentSet = new HashSet<>();
                d.setPrecincts(currentSet);
            }
        }
    }

    public Set<State> getStateSet() {
        return states;
    }

    public Set<District> getDistrictSet() {
        return districts;
    }

    public Set<Precinct> getPrecinctSet() {
        return precincts;
    }

    public List<State> getStateList() {
        return stateList;
    }

    public List<District> getDistrictList() {
        return districtList;
    }

    public List<Precinct> getPrecinctList() {
        return precinctList;
    }
}
