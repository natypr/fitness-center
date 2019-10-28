package by.naty.fitnesscenter.model.command;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    //method returns a response page
    String execute(HttpServletRequest request);
}
