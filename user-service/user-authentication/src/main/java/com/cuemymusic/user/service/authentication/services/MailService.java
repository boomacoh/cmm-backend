package com.cuemymusic.user.service.authentication.services;

import com.cuemymusic.user.service.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public SimpleMailMessage sendResetPasswordMail(String token, User user) {
        String url = "toke=" + token;
        String message = "If this is not intended, Please IGNORE IT";
        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    private SimpleMailMessage constructEmail(String subject,
                                             String body,
                                             User user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom("support@cuemymusic.com");
        mailSender.send(
                email
        );
        return email;
    }
}
