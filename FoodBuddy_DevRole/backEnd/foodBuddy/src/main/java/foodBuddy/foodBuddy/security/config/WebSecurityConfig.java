package foodBuddy.foodBuddy.security.config;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // disabling csrf since we won't use form login
                .csrf().disable()
                // giving permission to every request for /login endpoint
                .authorizeRequests().requestMatchers("/api/v*/registration/**").permitAll()
                    .requestMatchers("/api/v*/login/**").permitAll()
                .requestMatchers("/api/v1/groupApi/**").permitAll()
                .requestMatchers("/api/v1/inventory/**").permitAll()
                .requestMatchers("/api/v1/recipe/**").permitAll()
                .requestMatchers("/api/v1/expenses/**").permitAll()
                .requestMatchers("/api/v1/notification/**").permitAll()
                    // for everything else, the user has to be authenticated
                    .anyRequest().authenticated()
                // setting stateless session, because we choose to implement Rest API
                .and().formLogin().disable();

        return http.build();
    }
}
