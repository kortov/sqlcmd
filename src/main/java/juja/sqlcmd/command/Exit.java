package juja.sqlcmd.command;

import java.sql.SQLException;

public class Exit extends Command {

    @Override
    public void executeConnected(String userInput) {
        try {
            databaseManager.close();
        } catch (SQLException e) {
            view.write("Упс" + e.getMessage());
        }
        view.write("Пока!");
    }

    @Override
    public void executeDisconnected(String userInput) {
        view.write("Привет и пока!");
    }
}
