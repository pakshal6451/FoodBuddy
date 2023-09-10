package foodBuddy.foodBuddy.appuser;

import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping(path = "/api/v1/login")
@AllArgsConstructor
@CrossOrigin
public class LoginController {
	private LoginService loginService;
    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest request){
        return loginService.login(request);
    }

}
