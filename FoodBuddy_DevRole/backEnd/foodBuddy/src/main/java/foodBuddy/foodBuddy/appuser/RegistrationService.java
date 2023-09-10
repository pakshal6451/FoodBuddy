package foodBuddy.foodBuddy.appuser;

import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private EmailValidator emailValidator;
    private final AppUserService appUserService;

    public RegistrationService(EmailValidator emailValidator, AppUserService appUserService) {
        this.emailValidator = emailValidator;
        this.appUserService = appUserService;
    }

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail){
            throw new IllegalStateException("Email Not Valid");
        }
        return appUserService.signUpUser(new AppUser(request.getFirstName(),
                request.getLastName(),
                request.getPassword(),
                request.getEmail()));
    }


}
