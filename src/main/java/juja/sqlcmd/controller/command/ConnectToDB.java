package juja.sqlcmd.controller.command;

import juja.sqlcmd.model.DatabaseManager;
import juja.sqlcmd.view.View;

public class ConnectToDB extends Command {

    ConnectToDB(DatabaseManager databaseManager, View view) {
        super(databaseManager, view);
    }

    @Override
    protected void executeConnected(String userInput) {
        view.write("Вы уже подключились к базе");
    }

    @Override
    protected void executeDisconnected(String userInput) {
        String[] connectionData = userInput.split("\\|");
        int dbIndex = 1;
        int userNameIndex = 2;
        int userPasswordIndex = 3;
        String dbName = connectionData[dbIndex];
        String userName = connectionData[userNameIndex];
        String password = connectionData[userPasswordIndex];
        boolean isConnected = databaseManager.connect(dbName, userName, password);
        if (isConnected) {
            view.write("Подключено успешно");
        } else {
            view.write("Произошла ошибка подключения");
        }
    }
}
