package MyFoodWasteCombat.FoodWasteCombat.controller;

import MyFoodWasteCombat.FoodWasteCombat.entity.Food;
import MyFoodWasteCombat.FoodWasteCombat.service.MyFoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor

public class MyRefrigeratorController {

    private final MyFoodService myFoodService;

    @GetMapping("/refrigerator")
    public String getCloset(){
        return "refrigerator";
    }
    @GetMapping("/refrigerator/foods")
    public String getFoodsInCloset(Model model){
        List<Food> foodInRefrigerator =myFoodService.getFoodByPlace("refrigerator");
        model.addAttribute("foods", foodInRefrigerator);
        return "refrigerator_foods";
    }
    @GetMapping("/refrigerator/foods/create")
    public String showForm(Model model){
        model.addAttribute("food", new Food());
        return "refrigeratorCreateFood";
    }
    @PostMapping("/refrigerator/foods/create")
    public String addFoodToRefrigerator(@ModelAttribute Food food){
        food.setPlace("refrigerator");
        myFoodService.saveFood(food);
        return "redirect:/refrigerator/foods";
    }
    @GetMapping("/refrigerator/foods/edit/{id}")
    public String showFoodForEditing(@PathVariable("id") Long id, Model model){
        model.addAttribute("food",myFoodService.getFoodById(id));
        return "refrigeratorUpdateFood";
    }

    @PostMapping("refrigerator/foods/edit/{id}")
    public String editFood(@ModelAttribute Food food){
        myFoodService.updateFoodToCloset(food);
        return "redirect:/refrigerator/foods";
    }

    @GetMapping("refrigerator/foods/delete/{id}")
    public String deleteFood(@PathVariable("id") Long id){
        myFoodService.deleteFoodById(id);
        return "redirect:/refrigerator/foods";
    }

    @PostMapping("/refrigerator/foods_that_will_expire_soon")
    public String getFoodsThatWillExpireSoon(@RequestParam("expirationDate") LocalDate expirationDate, Model model){
        expirationDate = expirationDate.plusDays(1);
        List<Food> listOfFoods=myFoodService.getAllFoodBeforeExpiration("refrigerator",expirationDate);
        model.addAttribute("foods",listOfFoods);
        return "foodsThatWillExpireSoon";
    }

    @GetMapping("/refrigerator/clearAll")
    public String clearAll(){
        myFoodService.deleteFoodByPlace("refrigerator");
        return "redirect:/refrigerator";
    }
}
