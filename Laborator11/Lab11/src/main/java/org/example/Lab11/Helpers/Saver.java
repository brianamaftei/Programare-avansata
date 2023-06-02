package org.example.Lab11.Helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public class Saver {
    public static void save(ToSave ts) {
        ObjectMapper om = new ObjectMapper();
        om.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            om.writeValue(new File("C:/Users/pinza/Desktop/pa/Lab11/src/main/resources/data.json"), ts);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ToSave load() {
        ObjectMapper om = new ObjectMapper();
        try {
            return (om.readValue(new File("C:/Users/pinza/Desktop/pa/Lab11/src/main/resources/data.json"), ToSave.class));
        } catch (IOException e) {
            System.out.println("NO DATA");
            return null;
        }
    }


}
