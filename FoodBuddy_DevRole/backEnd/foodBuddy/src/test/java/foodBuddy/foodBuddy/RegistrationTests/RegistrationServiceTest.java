package foodBuddy.foodBuddy.RegistrationTests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import foodBuddy.foodBuddy.appuser.AppUser;
import foodBuddy.foodBuddy.appuser.AppUserService;
import foodBuddy.foodBuddy.appuser.EmailValidator;
import foodBuddy.foodBuddy.appuser.RegistrationRequest;
import foodBuddy.foodBuddy.appuser.RegistrationService;
import org.junit.Before;
import org.junit.Test;
public class RegistrationServiceTest {

    private RegistrationService registrationService;
    private EmailValidator emailValidator;
    private AppUserService appUserService;

    @Before
    public void setUp() {
        emailValidator = mock(EmailValidator.class);
        appUserService = mock(AppUserService.class);
        registrationService = new RegistrationService(emailValidator, appUserService);
    }

    @Test
    public void register_ValidEmailAndUserDetails_SuccessfulSignUp() {
        // Arrange
        RegistrationRequest request = new RegistrationRequest("John", "Doe", "password", "john@example.com");
        when(emailValidator.test(request.getEmail())).thenReturn(true);
        when(appUserService.signUpUser(new AppUser(request.getFirstName(),
                request.getLastName(),
                request.getPassword(),
                request.getEmail()))).thenReturn("1234");

        // Act
        String userId = registrationService.register(request);

        // Assert
        assertEquals("1234", userId);
        verify(appUserService).signUpUser(new AppUser(request.getFirstName(),
                request.getLastName(),
                request.getPassword(),
                request.getEmail()));
    }

    @Test
    public void register_InvalidEmail_ThrowsIllegalStateException() {
        // Arrange
        RegistrationRequest request = new RegistrationRequest("John", "Doe", "password", "invalid-email");
        when(emailValidator.test(request.getEmail())).thenReturn(false);

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> registrationService.register(request));
        // remove the verify statement since signUpUser should not be called in this case
    }

}
