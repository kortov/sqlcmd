package juja.sqlcmd.controller.command;

import juja.sqlcmd.model.DatabaseManager;
import juja.sqlcmd.view.View;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UnsupportedTest {
    @Mock
    private DatabaseManager databaseManager;

    @Mock
    private View view;

    private Command unsupported;


    @Before
    public void setup() {
        unsupported = new Unsupported(databaseManager, view);
    }

    @Test
    public void executeConnected() {
        unsupported.executeConnected("bla");
        Mockito.verify(view).write("Такой команды не существует: bla");
    }

    @Test
    public void executeDisconnected() {
        unsupported.executeDisconnected("bla");
        Mockito.verify(view).write("Такой команды не существует: bla");
    }
}