package Homework;

import Compulsory.Catalog.Catalog;
import Homework.Exceptions.InvalidCommandException;
import Homework.Exceptions.InvalidOpenFileException;
import Homework.Exceptions.InvalidWriteFileException;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class ReportCommand implements Command {

    private final Catalog catalog;
    private final String filePath;

    public ReportCommand(Catalog catalog, String filePath) throws InvalidCommandException {
        if (filePath == null || filePath.isEmpty()) {
            throw new InvalidCommandException("File path is null or empty");
        }
        this.catalog = catalog;
        this.filePath = filePath;
    }

    /**
     * Executes the report command by using an Apache Velocity template to generate an HTML report of the catalog
     * The generated report is opened in a web browser
     *
     * @throws InvalidWriteFileException if the file cannot be written to
     * @throws InvalidWriteFileException if the file cannot be opened
     */
    @Override
    public void execCommand() throws InvalidWriteFileException {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        try {
            velocityEngine.init();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Template t;
        try {
            t = velocityEngine.getTemplate("index.vm");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        VelocityContext context = new VelocityContext();

        context.put("name", catalog.getName());
        System.out.println(catalog.getDocs());
        context.put("documents", catalog.getDocs());

        StringWriter writer = new StringWriter();
        try {
            t.merge(context, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileWriter file = null;
        try {
            file = new FileWriter(filePath);
            file.write(writer.toString());
            Desktop desktop = Desktop.getDesktop();
            File fileOpen = new File(filePath);
            try {
                desktop.open(fileOpen);
            } catch (IOException exception) {
                throw new InvalidOpenFileException("Can't open the file");
            }
        } catch (IOException e) {
            throw new InvalidWriteFileException("Can't write to the file");
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
