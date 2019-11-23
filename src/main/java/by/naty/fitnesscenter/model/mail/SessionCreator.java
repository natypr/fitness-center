package by.naty.fitnesscenter.model.mail;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

class SessionCreator {

    private static final String TRUE = "true";
    private static final String FALSE = "false";
    private static final String CLASS_SOCKET_FACTORY = "javax.net.ssl.SSLSocketFactory";

    private String userName;
    private String userPassword;
    private Properties sessionProperties;

    SessionCreator(Properties configProperties) {
        String smtpHost = configProperties.getProperty("mail.smtp.host");
        String smtpPort = configProperties.getProperty("mail.smtp.port");
        userName = configProperties.getProperty("mail.user.name");
        userPassword = configProperties.getProperty("mail.user.password");

        // load mail server parameters into the properties of the mail session
        sessionProperties = new Properties();

        sessionProperties.setProperty("mail.transport.protocol", "smtp");
        sessionProperties.setProperty("mail.host", smtpHost);

        sessionProperties.put("mail.smtp.auth", TRUE);
        sessionProperties.put("mail.smtp.port", smtpPort);
        sessionProperties.put("mail.smtp.host", smtpHost);
        sessionProperties.put("mail.smtp.name", userName);
        sessionProperties.put("mail.smtp.socketFactory.port", smtpPort);
        sessionProperties.put("mail.smtp.socketFactory.class", CLASS_SOCKET_FACTORY);
        sessionProperties.put("mail.smtp.socketFactory.fallback", FALSE);
        sessionProperties.setProperty("mail.smtp.quitwait", FALSE);
    }

    Session createSession() {
        return Session.getDefaultInstance(sessionProperties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, userPassword);
            }
        });
    }
}