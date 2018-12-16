package com.example.Gerrymandering.controller;

import com.example.Gerrymandering.domain.SaveWeightsPOJO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class WeightSaveController {

    @ResponseBody
    @RequestMapping(value = "/weights", method = RequestMethod.POST)
    public void saveWeights(@RequestBody String weightsJSON) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            SaveWeightsPOJO objectiveFunction = mapper.readValue(weightsJSON, SaveWeightsPOJO.class);
            objectiveFunction.addWeights();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
