package foodBuddy.foodBuddy.expense;

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
public class userExpenses {

    private String emailId;
    private Double amount;

    @SequenceGenerator(
            name = "userExpense_sequence",
            sequenceName = "userExpense_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "userExpense_sequence"
    )
    private int itemId;

    public userExpenses(String emailId, Double amount) {
        this.amount = amount;
        this.emailId =emailId;

    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
