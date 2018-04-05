package juja.sqlcmd.command;

import juja.sqlcmd.DatabaseManager;
import juja.sqlcmd.controller.CommandHandler;
import juja.sqlcmd.view.View;

public class ConnectToDB implements Command {

    @Override
    public void executeConnected(String userInput, CommandHandler commandHandler) {
        View view = commandHandler.getView();
        view.write("Ты уже подключился");
    }

    @Override
    public void executeDisconnected(String userInput, CommandHandler commandHandler) {
        DatabaseManager databaseManager = commandHandler.getDatabaseManager();
        View view = commandHandler.getView();
        String[] connectionData = userInput.split("\\|");
        int dbIndex = 1;
        int userNameIndex = 2;
        int userPasswordIndex = 3;
        String dbName = connectionData[dbIndex];
        String userName = connectionData[userNameIndex];
        String password = connectionData[userPasswordIndex];
        databaseManager.connect(dbName, userName, password);
        view.write("Успешно Подключился");
    }
}
