package com.example.Gerrymandering.initializers;

import com.example.Gerrymandering.domain.District;
import com.example.Gerrymandering.domain.Precinct;
import com.example.Gerrymandering.domain.State;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PopulateDistricts {

    List<District> districtList = new ArrayList<>();

    public List populate(List states) throws IOException {
        String csvFileToRead = "Districts.csv";
        BufferedReader br = null;
        String line = "";
        String splitBy = ",";


        br = new BufferedReader(new FileReader(csvFileToRead));
        while ((line = br.readLine()) != null) {
            String[] districtLine = line.split(splitBy);
            District district = new District();
            State currentState = (State) states.get(Integer.parseInt(districtLine[0])-1); //gets the state object
            district.setState(currentState);
            district.setID(Integer.parseInt(districtLine[1]));
//            district.setPopulation(Integer.parseInt(districtLine[2]));
            districtList.add(district);

        }
        return districtList;
    }

    public void addPrecinctSet(Set<Precinct> precinctSet){
        for(District d : districtList){
            Set<Precinct> curentset = new HashSet<>();
            int districtID = d.getId();
            for(Precinct p : precinctSet){
                if(p.getDistrict().getId() == districtID){
                    curentset.add(p);
                }
            }
            d.setPrecincts(curentset);
        }

    }


    public void printList(List<District> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print("State name: " + list.get(i).getState().getName() + " |");
            System.out.print("State id: " + list.get(i).getState().getId() + " |");
            System.out.println("DistrictId: " + list.get(i).getId() + " |");
//            System.out.println("District population : " + list.get(i).getPopulation() + " |");

        }
    }
}
