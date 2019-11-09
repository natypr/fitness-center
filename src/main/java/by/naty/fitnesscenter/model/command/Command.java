package by.naty.fitnesscenter.model.command;

import by.naty.fitnesscenter.model.exception.CommandFCException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    CommandRF execute(HttpServletRequest request) throws CommandFCException;
}
