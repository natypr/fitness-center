package by.naty.fitnesscenter.model.command.impl;

import by.naty.fitnesscenter.model.command.Command;
import by.naty.fitnesscenter.model.command.CommandRouter;
import by.naty.fitnesscenter.model.entity.User;
import by.naty.fitnesscenter.model.exception.CommandException;
import by.naty.fitnesscenter.model.exception.LogicException;
import by.naty.fitnesscenter.model.logic.UserLogic;
import by.naty.fitnesscenter.model.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.*;

public class AdminBlockClientCommand implements Command {
    private static final Logger LOG = LogManager.getLogger();

    private UserLogic userLogic;

    public AdminBlockClientCommand(UserLogic userLogic) {
        this.userLogic = userLogic;
    }

    @Override
    public CommandRouter execute(HttpServletRequest request) throws CommandException {
        String[] checkboxClient = request.getParameterValues(SELECT_CLIENT);
        String actionAdminBlockClient = request.getParameter(BUTTON_ADMIN_BLOCK_CLIENT);
        String actionAdminUnblockClient = request.getParameter(BUTTON_ADMIN_UNBLOCK_CLIENT);
        ArrayList<User> usersToProcess = new ArrayList<>();

        String page;    //LOOKME 4: блокировка user (пока таблицы клиентов)
        try {
            if (checkboxClient != null) {
                for (String s : checkboxClient) {
                    User currentUser = userLogic.findUserById(Long.parseLong(s));
                    LOG.info("Find client " + currentUser);
                    usersToProcess.add(currentUser);
                }
            }
            for (User user : usersToProcess) {
                if (actionAdminBlockClient != null) {
                    userLogic.blockUserById(user.getId());
                    LOG.info("Blocked client.");
                }
                if (actionAdminUnblockClient != null) {
                    userLogic.unblockUserById(user.getId());
                    LOG.info("Unblocked client.");
                }
            }
            request.getSession().setAttribute(CLIENTS, userLogic.findAllUsers());
            page = ConfigurationManager.getProperty("path.page.admin.cabinet");
            return new CommandRouter(CommandRouter.DispatchType.REDIRECT, page);
        } catch (LogicException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
