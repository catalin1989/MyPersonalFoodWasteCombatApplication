package MyFoodWasteCombat.FoodWasteCombat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FoodWasteCombatApplication {
	public String PORT=System.getenv("PORT");
	public static void main(String[] args) {
		SpringApplication.run(FoodWasteCombatApplication.class, args);
	}

}
