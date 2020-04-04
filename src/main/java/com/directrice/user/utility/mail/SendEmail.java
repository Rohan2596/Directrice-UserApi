package com.directrice.user.utility.mail;

import com.directrice.user.dto.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class SendEmail {



    private JavaMailSender javaMailSender;

    public SendEmail(JavaMailSender javaMailSender){
        this.javaMailSender=javaMailSender;
    }

    public void send(Email email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(email.getEmailFrom());
        mailMessage.setTo(email.getEmailTo());
        mailMessage.setSubject(email.getSubject());
        mailMessage.setText(email.getBody());
        javaMailSender.send(mailMessage);
    }

}
