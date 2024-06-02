package MyFoodWasteCombat.FoodWasteCombat.service;

import MyFoodWasteCombat.FoodWasteCombat.entity.Food;
import MyFoodWasteCombat.FoodWasteCombat.repository.MyFoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyFoodService {

    private final MyFoodRepository foodRepository;

    public List<Food> getFoodByPlace(String place){
        return foodRepository.getFoodByPlace(place);
    }

    public void saveFood(Food food){

        foodRepository.save(food);
    }

    public void updateFood(Food food){
        Food existingFood=foodRepository.getReferenceById(food.getId());
        if(food.getExpirationDate()==null) {
            food.setExpirationDate(existingFood.getExpirationDate());
        }
       food.setPlace(existingFood.getPlace());
        foodRepository.save(food);
    }

    public Food getFoodById(Long id){
       return foodRepository.getReferenceById(id);
    }

    public void deleteFoodById(Long id){
        foodRepository.deleteById(id);
    }
    public List<Food> getAllFoodBeforeExpiration(String place, LocalDate expirationDate){
        return foodRepository.getFoodByPlaceAndExpirationDateBefore(place, expirationDate);
    }
    @Transactional
    public void deleteFoodByPlace(String place){
        foodRepository.deleteFoodByPlace(place);
    }
}
