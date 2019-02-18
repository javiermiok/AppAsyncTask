package com.example.a21752434.appasynctask.data;

import org.json.JSONException;
import org.json.JSONObject;

public class Pokemon {
    public String url;
    public String nombre;

    public Pokemon(JSONObject objetoJSON) {
        try {
            url = objetoJSON.getString("url");
            nombre = objetoJSON.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return " url  : " + url + "\n name : " + nombre
                + "\n---------------------------------------\n";
    }
}

