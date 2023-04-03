package Compulsory.Catalog;

import Compulsory.Catalog.Exceptions.DocumentCantBeAddedException;
import Compulsory.Catalog.Exceptions.DocumentNotFoundException;
import Compulsory.Document.Document;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Catalog class represents a catalog of documents
 */
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

    /**
     * Adds a document to the list of documents in the catalog if the document do not exist in the catalog
     * @param doc the document to be added
     * @throws DocumentCantBeAddedException if the document already exists in the catalog
     */

    public void add(Document doc) {

        var element = docs.stream().filter(d -> d.equals(doc)).findFirst().orElse(null);
        if(element==null){
            docs.add(doc);
        }
        else {
            throw new DocumentCantBeAddedException("The document cannot be add to the catalog");
        }
    }

    /**
     * Searches for a document in the catalog by its name.
     * @param nameOfDocument the name of the document to be found in the catalog
     * @return the document object found in the catalog
     * @throws DocumentNotFoundException if the document is not found in the catalog
     */
    public Document findByNameOfDocument(String nameOfDocument)  {
        var doc = docs.stream().filter(d -> d.getTitle().equals(nameOfDocument)).findFirst().orElse(null);
        if (doc == null) {
            throw new DocumentNotFoundException("The document cannot be found");
        } else
            return doc;
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Compulsory/Catalog ").append(name).append(":");
        str.append('\n');
        for (var entry : docs) {
            str.append("-");
            str.append(entry);
            str.append('\n');
        }
        str.append('\n');
        return str.toString();
    }


}