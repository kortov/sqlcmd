package juja.sqlcmd.controller;

import juja.sqlcmd.model.DatabaseManager;
import juja.sqlcmd.view.View;

public class MainController {
    private View view;
    private DatabaseManager databaseManager;

    public MainController(View view, DatabaseManager databaseManager) {
        this.view = view;
        this.databaseManager = databaseManager;
    }

    public void run() {
        CommandHandler commandHandler = new CommandHandler(databaseManager, view);
        view.write("Привет, юзер!");
        view.write("Введи, пожалуйста, имя базы данных, имя пользователя и пароль в формате: connect|database|userName|password");

        while (true) {
            String input = view.read();
            commandHandler.handleCommand(input);
            if (input.toLowerCase().equals("exit")) {
                break;
            }
            view.write("Введи команду (или help для помощи):");
        }
    }
}