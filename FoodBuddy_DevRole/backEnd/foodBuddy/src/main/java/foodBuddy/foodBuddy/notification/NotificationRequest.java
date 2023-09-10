package foodBuddy.foodBuddy.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class NotificationRequest {
    private String groupCode;
    private String itemName;

}
