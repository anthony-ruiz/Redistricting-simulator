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
    
    public Set<Precinct> getSeedPrecincts(String strategy) {
        seedPrecincts = new HashSet<>();
        if(strategy.equals("REP_SEED")) {
            for(Precinct p : precincts) {
                if(p.getID().equals("0401711") || p.getID().equals("04025014") || p.getID().equals("04019136") ||
                        p.getID().equals("040135") || p.getID().equals("0401398") || p.getID().equals("04013876") ||
                        p.getID().equals("04013366") || p.getID().equals("04013377") || p.getID().equals("0400327")) {
                    seedPrecincts.add(p);
                }
            }
        } else {
            List<Precinct> mainList = new ArrayList<>();
            mainList.addAll(precincts);
            Collections.shuffle(mainList);
            Iterator<Precinct> iter = mainList.iterator();
            for(int i = 0; i < districtAmount; i ++) {
                seedPrecincts.add(iter.next());
                Collections.shuffle(mainList);
            }
        }
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
