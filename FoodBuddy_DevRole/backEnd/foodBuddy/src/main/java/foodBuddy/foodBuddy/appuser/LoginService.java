package foodBuddy.foodBuddy.appuser;

import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


@Service
public class LoginService {
    private EmailValidator emailValidator;
    private AppUserService appUserService;


    public LoginService(AppUserService appUserService, EmailValidator emailValidator) {
        this.appUserService = appUserService;
        this.emailValidator = emailValidator;
    }
    public LoginResponse login(LoginRequest request) {
        LoginResponse response = new LoginResponse();
        try {
            InternetAddress internetAddress = new InternetAddress(request.getUsername());
            internetAddress.validate();
            AppUser appUser =new AppUser(request.getUsername(), request.getPassword());
            response = appUserService.loginUser(appUser);
            return response;
        } catch (AddressException e) {
            // invalid email address
            response.setStatus("failure");
            response.setMessage(e.toString());
            response.setUsername(null);
            response.setGroupCode(null);
            return  response;
        }
    }

}
