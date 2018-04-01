package juja.sqlcmd.command;

import juja.sqlcmd.controller.Handler;
import juja.sqlcmd.view.View;

public class Unsupported implements Command {

    @Override
    public void execute(String userInput, Handler handler) {
        View view = handler.getView();
        view.write("Такой команды не существует: " + userInput);
    }

    @Override
    public void executeWithoutConnection(String userInput, Handler handler) {
        execute(userInput, handler);
    }
}
