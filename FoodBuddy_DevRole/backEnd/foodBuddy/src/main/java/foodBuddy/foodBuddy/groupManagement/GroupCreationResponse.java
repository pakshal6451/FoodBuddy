package foodBuddy.foodBuddy.groupManagement;

import lombok.*;

@EqualsAndHashCode
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupCreationResponse {
    private String status;
    private String message;

    private String groupCode;
}
