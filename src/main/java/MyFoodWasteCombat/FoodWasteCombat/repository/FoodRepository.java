package MyFoodWasteCombat.FoodWasteCombat.repository;

import MyFoodWasteCombat.FoodWasteCombat.food.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface FoodRepository extends JpaRepository<Food,Long> {
@Modifying
@Query(value = "INSERT INTO foods_in_closet (name, quantity, expiration_date) VALUES (:name, :quantity,:expiration_date)",nativeQuery = true)
    void insertFoodInCloset(@Param("name")String name, @Param("quantity")int quantity, @Param("expiration_date")LocalDate exppirationDate);


    @Query(value = "SELECT * FROM foods_in_closet",nativeQuery = true)
    List<Food> selectAllFoodsInCloset();
}


