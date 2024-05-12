package MyFoodWasteCombat.FoodWasteCombat.controller;

import MyFoodWasteCombat.FoodWasteCombat.food.Food;
import MyFoodWasteCombat.FoodWasteCombat.service.FoodInRefrigeratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyRefrigeratorController {

private final FoodInRefrigeratorService foodInRefrigeratorService;

    @GetMapping("refrigerator")
    public String getPage() {
        return "refrigerator";
    }

    @PostMapping("/refrigerator/add")
    public String addFoodToTheRefrigerator(@ModelAttribute Food food){
    foodInRefrigeratorService.insertOrUpdateFoodInRefrigerator(food.getName().toLowerCase(),food.getQuantity(),food.getExpirationDate());
    return "redirect:/refrigerator";
    }

    @GetMapping("/refrigerator/foods")
    public String getAllFoods(Model model){
        List<Food> existingFoods=foodInRefrigeratorService.selectAllFoodInRefrigerator();
        Collections.sort(existingFoods);
        model.addAttribute("foods",existingFoods);
        return "refrigerator_foods";
    }

    @PostMapping("/refrigerator/remove")
    public String removeOrUpdateFood(@ModelAttribute Food food, Model model){
        foodInRefrigeratorService.removeOrUpdateFoodFromRefrigerator(food.getName().toLowerCase(),food.getQuantity(),food.getExpirationDate());
        return  "redirect:/refrigerator";
    }
    @PostMapping("/refrigerator/foods_that_will_expire_soon")
    public String seeFoodsThatWillExpireSoon(@RequestParam("expirationDate") LocalDate expirationDate, Model model ){
        System.out.println("here");
        List<Food>foods= foodInRefrigeratorService.selectFoodsThatWillExpireSoon(expirationDate);
        Collections.sort(foods);
        model.addAttribute("foods",foods);
        return "refrigerator_foods_thatWillExpireSoon";
    }
    @GetMapping("/refrigerator/clearAll")
    public String clearAllFoods(){
        foodInRefrigeratorService.clearAllFoodsInRefrigerator();
        return "redirect:/refrigerator";
    }
}
