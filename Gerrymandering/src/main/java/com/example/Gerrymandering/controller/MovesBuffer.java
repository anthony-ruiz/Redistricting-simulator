package com.example.Gerrymandering.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class MovesBuffer {
    public static Queue<JSONObject> moves = new LinkedList<>();
    public static Queue<JSONArray> tempResults = new LinkedList<>();
    public static JSONArray results = new JSONArray();
    public static JSONArray population = new JSONArray();
    public static JSONArray political = new JSONArray();

//    public static JSONArray moves = new JSONArray();

    public void constructJson(String precinctID, int districtID) {   //add boolean for telling frontend about end of algorithm
        JSONObject move = new JSONObject();
        move.put("precinctID", precinctID);
        move.put("districtID", districtID);
        moves.add(move);
    }

    public void addPopulationResult(int districtID, int population) {
        JSONObject move = new JSONObject();
        move.put("districtID", districtID);
        move.put("population", population);
        this.population.put(move);
    }

    public void addPoliticalResult(int districtID, String political) {
        JSONObject move = new JSONObject();
        move.put("districtID", districtID);
        move.put("political", political);
        this.political.put(move);
    }

    public void combineResults() {
        results.put(population);
        results.put(political);
        tempResults.add(results);
    }

}
