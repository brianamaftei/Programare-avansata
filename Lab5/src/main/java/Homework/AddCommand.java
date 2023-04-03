package Homework;

import Compulsory.Catalog.Catalog;
import Compulsory.Catalog.CatalogUtil;
import Compulsory.Document.Document;

public class AddCommand implements Command {
    protected Catalog catalog;
    protected Document document;


    public AddCommand(Catalog catalog, Document document) {
        this.catalog = catalog;
        this.document = document;
    }

    @Override
    public void execCommand() {
        catalog.add(document);
        try {
            CatalogUtil.save(catalog, document.getLocation());
            System.out.println("Saved");
        } catch (java.io.IOException exception) {
            System.out.println("There is a problem with the saving process");
        }
    }

}


