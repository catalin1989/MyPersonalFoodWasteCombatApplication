package MyFoodWasteCombat.FoodWasteCombat.repository;

import MyFoodWasteCombat.FoodWasteCombat.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface MyFoodRepository extends JpaRepository<Food,Long> {

    List<Food> getFoodByPlace(String place);
    List<Food> getFoodByName(String name);

    List<Food> getFoodByPlaceAndExpirationDateBefore(String place, LocalDate expirationDate);

    void deleteFoodByPlace(String place);
}


