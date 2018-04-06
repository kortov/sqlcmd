package juja.sqlcmd.command;

public class Unsupported extends Command {

    @Override
    public void executeConnected(String userInput) {
        view.write("Такой команды не существует: " + userInput);
    }

    @Override
    public void executeDisconnected(String userInput) {
        executeConnected(userInput);
    }
}
