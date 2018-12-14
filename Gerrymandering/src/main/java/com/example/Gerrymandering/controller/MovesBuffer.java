package com.example.Gerrymandering.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovesBuffer {

    public static JSONArray moves = new JSONArray();

    public void constructJson(String precinctID, String districtID) {   //add boolean for telling frontend about end of algorithm
        JSONObject move = new JSONObject();
        move.put("precinctID", precinctID);
        move.put("districtID", districtID);
        moves.put(move);
    }

}
