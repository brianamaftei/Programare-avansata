package Compulsory;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonDeserialize(as = Catalog.class)
public class Catalog implements Serializable {
    private String name;
    private List<Document> docs = new ArrayList<>();
    public Catalog(String name) {
        this.name = name;
    }
    public Catalog() {
    }

    public List<Document> getDocs() {
        return docs;
    }
    public String getName() {
        return name;
    }
    @JsonSetter
    public void setDocs(List<Document> docs) {
        this.docs = docs;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void add(Document doc) {
        docs.add(doc);
    }
    public Document findById(String id) {
        return docs.stream().filter(d -> d.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "Catalog{" + "name='" + name + '\'' + ", docs=" + docs + '}';
    }

}