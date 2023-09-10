package foodBuddy.foodBuddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"foodBuddy.foodBuddy"})
public class FoodBuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodBuddyApplication.class, args);
	}

}
