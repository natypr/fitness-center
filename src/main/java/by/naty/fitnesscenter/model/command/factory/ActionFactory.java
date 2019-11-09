package by.naty.fitnesscenter.model.command.factory;

import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.client.CommandType;
import by.naty.fitnesscenter.model.exception.CommandFCException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ActionFactory {
    private static final Logger LOG = LogManager.getLogger();

    public static Optional<Command> defineCommand(String commandName) throws CommandFCException {
        Optional<Command> current = Optional.empty();
        if(commandName == null){
            return current;
        }

        try {
            CommandType commandType = CommandType.valueOf(commandName.toUpperCase());
            current = Optional.of(commandType.getCommand());
        } catch (IllegalArgumentException e){
            LOG.error("Unknown command. It doesn't exist in CommandType. ", e);
            throw new CommandFCException(e);
        }
        return current;
    }
}
