package Homework;

import Compulsory.Catalog.Catalog;
import Compulsory.Catalog.CatalogUtil;
import Compulsory.Catalog.Exceptions.DocumentNotFoundException;
import Compulsory.Document.Article;
import Compulsory.Document.Book;
import Homework.Exceptions.InvalidCommandException;

import java.io.Console;
import java.io.IOException;

public class Main {
    private static Command getCommand(String commandLine, Catalog catalog) throws InvalidCommandException {
        String[] commandElements = commandLine.split("\\s+");
        if (commandElements.length == 1) {
            if (commandElements[0].equals("list")) {
                return new ListCommand(catalog);
            } else if (commandElements[0].equals("exit")) {
                return null;
            }
        } else if (commandElements.length == 2) {
            if (commandElements[0].equals("report")) {
                return new ReportCommand(catalog, commandElements[1]);
            } else if (commandElements[0].equals("view")) {
                try {
                    return new ViewCommand(catalog, commandElements[1]);
                } catch (DocumentNotFoundException e) {
                    throw new DocumentNotFoundException("Document cannot be found");
                }
            }
        } else if (commandElements.length == 5) {
            if (commandElements[0].equals("addBook")) {
                return new AddCommand(catalog, new Book(commandElements[1], commandElements[2], commandElements[3], commandElements[4]));
            } else if (commandElements[0].equals("addArticle")) {
                return new AddCommand(catalog, new Article(commandElements[1], commandElements[2], commandElements[3], commandElements[4]));
            }
        }

        throw new InvalidCommandException("Invalid command");
    }

    public static void main(String[] args) {
        Catalog catalog = new Catalog();
        boolean exit = false;
        String path;
        boolean setCatalog = false;
        Console console = System.console();
        if (console == null) {
            System.out.println("Console not found");
            System.exit(-1);
        }
        while (!exit) {
            if (!setCatalog) {
                System.out.println("Enter the name of the catalog: ");
                String commandLine = console.readLine();
                String[] commandElements = commandLine.split("\\s+");
                catalog.setName(commandElements[0]);
                System.out.println("Enter the path for the catalog to be saved: ");
                commandLine = console.readLine();
                commandElements = commandLine.split("\\s+");
                path = commandElements[0];
                System.out.println(path);
                try {
                    CatalogUtil.save(catalog, path + catalog.getName() + ".json");
                    System.out.println("The catalog " + catalog.getName() + ".json" + " is saved");
                } catch (java.io.IOException exception) {
                    System.out.println("There is a problem with the saving process");
                }
                setCatalog = true;
            } else {
                System.out.print("Enter command: ");
                String commandLine = console.readLine();
                try {
                    Command command = getCommand(commandLine, catalog);
                    if (command == null) {
                        exit = true;
                    } else {
                        command.execCommand();
                    }
                } catch (InvalidCommandException | IOException e) {
                    System.out.println(e.getMessage());
                }
            }

        }
    }
}