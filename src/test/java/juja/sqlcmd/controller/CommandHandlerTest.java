package juja.sqlcmd.controller;

import juja.sqlcmd.model.DatabaseManager;
import juja.sqlcmd.view.View;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommandHandlerTest {

    @Mock
    private DatabaseManager databaseManager;

    @Mock
    private View view;

    private CommandHandler commandHandler;

    @BeforeClass
    public static void init() {

    }

    @Before
    public void setup() {
        commandHandler = new CommandHandler(databaseManager, view);
    }

    @Test
    public void connectWhenDisconnected() {
        Mockito.when(databaseManager.connect("database", "userName", "password")).thenReturn(true);
        commandHandler.handleCommand("connect|database|userName|password");
        Mockito.verify(view).write("Подключено успешно");
    }

    @Test
    public void handleCommandWrongCommand() {
        Mockito.when(databaseManager.isConnected()).thenReturn(true);
        commandHandler.handleCommand("bla");
        Mockito.verify(view).write("Такой команды не существует: bla");
    }

    @Test
    public void handleCommandWrongNumberOfArguments() {
        Mockito.when(databaseManager.isConnected()).thenReturn(true);
        commandHandler.handleCommand("help|help");
        Mockito.verify(view).write("Проверьте правильность ввода команды, команда должна быть вида: help");
    }
}