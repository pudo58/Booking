package com.insmart.app.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class MailSender {
    public static void sendCode(String email,String text,String content) throws MessagingException, UnsupportedEncodingException {
        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage mailMessage;
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        mailMessage = new MimeMessage(getMailSession);
        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        mailMessage.setText(text);
        mailMessage.setSubject(content);
        Transport transport = getMailSession.getTransport("smtp");
        transport.connect("smtp.gmail.com", "lavantho0508@gmail.com", "zjipajndhlovrtvk");
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();
    }
}
