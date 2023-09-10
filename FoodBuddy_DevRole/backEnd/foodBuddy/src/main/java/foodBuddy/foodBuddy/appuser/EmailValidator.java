package foodBuddy.foodBuddy.appuser;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class EmailValidator implements Predicate<String> {
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\."+
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";


    @Override
    public boolean test(String email) {
        //validate using regex
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        if (email == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        System.out.println("<><><><><><");
        System.out.println(matcher.matches());
        return matcher.matches();
    }

    public boolean validate(String email) {
        return test(email);
    }

    public Object isValid(String any) {
        return test(any);
    }
}
