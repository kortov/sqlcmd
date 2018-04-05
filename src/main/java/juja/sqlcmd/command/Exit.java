package juja.sqlcmd.command;

import juja.sqlcmd.DatabaseManager;
import juja.sqlcmd.controller.CommandHandler;
import juja.sqlcmd.view.View;

import java.sql.SQLException;

public class Exit implements Command {

    @Override
    public void executeConnected(String userInput, CommandHandler commandHandler) {
        DatabaseManager databaseManager = commandHandler.getDatabaseManager();
        View view = commandHandler.getView();
        try {
            databaseManager.close();
        } catch (SQLException e) {
            view.write("Упс" + e.getMessage());
        }
        view.write("Пока!");
    }

    @Override
    public void executeDisconnected(String userInput, CommandHandler commandHandler) {
        View view = commandHandler.getView();
        view.write("Привет и пока!");
    }
}
