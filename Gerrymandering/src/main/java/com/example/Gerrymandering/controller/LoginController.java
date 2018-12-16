package com.example.Gerrymandering.controller;

import com.example.Gerrymandering.domain.LoginCredentials;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class LoginController {

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public String loginRequest(@RequestBody String loginJSON) { //
        ObjectMapper mapper = new ObjectMapper();
        try {
            LoginCredentials logcred = mapper.readValue(loginJSON, LoginCredentials.class);
            String successful = logcred.attemptLogin();
            if(successful.equals("Not Found")) {
                return "login failed"; // return user info as json string
            } else {
                return successful;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "login failed";
    }

}
