package juja.sqlcmd;

import juja.sqlcmd.controller.MainController;
import juja.sqlcmd.model.DatabaseManager;
import juja.sqlcmd.model.InMemoryDatabaseManager;
import juja.sqlcmd.view.Console;
import juja.sqlcmd.view.View;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    private InputStream originalIn;
    private View view;
    private DatabaseManager databaseManager;
    private MainController mainController;

    @Before
    public void setUp() {
        originalOut = System.out;
        originalIn = System.in;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        view = new Console();
        databaseManager = new InMemoryDatabaseManager();
    }

    @After
    public void cleanUpStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void main() {
        String testLine = "exit";
        setInputStreamMessage(testLine);
        mainController = new MainController(databaseManager, view);
        String expected = "Привет, юзер!" + LINE_SEPARATOR +
                "Введи, пожалуйста, имя базы данных, имя пользователя и пароль в формате: connect|database|userName|password" + LINE_SEPARATOR +
                "Пока!" + LINE_SEPARATOR;
        mainController.run();
        assertEquals(expected, outputStream.toString());
    }

    private void setInputStreamMessage(String message) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(message.getBytes());
        view = new Console(inputStream, outputStream);
        System.setIn(inputStream);
    }
}