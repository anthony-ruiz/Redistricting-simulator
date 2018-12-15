package com.example.Gerrymandering.domain;

import java.io.Serializable;
import java.util.*;

public class State implements Serializable{
    private int id;
    private String name;
    private Set<District> districts;
    private int districtAmount;
    private Set<Precinct> precincts;
    private Set<Precinct> borderPrecincts;
    private Set<Representative> representatives;
    private Set<Precinct> seedPrecincts;
    private int usedCount;
    
    public Set<Precinct> getSeedPrecincts() {
        seedPrecincts = new HashSet<>();
        for(Precinct p : precincts) {
            if(p.getID().equals("0402719") || p.getID().equals("040193") || p.getID().equals("0400901") || p.getID().equals("0400149") || p.getID().equals("0400156") || p.getID().equals("0400547") || p.getID().equals("0401562") || p.getID().equals("0401254") || p.getID().equals("04013824")) {
                seedPrecincts.add(p);
            }
        }

//        List<Precinct> mainList = new ArrayList<>();
//        mainList.addAll(precincts);
//        Collections.shuffle(mainList);
//        seedPrecincts = new HashSet<>();
//
//        Iterator<Precinct> iter = mainList.iterator();
//        for(int i = 0; i < districtAmount; i ++) {
//            seedPrecincts.add(iter.next());
//            Collections.shuffle(mainList);
//        }
        return seedPrecincts;
    }

    public void incrementUsedCount() {
        usedCount++;
    }

    public int getUsedCount() {
        return this.usedCount;
    }

    public Set<District> getDistricts() {
        return districts;
    }

    public Set<Precinct> getPrecincts() {
        return precincts;
    }

    public void setDistricts(Set<District> districts) {
        this.districts = districts;
    }

    public void setDistricAmount(int districAmount){
        this.districtAmount = districAmount;
    }

    public void setId(int id ){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getDistrictAmount() {
        return districtAmount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setPrecincts(Set<Precinct> precincts) {
        this.precincts = precincts;
    }
    
}
