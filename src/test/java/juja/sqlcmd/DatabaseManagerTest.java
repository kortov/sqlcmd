package juja.sqlcmd;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DatabaseManagerTest {
    private static final String SCHEMA_PATTERN = "test";
    private static final String DB_NAME = "sqlcmd";
    private static final String DB_USER = "sqlcmd";
    private static final String DB_USER_PASSWORD = "sqlcmd";
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/";
    private static DatabaseManager databaseManager;
    private static Connection connection;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private PrintStream originalOut;
    private PrintStream originalErr;

    @BeforeClass
    public static void setTestingEnvironment() throws SQLException {
        databaseManager = new DatabaseManager();
        connection = DriverManager.getConnection(
                JDBC_URL + DB_NAME + "?currentschema" + SCHEMA_PATTERN, DB_USER, DB_USER_PASSWORD);
        recreateDbSchema();
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @Before
    public void setUpStreamsAndDbManager() {
        databaseManager = new DatabaseManager();
        databaseManager.setSchemaPattern(SCHEMA_PATTERN);
        databaseManager.connect(DB_NAME, DB_USER, DB_USER_PASSWORD);

        originalOut = System.out;
        originalErr = System.err;
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

    }

    @After
    public void cleanUpStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void ConnectWithAllValidParameters() {
        assertTrue(databaseManager.connect(DB_NAME, DB_USER, DB_USER_PASSWORD));
    }

    @Test
    public void ConnectWithInvalidPassword() {
        assertFalse(databaseManager.connect(DB_NAME, DB_USER, "wrongPass"));
    }

    @Test
    public void ConnectWithInvalidUserName() {
        assertFalse(databaseManager.connect(DB_NAME, "noSqlcmd", DB_USER_PASSWORD));
    }

    @Test
    public void ConnectWithInvalidDbName() {
        assertFalse(databaseManager.connect("doesNotExist", DB_USER, DB_USER_PASSWORD));
    }

    @Test
    public void InitDatabaseManagerWithInvalidJdbcDriver() {
        databaseManager = new DatabaseManager("noDriver", "jdbc:postgresql://localhost:5432/");
        assertFalse(databaseManager.connect(DB_NAME, DB_USER, DB_USER_PASSWORD));
    }

    @Test
    public void InitDatabaseManagerWithInvalidJdbcUrl() {
        databaseManager = new DatabaseManager("org.postgresql.Driver", "noJdbcUrl");
        assertFalse(databaseManager.connect(DB_NAME, DB_USER, DB_USER_PASSWORD));
    }

    @Test
    public void getTableNamesWith2tables() throws SQLException {
        executeSqlQuery("CREATE TABLE " + SCHEMA_PATTERN + ".table1()");
        executeSqlQuery("CREATE TABLE " + SCHEMA_PATTERN + ".table2()");
        String[] expectedArray = {"table1", "table2"};
        String[] actualArray = databaseManager.getTableNames();
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void getTableNamesReturnsArrayWith2Tables() throws SQLException {
        recreateDbSchema();
        String[] expectedArray = new String[]{};
        String[] actualArray = databaseManager.getTableNames();
        assertArrayEquals(expectedArray, actualArray);
    }

    private static void executeSqlQuery(String sqlQuery) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sqlQuery);
        }
    }

    private static void recreateDbSchema() throws SQLException {
        executeSqlQuery("DROP SCHEMA IF EXISTS " + SCHEMA_PATTERN + " CASCADE");
        executeSqlQuery("CREATE SCHEMA " + SCHEMA_PATTERN);
    }
}