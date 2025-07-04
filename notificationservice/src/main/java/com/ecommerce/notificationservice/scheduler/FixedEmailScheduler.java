package com.ecommerce.notificationservice.scheduler;
import org.springframework.beans.factory.annotation.Value;
import com.ecommerce.notificationservice.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class FixedEmailScheduler {

    private final EmailService emailService;

    @Value("${admin.email}")
    private String adminEmail;

    public FixedEmailScheduler(EmailService emailService) {
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 0 8 * * ?")  // Every day at 8:00 AM
    public void sendDailyEmailToAdmin() {
        try {
            emailService.sendAdminReport(adminEmail, "🗂 Daily Admin Report");
            System.out.println("✅ Cron: Daily email sent to admin");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
