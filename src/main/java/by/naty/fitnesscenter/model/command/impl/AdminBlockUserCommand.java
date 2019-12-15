package by.naty.fitnesscenter.model.command.impl;

import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.CommandRouter;
import by.naty.fitnesscenter.model.entity.User;
import by.naty.fitnesscenter.model.entity.UserType;
import by.naty.fitnesscenter.model.exception.CommandException;
import by.naty.fitnesscenter.model.exception.LogicException;
import by.naty.fitnesscenter.model.logic.UserLogic;
import by.naty.fitnesscenter.model.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.*;

public class AdminBlockUserCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private UserLogic userLogic;

    public AdminBlockUserCommand(UserLogic userLogic) {
        this.userLogic = userLogic;
    }

    @Override
    public CommandRouter execute(HttpServletRequest request) throws CommandException {
        String[] checkboxUser = request.getParameterValues(SELECT_USER);
        String actionAdminBlockUser = request.getParameter(BUTTON_ADMIN_BLOCK_USER);
        String actionAdminUnblockUser = request.getParameter(BUTTON_ADMIN_UNBLOCK_USER);
        ArrayList<User> usersToProcess = new ArrayList<>();

        String page;
        try {
            if (checkboxUser != null) {
                for (String s : checkboxUser) {
                    User currentUser = userLogic.findUserById(Long.parseLong(s));
                    LOG.info("Selected user: " + currentUser);
                    usersToProcess.add(currentUser);
                }
            }
            for (User user : usersToProcess) {
                if (actionAdminBlockUser != null) {
                    if (!user.getRole().equals(UserType.ADMIN.getTypeUser())) {
                        userLogic.blockUserById(user.getId());
                        LOG.info("Blocked user: " + user.getEmail());
                    }
                }
                if (actionAdminUnblockUser != null) {
                    userLogic.unblockUserById(user.getId());
                    LOG.info("Unblocked user: " + user.getEmail());
                }
            }
            request.getSession().setAttribute(USERS, userLogic.findAllUsers());

            page = ConfigurationManager.getProperty("path.page.admin.cabinet");
            return new CommandRouter(CommandRouter.DispatchType.REDIRECT, page);
        } catch (LogicException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
