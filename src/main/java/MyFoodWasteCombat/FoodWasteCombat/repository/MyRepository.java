package MyFoodWasteCombat.FoodWasteCombat.repository;

import MyFoodWasteCombat.FoodWasteCombat.food.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MyRepository extends JpaRepository<Food,Long> {

    List<Food> getFoodByPlace(String place);

}


