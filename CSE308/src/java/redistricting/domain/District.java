/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redistricting.domain;

import redistricting.domain.Precinct;
import java.util.Set;

/**
 *
 * @author Tom Biscardi
 */
public class District{
    private State state;
    private String ID;
    private int population;
    private double volume;
    private Representative representative;
    private Set<Precinct> precincts;
}
