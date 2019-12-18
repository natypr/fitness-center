package by.naty.fitnesscenter.model.command;

import by.naty.fitnesscenter.model.command.impl.*;
import by.naty.fitnesscenter.model.service.ClientService;
import by.naty.fitnesscenter.model.service.OrderService;
import by.naty.fitnesscenter.model.service.TrainerService;
import by.naty.fitnesscenter.model.service.UserService;

public enum CommandType {
    LOCALE(new LocaleCommand(new UserService())),
    LOGIN(new LoginCommand(new UserService(), new ClientService(), new TrainerService())),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand()),
    CLIENT_CABINET(new ClientCabinetCommand(new ClientService(), new OrderService())),
    TRAINER_CABINET(new TrainerCabinetCommand(new ClientService(), new TrainerService(), new OrderService())),
    CLIENT_UPDATE(new ClientUpdateCommand(new ClientService())),
    TRAINER_UPDATE(new TrainerUpdateCommand(new TrainerService())),
    ADMIN_BLOCK_USER(new AdminBlockUserCommand(new UserService())),
    ORDER(new OrderCommand(new ClientService(), new TrainerService(), new OrderService())),
    SHOW_TRAINER_LIST(new ShowTrainerListCommand(new TrainerService())),
    ORDER_PAYMENT(new OrderPaymentCommand(new ClientService(), new OrderService())),
    ADMIN_SHOW_INFO(new AdminShowInfoCommand(new ClientService(), new TrainerService()));

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
