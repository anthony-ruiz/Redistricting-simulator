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
//        JSONArray ja = new JSONArray();
//        JSONObject jo = new JSONObject();
//        jo.put("Hello", "World");
//        JSONObject jo2 = new JSONObject();
//        jo2.put("Goodbye", "Life");
//        ja.put(jo);
//        ja.put(jo2);
//        System.out.println(ja.toString());
        if(MovesBuffer.moves.peek() != null) {
            System.out.println(MovesBuffer.moves.peek());
            JSONObject jo = MovesBuffer.moves.poll();
            return jo.toString();
        } else {
            return "[]";
        }


//        return ja.toString();
    }
}
