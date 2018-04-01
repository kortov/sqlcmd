package juja.sqlcmd.controller;

import juja.sqlcmd.DatabaseManager;
import juja.sqlcmd.command.Command;
import juja.sqlcmd.command.CommandType;
import juja.sqlcmd.view.View;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class Handler {
    private DatabaseManager databaseManager;
    private View view;
    private CommandFactory commandFactory;

    public Handler(DatabaseManager databaseManager, View view) {
        this.databaseManager = databaseManager;
        this.view = view;
        commandFactory = new CommandFactory();
    }

    public void handle(String userInput) {
        String commandLiteral = getFirstWord(userInput);
        Command command = getCommand(commandLiteral);

        if (hasConnection()) {
            command.execute(userInput, this);
        } else {
            command.executeWithoutConnection(userInput, this);
        }
    }

    private boolean hasConnection() {
        return databaseManager.isConnected();
    }

    private Command getCommand(String commandLiteral) {
        return commandFactory.getCommand(commandLiteral);
    }

    private String getFirstWord(String string) {
        String splitSeparator = "\\|";
        return string.split(splitSeparator)[0];
    }

    private class CommandFactory {
        private Map<String, Command> commandMap = new HashMap<>();

        {
            for (CommandType commandType : EnumSet.allOf(CommandType.class)) {
                commandMap.put(commandType.getName(), commandType.getCommand());
            }
        }

        Command getCommand(String commandLiteral) {
            Command command = commandMap.get(commandLiteral);
            if (command == null) {
                command = commandMap.get(CommandType.UNSUPPORTED.getName());
                return command;
            }
            return command;
        }
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public View getView() {
        return view;
    }
}
