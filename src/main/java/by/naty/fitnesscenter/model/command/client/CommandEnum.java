package by.naty.fitnesscenter.model.command.client;

import by.naty.fitnesscenter.model.command.ActionCommand;
import by.naty.fitnesscenter.model.command.LoginCommand;
import by.naty.fitnesscenter.model.command.LogoutCommand;

public enum  CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    };

    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
