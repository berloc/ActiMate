package com.codecool.fittinder.service.email;


import javax.mail.MessagingException;

public interface EmailService {

    void sendMessage(String to, String subject, String text) throws MessagingException;

}
