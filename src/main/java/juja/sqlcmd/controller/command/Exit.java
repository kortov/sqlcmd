package juja.sqlcmd.controller.command;

import juja.sqlcmd.model.DatabaseManager;
import juja.sqlcmd.view.View;

import java.sql.SQLException;

public class Exit extends Command {

    Exit(DatabaseManager databaseManager, View view) {
        super(databaseManager, view);
    }

    @Override
    protected void executeConnected(String userInput) {
        try {
            databaseManager.close();
        } catch (SQLException e) {
            view.write("Упс " + e.getMessage());
        }
        view.write("Пока!");
    }

    @Override
    protected void executeDisconnected(String userInput) {
        view.write("Привет и пока!");
    }
}
