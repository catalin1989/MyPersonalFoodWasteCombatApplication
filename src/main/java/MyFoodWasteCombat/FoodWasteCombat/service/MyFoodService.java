package MyFoodWasteCombat.FoodWasteCombat.service;

import MyFoodWasteCombat.FoodWasteCombat.food.Food;
import MyFoodWasteCombat.FoodWasteCombat.repository.MyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyFoodService {

    private final MyRepository foodRepository;

    public List<Food> getFoodByPlace(String place){
        return foodRepository.getFoodByPlace(place);
    }

    public void saveFoodToCloset(Food food){
        food.setPlace("closet");
        foodRepository.save(food);
    }

    public void updateFoodToCloset(Food food){
        Food existingFood=foodRepository.getReferenceById(food.getId());
        System.out.println(existingFood);
        if(food.getExpirationDate()==null) {
            food.setExpirationDate(existingFood.getExpirationDate());
        }
       food.setPlace(existingFood.getPlace());
        System.out.println(food);
        foodRepository.save(food);
    }

    public Food getFoodById(Long id){
       return foodRepository.getReferenceById(id);
    }

    public void deleteFoodById(Long id){
        foodRepository.deleteById(id);
    }
}
