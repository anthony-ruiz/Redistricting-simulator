package com.example.Gerrymandering.initializers;

import com.example.Gerrymandering.domain.District;
import com.example.Gerrymandering.domain.State;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PopulateDistricts {
    public List populate(List states) throws IOException {
        String csvFileToRead = "Districts.csv";
        BufferedReader br = null;
        String line = "";
        String splitBy = ",";
        List districtList = new ArrayList();

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

    public void printList(List<District> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print("State name: " + list.get(i).getState().getName() + " |");
            System.out.print("State id: " + list.get(i).getState().getId() + " |");
            System.out.println("DistrictId: " + list.get(i).getId() + " |");
//            System.out.println("District population : " + list.get(i).getPopulation() + " |");

        }
    }
}
