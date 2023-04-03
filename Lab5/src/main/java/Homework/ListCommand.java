package Homework;

import Compulsory.Catalog.Catalog;


public class ListCommand implements Command {

    protected Catalog catalog;

    public ListCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public void execCommand() {
        System.out.println(catalog.getDocs());
    }
}
