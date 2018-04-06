package juja.sqlcmd.controller;

import juja.sqlcmd.DatabaseManager;
import juja.sqlcmd.command.Command;
import juja.sqlcmd.command.CommandType;
import juja.sqlcmd.command.Executable;
import juja.sqlcmd.view.View;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private DatabaseManager databaseManager;
    private View view;
    private CommandFactory commandFactory;

    public CommandHandler(DatabaseManager databaseManager, View view) {
        this.databaseManager = databaseManager;
        this.view = view;
        commandFactory = new CommandFactory();
    }

    public void handleCommand(String userInput) {
        String commandLiteral = getFirstWord(userInput);
        Executable executable = getCommand(commandLiteral.toLowerCase());
        executable.execute(userInput);
    }

    private Executable getCommand(String commandLiteral) {
        return commandFactory.getCommand(commandLiteral);
    }

    private String getFirstWord(String string) {
        String splitSeparator = "\\|";
        return string.split(splitSeparator)[0];
    }

    private class CommandFactory {
        private Map<String, Command> commandMap = new HashMap<>();

        {
            for (CommandType commandType : CommandType.values()) {
                Command command = commandType.getInstance();
                command.setDatabaseManager(databaseManager);
                command.setView(view);
                commandMap.put(commandType.getName().toLowerCase(), command);
            }
        }

        Executable getCommand(String commandLiteral) {
            Executable executable = commandMap.get(commandLiteral);
            if (executable == null) {
                executable = commandMap.get(CommandType.UNSUPPORTED.getName());
                return executable;
            }
            return executable;
        }
    }
}
