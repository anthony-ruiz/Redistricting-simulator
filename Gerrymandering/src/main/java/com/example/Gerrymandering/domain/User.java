/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Gerrymandering.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User {
    private int ID;
    private String email;
    private String username;
    private String password;
    private ArrayList<HashMap<String, Double>> savedWeights;

    public User() {}

    public User(String email, String username, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        savedWeights = new ArrayList<>();
    }

    public User(String savedUser) {
        JSONObject jo = new JSONObject(savedUser);
        this.email = jo.getString("Email");
        this.username = jo.getString("Username");
        this.password = jo.getString("Password");
        savedWeights = new ArrayList<>();
        JSONArray jaSavedW = jo.getJSONArray("Saved Weights");
        if (jaSavedW != null && !jaSavedW.isEmpty()) {
            for (int i = 0; i < jaSavedW.length(); i ++){
                HashMap<String, Double> weight = new HashMap<>();
                Double w1 = jaSavedW.getJSONObject(i).getDouble("Population Equality");
                Double w2 = jaSavedW.getJSONObject(i).getDouble("Political Fairness");
                Double w3 = jaSavedW.getJSONObject(i).getDouble("Compactness");
                weight.put("Population Equality", w1);
                weight.put("Political Fairness", w2);
                weight.put("Compactness", w3);
                savedWeights.add(weight);
                if(!weight.isEmpty()) {
                    savedWeights.add(weight);
                }
            }
        }
//        this.savedWeights = jo.getJSONArray("Saved Weights");
    }

    public void setSavedWeights(List<HashMap<String, Double>> savedWeights) {
        this.savedWeights = (ArrayList<HashMap<String, Double>>) savedWeights;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addToListOfWeights(HashMap<String, Double> listToAdd) {
        savedWeights.add(listToAdd);
    }

    public ArrayList<HashMap<String, Double>> getSavedWeights() {
        return savedWeights;
    }

    public void saveWeights(ObjectiveFunction weights) {
        this.savedWeights = savedWeights;
    }

    public String toString() {
        JSONObject jo = new JSONObject();
        jo.put("Saved Weights", this.savedWeights);
        jo.put("Password", this.password);
        jo.put("Username", this.username);
        jo.put("Email", this.email);
        return jo.toString();
    }
}
