package foodBuddy.foodBuddy.appuser;

import lombok.*;

@EqualsAndHashCode
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String groupCode;
    private String username;
    private String status;
    private String message;
}
