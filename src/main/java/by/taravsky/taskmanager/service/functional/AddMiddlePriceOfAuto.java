package by.taravsky.taskmanager.service.functional;

import by.taravsky.taskmanager.model.Auto;
import by.taravsky.taskmanager.model.MiddlePrice;
import by.taravsky.taskmanager.repo.MiddlePriceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class AddMiddlePriceOfAuto {
    @Autowired
    private MiddlePriceRepository middlePriceRepository;

    public void addMiddlePriceOfAuto (Auto auto) {
        MiddlePrice middlePrice = middlePriceRepository.findByBrandAndModelAndYear(auto.getBrand(), auto.getModel(), auto.getYear());
        if (middlePrice != null && middlePrice.equals(auto)) {
            middlePrice.setCount(middlePrice.getCount()+1);
            middlePrice.setSum(middlePrice.getSum()+auto.getCost());
            middlePrice.setMiddlePrice(middlePrice.getSum()/middlePrice.getCount());
            middlePriceRepository.save(middlePrice);
        } else {
            MiddlePrice middlePrice1 = new MiddlePrice();
            middlePrice1.setBrand(auto.getBrand());
            middlePrice1.setModel(auto.getModel());
            middlePrice1.setYear(auto.getYear());
            middlePrice1.setCount(1);
            middlePrice1.setSum(auto.getCost());
            middlePrice1.setMiddlePrice(auto.getCost());
            middlePriceRepository.save(middlePrice1);
        }

    }
}
