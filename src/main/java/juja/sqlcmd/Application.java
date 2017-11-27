package juja.sqlcmd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Application {

    private static final String JDBC_POSTGRESQL_DRIVER = "org.postgresql.Driver";
    private static final String JDBC_PROTOCOL = "jdbc:postgresql://";
    private static final String SQL_URL = "localhost:5432/";
    private static final String DATABASE_NAME = "sqlcmd";
    private static final String USER_NAME = "sqlcmd";
    private static final String USER_PASSWORD = "sqlcmd";
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final String USER_TABLE_NAME = "\"user\"";
    private Connection connection = null;

    Application() throws SQLException, ClassNotFoundException {
        connection = getConnection();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new Application().simpleSQL();

    }

    public void simpleSQL() {
        printTableList();

        dropTableIfExists();
        printTableList();

        createTableUser();
        printTable(USER_TABLE_NAME);
        printTableList();

        insertIntoUserTable("user1", "password1");
        insertIntoUserTable("user2", "password2");
        insertIntoUserTable("user3", "password3");
        printTable(USER_TABLE_NAME);

        changePasswordInUserTable("user1", "password2");
        printTable(USER_TABLE_NAME);

        removeFromUserTable("user3");
        printTable(USER_TABLE_NAME);

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void printStatus(String operation, String subject) {
        System.out.println(operation + " table " + subject + "..");
    }

    private void dropTableIfExists() {
        String sqlQuery = "DROP TABLE IF EXISTS " + USER_TABLE_NAME;
        printStatus("Removing", USER_TABLE_NAME);
        int affectedRows = executeUpdateQuery(sqlQuery);
        printAffectedRows(affectedRows);
    }

    private void changePasswordInUserTable(String name, String password) {
        String sqlQuery = String.format("UPDATE " + USER_TABLE_NAME + " SET password = '%s'" + " WHERE name='%s'", password, name);
        printStatus("Updating", USER_TABLE_NAME);
        int affectedRows = executeUpdateQuery(sqlQuery);
        printAffectedRows(affectedRows);
    }

    private void removeFromUserTable(String name) {
        String sqlQuery = String.format("DELETE FROM " + USER_TABLE_NAME + " WHERE name='%s'", name);
        int affectedRows = executeUpdateQuery(sqlQuery);
        printAffectedRows(affectedRows);
    }

    private void printTable(String tableName) {
        String sqlQuery = String.format("SELECT * FROM %s order by %<s.id", USER_TABLE_NAME);
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            printStatus("Printing", tableName);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("table is empty" + LINE_SEPARATOR);
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
                stringBuilder.delete(stringBuilder.length() - columnsSeparator.length(), stringBuilder.length());
                stringBuilder.append(LINE_SEPARATOR);
            }
            System.out.println(stringBuilder);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertIntoUserTable(String name, String password) {
        String sqlQuery = String.format("INSERT INTO " + USER_TABLE_NAME + "(name, password) VALUES('%s','%s') ", name, password);
        printStatus("Inserting into", USER_TABLE_NAME);
        int affectedRows = executeUpdateQuery(sqlQuery);
        printAffectedRows(affectedRows);
    }

    private void printAffectedRows(int affectedRows) {
        if (affectedRows < 0) {
            System.out.println("The query ended unsuccessfully, 0 rows affected" + LINE_SEPARATOR);
        } else {
            System.out.println("The query ended successfully, " + affectedRows + " row(s) affected" + LINE_SEPARATOR);
        }
    }

    private int executeUpdateQuery(String sqlQuery) {
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            // gotcha
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

    private void createTableUser() {
        String sqlQuery = "CREATE TABLE IF NOT EXISTS " + USER_TABLE_NAME +
                "(" +
                "id SERIAL PRIMARY KEY," +
                "name text," +
                "password text" +
                ")";
        printStatus("Creating", USER_TABLE_NAME);
        int affectedRows = executeUpdateQuery(sqlQuery);
        printAffectedRows(affectedRows);
    }

    private void printTableList() {
        try (ResultSet rs = connection.getMetaData().getTables(null, "public", "%", new String[]{"TABLE"})) {
            printStatus("Printing", "list");

            if (!rs.isBeforeFirst()) {
                System.out.println("db is empty");
            }

            while (rs.next()) {
                System.out.println(rs.getString(3));
            }

            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection == null) {
            Class.forName(JDBC_POSTGRESQL_DRIVER);
            return DriverManager.getConnection(
                    JDBC_PROTOCOL + SQL_URL + DATABASE_NAME, USER_NAME,
                    USER_PASSWORD);
        } else {
            return connection;
        }
    }
}
