package foodBuddy.foodBuddy.expense;

import lombok.*;

import java.util.List;

@EqualsAndHashCode
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupExpenseResponse {
    private String status;
    private String message;
    private List<userExpenses> groupExpenses;

}
