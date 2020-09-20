package by.taravsky.taskmanager.model;

import com.sun.istack.Nullable;
import lombok.Data;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Data
public class Filter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Nullable
    private String brand;
    @Nullable
    private String model;
    @Nullable
    private String yearfrom;
    @Nullable
    private String yearbefore;
    @Nullable
    private String costfrom;
    @Nullable
    private String costbefore;
    @Nullable
    private String city;
    @Nullable
    private String transmission;
    @Nullable
    private String engine;
    @Nullable
    private String body;
    @Nullable
    private String mileagefrom;
    @Nullable
    private String mileagebefore;
    @Nullable
    private String capacityfrom;
    @Nullable
    private String capacitybefore;
    @Nullable
    private String emailcurrent;
    @Nullable
    private String usercurrent;
    @Nullable
    private String profit;
    @Nullable
    private Long telegramuserid;

    @Override
    public boolean equals(Object o) {
        Auto auto = (Auto) o;
        return
                (
                        (Objects.equals(model, auto.getModel()) || model.equals("")) &&
                        ((Integer.parseInt(yearfrom) <= auto.getYear()) && (Integer.parseInt(yearbefore) >= auto.getYear())) &&
                        ((Long.parseLong(costfrom) <= auto.getCost()) && (Long.parseLong(costbefore) >= auto.getCost())) &&
                        ((Long.parseLong(mileagefrom) <= auto.getMileage()) && (Long.parseLong(mileagebefore) >= auto.getMileage())) &&
                                ((Long.parseLong(profit) <= auto.getProfit()) || profit.equals("")) &&
                        (Objects.equals(city, auto.getCity()) || city.equals("")) &&
                        (Objects.equals(transmission, auto.getTransmission()) || transmission.equals("")) &&
                        (Objects.equals(engine, auto.getEngine()) || engine.equals("")) &&
                        (capacityfrom.equals("") && capacitybefore.equals("") ||
                                ((Float.parseFloat(capacityfrom) <= auto.getCapacity()) && (Float.parseFloat(capacitybefore)) >= auto.getCapacity()))
                );
    }

}
