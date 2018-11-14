/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redistricting.domain;

import redistricting.domain.Precinct;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Tom Biscardi
 */
public class State {
    private String name;
    private HashMap<String, District> districts;
    private int districtAmount;
    private HashMap<String, Precinct> precincts;
    private HashMap<String, Precinct> borderPrecincts;
    private Set<Representative> representatives;
    private Set<Precinct> seedPrecincts;
}
