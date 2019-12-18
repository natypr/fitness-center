package by.naty.fitnesscenter.model.command.impl;

import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.CommandRouter;
import by.naty.fitnesscenter.model.entity.User;
import by.naty.fitnesscenter.model.entity.UserType;
import by.naty.fitnesscenter.model.exception.CommandException;
import by.naty.fitnesscenter.model.exception.LogicException;
import by.naty.fitnesscenter.model.service.UserService;
import by.naty.fitnesscenter.model.manager.ConfigurationManager;
import by.naty.fitnesscenter.model.manager.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.*;

public class AdminBlockUserCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private UserService userService;

    public AdminBlockUserCommand(UserService userService) {
        this.userService = userService;
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
                    User currentUser = userService.findUserById(Long.parseLong(s));
                    LOG.info("Selected user: " + currentUser);
                    usersToProcess.add(currentUser);
                }
            } else {
                request.setAttribute(SELECT_USER_CHECKBOX, MessageManager.getProperty("messages.selectUserCheckbox"));
                page = ConfigurationManager.getProperty("path.page.admin.cabinet");
                return new CommandRouter(CommandRouter.DispatchType.FORWARD, page);
            }
            for (User user : usersToProcess) {
                if (actionAdminBlockUser != null) {
                    if (!user.getRole().equals(UserType.ADMIN.getTypeUser())) {
                        userService.blockUserById(user.getId());
                        LOG.info("Blocked user: " + user.getEmail());
                    } else {
                        request.setAttribute(
                                ADMIN_CANNOT_BE_BLOCKED, MessageManager.getProperty("messages.adminCannotBeBlocked"));
                        page = ConfigurationManager.getProperty("path.page.admin.cabinet");
                        return new CommandRouter(CommandRouter.DispatchType.FORWARD, page);
                    }
                }
                if (actionAdminUnblockUser != null) {
                    userService.unblockUserById(user.getId());
                    LOG.info("Unblocked user: " + user.getEmail());
                }
            }
            request.getSession().setAttribute(USERS, userService.findAllUsers());

            page = ConfigurationManager.getProperty("path.page.admin.cabinet");
            return new CommandRouter(CommandRouter.DispatchType.REDIRECT, page);
        } catch (LogicException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
