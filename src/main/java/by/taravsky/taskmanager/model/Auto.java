package by.taravsky.taskmanager.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long profit;
    private String url;
    private String brand;
    private String model;
    private Integer year;
    private Long cost;
    private String city;
    private String transmission;
    private String engine;
    private String body;
    private Long mileage;
    private Float capacity;
//    private String company;
//    private String phone;

}

