package by.naty.fitnesscenter.model.command;

import by.naty.fitnesscenter.model.command.impl.*;
import by.naty.fitnesscenter.model.logic.ClientLogic;
import by.naty.fitnesscenter.model.logic.OrderLogic;
import by.naty.fitnesscenter.model.logic.TrainerLogic;
import by.naty.fitnesscenter.model.logic.UserLogic;

public enum CommandType {
    LOCALE(new LocaleCommand(new UserLogic())),
    LOGIN(new LoginCommand(new UserLogic(), new ClientLogic(), new TrainerLogic())),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand()),
    CLIENT_CABINET(new ClientCabinetCommand(new ClientLogic(), new OrderLogic())),
    TRAINER_CABINET(new TrainerCabinetCommand(new ClientLogic(), new TrainerLogic(), new OrderLogic())),
    CLIENT_UPDATE(new ClientUpdateCommand(new ClientLogic())),
    TRAINER_UPDATE(new TrainerUpdateCommand(new TrainerLogic())),
    ADMIN_BLOCK_USER(new AdminBlockUserCommand(new UserLogic())),
    ORDER(new OrderCommand(new ClientLogic(), new TrainerLogic(), new OrderLogic())),
    SHOW_TRAINER_LIST(new ShowTrainerListCommand(new TrainerLogic())),
    ORDER_PAYMENT(new OrderPaymentCommand(new ClientLogic(), new OrderLogic())),
    ADMIN_SHOW_INFO(new AdminShowInfoCommand(new ClientLogic(), new TrainerLogic()));

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
