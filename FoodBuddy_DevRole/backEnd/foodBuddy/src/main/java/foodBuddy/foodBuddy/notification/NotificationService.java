package foodBuddy.foodBuddy.notification;

import foodBuddy.foodBuddy.appuser.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements EmailSender {
    private final JavaMailSender mailSender;
    private final UserRepository userRepository;

    public NotificationService(JavaMailSender mailSender, UserRepository userRepository) {
        this.mailSender = mailSender;
        this.userRepository = userRepository;
    }
    @Override
    public String send(String[] toEmailsIds, String itemName) {
        try {
            String[] recipients = toEmailsIds;

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("foodbuddydal@gmail.com");
            helper.setTo(recipients);
            helper.setSubject("Action Required: Grocery Inventory Update");

            StringBuilder sb = new StringBuilder();
            sb.append("<html><body><p>Dear Group Users,</p>")
                    .append("<p>This email is to inform you that your grocery inventory is running short on <b>")
                    .append(itemName)
                    .append("</b>. To avoid any inconvenience or delay in meal preparation, we kindly request that you add <b>")
                    .append(itemName)
                    .append("</b> to your grocery list or purchase it at your earliest convenience.</p>")
                    .append("<p>Your prompt attention to this matter is appreciated. Thank you for your cooperation.</p>")
                    .append("<p>Best regards,<br>FoodBuddy App</p>")
                    .append("</body></html>");

            helper.setText(sb.toString(), true);
            mailSender.send(message);
            return "success";
        } catch (MessagingException me) {
            System.err.println(me.getMessage());
            return "failure";
        }
    }


    public String fetchEmailAndSend(String groupCode, String itemName) {
        String [] toEmails = userRepository.findUsernames(groupCode).toArray(new String[0]);
        return send(toEmails,itemName);
    }
}