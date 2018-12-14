package com.example.Gerrymandering.initializers;

import com.example.Gerrymandering.domain.District;
import com.example.Gerrymandering.domain.Precinct;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PopulatePrecincts {

    List precinctList = new ArrayList();
        public List populate(List districts) throws IOException {
            String csvFileToRead = "Precinct.csv";
            BufferedReader br = null;
            String line = "";
            String splitBy = ",";
            br = new BufferedReader(new FileReader(csvFileToRead));
            while ((line = br.readLine()) != null) {
                String[] precinctLine = line.split(splitBy);
                Precinct precinct = new Precinct();
                District currentDistrict = (District) districts.get(Integer.parseInt(precinctLine[0])-1); //gets the state object
                precinct.setDistrict(currentDistrict);
                precinct.setID(precinctLine[1]);
                precinct.setVolume(Double.parseDouble(precinctLine[2]));
                precinctList.add(precinct);

            }
           setNeighbors();
            return precinctList;
        }

    public void setNeighbors(){
        Neighbors neighbors = new Neighbors();
        neighbors.populateNeigbors();
        for(int i =0; i<precinctList.size();i++){
            Set<Precinct> setforCurrent = new HashSet<>();
            Precinct currentPrecinct = (Precinct) precinctList.get(i);
            List neighborsOfCurrentList = neighbors.getNeighbors(currentPrecinct.getID());
            for(int j = 0; j<neighborsOfCurrentList.size();j++){
                Precinct p = (Precinct) precinctList.get(j);
                setforCurrent.add(p);
            }
            currentPrecinct.setNeighbors(setforCurrent);

        }


    }
    public void printList(List<Precinct> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print("Precinct is in state: " + list.get(i).getDistrict().getState().getName()+ " |");
            System.out.print("Precinct id: " + list.get(i).getID() + " |");
            System.out.print("precinct neighbors: " + list.get(i).getNeighbors() + " |");
            System.out.println("precinct Area : " + list.get(i).getVolume() + " |");


        }
    }
}
