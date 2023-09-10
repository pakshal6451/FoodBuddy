package foodBuddy.foodBuddy.appuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginRequest {
	    private  String password;
	    private  String username;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
