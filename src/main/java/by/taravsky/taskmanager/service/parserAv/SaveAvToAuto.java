package by.taravsky.taskmanager.service.parserAv;

import by.taravsky.taskmanager.model.Auto;
import by.taravsky.taskmanager.model.Filter;
import by.taravsky.taskmanager.repo.AutoRepository;
import by.taravsky.taskmanager.repo.FilterRepository;
import by.taravsky.taskmanager.service.functional.AddMiddlePriceOfAuto;
import by.taravsky.taskmanager.service.functional.RatioToPrice;
import by.taravsky.taskmanager.service.toSender.SaveAutoInSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveAvToAuto {

    @Autowired
    private AutoRepository autoRepository;
    @Autowired
    private FilterRepository filterRepository;
    @Autowired
    private SaveAutoInSender saveAutoInSender;
    @Autowired
    private AddMiddlePriceOfAuto addMiddlePriceOfAuto;
    @Autowired
    private RatioToPrice ratioToPrice;

    public void saveToAuto(String url, Auto auto) {
        if (autoRepository.findByUrl(url).isEmpty()) {
            addMiddlePriceOfAuto.addMiddlePriceOfAuto(auto);
            ratioToPrice.ratioPrice(auto);
            autoRepository.save(auto);
            System.out.println(auto.getProfit());
            Iterable<Filter> filters = filterRepository.findByBrand(auto.getBrand());
            saveAutoInSender.saveToSender(filters, auto);
        }
    }
}
