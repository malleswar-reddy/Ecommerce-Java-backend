package com.ecommerce.controller;


import com.ecommerce.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notify")
public class NotificationController {

    @Autowired
    private EmailService emailService;


    @PostMapping("/plain")
    public String sendPlainEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
        emailService.sendSimpleEmail(to, subject, body);
        return "Plain email sent successfully!";
    }

    @PostMapping("/html")
    public String sendUserHtmlEmail(@RequestParam String to, @RequestParam String subject) {
        try {
            emailService.sendUserOrderConfirmation(to, subject);
            return "✅ Order confirmation sent to user";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "❌ Failed to send email to user";
        }
    }


}


















/*

@PostMapping("/html")
    public String sendHtmlEmail(@RequestParam String to, @RequestParam String subject) throws MessagingException {
        emailService.sendHtmlEmail(to, subject);
        return "HTML email sent successfully!";
 */