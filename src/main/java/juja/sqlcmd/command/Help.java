package juja.sqlcmd.command;

public class Help extends Command {
    @Override
    public void executeConnected(String userInput) {
        view.write("Список команд");
    }

    @Override
    public void executeDisconnected(String userInput) {
        executeConnected(userInput);
    }
}
