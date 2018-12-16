package com.example.Gerrymandering.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoginCredentials {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;

    public String attemptLogin() {
        BufferedReader reader;
        try {
            File file = new File("users.txt");
            if (!file.exists()) {
                return "Not Found";
            }
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                JSONObject jo = new JSONObject(line);
                if(jo.get("Username").equals(this.username) && jo.get("Password").equals(this.password)) {
                    reader.close();
                    return jo.toString();
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Not Found";
    }

}
