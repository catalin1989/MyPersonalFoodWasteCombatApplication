package MyFoodWasteCombat.FoodWasteCombat.controller;

import MyFoodWasteCombat.FoodWasteCombat.food.Food;
import MyFoodWasteCombat.FoodWasteCombat.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyClosetController  {

    private final FoodService foodService;



    @GetMapping("/closet")
    public String getPage() {

        return "closet";
    }

    @PostMapping("/closet/add")
    public String addFoodInCloset(@ModelAttribute Food food, Model model) {

        foodService.insertOrUpdateFood(food.getName(), food.getQuantity(), food.getExpirationDate());

        return "redirect:/closet";
    }

    @GetMapping("/closet/foods")
    public String viewAllFoodsInCloset(Model model){
    List<Food> foods=foodService.selectAllFoodsInCloset();
    model.addAttribute("foods",foods);
    return "closet_foods";
    }

    @PostMapping("/closet/remove")
    public String removeOrUpdateFood(@ModelAttribute Food food, Model model){
        foodService.removeOrUpdateFood(food.getName(),food.getQuantity());
        return  "redirect:/closet";
    }

    @PostMapping("/closet/foods_that_will_expire_soon")
    public String seeFoodsThatWillExpireSoon(@RequestParam("expirationDate") LocalDate expirationDate, Model model ){
        System.out.println("here");
        List<Food>foods=foodService.selectFoodsThatWillExpireSoon(expirationDate);
        model.addAttribute("foods",foods);
        return "closet_foods_thatWillExpireSoon";
    }

    @GetMapping("/closet/clearAll")
    public String clearAllFoods(){
        foodService.clearAllFoodsInCloset();
        return "redirect:/closet";
    }

}
