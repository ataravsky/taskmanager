package by.taravsky.taskmanager.service.toSender;

import by.taravsky.taskmanager.model.Sender;
import by.taravsky.taskmanager.repo.SenderRepository;
import by.taravsky.taskmanager.service.telegram.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AutoSender {
    @Autowired
    private SendEmail sendEmail;

    @Autowired
    private SenderRepository senderRepository;

    @Autowired
    private Bot bot;

    public void AutoSender() throws IOException, InterruptedException {
        List<Sender> senders = senderRepository.findAll();
        for (Sender sender : senders) {
            String text = sender.toString();
            String to = sender.getEmail();
            String subject = "Авто-объявления av.by";

            if (sender.getTelegramuserid() != null && sender.getTelegramuserid() != 0) {
                bot.sendMsg(sender, text);
                System.out.println("отправил в телегу");
            }  else {
                sendEmail.send(to, subject, text);
                System.out.println("отправил на почту");
            }

            senderRepository.delete(sender);
        }
    }




}