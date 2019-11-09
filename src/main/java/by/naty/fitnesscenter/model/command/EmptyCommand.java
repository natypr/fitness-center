package by.naty.fitnesscenter.model.command;

import by.naty.fitnesscenter.model.logic.DefaultLogic;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {
    private DefaultLogic receiver = new DefaultLogic();

    @Override
    public CommandRF execute(HttpServletRequest request) {
        return new CommandRF(CommandRF.DispatchType.FORWARD, receiver.getPath());
    }
}
