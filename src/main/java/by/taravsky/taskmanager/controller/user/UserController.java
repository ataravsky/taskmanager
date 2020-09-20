package by.taravsky.taskmanager.controller.user;

import by.taravsky.taskmanager.model.User;
import by.taravsky.taskmanager.repo.UserRepository;
import by.taravsky.taskmanager.service.activate.EmailActivate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private EmailActivate emailActivate;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{id}")
    public String userCabinet(@PathVariable Long id, Model model) {
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        long idc = userRepository.findByUsername(auth).getId();
        if (id != idc) {
            return "redirect:/user/" + idc;
        }
        User user = userRepository.findById(id).orElseThrow();
        model.addAttribute("user", user);
        return "user/user";

    }

    @PostMapping("/user/{id}")
    public String addEmail(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElseThrow();
        return "user/user";
    }

}
