package com.example.Gerrymandering.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.Queue;

public class MovesBuffer {
    public static Queue<JSONObject> moves = new LinkedList<>();

//    public static JSONArray moves = new JSONArray();

    public void constructJson(String precinctID, int districtID) {   //add boolean for telling frontend about end of algorithm
        JSONObject move = new JSONObject();
        move.put("precinctID", precinctID);
        move.put("districtID", districtID);
        moves.add(move);
    }

}
