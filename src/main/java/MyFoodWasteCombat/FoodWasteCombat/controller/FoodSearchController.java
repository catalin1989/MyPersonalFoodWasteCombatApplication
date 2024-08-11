package MyFoodWasteCombat.FoodWasteCombat.controller;

import MyFoodWasteCombat.FoodWasteCombat.entity.Food;
import MyFoodWasteCombat.FoodWasteCombat.service.MyFoodService;
import MyFoodWasteCombat.FoodWasteCombat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor

public class FoodSearchController {
    private final MyFoodService myFoodService;
    private final UserService userService;

    @GetMapping("search-for-food")
    public String searchForFood() {
        return "food-search/search-for-food";
    }

    @PostMapping("search-for-food")
    public String searchForFood(@RequestParam("name")String name, RedirectAttributes redirectAttributes) {
        System.out.println(userService.getCurrentUserId());
        List<Food>foodList=myFoodService.getAllFoodsWithName(name,userService.getCurrentUserId());

        redirectAttributes.addFlashAttribute("foods",foodList);
        return "redirect:/search-for-food";
    }
}
