package by.taravsky.taskmanager.service.activate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailActivate{

    @Autowired
    private JavaMailSender emailSender;

    public void sendCode(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public long generatedCode() {
        long code = (long) (Math.random() * 899999 + 100000);
        return code;
    }
}