package foodBuddy.foodBuddy.groupManagement;

import lombok.*;


@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class GroupCreationRequest {
	private final String groupName;
	private final String groupCode;
	private final String userName;
}
