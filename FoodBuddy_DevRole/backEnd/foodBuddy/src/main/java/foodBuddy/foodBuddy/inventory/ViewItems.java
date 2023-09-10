package foodBuddy.foodBuddy.inventory;

import lombok.*;

@EqualsAndHashCode
@ToString
@Data
@AllArgsConstructor
public class ViewItems {
    private  String itemName;
    private String expDate;

    public ViewItems(String itemName) {
        this.itemName = itemName;
    }

    private int quantity;

    private double amount;
}
