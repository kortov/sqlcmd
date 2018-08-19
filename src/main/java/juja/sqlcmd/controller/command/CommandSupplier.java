package juja.sqlcmd.controller.command;

import juja.sqlcmd.model.DatabaseManager;
import juja.sqlcmd.view.View;

@FunctionalInterface
public interface CommandSupplier<C extends Command> {
    C get(DatabaseManager databaseManager, View view);
}
