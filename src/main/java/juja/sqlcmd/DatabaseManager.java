package juja.sqlcmd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private final String jdbcDriverClass;
    private final String jdbcUrl;
    private Connection connection;

    public DatabaseManager() {
        jdbcDriverClass = "org.postgresql.Driver";
        jdbcUrl = "jdbc:postgresql://localhost:5432/";
    }

    DatabaseManager(String jdbcDriverClass, String jdbcUrl) {
        this.jdbcDriverClass = jdbcDriverClass;
        this.jdbcUrl = jdbcUrl;
    }

    public boolean connect(String database, String user, String password) {
        try {
            Class.forName(jdbcDriverClass);
            connection = DriverManager.getConnection(
                    jdbcUrl + database, user, password);
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
