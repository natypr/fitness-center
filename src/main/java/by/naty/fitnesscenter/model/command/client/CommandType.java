package by.naty.fitnesscenter.model.command.client;

import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.LoginCommand;

public enum CommandType {
    LOGIN(new LoginCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
