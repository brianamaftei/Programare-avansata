package Bonus;

import Compulsory.Catalog.Catalog;
import Homework.Command;
import Homework.Exceptions.InvalidWriteFileException;
import Homework.ReportCommand;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class InfoCommand implements Command {
    private final Catalog catalog;
    private final String filePath;

    public InfoCommand(Catalog catalog, String filePath) {
        this.catalog = catalog;
        this.filePath = filePath;
    }

    /**
     * /**
     * Print information about the Catalog and its Documents
     * Calls the ReportCommand to create the html file
     * Parses the metadata of the file
     *
     * @throws InvalidWriteFileException if an invalid file path is provided
     */
    @Override
    public void execCommand() throws InvalidWriteFileException {

        ReportCommand command = new ReportCommand(catalog, filePath);
        command.execCommand();

        File file = new File(filePath);
        ParseContext context = new ParseContext();
        HtmlParser parser = new HtmlParser();
        Metadata metadata = new Metadata();
        BodyContentHandler handler = new BodyContentHandler();
        try {
            parser.parse(new FileInputStream(file), handler, metadata, context);
        } catch (IOException | SAXException | TikaException e) {
            throw new RuntimeException(e);
        }

       /* System.out.println("All the info: " + handler);
        for (String name : metadata.names()) {
            System.out.println(name + " : " + metadata.get(name));
        }
        */

        System.out.println("Prints the info about the catalog:");
        List<String> ids = Arrays.asList(metadata.getValues("id"));
        List<String> titles = Arrays.asList(metadata.getValues("title"));
        List<String> paths = Arrays.asList(metadata.getValues("location"));
        List<String> dates = Arrays.asList(metadata.getValues("date"));

        int size = ids.size();
        for (int i = 0; i < size; i++) {
            System.out.println("Document " + i + 1 + ":");
            System.out.println("ID: " + ids.get(i));
            System.out.println("Title: " + titles.get(i));
            System.out.println("Path: " + paths.get(i));
            System.out.println("Date: " + dates.get(i));
            System.out.println();
        }


    }
}