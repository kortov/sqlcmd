package juja.sqlcmd.controller.command;

import juja.sqlcmd.model.DatabaseManager;
import juja.sqlcmd.view.View;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.SQLException;

@RunWith(MockitoJUnitRunner.class)
public class ExitTest {

    @Mock
    private DatabaseManager databaseManager;

    @Mock
    private View view;

    private Command exit;


    @Before
    public void setup() {
        exit = new Exit(databaseManager, view);
    }

    @Test
    public void executeConnectedException() throws SQLException {
        Mockito.doThrow(new SQLException("mock sql exception")).when(databaseManager).close();
        exit.executeConnected("exit");
        Mockito.verify(view).write("Упс " + "mock sql exception");
    }

    @Test
    public void executeConnectedNormalPath() {
        exit.executeConnected("exit");
        Mockito.verify(view).write("Пока!");
    }

    @Test
    public void executeDisconnected() {
        exit.executeDisconnected("exit");
        Mockito.verify(view).write("Привет и пока!");
    }
}