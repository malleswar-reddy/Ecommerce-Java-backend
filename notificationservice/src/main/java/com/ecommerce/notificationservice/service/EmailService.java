package com.ecommerce.notificationservice.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    @Value("${spring.mail.username}")
    private String fromAddress;

    @Autowired
    private JavaMailSender mailSender;

    // Send plain text email
    public void sendSimpleEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromAddress);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }




    public void sendUserOrderConfirmation(String to, String subject) throws MessagingException {
        String htmlBody = getUserHtmlTemplate();
        sendHtml(to, subject, htmlBody);
    }

    public void sendAdminReport(String to, String subject) throws MessagingException {
        String htmlBody = getAdminHtmlTemplate();
        sendHtml(to, subject, htmlBody);
    }

    private void sendHtml(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(fromAddress);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        mailSender.send(mimeMessage);
    }


    private String getUserHtmlTemplate() {
        return """
        <html>
          <body style="font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;">
            <div style="max-width: 600px; margin: auto; background-color: #fff; padding: 30px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1);">
              <h2 style="color: #4CAF50;">Order Confirmation</h2>
              <p>Hello <strong>Customer</strong>,</p>
              <p>Thank you for shopping with us. Your order has been placed successfully.</p>

              <h4>Order Details:</h4>
              <ul>
                <li><strong>Order ID:</strong> #123456</li>
                <li><strong>Date:</strong> 2025-07-04</li>
                <li><strong>Item:</strong> Wireless Headphones</li>
                <li><strong>Amount:</strong> ₹2,499</li>
              </ul>

              <p>You’ll receive another email once your order is shipped.</p>

              <hr style="margin-top: 30px;">
              <p style="font-size: 12px; color: #888;">This is an automated message. Please do not reply.</p>
            </div>
          </body>
        </html>
        """;
}


    private String getAdminHtmlTemplate() {
        return "<html>" +
                "<body>" +
                "<h2 style='color: navy;'>🗂 Daily Order Summary</h2>" +
                "<p>Hello <strong>Admin</strong>,</p>" +
                "<p>Here is today's order summary:</p>" +
                "<ul>" +
                "<li>Total Orders: 24</li>" +
                "<li>Total Revenue: ₹58,000</li>" +
                "</ul>" +
                "<p>Generated at: " + java.time.LocalDateTime.now() + "</p>" +
                "</body>" +
                "</html>";
    }

}


