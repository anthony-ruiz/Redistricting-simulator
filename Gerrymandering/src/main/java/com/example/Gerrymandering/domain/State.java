package com.example.Gerrymandering.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import redistricting.persistence.PersistenceUnit;

//@Entity
//@Table(name="states")
public class State implements Serializable{
//    @Id
//    @Column(name = "state_id")
    private int id;
//    @Column(name = "state_name")
    private String name;
    private Set<District> districts;
    private int districtAmount;
    private Set<Precinct> precincts;
    private Set<Precinct> borderPrecincts;
    private Set<Representative> representatives;
    private Set<Precinct> seedPrecincts;
    
    public Set<Precinct> getSeedPrecincts() {
        int districtAmount = districts.size();
        Collections.shuffle((List<?>) precincts);
        Iterator<Precinct> iter = precincts.iterator();
        for(int i = 0; i < districtAmount; i ++) {
            seedPrecincts.add(iter.next());
        }
        return seedPrecincts;
    }

    public Set<District> getDistricts() {
        return districts;
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
