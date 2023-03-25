package Compulsory;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class CatalogUtil {
    public static void save(Catalog catalog, String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(path), catalog);
    }

    public static Catalog load(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Catalog catalog = new Catalog();
        try {
            catalog = objectMapper.readValue(new File(path), Catalog.class);
        } catch (IOException exception) {
            System.out.println(exception);
        }
        return catalog;
    }
}
