/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redistricting.domain;

//import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Tom Biscardi
 */
public class Precinct {
    private District district;
    private String ID;
    private boolean used;
    private Set<Precinct> neighbors;
    private double volume;
    private boolean onDistrictBorder;
//    private HashMap<Line, Precinct> borders;
}
