package by.taravsky.taskmanager.repo;


import by.taravsky.taskmanager.controller.user.activation.Telegram;
import by.taravsky.taskmanager.model.Telegramid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface TelegramRepository extends CrudRepository<Telegramid, Long> {
    Telegramid findByChatid(Long chatid);
    Telegramid findByCode(Long code);
}
