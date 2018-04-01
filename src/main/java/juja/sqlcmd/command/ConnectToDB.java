package juja.sqlcmd.command;

import juja.sqlcmd.DatabaseManager;
import juja.sqlcmd.controller.Handler;
import juja.sqlcmd.view.View;

public class ConnectToDB implements Command {

    @Override
    public void execute(String userInput, Handler handler) {
        View view = handler.getView();
        view.write("Ты уже подключился");
    }

    @Override
    public void executeWithoutConnection(String userInput, Handler handler) {
        DatabaseManager databaseManager = handler.getDatabaseManager();
        View view = handler.getView();
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
