package MyFoodWasteCombat.FoodWasteCombat.controller;

import MyFoodWasteCombat.FoodWasteCombat.entity.Food;
import MyFoodWasteCombat.FoodWasteCombat.entity.FoodStock;
import MyFoodWasteCombat.FoodWasteCombat.service.MyFoodStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyFoodStockController {

private final MyFoodStockService myFoodStockService;

    @GetMapping("/required-foods")
    public String requiredFoods(Model model) {
        List<FoodStock> listOfFoodStocks=myFoodStockService.getAllFoodStock();
        model.addAttribute("listOfFoods", listOfFoodStocks);
        return "foodStocks/required-foods";
    }

    @GetMapping("/required-foods/create")
    public String showForm(Model model){

        model.addAttribute("foodStock", new FoodStock());

        return "foodStocks/required-foods-create";
    }
    @PostMapping("/required-foods/create")
    public String addFoodToClosed(@ModelAttribute FoodStock foodStock){

        myFoodStockService.addFoodStock(foodStock);

        return "redirect:/required-foods";
    }

    @GetMapping("required-foods/delete-all")
    public String deleteAll(){
        myFoodStockService.deleteAllFoodStock();
        return "redirect:/required-foods";
    }

    @GetMapping("required-foods/edit/{id}")
    public String showFoodForEditing(@PathVariable("id") Long id, Model model){
        System.out.println("1");
        model.addAttribute("foodStock",myFoodStockService.getFoodStockById(id));
        System.out.println("2");
        return "foodStocks/required-foods-update";
    }

    @PostMapping("required-foods/edit/{id}")
    public String editFood(@ModelAttribute FoodStock foodStock){
        System.out.println("3");
       myFoodStockService.updateFoodStock(foodStock);
        System.out.println("4");
        return "redirect:/required-foods";
    }
    @GetMapping("required-foods/delete/{id}")
    public String deleteFood(@PathVariable("id") Long id){
       myFoodStockService.deleteById(id);
        return "redirect:/required-foods";
    }
}
