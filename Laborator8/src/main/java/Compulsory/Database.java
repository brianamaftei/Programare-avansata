package Compulsory;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/albums";
    private static final String USER = "postgres";
    private static final String PASSWORD = "password";
    private static Connection connection = null;

    private Database() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            createConnection();
        }
        return connection;
    }
    public static void printTables() throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        String[] tableTypes = {"TABLE"};
        ResultSet tables = metaData.getTables(null, null, null, tableTypes);
        while (tables.next()) {
            String tableName = tables.getString("TABLE_NAME");
            System.out.printf("Table: %s\n", tableName);
            try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName)) {
                while (rs.next()) {
                    ResultSetMetaData rsMetaData = rs.getMetaData();
                    int columnCount = rsMetaData.getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = rsMetaData.getColumnName(i);
                        String value = rs.getString(columnName);
                        System.out.printf("- %s: %s\n", columnName, value);
                    }
                }
            }
            System.out.println();
        }
    }
    public static void rollback() {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void createConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
