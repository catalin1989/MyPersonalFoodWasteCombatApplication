package MyFoodWasteCombat.FoodWasteCombat.repository;

import MyFoodWasteCombat.FoodWasteCombat.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MyFoodRepository extends JpaRepository<Food,Long> {

    List<Food> getFoodByPlaceAndIdOfUser(String place, Long userId);
    List<Food> getFoodByNameAndIdOfUser(String name, Long userId);

    List<Food> getFoodByPlaceAndExpirationDateBeforeAndIdOfUser(String place, LocalDate expirationDate,Long id);
    List<Food> getFoodByNameAndPlaceAndIdOfUser(String name, String place,Long idOfUser);
    void deleteFoodByPlaceAndIdOfUser(String place,Long id);

}


