package foodBuddy.foodBuddy.inventory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEntity {
    public InventoryEntity(String itemName, String expDate, int quantity, Double amount) {
        this.itemName = itemName;
        this.expDate = expDate;
        this.quantity = quantity;
        this.amount =amount;
    }

    public InventoryEntity(String itemName, String expDate, int quantity, String groupCode, Double amount) {
        this.itemName = itemName;
        this.expDate = expDate;
        this.quantity = quantity;
        this.groupCode = groupCode;
        this.amount= amount;
    }

    private String itemName;
    private String expDate;
    @SequenceGenerator(
            name = "item_sequence",
            sequenceName = "item_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_sequence"
    )
    private int itemId;

    private int quantity;

    private String groupCode;

    private Double amount;

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public Double getAmount() {
        return amount;
    }
}
