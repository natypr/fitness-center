package by.naty.fitnesscenter.model.command.client;

import by.naty.fitnesscenter.model.command.*;
import by.naty.fitnesscenter.model.logic.ClientLogic;
import by.naty.fitnesscenter.model.logic.OrderLogic;
import by.naty.fitnesscenter.model.logic.TrainerLogic;
import by.naty.fitnesscenter.model.logic.UserLogic;

public enum CommandType {
    LOCALE(new LocaleCommand(new UserLogic())),
    LOGIN(new LoginCommand(new UserLogic(), new ClientLogic(), new TrainerLogic(), new OrderLogic())),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand(new UserLogic())),
    CLIENT_CABINET(new ClientCabinetCommand(new ClientLogic())),
    TRAINER_CABINET(new TrainerCabinetCommand(new ClientLogic(), new TrainerLogic()));

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
