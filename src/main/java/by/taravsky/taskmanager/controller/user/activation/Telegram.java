package by.taravsky.taskmanager.controller.user.activation;

import by.taravsky.taskmanager.model.Telegramid;
import by.taravsky.taskmanager.model.User;
import by.taravsky.taskmanager.repo.TelegramRepository;
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
public class Telegram {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TelegramRepository telegramRepository;

    @GetMapping("/user/{id}/telegram")
    public String userTelegram(@PathVariable Long id, Model model) {
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        long idc = userRepository.findByUsername(auth).getId();
        if (id != idc) {
            return "redirect:/user/" + idc;
        }

        User user = userRepository.findById(id).orElseThrow();
        model.addAttribute("user", user);
        return "user/telegram";
    }

    @PostMapping("/user/{id}/telegram")
    public String userAddTelegram(@PathVariable Long id, @RequestParam String telegramactivated,
                               Model model) {
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        long idc = userRepository.findByUsername(auth).getId();
        if (id != idc) {
            return "redirect:/user/" + idc;
        }

        User user = userRepository.findById(id).orElseThrow();
        System.out.println(user.getTelegramuserid());
        if (telegramRepository.findByCode(Long.parseLong(telegramactivated)) != null && user.getTelegramuserid() == null ) {
            Telegramid telegramid = telegramRepository.findByCode(Long.parseLong(telegramactivated));
            user.setTelegramuserid(telegramid.getChatid());
            userRepository.save(user);
            telegramRepository.delete(telegramid);
            model.addAttribute("user", user);
            return "redirect:/user/" + idc;
        } else {
            return "redirect:/user/" + idc + "/telegram";
        }
    }


    @GetMapping("/user/{id}/telegram/delete")
    public String userTelegramDelete(@PathVariable Long id, Model model) {
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        long idc = userRepository.findByUsername(auth).getId();
        if (id != idc) {
            return "redirect:/user/" + idc;
        }
        User user = userRepository.findById(id).orElseThrow();
        user.setTelegramuserid(null);
        userRepository.save(user);
        return "redirect:/user/" + id;
    }
}
