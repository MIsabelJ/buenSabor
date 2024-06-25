package com.entidades.buenSabor.utils.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailManager {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailWithAttachment(String to, String subject, String body, byte[] attachment, String filename) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body);

        helper.addAttachment(filename, new ByteArrayResource(attachment));

        mailSender.send(message);
    }

}
