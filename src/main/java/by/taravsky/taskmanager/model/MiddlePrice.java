package by.taravsky.taskmanager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@Table(name = "middleprice")
public class MiddlePrice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String brand;
    private String model;
    private Integer year;
    private Long MiddlePrice;
    private Integer count;
    private Long sum;

    @Override
    public boolean equals(Object o) {
        Auto auto = (Auto) o;
        return ((Objects.equals(model, auto.getModel())) &&
                                (Objects.equals(brand, auto.getBrand())) &&
                                (Objects.equals(year, auto.getYear())));
    }
}
