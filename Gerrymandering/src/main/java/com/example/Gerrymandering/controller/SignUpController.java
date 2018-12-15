package com.example.Gerrymandering.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
public class SignUpController {

    @ResponseBody
    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public String loginRequest(@RequestBody String signupJSON) {
//        ObjectMapper mapper = new ObjectMapper();
        System.out.println(signupJSON);
        return "";
    }
}
