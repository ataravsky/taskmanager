package by.taravsky.taskmanager.controller;

import by.taravsky.taskmanager.model.Filter;
import by.taravsky.taskmanager.model.User;
import by.taravsky.taskmanager.repo.FilterRepository;
import by.taravsky.taskmanager.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.io.IOException;


@Controller
public class FilterController {
    @Autowired
    private FilterRepository filterRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/filter")
    public String allFilter(Model model) throws IOException {
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        Iterable<Filter> filter = filterRepository.findByUsercurrent(auth);
        model.addAttribute("filter", filter);
        return "filter";
    }


    @PostMapping("/filter")
    public String addFilter(Filter newFilter, Model model) {
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        User user1 = userRepository.findByUsername(auth);
        String email = user1.getEmail();
        Long telegramuserid = user1.getTelegramuserid();
        if (email == null ) {
            return "redirect:/cabinet";
        } else {
            newFilter.setEmailcurrent(email);
            newFilter.setUsercurrent(auth);
            newFilter.setTelegramuserid(telegramuserid);
            filterRepository.save(newFilter);
            Iterable<Filter> filter = filterRepository.findByUsercurrent(auth);
            model.addAttribute("filter", filter);
            return "filter";
        }
    }

    @GetMapping("/filter/{id}/delete")
    public String deleteFilter(@PathVariable("id") Long id) {
        filterRepository.deleteById(id);
        return "redirect:/filter";
    }

    @GetMapping("/")
    public String main() {
        return "redirect:/filter";
    }

}
