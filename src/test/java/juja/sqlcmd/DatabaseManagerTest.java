package juja.sqlcmd;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DatabaseManagerTest {
    private DatabaseManager databaseManager;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private PrintStream originalOut;
    private PrintStream originalErr;

    @Before
    public void setUpStreamsAndDbManager() {
        originalOut = System.out;
        originalErr = System.err;
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        databaseManager = new DatabaseManager();
    }

    @After
    public void cleanUpStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testConnectWithAllValidParameters() {
        assertTrue(databaseManager.connect("sqlcmd", "sqlcmd", "sqlcmd"));
    }

    @Test
    public void testConnectWithInvalidPassword() {
        assertFalse(databaseManager.connect("sqlcmd", "sqlcmd", "wrongPass"));
    }

    @Test
    public void testConnectWithInvalidUserName() {
        assertFalse(databaseManager.connect("sqlcmd", "noSqlcmd", "sqlcmd"));
    }

    @Test
    public void testConnectWithInvalidDbName() {
        assertFalse(databaseManager.connect("doesNotExist", "sqlcmd", "sqlcmd"));
    }

    @Test
    public void testInitDatabaseManagerWithInvalidJdbcDriver() {
        databaseManager = new DatabaseManager("noDriver", "jdbc:postgresql://localhost:5432/");
        assertFalse(databaseManager.connect("sqlcmd", "sqlcmd", "sqlcmd"));
    }

    @Test
    public void testInitDatabaseManagerWithInvalidJdbcUrl() {
        databaseManager = new DatabaseManager("org.postgresql.Driver", "noJdbcUrl");
        assertFalse(databaseManager.connect("sqlcmd", "sqlcmd", "sqlcmd"));
    }
}