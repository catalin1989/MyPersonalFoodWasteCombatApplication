package MyFoodWasteCombat.FoodWasteCombat.service;

import MyFoodWasteCombat.FoodWasteCombat.entity.Food;
import MyFoodWasteCombat.FoodWasteCombat.repository.MyFoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyShoppingListService {

    private final MyFoodRepository foodRepository;
    private final MyFoodStockService foodStockService;
    private final UserService userService;


    public void generateShoppingList(Long idOfUser){
        List<Food>listOfFoodStocks = foodStockService.getAllFoodStock();
        for(Food foodStock:listOfFoodStocks){

            List<Food> foodsInCloset=foodRepository.getFoodByNameAndPlaceAndIdOfUser(foodStock.getName(),"closet",idOfUser);
            List<Food> foodsInFreezer=foodRepository.getFoodByNameAndPlaceAndIdOfUser(foodStock.getName(),"freezer",idOfUser);
            List<Food> foodsInRefrigerator=foodRepository.getFoodByNameAndPlaceAndIdOfUser(foodStock.getName(),"refrigerator",idOfUser);
            List<Food> listOfFoods = new ArrayList<>(foodsInCloset);
            listOfFoods.addAll(foodsInFreezer);
            listOfFoods.addAll(foodsInRefrigerator);
            int foodQuantity=getQuantity(listOfFoods);
            if(foodQuantity<foodStock.getQuantity()){
                int quantityDifference=foodStock.getQuantity()-foodQuantity;
                Food food=new Food(foodStock.getName(),quantityDifference,"shopping_list",idOfUser);
                if(!checkIfFoodIsInShoppingList(food)){
                    foodRepository.save(food);
                }
            }
        }

    }

    public int getQuantity(List<Food>foods){
        int result=0;
        for(Food food:foods){
            result+=food.getQuantity();
        }
        return result;
    }

    public boolean checkIfFoodIsInShoppingList(Food food){
        List<Food> listOfShoppingListFoods=foodRepository.getFoodByNameAndPlaceAndIdOfUser(food.getName(), "shopping_list", userService.getCurrentUserId());
        for(Food existingFood:listOfShoppingListFoods){
            if(existingFood.getName().equals(food.getName())){
                return true;

            }
        }
        return false;
    }
}
