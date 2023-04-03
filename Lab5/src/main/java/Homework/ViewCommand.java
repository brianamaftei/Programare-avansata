package Homework;

import Compulsory.Catalog.Catalog;
import Compulsory.Document.Document;
import Homework.Exceptions.InvalidDocumentPathException;
import Homework.Exceptions.InvalidOpenFileException;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * The ViewCommand class represents a command that allows viewing a specific document from a catalog and opening its associated file
 */
public class ViewCommand implements Command {
    private final Catalog catalog;
    private final String nameOfTheDocument;

    public ViewCommand(Catalog catalog, String nameOfTheDocument) {
        this.catalog = catalog;
        this.nameOfTheDocument = nameOfTheDocument;
    }

    /**
     * Checks if the specified path is valid
     * @param path the path to be checked
     * @return true if the path is valid, false otherwise
     */
    public static boolean isValidPath(String path) {
        File file = new File(path);
        return file.exists() && !file.isDirectory();
    }

    /**
     * Find the document with the specified name in the catalog
     * If the path is valid. prints its information and open the document
     *
     * @throws InvalidDocumentPathException
     */
    @Override
    public void execCommand() throws InvalidDocumentPathException {
        Document document = catalog.findByNameOfDocument(nameOfTheDocument);
        System.out.println(document);
        if (isValidPath(document.getLocation())) {
            view(document);
        } else {
            throw new InvalidDocumentPathException("Invalid file path: " + document.getLocation());
        }
    }

    /**
     * Opens the file with the specified document
     * @param doc the document
     * @throws InvalidOpenFileException if the file cannot be opened
     */

    public static void view(Document doc) throws InvalidOpenFileException {
        Desktop desktop = Desktop.getDesktop();
        File file = new File(doc.getLocation());
        try {
            desktop.open(file);
        } catch (IOException exception) {
            throw new InvalidOpenFileException("The document cannot be opened");
        }
    }
}
