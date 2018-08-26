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
public class ConnectToDBTest {

    @Mock
    private DatabaseManager databaseManager;

    @Mock
    private View view;

    private Command connectDB;


    @Before
    public void setup() {
        connectDB = new ConnectToDB(databaseManager, view);
    }

    @Test
    public void executeConnected() {
        connectDB.executeConnected("any string");
        Mockito.verify(view).write("Вы уже подключились к базе");
    }

    @Test
    public void executeDisconnectedCorrectCredentials() {
        Mockito.when(databaseManager.connect("database", "userName", "password")).thenReturn(true);
        connectDB.executeDisconnected("connect|database|userName|password");
        Mockito.verify(view).write("Подключено успешно");
    }

    @Test
    public void executeDisconnectedWrongCredentials() {
        Mockito.when(databaseManager.connect("database", "anydb", "password")).thenReturn(false);
        connectDB.executeDisconnected("connect|anydb|userName|password");
        Mockito.verify(view).write("Произошла ошибка подключения");
    }
}