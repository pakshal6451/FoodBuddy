package IntegrationTesting;

import foodBuddy.foodBuddy.appuser.*;
import foodBuddy.foodBuddy.appuser.registration.token.ConfirmationTokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = {foodBuddy.foodBuddy.FoodBuddyApplication.class})
@ComponentScan(basePackages = {"src/main/java/foodBuddy.foodBuddy.appuser"})
public class LoginApiIntegrationTest {
    @Autowired
    LoginController controller;
    @Autowired
    LoginService service;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;
    @Test
    public void loginApiIntegrationTestSuccess(){
        String password="pass";
        String userName="sujahidms@gmail.com";
        LoginRequest request = new LoginRequest(password,userName);
        LoginResponse response = controller.login(request);
        assertEquals("Login Successful",response.getMessage());
    }

    @Test
    public void loginApiIntegrationTestFailure(){
        String password="password";
        String userName="sujahidms@gmail.com";
        LoginRequest request = new LoginRequest(password,userName);
        LoginResponse response = controller.login(request);
        assertEquals("Login failed",response.getMessage());
    }

}

