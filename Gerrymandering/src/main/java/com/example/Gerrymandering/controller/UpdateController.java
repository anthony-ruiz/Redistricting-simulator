package com.example.Gerrymandering.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UpdateController {

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.GET, produces = "application/json") //, method = RequestMethod.POST, produces = "application/json"
    public String updatePrecincts() {
        if(MovesBuffer.moves.peek() != null) {
//            System.out.println(MovesBuffer.moves.peek());
            JSONObject jo = MovesBuffer.moves.poll();
            return jo.toString();
        } else {
            return "[]";
        }


//        return ja.toString();
    }


    @ResponseBody
    @RequestMapping(value = "/updatepop", method = RequestMethod.GET, produces = "application/json") //, method = RequestMethod.POST, produces = "application/json"
    public String updatePopulation() {
        if(MovesBuffer.moves.peek() != null) {
//            System.out.println(MovesBuffer.moves.peek());
            JSONObject jo = MovesBuffer.moves.poll();
            return jo.toString();
        } else {
            return "[]";
        }


//        return ja.toString();
    }
}
