package by.taravsky.taskmanager.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Sender {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String brand;
    private String model;
    private int year;
    private long cost;
    private String city;
    private String transmission;
    private String engine;
    private String body;
    private Long mileage;
    private float capacity;
    private String email;
    private Long profit;
    private Long telegramuserid;
    private String url;

    @Override
    public String toString() {
        return "Выгода " + profit + "% от средней цены." + "\n" +
                "Авто: " + brand + " " + model +"\n" +
                "Цена: " + cost + "  Год выпуска: " + year + "\n" +
                "Город: " + city + "  КПП: " + transmission + "\n" +
                "Двигатель: " + engine + "  Кузов: " + body +"\n" +
                "Пробег: " + mileage + "  Объем: " + capacity + "\n" +
                "Ссылка: " + url;
    }
}