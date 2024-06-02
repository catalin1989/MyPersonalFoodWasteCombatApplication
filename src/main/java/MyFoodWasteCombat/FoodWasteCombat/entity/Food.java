package MyFoodWasteCombat.FoodWasteCombat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity(name="food")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food implements Comparable<Food> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int quantity;
    private LocalDate expirationDate;
    private String place;

    public Food(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
    public Food(String name, int quantity,String place) {
        this.name = name;
        this.quantity = quantity;
        this.place = place;
    }


    @Override
    public int compareTo(Food o) {

        if(this.name.compareTo(o.name)==0){
            return this.getExpirationDate().compareTo(o.getExpirationDate());
        }
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", expirationDate=" + expirationDate +
                ", place='" + place + '\'' +
                '}';
    }
}
