package com.oilseller.oilbrocker.email.service.impl;

import com.oilseller.oilbrocker.email.dto.EmailParam;
import com.oilseller.oilbrocker.email.service.EmailService;
import com.oilseller.oilbrocker.platform.exception.ServiceRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.Properties;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

    private Environment environment;

    @Autowired
    public EmailServiceImpl(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void init() {
        senderEmail = environment.getRequiredProperty("email.sender.address");
        senderPassword = environment.getRequiredProperty("email.sender.password");
    }

    private static String senderEmail;
    private static String senderPassword;

    @Override
    public Boolean sendEmail(EmailParam emailParam) {
        Properties props = getProperties();

        try {
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            Message emailMessage = buildEmailMessage(emailParam.getReceiverAddress(), emailParam.getSubject(), emailParam.getContent(),
                    session);
            Transport.send(emailMessage);
        } catch (Exception ex) {
            log.error("Unable to send email to {}, Error occured while sending email {}", emailParam.getReceiverAddress(), ex.getMessage());
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    private Message buildEmailMessage(String receiverEmail, String emailSubject, String emailBody, Session session) throws MessagingException {
        Message msg = new MimeMessage(session);
        msg.setText(emailBody);
        msg.setSubject(emailSubject);
        msg.setFrom(new InternetAddress(senderEmail));
        msg.addRecipient(Message.RecipientType.TO,
                new InternetAddress(receiverEmail));
        return msg;
    }

    private Properties getProperties() {

        Properties props = new Properties();
        props.put("mail.smtp.user", senderEmail);
        props.put("mail.smtp.host", environment.getRequiredProperty("email.smtp.server"));
        props.put("mail.smtp.port", environment.getRequiredProperty("email.server.port"));
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.user", "true");
        props.put("mail.smtp.socketFactory.port", environment.getRequiredProperty("email.server.port"));
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        return props;
    }

    private static class SMTPAuthenticator extends javax.mail.Authenticator {

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(senderEmail, senderPassword);
        }
    }
}
