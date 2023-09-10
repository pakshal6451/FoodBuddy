package foodBuddy.foodBuddy.groupManagement;

import lombok.*;

import java.util.List;

@EqualsAndHashCode
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewGroupUsersResponse {
    private List<ViewGroupUsers> groupUsersList;
    private String status;
    private String message;
}
