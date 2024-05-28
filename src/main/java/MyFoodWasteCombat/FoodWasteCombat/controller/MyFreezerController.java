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
public class MyFreezerController {

    private final MyFoodService myFoodService;

    @GetMapping("/freezer")
    public String getFreezer(){
        return "freezer";
    }


    @GetMapping("/freezer/foods")
    public String getFoodsInCloset(Model model){
        List<Food> foodInFreezer=myFoodService.getFoodByPlace("freezer");
        model.addAttribute("foods",foodInFreezer);
        return "freezer_foods";
    }
    @GetMapping("/freezer/foods/create")
    public String showForm(Model model){
        model.addAttribute("food", new Food());
        return "freezerCreateFood";
    }
    @PostMapping("/freezer/foods/create")
    public String addFoodToFreezer(@ModelAttribute Food food){
        food.setPlace("freezer");
        myFoodService.saveFood(food);
        return "redirect:/freezer/foods";
    }
    @GetMapping("/freezer/foods/edit/{id}")
    public String showFoodForEditing(@PathVariable("id") Long id, Model model){
        model.addAttribute("food",myFoodService.getFoodById(id));
        return "freezerUpdateFood";
    }

    @PostMapping("freezer/foods/edit/{id}")
    public String editFood(@ModelAttribute Food food){
        myFoodService.updateFoodToCloset(food);
        return "redirect:/freezer/foods";
    }
    @GetMapping("freezer/foods/delete/{id}")
    public String deleteFood(@PathVariable("id") Long id){
        myFoodService.deleteFoodById(id);
        return "redirect:/freezer/foods";
    }

    @PostMapping("/freezer/foods_that_will_expire_soon")
    public String getFoodsThatWillExpireSoon(@RequestParam("expirationDate") LocalDate expirationDate, Model model){
        expirationDate = expirationDate.plusDays(1);
        List<Food> listOfFoods=myFoodService.getAllFoodBeforeExpiration("freezer",expirationDate);
        model.addAttribute("foods",listOfFoods);
        return "foodsThatWillExpireSoon";
    }

    @GetMapping("/freezer/clearAll")
    public String clearAll(){
        myFoodService.deleteFoodByPlace("freezer");
        return "redirect:/freezer";
    }
}

