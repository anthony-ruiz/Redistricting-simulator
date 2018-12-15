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
    
    public Set<Precinct> getSeedPrecincts() {
//        int districtAmount = districts.size();
//        Collections.shuffle((List<?>) precincts);
        seedPrecincts = new HashSet<>();
        int count = 0;
        for(Precinct p : precincts) {
            seedPrecincts.add(p);
            count++;
            if(count == districtAmount) {
                break;
            }
        }
//        Iterator<Precinct> iter = precincts.iterator();
//        for(int i = 0; i < districtAmount; i ++) {
//            seedPrecincts.add(iter.next());
//        }
        return seedPrecincts;
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
