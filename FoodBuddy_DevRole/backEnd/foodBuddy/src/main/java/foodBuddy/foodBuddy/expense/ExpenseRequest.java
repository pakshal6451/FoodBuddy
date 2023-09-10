package foodBuddy.foodBuddy.expense;

import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@NoArgsConstructor
public class ExpenseRequest {
    private String emailId;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
