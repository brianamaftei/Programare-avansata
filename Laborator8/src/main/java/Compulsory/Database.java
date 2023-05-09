package Compulsory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/albums";
    private static final String USER = "postgres";
    private static final String PASSWORD = "password";
    private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);

        config.setMaximumPoolSize(100);
        config.setAutoCommit(false);

        dataSource = new HikariDataSource(config);
    }

    private Database() {
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void printTables(Connection connection) throws SQLException {
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
            Connection connection = getConnection();
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
