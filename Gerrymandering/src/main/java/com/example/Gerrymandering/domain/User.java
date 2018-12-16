/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Gerrymandering.domain;

import org.json.JSONObject;

import java.util.ArrayList;

public class User {
    private int ID;
    private String username;
    private String email;
    private String password;
    private ArrayList<ObjectiveFunction> savedWeights;

    public User(String email, String username, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        savedWeights = new ArrayList<>();
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

    public ArrayList<ObjectiveFunction> getSavedWeights() {
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
