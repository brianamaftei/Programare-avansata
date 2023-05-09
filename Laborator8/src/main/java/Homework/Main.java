package Homework;

import Compulsory.Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String csvFilePath = "src/main/resources/albumlist.csv";
        try (Connection connection = Database.getConnection()) {
            ImportTool.importData(csvFilePath, connection);
            connection.commit();
            Database.printTables(connection);
            connection.commit();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
