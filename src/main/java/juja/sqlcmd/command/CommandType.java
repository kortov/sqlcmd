package juja.sqlcmd.command;

import java.util.function.Supplier;

public enum CommandType {
    CONNECT_TO_DB(
            "connect",
            ConnectToDB::new
    ),
    EXIT(
            "exit",
            Exit::new
    ),
    HELP(
            "help",
            Help::new
    ),
    UNSUPPORTED(
            "unsupported",
            Unsupported::new
    );

    private String name;
    private Supplier<Command> instantiator;

    CommandType(String name, Supplier<Command> instantiator) {
        this.name = name;
        this.instantiator = instantiator;
    }

    public String getName() {
        return name;
    }

    public Command getInstance() {
        return instantiator.get();
    }
}
