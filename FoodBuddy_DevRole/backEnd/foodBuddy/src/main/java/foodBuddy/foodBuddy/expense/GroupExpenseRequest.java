package foodBuddy.foodBuddy.expense;

import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@NoArgsConstructor
public class GroupExpenseRequest {
    private String userGroupNumber;
}
