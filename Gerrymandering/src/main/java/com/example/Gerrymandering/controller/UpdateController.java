package com.example.Gerrymandering.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UpdateController {

    @ResponseBody
    @RequestMapping(value = "/begin", method = RequestMethod.GET) //, method = RequestMethod.POST, produces = "application/json"
    public String getAlgorithm(@RequestBody String s) {

        String response = s;
        return response;
    }
}
