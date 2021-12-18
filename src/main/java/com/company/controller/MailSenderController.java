package com.company.controller;

import com.company.model.MailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MailSenderController {

    @Autowired
    public JavaMailSender emailSender;

    @GetMapping("/send_mail")
    public String getMail(Model model) {
        model.addAttribute("mail", new MailModel());
        return "mail";
    }

    @PostMapping("/send_mail")
    public String sendMail(@ModelAttribute("mail") MailModel mail) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(mail.getRecipient());
        message.setSubject(mail.getSubject());
        message.setText(mail.getMessage());

        this.emailSender.send(message);

        //обнуление модели письма
        mail.setRecipient(null);
        mail.setMessage(null);
        mail.setSubject(null);

        return "mail";
    }
}
