package MyFoodWasteCombat.FoodWasteCombat.repository;

import MyFoodWasteCombat.FoodWasteCombat.food.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface FoodInClosetRepository extends JpaRepository<Food,Long> {
@Modifying
@Query(value = "INSERT INTO foods_in_closet (name, quantity, expiration_date) VALUES (:name, :quantity,:expiration_date)",nativeQuery = true)
    void insertFoodInCloset(@Param("name")String name, @Param("quantity")int quantity, @Param("expiration_date")LocalDate expirationDate);


    @Query(value = "SELECT * FROM foods_in_closet",nativeQuery = true)
    List<Food> selectAllFoodsInCloset();

    @Modifying
    @Query(value="DELETE FROM foods_in_closet",nativeQuery = true)
    void clearAllFoodsFromCloset();

    @Query(value="SELECT * FROM foods_in_closet Where foods_in_closet.name=:name",nativeQuery = true)
    Food findByName(String name);
    @Modifying
    @Query(value = "UPDATE foods_in_closet SET quantity = :newQuantity WHERE name = :name", nativeQuery = true)
    void updateFoodByName(@Param("name")String name, @Param("newQuantity")int newQuantity);

    @Modifying
    @Query(value="DELETE FROM foods_in_closet WHERE foods_in_closet.name=:name",nativeQuery = true)
    void deleteFoodByName(@Param("name") String name);


    @Query(value="SELECT * FROM foods_in_closet WHERE foods_in_closet.expiration_date <= :expirationDate",nativeQuery = true)
    List<Food> selectFoodsThatWillExpireSoon(@Param("expirationDate")LocalDate expirationDate);

}


