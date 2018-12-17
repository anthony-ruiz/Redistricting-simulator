package com.example.Gerrymandering.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public class SaveWeightsPOJO {

    @JsonProperty("weightsList")
    private List<HashMap<String, Double>> weights;

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
                JSONObject jo = new JSONObject(line);
                ObjectiveFunction of = new ObjectiveFunction(politicalFairness, compactness, populationEquality);
                if(jo.getString("Username").equals(this.username)) {
                    User user = new User(jo.toString());
                    user.addToListOfWeights(of.getObjective());
                    line = line.replace(line, user.toString());
                }
                writer.println(line);
            }
            File realName = new File("user.txt");
            realName.delete();
            new File("users.temp").renameTo(realName);
            writer.close();
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
    }
}
