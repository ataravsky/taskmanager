package by.taravsky.taskmanager.service.toSender;

import by.taravsky.taskmanager.model.Sender;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//@Service
public class SendTelegram extends TelegramLongPollingBot {

    public void sendMsg(Sender sender, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(sender.getTelegramuserid());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {

        return "autoPeelotqueBot";
    }

    @Override
    public String getBotToken() {
        return "1313657908:AAEj2p4b5c8GHznc8TdbH1rxkALCH2-XWpM";
    }
}
