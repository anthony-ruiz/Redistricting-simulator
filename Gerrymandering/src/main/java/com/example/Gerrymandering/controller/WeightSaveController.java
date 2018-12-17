package com.example.Gerrymandering.controller;

import com.example.Gerrymandering.domain.CurrentUser;
import com.example.Gerrymandering.domain.ObjectiveFunction;
import com.example.Gerrymandering.domain.SaveWeightsPOJO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class WeightSaveController {

    @ResponseBody
    @RequestMapping(value = "/save_weights", method = RequestMethod.POST)
    public void saveWeights(@RequestBody String weightsJSON) {
        JSONArray ja = new JSONArray(weightsJSON);
        ArrayList<HashMap<String, Double>> listdata = new ArrayList<>();
        if(ja != null) {
            for(int i = 0; i < ja.length(); i ++) {
                JSONObject jsonObject1 = ja.getJSONObject(i);
                Double w1 = jsonObject1.optDouble("w1");
                Double w2 = jsonObject1.optDouble("w2");
                Double w3 = jsonObject1.optDouble("w3");
                ObjectiveFunction of = new ObjectiveFunction(w1, w2, w3);
                listdata.add(of.getObjective());
            }
        }
        SaveWeightsPOJO objectiveFunction = new SaveWeightsPOJO();
        objectiveFunction.setWeights(listdata);
        objectiveFunction.addWeights();

    }
}
