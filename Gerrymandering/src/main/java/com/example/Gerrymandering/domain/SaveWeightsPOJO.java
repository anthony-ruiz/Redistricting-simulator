package com.example.Gerrymandering.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SaveWeightsPOJO {

    private List<HashMap<String, Double>> weights;
    private ArrayList<String> fileLines;

    public void setWeights(List<HashMap<String, Double>> weights) {
        this.weights = weights;
    }

    public SaveWeightsPOJO() {}

    public void addWeights() {
        FileReader reader = null;
        BufferedReader br = null;
        fileLines = new ArrayList<>();
        try {
            reader = new FileReader("users.txt");
            br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                JSONObject jo = new JSONObject(line);
                if(jo.getString("Username").equals(CurrentUser.currentUser.getUsername())) {
                    CurrentUser.currentUser.setSavedWeights(weights);
                    line = line.replace(line, CurrentUser.currentUser.toString());
                }
                fileLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                reader.close();
                br.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter("users.txt")));
            for (String line: fileLines) {
                pw.write(line + "\n");
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveWeights() {
        try {
            BufferedReader file = new BufferedReader(new FileReader("notes.txt"));
            String line;
            StringBuffer inputBuffer = new StringBuffer();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
