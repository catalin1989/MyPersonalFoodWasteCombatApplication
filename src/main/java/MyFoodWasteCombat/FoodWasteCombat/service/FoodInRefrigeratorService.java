package MyFoodWasteCombat.FoodWasteCombat.service;

import MyFoodWasteCombat.FoodWasteCombat.food.Food;
import MyFoodWasteCombat.FoodWasteCombat.repository.FoodInRefrigeratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodInRefrigeratorService {

    private final FoodInRefrigeratorRepository foodInRefrigeratorRepository;
    @Transactional
    public void insertOrUpdateFoodInRefrigerator(String name, int quantity, LocalDate expirationDate) {
        Food existingFood=foodInRefrigeratorRepository.findByNameAndExpirationDate(name.toLowerCase().trim(),expirationDate);
        if(existingFood!=null){
            int newQuantity=quantity+existingFood.getQuantity();
            foodInRefrigeratorRepository.updateFoodByName(name.toLowerCase().trim(),newQuantity,expirationDate);
        }
        else {
            foodInRefrigeratorRepository.insertFoodInRefrigerator(name.toLowerCase().trim(),quantity,expirationDate);
        }
    }

    public List<Food> selectAllFoodInRefrigerator(){
       return foodInRefrigeratorRepository.selectAllFoodsInRefrigerator();
    }

    @Transactional
    public void removeOrUpdateFoodFromRefrigerator(String name, int quantity,LocalDate expirationDate) {
        Food existingFood=foodInRefrigeratorRepository.findByNameAndExpirationDate(name.toLowerCase().trim(),expirationDate);

        if(existingFood!=null&&existingFood.getQuantity()>quantity){
            int newQuantity=existingFood.getQuantity()-quantity;
            foodInRefrigeratorRepository.updateFoodByName(name.toLowerCase().trim(),newQuantity,expirationDate);
        }
        else if(existingFood!=null&&existingFood.getQuantity()<quantity){
            foodInRefrigeratorRepository.deleteFoodByName(name.toLowerCase().trim(),expirationDate);
        }


    }

    @Transactional
    public void clearAllFoodsInRefrigerator() {
        foodInRefrigeratorRepository.clearAllFoodsFromRefrigerator();

    }

    public List<Food> selectFoodsThatWillExpireSoon(LocalDate expirationDate){
        return foodInRefrigeratorRepository.selectFoodsThatWillExpireSoon(expirationDate);
    }


    }


