package Compulsory;

import Compulsory.Catalog.Catalog;
import Compulsory.Catalog.CatalogUtil;
import Compulsory.Document.Document;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();
    }

    private void testCreateSave() {
        Catalog catalog = new Catalog("MyDocuments");
        var book = new Document("article1", "Destinations To Visit in Your Lifetime");
        var book1 = new Document("article2", "Destinations", "Location2", "23/12/2022");
        var article = new Document("book1", "The Flavor Bible");
        catalog.add(book);
        catalog.add(book1);
        catalog.add(article);
        try {
            CatalogUtil.save(catalog, "D:/PA/catalog.json");
            System.out.println("Saved");
        } catch (java.io.IOException exception) {
            System.out.println("There is a problem with the saving process");
        }

    }

    private void testLoadView() {
        try {
            Catalog catalog = CatalogUtil.load("D:/PA/catalog.json");
            System.out.println("Loaded");
        } catch (java.io.IOException exception) {
            System.out.println("There is a problem with the loading process");
        }
    }
}