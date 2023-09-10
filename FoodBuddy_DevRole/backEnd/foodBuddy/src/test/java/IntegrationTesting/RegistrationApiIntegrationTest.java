package IntegrationTesting;

import foodBuddy.foodBuddy.appuser.*;
import foodBuddy.foodBuddy.appuser.registration.token.ConfirmationTokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = {foodBuddy.foodBuddy.FoodBuddyApplication.class})
@ComponentScan(basePackages = {"src/main/java/foodBuddy.foodBuddy.appuser"})
public class RegistrationApiIntegrationTest {
    @Autowired
    RegistrationController controller;
    @Autowired
    RegistrationService service;
    @Autowired
    AppUserService appUserService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;
    @Test
    public void registrationApiIntegrationTestSuccess(){
        String firstName="Lalith";
        String lastName="Sai";
        String password="password";
        String userName="lalithmaringa@gmail.com";
        RegistrationRequest request = new RegistrationRequest(firstName,lastName,password,userName);
        String response = controller.register(request);
        assertEquals("User Exists",response);
    }

}
