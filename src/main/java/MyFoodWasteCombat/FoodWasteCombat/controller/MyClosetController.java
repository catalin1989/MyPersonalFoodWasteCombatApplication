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

public class MyClosetController {

    private final MyFoodService myFoodService;

    @GetMapping("/closet")
    public String getCloset(){
        return "closet";
    }


    @GetMapping("/closet/foods")
    public String getFoodsInCloset(Model model){
        List<Food> foodInCloset=myFoodService.getFoodByPlace("closet");
        model.addAttribute("foods",foodInCloset);
        return "closet_foods";
    }
    @GetMapping("/closet/foods/create")
    public String showForm(Model model){
        model.addAttribute("food", new Food());
        return "closetCreateFood";
    }
    @PostMapping("/closet/foods/create")
    public String addFoodToClosed(@ModelAttribute Food food){
        food.setPlace("closet");
        myFoodService.saveFood(food);
        return "redirect:/closet/foods";
    }
    @GetMapping("/closet/foods/edit/{id}")
    public String showFoodForEditing(@PathVariable("id") Long id, Model model){
        model.addAttribute("food",myFoodService.getFoodById(id));
        return "updateFood";
    }

    @PostMapping("closet/foods/edit/{id}")
    public String editFood(@ModelAttribute Food food){
        myFoodService.updateFoodToCloset(food);
        return "redirect:/closet/foods";
    }
    @GetMapping("closet/foods/delete/{id}")
    public String deleteFood(@PathVariable("id") Long id){
        myFoodService.deleteFoodById(id);
        return "redirect:/closet/foods";
    }

    @PostMapping("/closet/foods_that_will_expire_soon")
    public String getFoodsThatWillExpireSoon(@RequestParam("expirationDate") LocalDate expirationDate, Model model){
        expirationDate = expirationDate.plusDays(1);
        List<Food> listOfFoods=myFoodService.getAllFoodBeforeExpiration("closet",expirationDate);
        model.addAttribute("foods",listOfFoods);
        return "foodsThatWillExpireSoon";
    }

    @GetMapping("/closet/clearAll")
    public String clearAll(){
        myFoodService.deleteFoodByPlace("closet");
        return "redirect:/closet";
    }
}
