package MyFoodWasteCombat.FoodWasteCombat.service;

import MyFoodWasteCombat.FoodWasteCombat.food.Food;
import MyFoodWasteCombat.FoodWasteCombat.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    /*@Transactional
    public void insertFoodInCloset(String name, int quantity, LocalDate expirationDate) {
        foodRepository.insertFoodInCloset(name.toLowerCase(),quantity,expirationDate);
    }*/

    @Transactional
    public void insertOrUpdateFood(String name, int quantity, LocalDate expirationDate){
        Food existingFood=foodRepository.findByName(name.toLowerCase());

        if(existingFood!=null){

            int newQuantity=quantity+existingFood.getQuantity();
            foodRepository.updateFoodByName(name.toLowerCase(),newQuantity);
        }
        else {
            System.out.println("1");
            foodRepository.insertFoodInCloset(name.toLowerCase(),quantity,expirationDate);
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
        Food existingFood=foodRepository.findByName(name.toLowerCase());
        if(existingFood!=null&&existingFood.getQuantity()>quantity){
            int newQuantity=existingFood.getQuantity()-quantity;
            foodRepository.updateFoodByName(name.toLowerCase(),newQuantity);
        }
        else if(existingFood!=null&&existingFood.getQuantity()<quantity){
            foodRepository.deleteFoodByName(name);
        }
    }
}
