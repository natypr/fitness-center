package by.naty.fitnesscenter.model.mail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailThread extends Thread {
    private static final Logger LOG = LogManager.getLogger();

    private static final String TYPE_MAIL = "text/html; charset=utf-8";

    private MimeMessage message;
    private String sendToEmail;
    private String mailSubject;
    private String mailText;
    private Properties properties;

    public MailThread(String sendToEmail, String mailSubject, String mailText, Properties properties) {
        this.sendToEmail = sendToEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
        this.properties = properties;
    }

    private void init() {
        Session mailSession = (new SessionCreator(properties)).createSession();
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
        try {
            message.setSubject(mailSubject);
            message.setContent(mailText, TYPE_MAIL);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
        } catch (MessagingException e) {
            LOG.error("Error generating message", e);
        }
    }

    public void run() {
        init();
        try {
            LOG.info("Sending mail.");
            Transport.send(message);
        } catch (MessagingException e) {
            LOG.error("Error sending message", e);
        }
    }
}