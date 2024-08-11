package MyFoodWasteCombat.FoodWasteCombat.entity;

import jakarta.persistence.*;
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
    private String unitMeasurement;
    private Long idOfUser;

    public Food(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
    public Food(String name, int quantity,String place) {
        this.name = name;
        this.quantity = quantity;
        this.place = place;
    }
    public Food(String name, int quantity,String place,Long idOfUser) {
        this.name = name;
        this.quantity = quantity;
        this.place = place;
        this.idOfUser = idOfUser;
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
                "name='" + name + '\'' +
                ", expirationDate=" + expirationDate +
                ", place='" + place + '\'' +
                ", idOfUser=" + idOfUser +
                ", quantity=" + quantity +
                '}';
    }
}
