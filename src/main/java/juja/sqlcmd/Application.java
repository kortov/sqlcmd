package juja.sqlcmd;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Application {

    private static final String JDBC_SQL_DRIVER = "org.postgresql.Driver";
    private static final String JDBC_PROTOCOLS = "jdbc:postgresql://";
    private static final String SQl_URL = "127.0.0.1:5432/";

    private static final String DATABASE_NAME = "sqlcmd";
    private static final String USER_NAME = "sqlcmd";
    private static final String USER_PASSWORD = "sqlcmd";

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private Connection connection = null;


    Application() {
        connection = getConnection();
    }

    public static void main(String[] args) {
        new Application().simpleSQL();

    }

    public void simpleSQL() {

        printTableList();

        String tableName = "user";

        dropTableifExists(tableName);
        printTableList();

        createTableUser(tableName);
        printTable(tableName);

        printTableList();

        insertIntoUser(tableName, "user1", "password1");
        insertIntoUser(tableName, "user2", "password2");
        insertIntoUser(tableName, "user3", "password3");

        printTable(tableName);

        changePasswordInTable(tableName, "user1", "password2");
        printTable(tableName);

        removeUserFromTable(tableName, "user3");
        printTable(tableName);


        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void printStatusMsg(String operation, String subject) {
        System.out.println(operation + " table " + subject + "..");
    }

    private void dropTableifExists(String tableName) {
        String sqlQuery = "DROP TABLE IF EXISTS " + tableNameFormatted(tableName);
        printStatusMsg("Removing", tableName);
        int affectedRows = executeQuery(sqlQuery);
        printMsgAffectedRows(affectedRows);
    }

    private void changePasswordInTable(String tableName, String name, String password) {

        String sqlQuery = "UPDATE " + tableNameFormatted(tableName) + " SET password = '" + password + "' WHERE name = '" + name + "'";
        printStatusMsg("Updating", tableName);
        int affectedRows = executeQuery(sqlQuery);
        printMsgAffectedRows(affectedRows);
    }

    private void removeUserFromTable(String tableName, String value) {
        String sqlQuery = String.format("DELETE FROM " + tableNameFormatted(tableName) + " WHERE name='%s'", value);
        int affectedRows = executeQuery(sqlQuery);
        printMsgAffectedRows(affectedRows);
    }

    private String tableNameFormatted(String tableName) {
        return "\"" + tableName + "\"";
    }


    private void printTable(String tableName) {
        String sqlQuery = "SELECT * FROM " + tableNameFormatted(tableName);
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            printStatusMsg("Printing", tableName);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("table is empty");
                System.out.println();
                return;
            }

            StringBuilder stringBuilder = new StringBuilder();
            int columnsCount = getColumnsCountFrom(resultSet);
            while (resultSet.next()) {
                String columnsSeparator = " |";
                for (int i = 1; i <= columnsCount; i++) {
                    stringBuilder.append(resultSet.getString(i));
                    stringBuilder.append(columnsSeparator);
                }
                truncate(stringBuilder, columnsSeparator.length());
                stringBuilder.append(LINE_SEPARATOR);
            }

            System.out.println(stringBuilder);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void truncate(StringBuilder stringBuilder, int charsCount) {
        stringBuilder.delete(stringBuilder.length() - charsCount, stringBuilder.length());
    }

    private void insertIntoUser(String tableName, String name, String password) {

        String sqlQuery = String.format("INSERT INTO " + tableNameFormatted(tableName) + "(name, password) VALUES('%s','%s') ", name, password);
        printStatusMsg("Inserting into", tableName);
        int affectedRows = executeQuery(sqlQuery);
        printMsgAffectedRows(affectedRows);
    }

    private void printMsgAffectedRows(int affectedRows) {
        if (affectedRows < 0) {
            System.out.println("The query ended unsuccessfully, 0 rows affected");
            System.out.println();
        } else {
            System.out.println("The query ended successfully, " + affectedRows + " row(s) affected");
            System.out.println();
        }
    }

    private int executeQuery(String sqlQuery) {
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


    private int getColumnsCountFrom(ResultSet resultSet) {
        try {
            ResultSetMetaData rsmd = resultSet.getMetaData();
            return rsmd.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private void createTableUser(String tableName) {
        String sqlQuery = "CREATE TABLE IF NOT EXISTS " + tableNameFormatted(tableName) +
                "(" +
                "id SERIAL PRIMARY KEY," +
                "name text," +
                "password text" +
                ")";
        printStatusMsg("Creating", tableName);
        int affectedRows = executeQuery(sqlQuery);
        printMsgAffectedRows(affectedRows);
    }


    private void printTableList() {
        try {
            printStatusMsg("Printing", "list");
            DatabaseMetaData md = connection.getMetaData();
            ResultSet resultSet = md.getTables(null, "public", "%", new String[]{"TABLE"});

            if (!resultSet.isBeforeFirst()) {
                System.out.println("db is empty");
            }

            while (resultSet.next()) {
                System.out.println(resultSet.getString(3));
            }

            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Connection getConnection() {
        if (connection == null) {
            try {
                return DriverManager.getConnection(
                        JDBC_PROTOCOLS + SQl_URL + DATABASE_NAME, USER_NAME,
                        USER_PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return connection;
        }
    }
}
