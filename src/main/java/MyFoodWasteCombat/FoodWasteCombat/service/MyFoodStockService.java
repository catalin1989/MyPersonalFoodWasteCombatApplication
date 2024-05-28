package MyFoodWasteCombat.FoodWasteCombat.service;

import MyFoodWasteCombat.FoodWasteCombat.entity.Food;
import MyFoodWasteCombat.FoodWasteCombat.entity.FoodStock;
import MyFoodWasteCombat.FoodWasteCombat.repository.MyFoodStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class MyFoodStockService {

    private final MyFoodStockRepository stockRepository;

    public List<FoodStock> getAllFoodStock() {
        return stockRepository.findAll();
    }

    public void addFoodStock(FoodStock foodStock) {
        stockRepository.save(foodStock);
    }

    public void deleteAllFoodStock() {
        stockRepository.deleteAll();
    }

    public FoodStock getFoodStockById(Long id) {
       return stockRepository.getReferenceById(id);
    }
    public void updateFoodStock(FoodStock foodStock){
        stockRepository.save(foodStock);
    }
    public void deleteById(Long id) {
        stockRepository.deleteById(id);
    }

}
