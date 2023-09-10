package foodBuddy.foodBuddy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Optional;

import foodBuddy.foodBuddy.groupManagement.GroupRepository;
import foodBuddy.foodBuddy.appuser.registration.token.ConfirmationTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import foodBuddy.foodBuddy.appuser.AppUser;
import foodBuddy.foodBuddy.appuser.AppUserService;
import foodBuddy.foodBuddy.appuser.UserRepository;
import foodBuddy.foodBuddy.appuser.EmailValidator;
import foodBuddy.foodBuddy.appuser.registration.token.ConfirmationTokenRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


class FoodBuddyApplicationTests {

	private AppUserService appUserService;
	private ConfirmationTokenService confirmationTokenService;
	@InjectMocks
	private EmailValidator emailValidator;
	
	@Mock
	private UserRepository userRepository;

	@Mock
	private GroupRepository groupRepository;
	
	@Mock
	private ConfirmationTokenRepository confirmationTokenRepository;


	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		confirmationTokenService = new ConfirmationTokenService(confirmationTokenRepository);
		appUserService = new AppUserService(userRepository,bCryptPasswordEncoder,confirmationTokenService);
	}

	@Test
	void loadUserByUserNameTestWhenUserExist() {
		String email="timH@email.com";
		UserDetails userDetails = new AppUser("Tim","Hortons","myDemoPassword","timH@email.com");
		when(userRepository.findByEmail(email)).thenReturn(Optional.of(new AppUser("Tim","Hortons","myDemoPassword","timH@email.com")));
		assertEquals(userDetails,appUserService.loadUserByUsername(email));
	}

	@Test
	void loadUserByNameTestWhenUserDoesNotExist() {
		String email="timH@email.com";
		Optional<AppUser> empty = Optional.empty();
		when(userRepository.findByEmail(email)).thenReturn(empty);
		assertThrows(UsernameNotFoundException.class,() -> appUserService.loadUserByUsername(email));
	}

	@Test
	void userLoginSuccessTest() {
		String email="timH@email.com";
		AppUser user = new AppUser("Tim","Hortons","myDemoPassword","timH@email.com");
		user.setEmail(email);
		when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
		when(userRepository.findPasswordByEmail(email)).thenReturn("myDemoPassword");
		when(bCryptPasswordEncoder.matches("myDemoPassword","myDemoPassword")).thenReturn(true);
		assertEquals("Login Successful",appUserService.loginUser(user).getMessage());
	}

	@Test
	void userNotExistingLoginFailureTest() {
		String email="timH@email.com";
		Optional<AppUser> empty = Optional.empty();
		when(userRepository.findByEmail(email)).thenReturn(empty);
		when(userRepository.findPasswordByEmail(email)).thenReturn("myDemoPassword");
		AppUser user = new AppUser("Tim","Hortons","myDemoPassword","timH@email.com");
		assertEquals("Login failed",appUserService.loginUser(user).getMessage());
	}

	@Test
	void userPasswordMismatchLoginFailureTest() {
		String email="timH@email.com";
		when(userRepository.findByEmail(email)).thenReturn(Optional.of(new AppUser("Tim","Hortons","myDemoPassword","timH@email.com")));
		when(userRepository.findPasswordByEmail(email)).thenReturn("myDemoPassword");
		AppUser user = new AppUser("Tim","Hortons","IncorrectPassword","timH@email.com");
		assertEquals("Login failed",appUserService.loginUser(user).getMessage());
	}

	@Test
	void emailValidatorTest() {
		String email = "timH@email.com";
		assertTrue(emailValidator.test(email));
	}

	@Test
    void loadUserByUserNameTestWhenUserIsNull() {
    String email = "nullUser@example.com";
	Optional<AppUser> empty = Optional.empty();
    when(userRepository.findByEmail(email)).thenReturn(empty);
    assertThrows(UsernameNotFoundException.class, () -> appUserService.loadUserByUsername(email));
}

	@Test
	void userRegistrationSuccess() {
		String fName="userFname";
		String lName="userLname";
		String password="mypass";
    String emailId="user@email.com";
	AppUser user = new AppUser(fName,lName,password,emailId);
	when(userRepository.findByEmail(emailId)).thenReturn(Optional.empty());
	String resultToke = appUserService.signUpUser(user);
	verify(userRepository, atLeastOnce()).save(user);

}

	@Test
	void userRegistrationWithExistingEmailTest() {
    String email = "existingUser@example.com";
    AppUser existingUser = new AppUser("Existing", "User", "password", email);
	when(userRepository.findByEmail(email)).thenReturn(Optional.of(existingUser));
    assertEquals("User Exists", appUserService.signUpUser(existingUser));
}
}
