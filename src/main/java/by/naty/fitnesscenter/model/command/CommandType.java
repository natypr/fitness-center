package by.naty.fitnesscenter.model.command;

import by.naty.fitnesscenter.model.command.impl.*;
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
    TRAINER_CABINET(new TrainerCabinetCommand(new ClientLogic(), new TrainerLogic())),
    CLIENT_UPDATE(new ClientUpdateCommand(new ClientLogic())),
    TRAINER_UPDATE(new TrainerUpdateCommand(new TrainerLogic())),
    ADMIN_BLOCK_CLIENT(new AdminBlockClientCommand(new ClientLogic())),
    ADMIN_BLOCK_TRAINER(new AdminBlockTrainerCommand(new TrainerLogic())),
    ORDER(new OrderCommand(new TrainerLogic(), new OrderLogic()));

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
