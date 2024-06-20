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
    private JavaMailSender emailSender;

    public void sendEmail(String to, byte[] pdfContent, String orderId) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("aig.development3@gmail.com");
        helper.setTo(to);
        helper.setSubject("Factura para tu pedido " + orderId);
        helper.setText("Adjunto encontrarás la factura para tu pedido reciente.");

        helper.addAttachment("factura.pdf", new ByteArrayResource(pdfContent));

        emailSender.send(message);
    }

}
