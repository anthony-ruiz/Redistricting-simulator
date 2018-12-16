package com.example.Gerrymandering.controller;

import com.example.Gerrymandering.domain.SignUpInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class SignUpController {

    @ResponseBody
    @RequestMapping(value = "/sign_up", method = RequestMethod.POST, produces = "application/json")
    public String signUpRequest(@RequestBody String signupJSON) {
        ObjectMapper mapper = new ObjectMapper();
        SignUpInfo sui;
        try {
            sui = mapper.readValue(signupJSON, SignUpInfo.class);
            boolean successful = sui.verifySignUp();
            if(successful) {
                return "success";
            } else {
                return "failure";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "failure";
    }
}
