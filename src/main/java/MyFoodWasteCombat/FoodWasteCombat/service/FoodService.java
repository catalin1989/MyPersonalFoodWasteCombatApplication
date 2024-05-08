package MyFoodWasteCombat.FoodWasteCombat.service;

import MyFoodWasteCombat.FoodWasteCombat.food.Food;
import MyFoodWasteCombat.FoodWasteCombat.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class FoodService {

    private final FoodRepository foodRepository;
    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }
    @Transactional
    public void insertFoodInCloset(String name, int quantity, LocalDate expirationDate) {
        foodRepository.insertFoodInCloset(name,quantity,expirationDate);
    }

    public List<Food> selectAllFoodsInCloset(){
        return foodRepository.selectAllFoodsInCloset();
    }
}
