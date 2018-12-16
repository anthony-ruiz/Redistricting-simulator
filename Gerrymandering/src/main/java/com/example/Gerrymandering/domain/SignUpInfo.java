package com.example.Gerrymandering.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

import java.io.*;

public class SignUpInfo {

    @JsonProperty("email")
    private String email;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;

    public boolean verifySignUp() {
        boolean unique = verify();
        if(!unique) {
            return false;
        }

        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            User newUser = new User(email, username, password);
            File file = new File("users.txt");
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            // true = append file
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(newUser.toString() + "\n");

        } catch (IOException e) {

            e.printStackTrace();

        } finally {
            try {
                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }

    private boolean verify() {
        BufferedReader reader;
        try {
            File file = new File("users.txt");
            if (!file.exists()) {
                file.createNewFile();
                return true;
            }
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                JSONObject jo = new JSONObject(line);
                if(jo.get("Username").equals(this.username)) {
                    reader.close();
                    return false;
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
