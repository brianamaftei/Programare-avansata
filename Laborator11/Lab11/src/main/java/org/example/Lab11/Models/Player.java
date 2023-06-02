package org.example.Lab11.Models;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;

public class Player {
    private String name;

    public Player(@RequestBody String name) {
        JSONObject json = new JSONObject(name);
        this.name = json.getString("name");
        System.out.println(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
