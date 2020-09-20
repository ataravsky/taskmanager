package by.taravsky.taskmanager;

import by.taravsky.taskmanager.service.telegram.Bot;

import by.taravsky.taskmanager.service.telegram.StartBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@SpringBootApplication
@EnableScheduling
public class TaskmanagerApplication {

    public static void main(String[] args) throws TelegramApiRequestException {
        ApiContextInitializer.init();
        SpringApplication.run(TaskmanagerApplication.class, args);
    }

}
