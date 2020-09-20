package by.taravsky.taskmanager.controller;

import by.taravsky.taskmanager.model.Filter;
import by.taravsky.taskmanager.repo.FilterRepository;
import by.taravsky.taskmanager.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/cabinet")
    public String redirectCabinet() throws IOException {
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        Long id = userRepository.findByUsername(auth).getId();

        return "redirect:/user/" + id;
    }
}
