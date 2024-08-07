package MyFoodWasteCombat.FoodWasteCombat.controller;

import MyFoodWasteCombat.FoodWasteCombat.entity.User;
import MyFoodWasteCombat.FoodWasteCombat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping("/MyFoods")
    public String myFoodWasteCombat() {
        return "front_page";
    }
    @GetMapping("/welcome")
    public String welcome() {
        return "login/welcome";
    }
    @GetMapping("/register")
    public String register() {
        return "login/register";
    }
    @PostMapping("/register/submit")
    public String createUser(@ModelAttribute User user) {
        user.setRole("users");
        userService.saveUser(user);
        System.out.println("Created user: " + user.getUsername());
        return "redirect:/welcome";
    }
}
