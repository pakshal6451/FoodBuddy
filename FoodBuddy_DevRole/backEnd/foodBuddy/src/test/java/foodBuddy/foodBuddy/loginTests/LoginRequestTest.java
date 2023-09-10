package foodBuddy.foodBuddy.loginTests;

import foodBuddy.foodBuddy.appuser.LoginRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginRequestTest {

    @Test
    public void testGettersAndSetters() {
        LoginRequest loginRequest = new LoginRequest("password", "username");

        assertEquals("password", loginRequest.getPassword());
        assertEquals("username", loginRequest.getUsername());

        loginRequest.setPassword("new_password");
        loginRequest.setUsername("new_username");

        assertEquals("new_password", loginRequest.getPassword());
        assertEquals("new_username", loginRequest.getUsername());
    }

}
