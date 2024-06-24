package MyFoodWasteCombat.FoodWasteCombat;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

public class FoodWasteCombatApplication implements CommandLineRunner {

	@Value("${SPRING_DATASOURCE_URL}")
	private String datasourceUrl;

	@Value("${PROD_DB_USERNAME}")
	private String dbUsername;

	@Value("${PROD_DB_PASSWORD}")
	private String dbPassword;

	public static void main(String[] args) {
		SpringApplication.run(FoodWasteCombatApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("SPRING_DATASOURCE_URL: " + datasourceUrl);
		System.out.println("PROD_DB_USERNAME: " + dbUsername);
		System.out.println("PROD_DB_PASSWORD: " + dbPassword); // Be careful with logging passwords!
	}
}


