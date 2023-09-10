package foodBuddy.foodBuddy.inventory.invenotryResponses;

import foodBuddy.foodBuddy.groupManagement.ViewGroupUsers;
import foodBuddy.foodBuddy.inventory.ViewItems;
import lombok.*;

import java.util.List;
@EqualsAndHashCode
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewItemsResponse {
    private List<ViewItems> itemList;
    private String status;
    private String message;
}
