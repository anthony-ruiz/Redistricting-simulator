package com.example.Gerrymandering.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
public class AccountController {

    @ResponseBody
    @RequestMapping(value = "/login_req", method = RequestMethod.POST)
    public void loginRequest(@RequestBody String loginJSON) {
//        ObjectMapper mapper = new ObjectMapper();
        System.out.println(loginJSON);
    }

}
