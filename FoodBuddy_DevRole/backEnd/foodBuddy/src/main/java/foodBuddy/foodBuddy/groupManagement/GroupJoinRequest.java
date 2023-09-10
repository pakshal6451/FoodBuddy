package foodBuddy.foodBuddy.groupManagement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Data
@AllArgsConstructor
public class GroupJoinRequest {
    private String groupCode;
    private String userName;
}
