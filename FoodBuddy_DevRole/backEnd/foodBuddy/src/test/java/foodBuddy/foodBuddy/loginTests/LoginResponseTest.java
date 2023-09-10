package foodBuddy.foodBuddy.loginTests;

import foodBuddy.foodBuddy.appuser.LoginResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class LoginResponseTest {

    @Test
    void testConstructor() {
        LoginResponse response = new LoginResponse("ABC123", "johndoe", "success", "Welcome back, John!");
        assertEquals("ABC123", response.getGroupCode());
        assertEquals("johndoe", response.getUsername());
        assertEquals("success", response.getStatus());
        assertEquals("Welcome back, John!", response.getMessage());
    }

    @Test
    void testEqualsAndHashCode() {
        LoginResponse response1 = new LoginResponse("ABC123", "johndoe", "success", "Welcome back, John!");
        LoginResponse response2 = new LoginResponse("ABC123", "johndoe", "success", "Welcome back, John!");
        LoginResponse response3 = new LoginResponse("DEF456", "janedoe", "success", "Welcome back, Jane!");

        assertEquals(response1, response2);
        assertNotEquals(response1, response3);
    }

    @Test
    void testToString() {
        LoginResponse response = new LoginResponse("ABC123", "johndoe", "success", "Welcome back, John!");
        assertEquals("LoginResponse(groupCode=ABC123, username=johndoe, status=success, message=Welcome back, John!)", response.toString());
    }

    @Test
    void testGettersAndSetters() {
        LoginResponse response = new LoginResponse();
        response.setGroupCode("ABC123");
        response.setUsername("johndoe");
        response.setStatus("success");
        response.setMessage("Welcome back, John!");

        assertEquals("ABC123", response.getGroupCode());
        assertEquals("johndoe", response.getUsername());
        assertEquals("success", response.getStatus());
        assertEquals("Welcome back, John!", response.getMessage());
    }
}
