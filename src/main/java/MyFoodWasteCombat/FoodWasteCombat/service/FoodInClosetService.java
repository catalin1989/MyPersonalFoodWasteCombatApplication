package MyFoodWasteCombat.FoodWasteCombat.service;

import MyFoodWasteCombat.FoodWasteCombat.food.Food;
import MyFoodWasteCombat.FoodWasteCombat.repository.FoodInClosetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodInClosetService {

    private final FoodInClosetRepository foodRepository;

    @Transactional
    public void insertOrUpdateFood(String name, int quantity, LocalDate expirationDate){
        Food existingFood=foodRepository.findByName(name.toLowerCase().trim());

        if(existingFood!=null){

            int newQuantity=quantity+existingFood.getQuantity();
            foodRepository.updateFoodByName(name.toLowerCase().trim(),newQuantity);
        }
        else {

            foodRepository.insertFoodInCloset(name.toLowerCase().trim(),quantity,expirationDate);
        }
    }

    public List<Food> selectAllFoodsInCloset(){
        return foodRepository.selectAllFoodsInCloset();
    }

    public List<Food> selectFoodsThatWillExpireSoon(LocalDate expirationDate){
        return foodRepository.selectFoodsThatWillExpireSoon(expirationDate);
    }

    @Transactional
    public void clearAllFoodsInCloset() {
         foodRepository.clearAllFoodsFromCloset();
    }

    @Transactional
    public void removeOrUpdateFood(String name, int quantity) {
        Food existingFood=foodRepository.findByName(name.toLowerCase().trim());
        if(existingFood!=null&&existingFood.getQuantity()>quantity){
            int newQuantity=existingFood.getQuantity()-quantity;
            foodRepository.updateFoodByName(name.toLowerCase().trim(),newQuantity);
        }
        else if(existingFood!=null&&existingFood.getQuantity()<quantity){
            foodRepository.deleteFoodByName(name.toLowerCase().trim());
        }
    }
}
