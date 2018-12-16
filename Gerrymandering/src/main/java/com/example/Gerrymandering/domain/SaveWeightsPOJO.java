package com.example.Gerrymandering.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.*;

public class SaveWeightsPOJO {

    @JsonProperty("username")
    private String username;
    @JsonProperty("Political Fairness")
    private Double politicalFairness;
    @JsonProperty("Compactness")
    private Double compactness;
    @JsonProperty("Population Equality")
    private Double populationEquality;

    public SaveWeightsPOJO() {}

    public void addWeights() {
        FileReader reader = null;
        BufferedReader br = null;
        try {
            reader = new FileReader("users.txt");
            br = new BufferedReader(reader);
            String line;
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("users.temp")));
            while ((line = br.readLine()) != null) {


//                if (request.getParameter("hname").equals(line)) {
//                    line = line.replace(request.getParameter("hname"),
//                            request.getParameter("book"));
//
//                    writer.println(line);
//
//                    writer.close();
//                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
