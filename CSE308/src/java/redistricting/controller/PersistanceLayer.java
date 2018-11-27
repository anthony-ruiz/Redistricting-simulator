/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redistricting.controller;

import redistricting.domain.State;
import redistricting.domain.District;
import redistricting.domain.Precinct;
import java.util.Set;

/**
 *
 * @author Tom Biscardi
 */
public class PersistanceLayer {
    private Set<State> states;
    private Set<District> districts;
    private Set<Precinct> precincts;
}
