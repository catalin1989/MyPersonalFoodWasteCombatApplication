package MyFoodWasteCombat.FoodWasteCombat.controller;

import MyFoodWasteCombat.FoodWasteCombat.entity.Food;
import MyFoodWasteCombat.FoodWasteCombat.service.MyFoodService;
import MyFoodWasteCombat.FoodWasteCombat.service.MyShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequiredArgsConstructor

public class MyShoppingListController {
    private final MyFoodService foodService;
    private final MyShoppingListService shoppingListService;


    @GetMapping("/shopping-list")
    public String shoppingList(){
        return "shopping-list/shopping-list";
    }

    @GetMapping("/shopping-list/generate")
    public String generateShoppingList(@ModelAttribute("extendedShoppingList") List<Food> extendedShoppingList,RedirectAttributes redirectAttributes){
        List<Food> shoppingList=shoppingListService.generateShoppingList();
        extendedShoppingList.addAll(shoppingList);
        redirectAttributes.addFlashAttribute("foods", extendedShoppingList);
        return "redirect:/shopping-list";
    }

    @GetMapping("/shopping-list/add")
    public String showForm(Model model){
        model.addAttribute("food", new Food());
        return "shopping-list/add-food-to-shopping-list";
    }
    @PostMapping("/shopping-list/add")
    public String addFoodToClosed(@ModelAttribute Food food,RedirectAttributes redirectAttributes){
        System.out.println(food);
        List<Food>listOfFoods=shoppingListService.generateShoppingList();
        List<Food>extendedShoppingList=shoppingListService.extendShoppingList(food,listOfFoods);
        redirectAttributes.addFlashAttribute("foods", extendedShoppingList);
        return "redirect:/shopping-list";
    }

    @GetMapping("/shopping-list/delete-all")
    public String deleteAllFoods(@ModelAttribute("extendedShoppingList") List<Food> extendedShoppingList){
    extendedShoppingList.clear();
        return "redirect:/shopping-list";
    }

    @GetMapping("/shopping-list/save-shopping-list")
    public String saveShoppingList(){
/*
        System.out.println(extendedShoppingList.size());
        for (Food food : extendedShoppingList) {
            System.out.println(food);
            foodService.saveFood(food);
        }
        //extendedShoppingList.clear();
        return "redirect:shopping-list/save-foods";*/
        return "null";
    }

    @GetMapping("shopping-list/save-foods")
    public String saveFoods(Model model){
        List<Food>listOfFoods=foodService.getFoodByPlace("null");
        System.out.println(listOfFoods.size());
        model.addAttribute("foods", listOfFoods);
        return "shopping-list/save-foods";
    }
}
