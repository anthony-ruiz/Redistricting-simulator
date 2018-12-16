package com.example.Gerrymandering.controller;

import com.example.Gerrymandering.domain.SaveWeightsPOJO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class WeightSaveController {

    @ResponseBody
    @RequestMapping(value = "/save_weights", method = RequestMethod.POST)
    public void saveWeights(@RequestBody String weightsJSON) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            SaveWeightsPOJO objectiveFunction = mapper.readValue(weightsJSON, SaveWeightsPOJO.class);
            objectiveFunction.addWeights();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @ResponseBody
    @RequestMapping(value = "/load_weights", method = RequestMethod.GET, produces ="application/json")
    public String loadWeights() {


        return "";
    }
}
