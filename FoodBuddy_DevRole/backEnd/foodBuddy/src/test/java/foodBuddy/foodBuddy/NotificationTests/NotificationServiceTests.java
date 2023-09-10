package foodBuddy.foodBuddy.NotificationTests;

import foodBuddy.foodBuddy.appuser.UserRepository;
import foodBuddy.foodBuddy.notification.NotificationService;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class NotificationServiceTests {
    @Mock
    private JavaMailSender mailSender;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));

    }


    @Test
    public void send_shouldSendEmailToRecipient() throws Exception {
        NotificationService notificationService = new NotificationService(mailSender, userRepository);
        String[] recipients = {"user1@example.com", "user2@example.com"};
        String itemName = "eggs";

        notificationService.send(recipients, itemName);

        MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true, "UTF-8");
        messageHelper.setFrom("foodbuddydal@gmail.com");
        messageHelper.setTo(recipients);
        messageHelper.setSubject("Action Required: Grocery Inventory Update");
        String expectedHtmlContent = "<html><body><p>Dear Group Users,</p>"
                + "<p>This email is to inform you that our grocery inventory is running short on <b>" + itemName + "</b>. To avoid any inconvenience or delay in meal preparation, we kindly request that you add <b>" + itemName + "</b> to our grocery list or purchase it at your earliest convenience.</p>"
                + "<p>Your prompt attention to this matter is appreciated. Thank you for your cooperation.</p>"
                + "<p>Best regards,<br>FoodBuddy App</p>"
                + "</body></html>";
        messageHelper.setText(expectedHtmlContent, true);
        verify(mailSender).send(messageHelper.getMimeMessage());
    }

    @Test
    public void fetchEmailAndSend_shouldFetchUsersAndSendEmailToRecipients() throws Exception {
        NotificationService notificationService = new NotificationService(mailSender, userRepository);
        String groupCode = "GROUP123";
        String itemName = "milk";
        String[] recipients = {"user1@example.com", "user2@example.com"};
        when(userRepository.findUsernames(groupCode)).thenReturn(Arrays.asList(recipients));

        notificationService.fetchEmailAndSend(groupCode, itemName);

        MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true, "UTF-8");
        messageHelper.setFrom("foodbuddydal@gmail.com");
        messageHelper.setTo(recipients);
        messageHelper.setSubject("Action Required: Grocery Inventory Update");
        String expectedHtmlContent = "<html><body><p>Dear Group Users,</p>"
                + "<p>This email is to inform you that our grocery inventory is running short on <b>" + itemName + "</b>. To avoid any inconvenience or delay in meal preparation, we kindly request that you add <b>" + itemName + "</b> to our grocery list or purchase it at your earliest convenience.</p>"
                + "<p>Your prompt attention to this matter is appreciated. Thank you for your cooperation.</p>"
                + "<p>Best regards,<br>FoodBuddy App</p>"
                + "</body></html>";
        messageHelper.setText(expectedHtmlContent, true);
        verify(mailSender).send(messageHelper.getMimeMessage());
    }
}
