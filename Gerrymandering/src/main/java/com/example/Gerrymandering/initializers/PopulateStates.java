package com.example.Gerrymandering.initializers;

import com.example.Gerrymandering.domain.District;
import com.example.Gerrymandering.domain.Precinct;
import com.example.Gerrymandering.domain.State;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author antho
 */
public class PopulateStates {

    List stateList = new ArrayList();

    public List  populate() throws FileNotFoundException, IOException{
        String csvFileToRead = "States.csv";
        BufferedReader br = null;
        String line = "";
        String splitBy = ",";


        br = new BufferedReader(new FileReader(csvFileToRead));
        while ((line = br.readLine()) != null) {
            String[] stateLine = line.split(splitBy);
            State state = new State();
            state.setId(Integer.parseInt(stateLine[0]));
            state.setName(stateLine[1]);
            state.setDistricAmount(Integer.parseInt(stateLine[2]));
            stateList.add(state);
        }
        return stateList;
    }

    public void printList(List<State> list) {
        for(int i = 0; i< list.size();i++){
            System.out.print("State name: " + list.get(i).getName()+ " ");
            System.out.print("StateId: " + list.get(i).getId()+ " ");
            System.out.println("amount of districts: " + list.get(i).getDistrictAmount()+ " ");
            Set<District> districts = list.get(i).getDistricts();
            for (District d : districts) {
                System.out.println("this state contains a district with the id: " + d.getId());
            }
//            System.out.println("districts: "+ list.get(i).getDistricts());
//            Set<Precinct> precincts = list.get(i).ge

        }
    }

    public void addDistricts(Set<District> districtSet, Set<Precinct>precinctSet) {
                State myState = (State) stateList.get(0);  //gets the fist state since its only AZ
                myState.setDistricts(districtSet);
                myState.setPrecincts(precinctSet);

    }


}
