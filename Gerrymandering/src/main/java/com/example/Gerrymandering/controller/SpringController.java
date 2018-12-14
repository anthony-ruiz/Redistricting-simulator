package com.example.Gerrymandering.controller;

import com.example.Gerrymandering.domain.AlgorithmJSON;
import com.example.Gerrymandering.domain.District;
import com.example.Gerrymandering.domain.Precinct;
import com.example.Gerrymandering.domain.State;
import com.example.Gerrymandering.initializers.InitializeData;
import com.example.Gerrymandering.initializers.PopulateDistricts;
import com.example.Gerrymandering.initializers.PopulatePrecincts;
import com.example.Gerrymandering.initializers.PopulateStates;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@Controller
public class SpringController {
    @ResponseBody
    @RequestMapping(value = "/begin", method = RequestMethod.POST) //, method = RequestMethod.POST, produces = "application/json"
    public String getAlgorithm(@RequestBody String myJSON) {
        System.out.println(myJSON);
        ObjectMapper mapper = new ObjectMapper();
        AlgorithmJSON ajson;
        try {
            ajson = mapper.readValue(myJSON, AlgorithmJSON.class);
            Algorithm algorithm = ajson.create();
            InitializeData init = new InitializeData(ajson.getAlgorithm());
            algorithm.setState(init.initialize(algorithm.getStateName()));
//            algorithm.beginAlgorithm();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Algorithm Complete!";
    }
}