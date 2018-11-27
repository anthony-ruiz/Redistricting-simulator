package redistricting.persistence;

import gerrymandering.HibernateManager;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import redistricting.domain.District;
import redistricting.domain.Precinct;
import redistricting.domain.State;

public class PersistenceUnit {

    public State getRGState(String stateName) {
        List<Object> stateList;
        List<Object> districtList;
        List<Object> precinctList;
        try {
            HibernateManager hb = HibernateManager.getInstance();
            stateList = hb.getAllRecords(State.class);
            Iterator<Object> itr = stateList.iterator();
            while (itr.hasNext()) {
                State state = (State) itr.next();
                if (state.getName().equals(stateName)) {
                    Map<String,Object> districtCriteria = new HashMap<>();
                    districtCriteria.put("state_Id", state.getId());
                    districtList = hb.getRecordsBasedOnCriteria(District.class, districtCriteria);
                    Iterator<Object> itr2 = districtList.iterator();
                    Set<District> districts = new HashSet<>();
                    Set<Precinct> precincts = new HashSet<>();
                    while (itr2.hasNext()) {
                        District district = (District) itr2.next();
                        districts.add(district);
                        Map<String,Object> precinctCriteria = new HashMap<>();
                        precinctCriteria.put("district_Id", district.getId());
                        precinctList = (hb.getRecordsBasedOnCriteria(Precinct.class, precinctCriteria));
                        Iterator<Object> itr3 = precinctList.iterator();
                        while(itr3.hasNext()) {
                            Precinct precinct = (Precinct) itr3.next();
                            precinct.setDistrict(null);
                            precincts.add(precinct);
                        }
                    }
                    state.setDistricts(districts);
                    state.setPrecincts(precincts);
                    return state;
                }
            }
        } catch (Throwable e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    public State getSAState(String stateName) {
        List<Object> stateList;
        List<Object> districtList;
        List<Object> precinctList;
        try {
            HibernateManager hb = HibernateManager.getInstance();
            stateList = hb.getAllRecords(State.class);
            Iterator<Object> itr = stateList.iterator();
            while (itr.hasNext()) {
                State state = (State) itr.next();
                if (state.getName().equals(stateName)) {
                    Map<String,Object> districtCriteria = new HashMap<>();
                    districtCriteria.put("state_Id", state.getId());
                    districtList = hb.getRecordsBasedOnCriteria(District.class, districtCriteria);
                    Iterator<Object> itr2 = districtList.iterator();
                    Set<District> districts = new HashSet<>();
                    Set<Precinct> precincts = new HashSet<>();
                    while (itr2.hasNext()) {
                        District district = (District) itr2.next();
                        districts.add(district);
                        Map<String,Object> precinctCriteria = new HashMap<>();
                        precinctCriteria.put("district_Id", district.getId());
                        precinctList = (hb.getRecordsBasedOnCriteria(Precinct.class, precinctCriteria));
                        Iterator<Object> itr3 = precinctList.iterator();
                        while(itr3.hasNext()) {
                            Precinct precinct = (Precinct) itr3.next();
                            precincts.add(precinct);
                        }
                        district.setPrecincts(precincts);
                    }
                    state.setDistricts(districts);
                    state.setPrecincts(precincts);
                    return state;
                }
            }
        } catch (Throwable e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}
