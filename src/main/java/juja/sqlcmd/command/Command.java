package juja.sqlcmd.command;

import juja.sqlcmd.controller.CommandHandler;

public interface Command {
    void executeConnected(String userInput, CommandHandler commandHandler);

    void executeDisconnected(String userInput, CommandHandler commandHandler);
}
