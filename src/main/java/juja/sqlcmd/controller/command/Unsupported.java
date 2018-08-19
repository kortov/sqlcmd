package juja.sqlcmd.controller.command;

import juja.sqlcmd.model.DatabaseManager;
import juja.sqlcmd.view.View;

public class Unsupported extends Command {

    Unsupported(DatabaseManager databaseManager, View view) {
        super(databaseManager, view);
    }

    @Override
    protected void executeConnected(String userInput) {
        view.write("Такой команды не существует: " + userInput);
    }

    @Override
    protected void executeDisconnected(String userInput) {
        executeConnected(userInput);
    }
}
