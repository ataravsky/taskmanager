package by.taravsky.taskmanager.controller.user.activation;

import by.taravsky.taskmanager.model.User;
import by.taravsky.taskmanager.repo.UserRepository;
import by.taravsky.taskmanager.service.activate.EmailActivate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class email {
    @Autowired
    private EmailActivate emailActivate;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{id}/email")
    public String userEmail(@PathVariable Long id, Model model) {
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        long idc = userRepository.findByUsername(auth).getId();
        if (id != idc) {
            return "redirect:/user/" + idc;
        }

        User user = userRepository.findById(id).orElseThrow();
        model.addAttribute("user", user);
        return "user/email";
    }

    @PostMapping("/user/{id}/email")
    public String userAddEmail(@PathVariable Long id, @RequestParam String emailactivated,
                               Model model) {
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        long idc = userRepository.findByUsername(auth).getId();
        if (id != idc) {
            return "redirect:/user/" + idc;
        }

        User user = userRepository.findById(id).orElseThrow();

        if (user.getEmailactivated() == null && user.getEmailcode() == 0) {
            user.setEmailactivated(emailactivated);
            long a = emailActivate.generatedCode();
            user.setEmailcode(a);
            emailActivate.sendCode(emailactivated ,"Код активации", "Ваш код: " + a);
            userRepository.save(user);
        }

        model.addAttribute("user", user);
        return "user/email";
    }


    @PostMapping("/user/{id}/email/code")
    public String userAddEmailCode(@PathVariable Long id, @RequestParam String emailcode, Model model) {
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        long idc = userRepository.findByUsername(auth).getId();
        if (id != idc) {
            return "redirect:/user/" + idc;
        }

        User user = userRepository.findById(id).orElseThrow();

        if (user.getEmailactivated() != null && user.getEmailcode() != 0) {
            if (Long.parseLong(emailcode) == user.getEmailcode()) {
                user.setEmail(user.getEmailactivated());
                userRepository.save(user);
            }
        }
        return "redirect:/user/" + id;
    }

    @GetMapping("/user/{id}/email/delete")
    public String userEmailDelete(@PathVariable Long id, Model model) {
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        long idc = userRepository.findByUsername(auth).getId();
        if (id != idc) {
            return "redirect:/user/" + idc;
        }

        User user = userRepository.findById(id).orElseThrow();
        user.setEmail(null);
        user.setEmailcode(0);
        user.setEmailactivated(null);
        userRepository.save(user);
        return "redirect:/user/" + id;
    }
}
