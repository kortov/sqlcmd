package juja.sqlcmd.controller.command;

import juja.sqlcmd.model.DatabaseManager;
import juja.sqlcmd.view.View;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HelpTest {
    @Mock
    private DatabaseManager databaseManager;

    @Mock
    private View view;

    private Command help;


    @Before
    public void setup() {
        help = new Help(databaseManager, view);
    }

    @Test
    public void executeConnected() {
        help.executeConnected("help");
        Mockito.verify(view).write("Список команд:");
    }

    @Test
    public void executeDisconnected() {
        help.executeDisconnected("exit");
        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view).write("Список команд:");
        inOrder.verify(view).write("\tconnect|database|userName|password - Подключиться к базе данных database");
    }
}