package MyFoodWasteCombat.FoodWasteCombat.service;

import MyFoodWasteCombat.FoodWasteCombat.food.Food;
import MyFoodWasteCombat.FoodWasteCombat.repository.FoodInRefrigeratorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class FoodInRefrigeratorServiceTest {

    @Mock
    private FoodInRefrigeratorRepository foodInRefrigeratorRepository;;

    @InjectMocks
    private FoodInRefrigeratorService foodInRefrigeratorService;

    @Test
    void testingTheRemainingFoodMethod_expectedSuccess_resultSuccessfully(){
        LocalDate date1=LocalDate.of(2024,5,18);
        LocalDate date2=LocalDate.of(2024,5,17);
        LocalDate date3=LocalDate.of(2024,5,16);
        Food food1=new Food(1L,"Iaurt",2,date1);
        Food food2=new Food(2L,"Iaurt",3,date2);
        Food food3=new Food(3L,"Iaurt",4,date3);
        List<Food> expectedFoods=new ArrayList<>();
        expectedFoods.add(food1);
        expectedFoods.add(food2);
        expectedFoods.add(food3);
        List<Food> remainingFoods=foodInRefrigeratorService.determineRemainingFoodItems(expectedFoods,6);
        assertEquals(2,remainingFoods.size());
    }
}