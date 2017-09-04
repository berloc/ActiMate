package com.codecool.fittinder.service.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);


    @Autowired
    public JavaMailSender emailSender;

    @Override
    public void sendMessage(String to, String subject, String text) throws MessagingException {

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
        mimeMessage.setContent(text, "text/html");
        helper.setTo(to);
        helper.setSubject(subject);
        emailSender.send(mimeMessage);
        logger.info("{} email has been sent to {}", subject, to);
    }
}
