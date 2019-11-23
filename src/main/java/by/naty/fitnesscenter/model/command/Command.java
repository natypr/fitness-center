package by.naty.fitnesscenter.model.command;

import by.naty.fitnesscenter.model.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    CommandRouter execute(HttpServletRequest request) throws CommandException;
}
