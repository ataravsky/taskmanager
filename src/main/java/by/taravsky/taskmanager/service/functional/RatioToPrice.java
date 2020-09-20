package by.taravsky.taskmanager.service.functional;

import by.taravsky.taskmanager.model.Auto;
import by.taravsky.taskmanager.model.MiddlePrice;
import by.taravsky.taskmanager.repo.AutoRepository;
import by.taravsky.taskmanager.repo.MiddlePriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatioToPrice {
    @Autowired
    private MiddlePriceRepository middlePriceRepository;
    @Autowired
    private AutoRepository autoRepository;

    public Auto ratioPrice(Auto auto) {
        MiddlePrice middlePrice = middlePriceRepository.findByBrandAndModelAndYear(auto.getBrand(), auto.getModel(), auto.getYear());
        double midPrice = (double) middlePrice.getMiddlePrice();
        double autoPrice = (double) auto.getCost();
        long profit = (long)(((midPrice - autoPrice) / midPrice)*100.0);
        auto.setProfit(profit);
        System.out.println(profit);
        return auto;
    }
}
