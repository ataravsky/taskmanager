package by.taravsky.taskmanager.controller;

import by.taravsky.taskmanager.model.Auto;
import by.taravsky.taskmanager.repo.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@Controller
public class AutoController {
    @Autowired
    private AutoRepository autoRepository;

    @GetMapping("/auto1")
    public String allAuto1(Model model) throws IOException {
        List<Auto> pageAuto = autoRepository.findAll();

        model.addAttribute("pageAuto", pageAuto);
        model.addAttribute("size", pageAuto.size());
        return "auto1";
    }

    @PostMapping("/auto1")
    public String autoFilter(Model model,
                             @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                             @RequestParam(value = "brand") String brand,
                             @RequestParam(value = "body") String body,
                             @RequestParam(value = "model") String model1,
                             @RequestParam(value = "city") String city,
                             @RequestParam(value = "mileagefrom") Long mileagefrom,
                             @RequestParam(value = "mileagebefore") Long mileagebefore,
                             @RequestParam(value = "transmission") String transmission,
                             @RequestParam(value = "engine") String engine,
                             @RequestParam(value = "costfrom") Long costfrom,
                             @RequestParam(value = "costbefore") Long costbefore,
                             @RequestParam(value = "capacityfrom") Float capacityfrom,
                             @RequestParam(value = "capacitybefore") Float capacitybefore,
                             @RequestParam(value = "yearfrom") Integer yearfrom,
                             @RequestParam(value = "yearbefore") Integer yearbefore
    ) throws IOException {
        List<Auto> pageAuto = autoRepository.findByFilter(model1, engine, city, mileagefrom, mileagebefore, body, brand,
                transmission, costfrom, costbefore, capacityfrom, capacitybefore, yearfrom, yearbefore);

        model.addAttribute("size", pageAuto.size());
        model.addAttribute("pageAuto", pageAuto);

        return "auto1";
    }
}
