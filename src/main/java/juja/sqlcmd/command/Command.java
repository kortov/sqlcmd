package juja.sqlcmd.command;

import juja.sqlcmd.controller.Handler;

public interface Command {
    void execute(String userInput, Handler handler);

    void executeWithoutConnection(String userInput, Handler handler);
}
