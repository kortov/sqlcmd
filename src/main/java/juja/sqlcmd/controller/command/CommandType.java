package juja.sqlcmd.controller.command;

public enum CommandType {
    CONNECT_TO_DB(
            "connect",
            "connect|database|userName|password",
            ConnectToDB::new
    ),
    EXIT(
            "exit",
            "exit",
            Exit::new
    ),
    HELP(
            "help",
            "help",
            Help::new
    ),
    UNSUPPORTED(
            "unsupported",
            "unsupported",
            Unsupported::new
    );

    private String name;
    private String commandPattern;
    private CommandSupplier commandSupplier;

    <C extends Command> CommandType(String name, String commandPattern, CommandSupplier<C> commandSupplier) {
        this.name = name;
        this.commandPattern = commandPattern;
        this.commandSupplier = commandSupplier;
    }

    public String getName() {
        return name;
    }

    public String getCommandPattern() {
        return commandPattern;
    }

    public CommandSupplier getCommandSupplier() {
        return commandSupplier;
    }
}
