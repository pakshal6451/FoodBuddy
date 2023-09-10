package foodBuddy.foodBuddy.loginTests;

import foodBuddy.foodBuddy.appuser.AppUser;
import foodBuddy.foodBuddy.appuser.AppUserService;
import foodBuddy.foodBuddy.appuser.LoginRequest;
import foodBuddy.foodBuddy.appuser.LoginResponse;
import foodBuddy.foodBuddy.appuser.LoginService;
import foodBuddy.foodBuddy.appuser.EmailValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class LoginServiceTest {
    private LoginService loginService;

    @Mock
    private EmailValidator emailValidator;
    @Mock
    private AppUserService appUserService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loginService = new LoginService(appUserService,emailValidator);
    }

    @Test
    void loginTestSuccess(){
        String userName="user@email.com";
        String password="password";
        String expectedMessage="success";
        LoginResponse appServiceResponse = new LoginResponse();
        appServiceResponse.setMessage("success");
        AppUser appUser =new AppUser(userName, password);
        when(appUserService.loginUser(appUser)).thenReturn(appServiceResponse);

        LoginRequest loginRequest = new LoginRequest(password,userName);
        LoginResponse response = loginService.login(loginRequest);

        assertEquals(expectedMessage,response.getMessage());
    }

    @Test
    void loginTestFailure(){
        String userName="user@email.com";
        String password="password";
        String expectedMessage="failure";
        LoginResponse appServiceResponse = new LoginResponse();
        appServiceResponse.setMessage("failure");
        AppUser appUser =new AppUser(userName, password);
        when(appUserService.loginUser(appUser)).thenReturn(appServiceResponse);

        LoginRequest loginRequest = new LoginRequest(password,userName);
        LoginResponse response = loginService.login(loginRequest);

        assertEquals(expectedMessage,response.getMessage());
    }

}
