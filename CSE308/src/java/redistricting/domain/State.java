/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redistricting.domain;

import java.util.Collections;
import redistricting.domain.Precinct;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Tom Biscardi
 */
public class State {
    private String name;
    private Set<District> districts;
    private int districtAmount;
    private Set<Precinct> precincts;
    private Set<Precinct> borderPrecincts;
    private Set<Representative> representatives;
    private Set<Precinct> seedPrecincts;
    
    public State() {
        
    }
    
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

    public int getDistrictAmount() {
        return districtAmount;
    }
    
    
}
