package com.example.Gerrymandering.controller;

import com.example.Gerrymandering.domain.AlgorithmJSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SpringController {
    @ResponseBody
    @RequestMapping(value = "/begin", method = RequestMethod.POST) //, method = RequestMethod.POST, produces = "application/json"
    public String getAlgorithm(@RequestBody String s) {
        System.out.print("Yay it worked!!!!!");

        String response = s;
        return response;
    }
}
