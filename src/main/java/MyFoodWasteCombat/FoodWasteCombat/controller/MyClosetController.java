package MyFoodWasteCombat.FoodWasteCombat.controller;

import MyFoodWasteCombat.FoodWasteCombat.food.Food;
import MyFoodWasteCombat.FoodWasteCombat.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MyClosetController implements MyController {

    private final FoodService foodService;

    @Autowired
    public MyClosetController(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    @GetMapping("/closet")
    public String getPage() {

        return "closet";
    }

    @PostMapping("/closet/add")
    public String addFoodInCloset(@RequestParam("name") String name, @RequestParam("quantity") int quantity, @RequestParam("expiration_date") LocalDate expirationDate) {

        foodService.insertFoodInCloset(name, quantity, expirationDate);

        return "redirect:/closet";
    }

    @GetMapping("/closet/foods")
    public String viewAllFoodsInCloset(Model model){
    List<Food> foods=foodService.selectAllFoodsInCloset();
    model.addAttribute("foods",foods);
    return "closet_foods";
    }

}
