package MyFoodWasteCombat.FoodWasteCombat.service;

import MyFoodWasteCombat.FoodWasteCombat.entity.Food;
import MyFoodWasteCombat.FoodWasteCombat.entity.FoodStock;
import MyFoodWasteCombat.FoodWasteCombat.repository.MyFoodStockRepository;
import MyFoodWasteCombat.FoodWasteCombat.repository.MyFoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyShoppingListService {
    private final MyFoodStockRepository myFoodStockRepository;
    private final MyFoodRepository foodRepository;
    public List<Food> extendShoppingList(Food food,List<Food>foods) {
        foods.add(food);
        return foods;
    }
    public List<Food> generateShoppingList(){
        List<FoodStock>listOfFoodStocks = myFoodStockRepository.findAll();
        List<Food> shoppingList=new ArrayList<>();
        for(FoodStock foodStock:listOfFoodStocks){
            List<Food>listOfFoods=foodRepository.getFoodByName(foodStock.getName());
            int foodQuantity=getQuantity(listOfFoods);
            if(foodQuantity<foodStock.getQuantity()){
                int quantityDifference=foodStock.getQuantity()-foodQuantity;
                Food food=new Food(foodStock.getName(),quantityDifference);
                shoppingList.add(food);
            }
        }
        return shoppingList;
    }

    public int getQuantity(List<Food>foods){
        int result=0;
        for(Food food:foods){
            result+=food.getQuantity();
        }
        return result;
    }
}
