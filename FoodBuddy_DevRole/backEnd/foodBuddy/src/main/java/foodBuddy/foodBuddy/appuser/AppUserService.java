package foodBuddy.foodBuddy.appuser;

import foodBuddy.foodBuddy.constants.AppConstants;
import foodBuddy.foodBuddy.appuser.registration.token.ConfirmationToken;
import foodBuddy.foodBuddy.appuser.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ConfirmationTokenService confirmationTokenService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email));
        }
        return userOptional.get();
    }

    public String signUpUser(AppUser appUser) {
        boolean userExists = userRepository.findByEmail(appUser.getEmail()).isPresent();
        if (userExists) {
            return "User Exists";
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        userRepository.save(appUser);
        ConfirmationToken confirmationToken = generateConfirmationToken(appUser);
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return confirmationToken.getToken();
    }

    public static ConfirmationToken generateConfirmationToken(AppUser appUser) {
        String token = UUID.randomUUID().toString();
        LocalDateTime createdDate = LocalDateTime.now();
        LocalDateTime expirationDate = createdDate.plusMinutes(Integer.parseInt(AppConstants.TOKEN_EXPIRATION_MINUTES.getValue().toString()));
        return new ConfirmationToken(token, createdDate, expirationDate);

    }


    public LoginResponse loginUser(AppUser appUser) {
        LoginResponse response = new LoginResponse();
    	if(userRepository.findByEmail(appUser.getEmail()).isPresent()) {
    		String password = userRepository.findPasswordByEmail(appUser.getEmail());
            Boolean userPassword = (bCryptPasswordEncoder.matches(appUser.getPassword(),password));
            if(userPassword){
                response.setGroupCode(userRepository.findGroupByEmail(appUser.getEmail()));
                response.setUsername(appUser.getEmail());
                response.setStatus("success");
                response.setMessage("Login Successful");
    			return response;
    		} else {
                response.setGroupCode(null);
                response.setUsername(null);
                response.setStatus("failure");
                response.setMessage("Login failed");
                return response;
    		}
    	} else {
    		//throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));
            response.setGroupCode(null);
            response.setUsername(null);
            response.setStatus("failure");
            response.setMessage("Login failed");
            return response;
    	}
    	
    	
    }
}
