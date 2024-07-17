package MyFoodWasteCombat.FoodWasteCombat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/MyFoods")
    public String myFoodWasteCombat() {
        return "index";
    }
}
