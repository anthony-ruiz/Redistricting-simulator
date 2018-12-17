package com.example.Gerrymandering.controller;

import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ResultsController {

    @ResponseBody
    @RequestMapping(value = "/results", method = RequestMethod.GET, produces = "application/json")
    public String getResultsController() {
        JSONArray ja = MovesBuffer.tempResults.poll();
        MovesBuffer.political = new JSONArray();
        MovesBuffer.population = new JSONArray();
        MovesBuffer.results = new JSONArray();
        return ja.toString();
    }
}
