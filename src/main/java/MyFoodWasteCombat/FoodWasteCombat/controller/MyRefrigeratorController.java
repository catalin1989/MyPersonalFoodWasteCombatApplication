package MyFoodWasteCombat.FoodWasteCombat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MyRefrigeratorController implements MyController{
    @Override
    @GetMapping("refrigerator")
    public String getPage() {
        return "refrigerator";
    }
}
