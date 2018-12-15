package com.example.Gerrymandering.controller;

import com.example.Gerrymandering.domain.AlgorithmJSON;
import com.example.Gerrymandering.domain.Data;
import com.example.Gerrymandering.domain.District;
import com.example.Gerrymandering.domain.Precinct;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;

@Controller
public class SpringController {
    @ResponseBody
    @RequestMapping(value = "/begin", method = RequestMethod.POST) //, method = RequestMethod.POST, produces = "application/json"
    public String getAlgorithm(@RequestBody String myJSON) {
        ObjectMapper mapper = new ObjectMapper();
        AlgorithmJSON ajson;
        try {
            ajson = mapper.readValue(myJSON, AlgorithmJSON.class);
            Algorithm algorithm = ajson.create();
//            MovesBuffer movesBuffer = new MovesBuffer();
//            movesBuffer.constructJson("111", 222);

            Data data = new Data();
            data.populateAll(ajson.getAlgorithm());
            algorithm.setState(data.getStateList().get(0));
            algorithm.beginAlgorithm();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Algorithm Complete!";
    }
}
