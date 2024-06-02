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


    public void generateShoppingList(){
        List<Food>listOfFoodStocks = foodStockService.getAllFoodStock();
        for(Food foodStock:listOfFoodStocks){

            List<Food> foodsInCloset=foodRepository.getFoodByNameAndPlace(foodStock.getName(),"closet");
            List<Food> foodsInFreezer=foodRepository.getFoodByNameAndPlace(foodStock.getName(),"freezer");
            List<Food> foodsInRefrigerator=foodRepository.getFoodByNameAndPlace(foodStock.getName(),"refrigerator");
            List<Food> listOfFoods = new ArrayList<>(foodsInCloset);
            listOfFoods.addAll(foodsInFreezer);
            listOfFoods.addAll(foodsInRefrigerator);
            int foodQuantity=getQuantity(listOfFoods);
            if(foodQuantity<foodStock.getQuantity()){
                int quantityDifference=foodStock.getQuantity()-foodQuantity;
                Food food=new Food(foodStock.getName(),quantityDifference,"shopping_list");
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
        List<Food> listOfShoppingListFoods=foodRepository.getFoodByNameAndPlace(food.getName(), "shopping_list");
        for(Food existingFood:listOfShoppingListFoods){
            if(existingFood.getName().equals(food.getName())){
                return true;

            }
        }
        return false;
    }
}
