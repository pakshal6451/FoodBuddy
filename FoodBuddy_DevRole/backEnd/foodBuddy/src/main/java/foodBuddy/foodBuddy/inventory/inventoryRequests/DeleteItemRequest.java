package foodBuddy.foodBuddy.inventory.inventoryRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class DeleteItemRequest {
    private final String itemName;
    private final String groupCode;

    private final Double amount;

    private final String emailId;
}