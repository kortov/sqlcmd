package juja.sqlcmd.command;

import juja.sqlcmd.DatabaseManager;
import juja.sqlcmd.controller.Handler;
import juja.sqlcmd.view.View;

import java.sql.SQLException;

public class Exit implements Command {

    @Override
    public void execute(String userInput, Handler handler) {
        DatabaseManager databaseManager = handler.getDatabaseManager();
        View view = handler.getView();
        try {
            databaseManager.close();
        } catch (SQLException e) {
            view.write("Упс" + e.getMessage());
        }
        view.write("Пока!");
    }

    @Override
    public void executeWithoutConnection(String userInput, Handler handler) {
        View view = handler.getView();
        view.write("Привет и пока!");
    }
}
