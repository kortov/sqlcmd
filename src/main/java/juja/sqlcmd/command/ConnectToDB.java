package juja.sqlcmd.command;

public class ConnectToDB extends Command {

    @Override
    public void executeConnected(String userInput) {
        view.write("Ты уже подключился");
    }

    @Override
    public void executeDisconnected(String userInput) {
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
