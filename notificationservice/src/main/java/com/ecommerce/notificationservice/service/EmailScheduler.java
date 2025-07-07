//package com.ecommerce.notificationservice.service;
//
//import com.ecommerce.notificationservice.dto.EmailRequest;
//import com.ecommerce.notificationservice.service.EmailService;
//import jakarta.mail.MessagingException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
//
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.Date;
//
//@Component
//public class EmailScheduler {
//
//    private final ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
//
//    @Autowired
//    private EmailService emailService;
//
//    public EmailScheduler() {
//        taskScheduler.initialize();
//    }
//
//    public void scheduleEmail(EmailRequest request) {
//        LocalDateTime scheduleTime = request.getScheduleTime();
//        if (scheduleTime == null || scheduleTime.isBefore(LocalDateTime.now())) {
//            System.out.println("❌ Invalid or past schedule time.");
//            return;
//        }
//
//        Date triggerTime = Date.from(scheduleTime.atZone(ZoneId.systemDefault()).toInstant());
//
//        taskScheduler.schedule(() -> {
//            try {
//                emailService.sendHtmlEmail(
//                        request.getTo(),
//                        request.getSubject()
//                );
//                System.out.println("✅ Email sent at: " + scheduleTime);
//            } catch (MessagingException e) {
//                e.printStackTrace();
//            }
//        }, triggerTime);
//    }
//}
