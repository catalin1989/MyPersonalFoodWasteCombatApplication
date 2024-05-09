package MyFoodWasteCombat.FoodWasteCombat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyFreezerController {
    @GetMapping("/freezer")

    public String getPage() {
        return "freezer";
    }
}
