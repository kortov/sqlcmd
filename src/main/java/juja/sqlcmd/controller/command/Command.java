package juja.sqlcmd.controller.command;

import juja.sqlcmd.model.DatabaseManager;
import juja.sqlcmd.utils.Commands;
import juja.sqlcmd.view.View;

import java.util.Optional;

public abstract class Command implements Executable {
    protected DatabaseManager databaseManager;
    protected View view;
    private Optional<CommandType> commandType;
    private Optional<Integer> sizeOfSplitCommand;

    public Command(DatabaseManager databaseManager, View view) {
        this.databaseManager = databaseManager;
        this.view = view;
    }

    public void setCommandType(Optional<CommandType> commandType) {
        this.commandType = commandType;
        sizeOfSplitCommand = Optional.of(
                Commands.sizeOfSplitArray(commandType.get().getCommandPattern()));
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
        return sizeOfSplitCommand.get() == Commands.sizeOfSplitArray(userInput);
    }


    private void writeInvalidArgumentsMessage() {
        view.write("Проверьте правильность ввода команды, команда должна быть вида:" + System.lineSeparator() +
                commandType.get());
    }


    protected abstract void executeConnected(String userInput);

    protected abstract void executeDisconnected(String userInput);
}
