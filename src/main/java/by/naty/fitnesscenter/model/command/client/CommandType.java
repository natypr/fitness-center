package by.naty.fitnesscenter.model.command.client;

import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.LoginCommand;
import by.naty.fitnesscenter.model.command.LogoutCommand;
import by.naty.fitnesscenter.model.logic.ClientLogic;
import by.naty.fitnesscenter.model.logic.OrderLogic;
import by.naty.fitnesscenter.model.logic.TrainerLogic;
import by.naty.fitnesscenter.model.logic.UserLogic;

public enum CommandType {
    LOGIN(new LoginCommand(new UserLogic(), new ClientLogic(), new TrainerLogic(), new OrderLogic())),
    LOGOUT(new LogoutCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
