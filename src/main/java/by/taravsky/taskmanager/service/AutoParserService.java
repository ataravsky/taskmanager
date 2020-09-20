package by.taravsky.taskmanager.service;


import by.taravsky.taskmanager.service.parserAv.ParseAvCompany;
import by.taravsky.taskmanager.service.parserAv.ParseAvPrivate;
import by.taravsky.taskmanager.service.toSender.AutoSender;
import by.taravsky.taskmanager.service.toSender.SaveAutoInSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AutoParserService {
    @Autowired
    private ParseAvPrivate parseAvPrivate;
    @Autowired
    private ParseAvCompany parseAvCompany;
    @Autowired
    private AutoSender autoSender;

    @Scheduled(fixedRate = 60000)
    public void AutoParserService() throws IOException, InterruptedException {
        parseAvPrivate.HtmlToSet();
        parseAvCompany.HtmlToSet();
        autoSender.AutoSender();
}


}
