package juja.sqlcmd.controller.command;

import juja.sqlcmd.model.DatabaseManager;
import juja.sqlcmd.view.View;

public class Help extends Command {
    Help(DatabaseManager databaseManager, View view) {
        super(databaseManager, view);
    }

    @Override
    protected void executeConnected(String userInput) {
        view.write("Список команд:");
        for (CommandType commandType : CommandType.values()) {
            view.write("\t" + commandType.getCommandPattern() + " - "
                    + commandType.getDescription());
        }
    }

    @Override
    protected void executeDisconnected(String userInput) {
        executeConnected(userInput);
    }
}
