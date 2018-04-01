package juja.sqlcmd.command;

public enum CommandType {
    CONNECT_TO_DB(
            "connect",
            new ConnectToDB()
    ),
    EXIT(
            "exit",
            new Exit()
    ),
    HELP(
            "help",
            new Help()
    ),
    UNSUPPORTED(
            "unsupported",
            new Unsupported()
    );

    private String name;
    private Command command;


    CommandType(String name, Command command) {
        this.name = name;
        this.command = command;
    }

    public String getName() {
        return name;
    }

    public Command getCommand() {
        return command;
    }
}
