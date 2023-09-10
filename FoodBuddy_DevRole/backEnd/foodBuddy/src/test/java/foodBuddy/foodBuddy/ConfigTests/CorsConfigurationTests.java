package foodBuddy.foodBuddy.ConfigTests;

import foodBuddy.foodBuddy.config.CorsConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

public class CorsConfigurationTests {

    @Test
    void addCorsMappingsTest(){
        CorsRegistry registry = new CorsRegistry();
        CorsConfiguration config = new CorsConfiguration();
        config.addCorsMappings(registry);
    }
}
