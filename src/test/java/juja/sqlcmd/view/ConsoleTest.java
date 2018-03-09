package juja.sqlcmd.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ConsoleTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private PrintStream originalOut;
    private PrintStream originalErr;
    private InputStream originalIn;
    private View console;

    @Before
    public void setUpStreams() {
        console = new Console();
        originalOut = System.out;
        originalErr = System.err;
        originalIn = System.in;
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        System.setIn(originalIn);
    }

    @Test
    public void writeEmptyLine() {
        String testLine = "";
        String expected = testLine + LINE_SEPARATOR;
        console.write(testLine);
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void writeOneLine() {
        String testLine = "the first line";
        String expected = testLine + LINE_SEPARATOR;
        console.write(testLine);
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void writeThreeLines() {
        String testLines = "the first line" + LINE_SEPARATOR +
                "the second line" + LINE_SEPARATOR +
                "the third line";
        String expected = testLines + LINE_SEPARATOR;
        console.write(testLines);
        assertEquals(expected, outContent.toString());
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void readEmptyLine() {
        String expected = "";
        ByteArrayInputStream in = new ByteArrayInputStream(expected.getBytes());
        System.setIn(in);
        String actual = console.read();
        assertEquals(expected, actual);
    }

    @Test
    public void readOneLine() {
        String expected = "test input from console";
        ByteArrayInputStream in = new ByteArrayInputStream(expected.getBytes());
        System.setIn(in);
        String actual = console.read();
        assertEquals(expected, actual);
    }

    @Test
    public void readTwoLinesReadsOnlyFirstLine() {
        String firstInputLine = "the first line from console";
        String expected = firstInputLine + LINE_SEPARATOR +
                "the second line from console";
        ByteArrayInputStream in = new ByteArrayInputStream(expected.getBytes());
        System.setIn(in);
        String actual = console.read();
        assertEquals(firstInputLine, actual);
    }
}