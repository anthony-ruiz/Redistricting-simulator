/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redistricting.domain;

import redistricting.enums.Party;
import redistricting.domain.District;
import redistricting.domain.Precinct;

/**
 *
 * @author Tom Biscardi
 */
public class Representative {
    private String name;
    private Party party;
    private District repDistrict;
    private Precinct homePrecinct;
}
