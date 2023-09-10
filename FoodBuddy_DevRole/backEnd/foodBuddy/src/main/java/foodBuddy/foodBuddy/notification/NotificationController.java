package foodBuddy.foodBuddy.notification;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping(path = "/api/v1/notification")
@CrossOrigin
public class NotificationController {

    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @PostMapping("/notify")
    public String sendNotification(@RequestBody NotificationRequest request) {
        notificationService.fetchEmailAndSend(request.getGroupCode(),request.getItemName());
        return "success";
    }

}
