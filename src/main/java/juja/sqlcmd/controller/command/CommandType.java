package juja.sqlcmd.controller.command;

public enum CommandType {
    CONNECT_TO_DB(
            "connect",
            "connect|database|userName|password",
            ConnectToDB::new,
            "Подключиться к базе данных database"
    ),
    EXIT(
            "exit",
            "exit",
            Exit::new,
            "Выйти из программы"
    ),
    HELP(
            "help",
            "help",
            Help::new,
            "Помощь"
    ),
    UNSUPPORTED(
            "unsupported",
            "unsupported",
            Unsupported::new,
            "Любая команда, которой нет в списке команд"
    );

    private String name;
    private String commandPattern;
    private CommandSupplier<? extends Command> commandSupplier;
    private String description;

    <C extends Command> CommandType(String name, String commandPattern, CommandSupplier<C> commandSupplier, String description) {
        this.name = name;
        this.commandPattern = commandPattern;
        this.commandSupplier = commandSupplier;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getCommandPattern() {
        return commandPattern;
    }

    public String getDescription() {
        return description;
    }

    public CommandSupplier<? extends Command> getCommandSupplier() {
        return commandSupplier;
    }
}
