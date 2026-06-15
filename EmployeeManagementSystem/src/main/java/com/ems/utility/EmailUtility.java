package com.ems.utility;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailUtility {

    private static final String FROM_EMAIL = "shaniashamshi@gmail.com";
    private static final String PASSWORD = "ehbx djua rnzy mhrj";

    public static void sendEmail(String toEmail, String subject, String msg) {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));

            message.setSubject(subject);
            message.setText(msg);

            Transport.send(message);

            System.out.println("Email sent successfully ✔");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}