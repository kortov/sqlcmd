package juja.sqlcmd.command;

import juja.sqlcmd.controller.CommandHandler;
import juja.sqlcmd.view.View;

public class Help implements Command {
    @Override
    public void executeConnected(String userInput, CommandHandler commandHandler) {
        View view = commandHandler.getView();
        view.write("Список команд");
    }

    @Override
    public void executeDisconnected(String userInput, CommandHandler commandHandler) {
        executeConnected(userInput, commandHandler);
    }
}
