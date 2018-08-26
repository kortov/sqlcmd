package juja.sqlcmd.controller.command;

import juja.sqlcmd.model.DatabaseManager;
import juja.sqlcmd.utils.Commands;
import juja.sqlcmd.view.View;

public abstract class Command implements Executable {
    protected DatabaseManager databaseManager;
    protected View view;
    private CommandType commandType;
    private int sizeOfSplitCommand;

    public Command(DatabaseManager databaseManager, View view) {
        this.databaseManager = databaseManager;
        this.view = view;
    }

    public void prepareCommand(CommandType commandType) {
        this.commandType = commandType;
        sizeOfSplitCommand = Commands.sizeOfSplitArray(commandType.getCommandPattern());
    }

    @Override
    public final void execute(String userInput) {
        if (!isCommandPatternValid(userInput)) {
            writeInvalidArgumentsMessage();
            return;
        }
        if (databaseManager.isConnected()) {
            executeConnected(userInput);
        } else {
            executeDisconnected(userInput);
        }
    }

    private boolean isCommandPatternValid(String userInput) {
        return sizeOfSplitCommand == Commands.sizeOfSplitArray(userInput);
    }


    private void writeInvalidArgumentsMessage() {
        view.write("Проверьте правильность ввода команды, команда должна быть вида:" + System.lineSeparator() +
                commandType);
    }


    protected abstract void executeConnected(String userInput);

    protected abstract void executeDisconnected(String userInput);
}
