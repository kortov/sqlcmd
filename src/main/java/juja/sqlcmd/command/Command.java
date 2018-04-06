package juja.sqlcmd.command;

import juja.sqlcmd.DatabaseManager;
import juja.sqlcmd.view.View;

public abstract class Command implements Executable {
    protected DatabaseManager databaseManager;
    protected View view;

    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void execute(String userInput) {
        if (databaseManager.isConnected()) {
            executeConnected(userInput);
        } else {
            executeDisconnected(userInput);
        }
    }

    public abstract void executeConnected(String userInput);

    public abstract void executeDisconnected(String userInput);
}
