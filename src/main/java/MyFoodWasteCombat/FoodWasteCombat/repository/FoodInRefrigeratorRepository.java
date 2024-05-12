package MyFoodWasteCombat.FoodWasteCombat.repository;

import MyFoodWasteCombat.FoodWasteCombat.food.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface FoodInRefrigeratorRepository extends JpaRepository<Food,Long> {

    @Modifying
    @Query(value ="INSERT INTO foods_in_refrigerator(name, quantity, expiration_date) VALUES(:name, :quantity,:expiration_date)",nativeQuery = true)
    void insertFoodInRefrigerator(@Param("name") String name, @Param("quantity") int quantity, @Param("expiration_date") LocalDate expirationDate);

    @Query(value = "SELECT * FROM foods_in_refrigerator",nativeQuery = true)
    List<Food> selectAllFoodsInRefrigerator();

    @Modifying
    @Query(value="DELETE FROM foods_in_refrigerator WHERE foods_in_refrigerator.name=:name AND foods_in_refrigerator.expiration_date = :expirationDate",nativeQuery = true)
    void deleteFoodByName(@Param("name") String name, @Param("expirationDate")LocalDate expirationDate);

    @Query(value="SELECT * FROM foods_in_refrigerator Where foods_in_refrigerator.name=:name AND foods_in_refrigerator.expiration_date = :expirationDate",nativeQuery = true)
    Food findByNameAndExpirationDate(@Param("name") String name,@Param("expirationDate")LocalDate expirationDate);
    @Modifying
    @Query(value = "UPDATE foods_in_refrigerator SET quantity = :newQuantity WHERE name = :name AND foods_in_refrigerator.expiration_date = :expirationDate", nativeQuery = true)
    void updateFoodByName(@Param("name")String name, @Param("newQuantity")int newQuantity, @Param("expirationDate")LocalDate expirationDate);

    @Modifying
    @Query(value="DELETE FROM foods_in_refrigerator",nativeQuery = true)
    void clearAllFoodsFromRefrigerator();

    @Query(value="SELECT * FROM foods_in_refrigerator WHERE foods_in_refrigerator.expiration_date <= :expirationDate",nativeQuery = true)
    List<Food> selectFoodsThatWillExpireSoon(@Param("expirationDate")LocalDate expirationDate);
}
