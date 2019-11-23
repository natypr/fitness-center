package by.naty.fitnesscenter.model.command;

import by.naty.fitnesscenter.model.exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ActionFactory {
    private static final Logger LOG = LogManager.getLogger();

    public static Optional<Command> defineCommand(String commandName) throws CommandException {
        Optional<Command> current = Optional.empty();
        if (commandName == null) {
            return current;
        }

        try {
            CommandType commandType = CommandType.valueOf(commandName.toUpperCase());
            current = Optional.of(commandType.getCommand());
            LOG.debug("Command " + commandType.toString() + " received.");
        } catch (IllegalArgumentException e) {
            LOG.error("Unknown command. It doesn't exist in CommandType. ", e);
            throw new CommandException(e);
        }
        return current;
    }
}
