package by.naty.fitnesscenter.controller;

import by.naty.fitnesscenter.model.mail.MailThread;
import by.naty.fitnesscenter.model.resource.ConfigurationManager;
import by.naty.fitnesscenter.model.resource.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

import static by.naty.fitnesscenter.model.constant.ConstantNameFromJsp.SUCCESSFULLY_UPDATED;

@WebServlet("/mailServlet")
public class MailServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger();

    private static final String MAIL = "mail";
    private static final String TO = "to";
    private static final String SUBJECT = "subject";
    private static final String BODY = "body";
    private static final String MAIL_SENT_SUCCESSFULLY = "mailSentSuccessfully";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Properties properties = new Properties();
        ServletContext context = getServletContext();
        String fileName = context.getInitParameter(MAIL);
        //loading mail server parameters into the properties object
        properties.load(context.getResourceAsStream(fileName));
        MailThread mailOperator = new MailThread(request.getParameter(TO),
                request.getParameter(SUBJECT), request.getParameter(BODY), properties);
        mailOperator.start();

        LOG.info("Message was sent by email.");
        request.setAttribute(MAIL_SENT_SUCCESSFULLY, MessageManager.getProperty("messages.mailsentsuccessfully"));
        // go to the page with a proposal to create a new letter
        String page = ConfigurationManager.getProperty("path.page.contacts");
        request.getRequestDispatcher(page).forward(request, response);
    }
}