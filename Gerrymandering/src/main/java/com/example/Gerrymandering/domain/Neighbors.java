package com.example.Gerrymandering.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Neighbors {
    Map<String, List<String>> outputLines = new HashMap<>();
    public void populateNeigbors(){
        String fileName = "neighbors.csv";
        File file = new File(fileName);
        //create 2 dimensional array list
        List<List<String>> lines = new ArrayList<>();
        Scanner inputStream;
        try {
            inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                String line = inputStream.next();
                String[] values = line.split(",");
                // this adds the currently parsed line to the 2-dimensional string array
                lines.add(Arrays.asList(values));
            }

            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int lineCount = 0;
        String[][] array = new String[lines.size()][2];

        // iterate trough the araylist and create a 2D array
        int lineNo = 1;
        for (List<String> line : lines) {
            int columnNo = 1;
            for (String value : line) {
                if (columnNo == 1) {
                    array[lineNo - 1][0] = value;
                } else {
                    array[lineNo - 1][1] = value;
                }
                columnNo++;
            }
            lineCount++;
            lineNo++;
        }
        //create a HashMap to store as output

        List<String> inner = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            if (inner.isEmpty()) {
                inner.add(array[i][0]);
                inner.add(array[i][1]);
            } else if (inner.get(0).equals(array[i][0])) {
                inner.add(array[i][1]);
                if(i == lines.size()-1){
                    String key = inner.get(0);
                    inner.remove(0);
                    outputLines.put(key, (List<String>) ((ArrayList<String>) inner).clone());
                    inner.clear();
                }
            } else {
                String key = inner.get(0);
                inner.remove(0);
                outputLines.put(key, (List<String>) ((ArrayList<String>) inner).clone());
                inner.clear();
                inner.add(array[i][0]);
                inner.add(array[i][1]);
            }
        }
    }
    public List getNeighbors(String precinct){
        return outputLines.get(precinct);
    }
}
