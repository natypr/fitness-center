package by.naty.fitnesscenter.controller;

import by.naty.fitnesscenter.model.mail.MailThread;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet("/jsp/mailServlet")
public class MailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Properties properties = new Properties();
        ServletContext context = getServletContext();
        String fileName = context.getInitParameter("mail");
        //loading mail server parameters into the properties object
        properties.load(context.getResourceAsStream(fileName));
        MailThread mailOperator = new MailThread(request.getParameter("to"),
                request.getParameter("subject"), request.getParameter("body"), properties);
        mailOperator.start();
        // go to the page with a proposal to create a new letter
        request.getRequestDispatcher("/jsp/util/sendResult.jsp").forward(request, response);
    }
}