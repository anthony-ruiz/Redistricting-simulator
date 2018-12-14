package com.example.Gerrymandering.controller;

import com.example.Gerrymandering.domain.*;

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
            Data data = new Data();

            ajson.setAlgorithm("SIMULATED_ANNEALING"); // just for testing purposes
            data.populateAll(ajson.getAlgorithm());

            Set<District> distrcs = data.getDistrictSet();  //printing the list of presincts that the district contains
            for(District d : distrcs){
                for(Precinct p : d.getPrecincts()){
                    System.out.println("district: " + d.getId() + " conatins precinct: " + p.getID());
                }
            }

//            algorithm.beginAlgorithm();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Algorithm Complete!";
    }
}