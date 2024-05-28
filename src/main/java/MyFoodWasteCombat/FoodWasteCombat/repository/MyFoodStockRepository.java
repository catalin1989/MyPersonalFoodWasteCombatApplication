package MyFoodWasteCombat.FoodWasteCombat.repository;

import MyFoodWasteCombat.FoodWasteCombat.entity.FoodStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyFoodStockRepository extends JpaRepository<FoodStock,Long> {
}
