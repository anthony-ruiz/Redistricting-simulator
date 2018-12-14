package com.example.Gerrymandering.controller;

import com.example.Gerrymandering.domain.AlgorithmJSON;
import com.example.Gerrymandering.initializers.PopulateDistricts;
import com.example.Gerrymandering.initializers.PopulatePrecincts;
import com.example.Gerrymandering.initializers.PopulateStates;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class SpringController {
    @ResponseBody
    @RequestMapping(value = "/begin", method = RequestMethod.POST) //, method = RequestMethod.POST, produces = "application/json"
    public String getAlgorithm(@RequestBody String s) throws IOException {
        PopulateStates ps = new PopulateStates();
        List states = ps.populate();
        PopulateDistricts pd = new PopulateDistricts();
        List districts = pd.populate(states);
        PopulatePrecincts pp = new PopulatePrecincts();
        List precincts = pp.populate(districts);
        pp.printList(precincts);

        System.out.print("Yay it worked!!!!!");

        String response = s;
        return response;
    }
}
