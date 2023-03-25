package Compulsory;

import java.io.*;
import java.util.*;

public class Document implements Serializable {
    private String id;
    private String title;
    private String location;

    public Document() {
    }

    public Document(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}