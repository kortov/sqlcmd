package juja.sqlcmd.controller;

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
public class MainControllerTest {

    @Mock
    private DatabaseManager databaseManager;

    @Mock
    private View view;

    private MainController controller;

    @Before
    public void setup() {
        controller = new MainController(databaseManager, view);
    }

    @Test
    public void run() {
        InOrder inOrder = Mockito.inOrder(view);
        Mockito.when(view.read()).thenReturn("exit");
        controller.run();
        inOrder.verify(view).write("Привет, юзер!");
        inOrder.verify(view).write("Введи, пожалуйста, имя базы данных, имя пользователя и пароль" +
                " в формате: connect|database|userName|password");
        Mockito.verify(view).write("Привет и пока!");
    }
}