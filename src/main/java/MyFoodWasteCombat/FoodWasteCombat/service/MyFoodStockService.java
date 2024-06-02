package MyFoodWasteCombat.FoodWasteCombat.service;

import MyFoodWasteCombat.FoodWasteCombat.entity.Food;
import MyFoodWasteCombat.FoodWasteCombat.repository.MyFoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class MyFoodStockService {

    private final MyFoodRepository foodRepository;

    public List<Food> getAllFoodStock() {
        return foodRepository.getFoodByPlace("food_stock");
    }

    public void addFoodStock(Food food) {
        foodRepository.save(food);
    }
    @Transactional
    public void deleteAllFoodStock() {
        foodRepository.deleteFoodByPlace("food_stock");
    }

    public Food getFoodStockById(Long id) {
       return foodRepository.getReferenceById(id);
    }
    public void updateFoodStock(Food food){
        foodRepository.save(food);
    }
    public void deleteById(Long id) {
        foodRepository.deleteById(id);
    }

}
