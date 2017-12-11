package juja.sqlcmd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DatabaseManager {
    private final String jdbcDriverClass;
    private final String jdbcUrl;
    private String schemaPattern = "public";
    private Connection connection;

    public String getSchemaPattern() {
        return schemaPattern;
    }

    public void setSchemaPattern(String schemaPattern) {
        this.schemaPattern = schemaPattern;
    }

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

    public static void main(String[] args) throws SQLException {
        DatabaseManager manager = new DatabaseManager();
        manager.connect("sqlcmd", "sqlcmd", "sqlcmd");
        System.out.println(Arrays.toString(manager.getTableNames()));
    }

    public String[] getTableNames() throws SQLException {
        try (ResultSet rs = connection.getMetaData().getTables(null, schemaPattern, "%", new String[]{"TABLE"})) {
            List<String> tablesList = new LinkedList<>();
            while (rs.next()) {
                tablesList.add(rs.getString("table_name"));
            }
            return tablesList.toArray(new String[0]);
        }
    }

}
