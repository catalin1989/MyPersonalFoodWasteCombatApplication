package MyFoodWasteCombat.FoodWasteCombat.controller;

import MyFoodWasteCombat.FoodWasteCombat.entity.Food;
import MyFoodWasteCombat.FoodWasteCombat.service.MyFoodService;
import MyFoodWasteCombat.FoodWasteCombat.service.MyShoppingListService;
import MyFoodWasteCombat.FoodWasteCombat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequiredArgsConstructor

public class MyShoppingListController {
    private final MyFoodService foodService;
    private final MyShoppingListService shoppingListService;
    private final UserService userService;

    @GetMapping("/shopping-list")
    public String shoppingList(){
        return "shopping-list/shopping-list";
    }

    @GetMapping("/shopping-list/generate")
    public String generateShoppingList(RedirectAttributes redirectAttributes){
        shoppingListService.generateShoppingList(userService.getCurrentUserId());
        List<Food>shoppingList=foodService.getFoodByPlace("shopping_list", userService.getCurrentUserId());
        redirectAttributes.addFlashAttribute("foods", shoppingList);
        return "redirect:/shopping-list";
    }

    @GetMapping("/shopping-list/add")
    public String showForm(Model model){
        model.addAttribute("food", new Food());
        return "shopping-list/add-food-to-shopping-list";
    }
    @PostMapping("/shopping-list/add")
    public String addFoodToClosed(@ModelAttribute Food food,RedirectAttributes redirectAttributes){
       food.setPlace("shopping_list");
       food.setIdOfUser(userService.getCurrentUserId());
       foodService.saveFood(food);
        List<Food>shoppingList=foodService.getFoodByPlace("shopping_list",userService.getCurrentUserId());
        redirectAttributes.addFlashAttribute("foods", shoppingList);
        return "redirect:/shopping-list";
    }

    @GetMapping("/shopping-list/delete-all")
    public String deleteAllFoods(){
    foodService.deleteFoodByPlace("shopping_list", userService.getCurrentUserId());
        return "redirect:/shopping-list";
    }

    @GetMapping("shopping-list/save-foods")
    public String saveFoods(Model model){
        List<Food>listOfFoods=foodService.getFoodByPlace("null",userService.getCurrentUserId());
        model.addAttribute("foods", listOfFoods);
        return "shopping-list/save-foods";
    }

    @GetMapping("/shopping-list/edit/{id}")
    public String showFoodForEditing(@PathVariable("id") Long id, Model model){
        model.addAttribute("food",foodService.getFoodById(id));
        return "shopping-list/update-shopping-list-food";
    }

    @PostMapping("/shopping-list/edit/{id}")
    public String editFood(@ModelAttribute Food food,RedirectAttributes redirectAttributes){
        foodService.updateFood(food);
        List<Food>shoppingList=foodService.getFoodByPlace("shopping_list",userService.getCurrentUserId());
        redirectAttributes.addFlashAttribute("foods", shoppingList);
        return "redirect:/shopping-list";
    }
    @GetMapping("/shopping-list/delete/{id}")
    public String deleteFood(@PathVariable("id") Long id,RedirectAttributes redirectAttributes){
        foodService.deleteFoodById(id);
        List<Food>shoppingList=foodService.getFoodByPlace("shopping_list",userService.getCurrentUserId());
        redirectAttributes.addFlashAttribute("foods", shoppingList);
        return "redirect:/shopping-list";
    }

    @GetMapping("/shopping-list/send-food-to-place/{id}")
    public String sendFood(@PathVariable("id") Long id,Model model){
        model.addAttribute("food",foodService.getFoodById(id));
        return "shopping-list/send-food-to-place";
    }
    @PostMapping("/shopping-list/send-food-to-place/{id}")
    public String sendFood(@ModelAttribute Food food,RedirectAttributes redirectAttributes){
        System.out.println(food);
        food.setIdOfUser(userService.getCurrentUserId());
        foodService.saveFood(food);
        List<Food>shoppingList=foodService.getFoodByPlace("shopping_list",userService.getCurrentUserId());
        System.out.println(shoppingList);
        redirectAttributes.addFlashAttribute("foods", shoppingList);
        return "redirect:/shopping-list";
    }
}
