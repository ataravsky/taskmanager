package by.taravsky.taskmanager.service.validate;

import by.taravsky.taskmanager.repo.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ValidateUserById {

    public String validate(UserRepository userRepository, Long id) {
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        long idc = userRepository.findByUsername(auth).getId();
        if (id != idc) {
            return "redirect:/user/" + idc;
        }
        return "redirect:/user/" + idc;
    }
}
