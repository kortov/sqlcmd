package juja.sqlcmd.command;

import juja.sqlcmd.controller.Handler;
import juja.sqlcmd.view.View;

public class Help implements Command {
    @Override
    public void execute(String userInput, Handler handler) {
        View view = handler.getView();
        view.write("Список команд");
    }

    @Override
    public void executeWithoutConnection(String userInput, Handler handler) {
        execute(userInput, handler);
    }
}
