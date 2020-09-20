package by.taravsky.taskmanager.service.toSender;

import by.taravsky.taskmanager.model.Auto;
import by.taravsky.taskmanager.model.Filter;
import by.taravsky.taskmanager.model.Sender;
import by.taravsky.taskmanager.model.User;
import by.taravsky.taskmanager.repo.SenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SaveAutoInSender {
    @Autowired
    private SenderRepository senderRepository;


    public void saveToSender(Iterable<Filter> filters, Auto auto) {

        System.out.println("зашел в метод save");
        for (Filter filter : filters) {
            System.out.println("зашел в цикл save");
            if(filter.equals(auto)) {
                System.out.println("записал");

                    Sender sender = new Sender();
                    sender.setEmail(filter.getEmailcurrent());
                    sender.setBrand(auto.getBrand());
                    sender.setModel(auto.getModel());
                    sender.setBody(auto.getBody());
                    sender.setCapacity(auto.getCapacity());
                    sender.setCity(auto.getCity());
                    sender.setCost(auto.getCost());
                    sender.setEngine(auto.getEngine());
                    sender.setMileage(auto.getMileage());
                    sender.setTransmission(auto.getTransmission());
                    sender.setYear(auto.getYear());
                    sender.setProfit(auto.getProfit());
                    sender.setUrl(auto.getUrl());
                    sender.setTelegramuserid(filter.getTelegramuserid());
                    senderRepository.save(sender);
            }
        }
    }
}
