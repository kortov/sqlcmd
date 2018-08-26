package juja.sqlcmd.controller;

import juja.sqlcmd.controller.command.Command;
import juja.sqlcmd.controller.command.CommandSupplier;
import juja.sqlcmd.controller.command.CommandType;
import juja.sqlcmd.controller.command.Executable;
import juja.sqlcmd.model.DatabaseManager;
import juja.sqlcmd.utils.Commands;
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
        Executable executable = getCommand(commandLiteral.trim().toLowerCase());
        executable.execute(userInput);
    }

    private Command getCommand(String commandLiteral) {
        return commandFactory.getCommand(commandLiteral);
    }

    private String getFirstWord(String string) {
        return Commands.splitArrayOf(string)[0].trim();
    }

    private class CommandFactory {
        private Map<String, Command> commandMap = new HashMap<>();

        {
            for (CommandType commandType : CommandType.values()) {
                CommandSupplier<? extends Command> commandSupplier = commandType.getCommandSupplier();
                Command command = createCommand(databaseManager, view, commandSupplier);
                command.prepareCommand(commandType);
                putCommandIntoMap(commandType, command);
            }

        }

        private void putCommandIntoMap(CommandType commandType, Command command) {
            commandMap.put(commandType.getName().toLowerCase(), command);
        }

        private <V extends Command> V createCommand(DatabaseManager databaseManager, View view,
                                                    CommandSupplier<V> commandSupplier) {
            return commandSupplier.get(databaseManager, view);
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
}
