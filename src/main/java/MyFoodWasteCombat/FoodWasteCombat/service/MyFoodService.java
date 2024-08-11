package MyFoodWasteCombat.FoodWasteCombat.service;

import MyFoodWasteCombat.FoodWasteCombat.entity.Food;
import MyFoodWasteCombat.FoodWasteCombat.repository.MyFoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyFoodService {

    private final MyFoodRepository foodRepository;

    public List<Food> getFoodByName(String name,Long idOfUser){
        return foodRepository.getFoodByNameAndIdOfUser(name,idOfUser);
    }

    public List<Food> getFoodByPlace(String place,Long id){
        return foodRepository.getFoodByPlaceAndIdOfUser(place,id);
    }

    public void saveFood(Food food){

        foodRepository.save(food);
    }

    public void updateFood(Food food){
        Food existingFood=foodRepository.getReferenceById(food.getId());
        if(food.getExpirationDate()==null) {
            food.setExpirationDate(existingFood.getExpirationDate());
        }
        food.setIdOfUser(existingFood.getIdOfUser());
       food.setPlace(existingFood.getPlace());
        foodRepository.save(food);
    }
    public List<Food> getFoodsByNameAndPlace(String name, String place,Long idOfUser){
        return foodRepository.getFoodByNameAndPlaceAndIdOfUser(name, place,idOfUser);
    }
    public Food getFoodById(Long id){
       return foodRepository.getReferenceById(id);
    }

    public void deleteFoodById(Long id){
        foodRepository.deleteById(id);
    }
    public List<Food> getAllFoodBeforeExpiration(String place, LocalDate expirationDate, Long idOfUser){
        return foodRepository.getFoodByPlaceAndExpirationDateBeforeAndIdOfUser(place, expirationDate, idOfUser);
    }
    @Transactional
    public void deleteFoodByPlace(String place,Long idOfUser){
        foodRepository.deleteFoodByPlaceAndIdOfUser(place,idOfUser);
    }

    public List<Food> getAllFoodsWithName(String name,Long idOfUser){
        List<Food>foodsFromCloset=foodRepository.getFoodByNameAndPlaceAndIdOfUser(name,"closet",idOfUser);
        List<Food>foodsFromFreezer=foodRepository.getFoodByNameAndPlaceAndIdOfUser(name,"freezer",idOfUser);
        List<Food>foodsFromRefrigerator=foodRepository.getFoodByNameAndPlaceAndIdOfUser(name,"refrigerator",idOfUser);
        List<Food>finalFoodList=new ArrayList<>();
        finalFoodList.addAll(foodsFromCloset);
        finalFoodList.addAll(foodsFromFreezer);
        finalFoodList.addAll(foodsFromRefrigerator);
        return finalFoodList;
    }
}
