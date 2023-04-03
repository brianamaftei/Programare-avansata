package Compulsory.Document;

import java.io.*;
import java.util.*;

/**
 * This class represents a document that can be stored in a catalog
 */

public class Document implements Serializable {
    private String id;
    private String title;
    private String location;
    private String date;
    private Map<String, Object> tags = new HashMap<>();

    public Document() {

    }

    public Document(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public Document(String id, String title, String location, String date) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    /**
     * Adds a new tag to the document with the given key and object
     * @param key    key for the tag
     * @param object the object associated with the key
     */
    public void addTag(String key, Object object) {
        tags.put(key, object);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, Object> getTags() {
        return tags;
    }

    public void setTags(Map<String, Object> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(title, document.title);
    }


    @Override
    public String toString() {
        return "Document{" + "id='" + id + '\'' + ", title='" + title + '\'' + ", location='" + location + '\'' + ", date=" + date + ", tags=" + tags + '}';
    }
}