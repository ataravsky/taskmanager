package by.taravsky.taskmanager.service.telegram;


import by.taravsky.taskmanager.model.Sender;
import by.taravsky.taskmanager.model.Telegramid;
import by.taravsky.taskmanager.repo.TelegramRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@Service
public class Bot extends TelegramLongPollingBot {

    @Autowired
    private TelegramRepository telegramRepository;

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {

            if (message.getText().equals("1")) {
                if(telegramRepository.findByChatid(message.getChatId()) == null) {
                    Telegramid telegramid = new Telegramid();
                    telegramid.setChatid(message.getChatId());
                    int code = (int) (Math.random() * 899999 + 100000);
                    telegramid.setCode((long) code);
                    telegramRepository.save(telegramid);
                    sendMsg(message, "Ваш код: " + code);
                } else {
                    Telegramid telegramid = telegramRepository.findByChatid(message.getChatId());
                    int code = (int) (Math.random() * 899999 + 100000);
                    telegramid.setCode((long) code);
                    telegramRepository.save(telegramid);
                    sendMsg(message, "Ваш новый код: " + code);
                }
            }
        }
    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Sender sender, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
//        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setChatId(sender.getTelegramuserid());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        // Return bot username
        // If bot username is @MyAmazingBot, it must return 'MyAmazingBot'
        return "autoPeelotqueBot";
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather
        return "1313657908:AAEj2p4b5c8GHznc8TdbH1rxkALCH2-XWpM";
    }
}
