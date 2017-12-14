package juja.sqlcmd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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

    public String[] getTableNames() throws SQLException {
        try (ResultSet rs = connection.getMetaData().getTables(
                null, "public", "%", new String[]{"TABLE"})) {
            int tablesCount = 0;
            if (rs.last()) {
                tablesCount = rs.getRow();
                rs.beforeFirst();
            }
            String[] tableNames = new String[tablesCount];
            int index = 0;
            while (rs.next()) {
                tableNames[index++] = rs.getString("table_name");
            }
            return tableNames;
        }
    }

}
