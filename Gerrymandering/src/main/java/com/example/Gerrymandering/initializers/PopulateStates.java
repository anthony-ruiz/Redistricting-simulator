package com.example.Gerrymandering.initializers;

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
import java.util.List;

/**
 *
 * @author antho
 */
public class PopulateStates {
    public List  populate() throws FileNotFoundException, IOException{
        String csvFileToRead = "States.csv";
        BufferedReader br = null;
        String line = "";
        String splitBy = ",";
        List stateList = new ArrayList();

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

        }
    }
}
