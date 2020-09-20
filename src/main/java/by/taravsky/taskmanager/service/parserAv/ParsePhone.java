package by.taravsky.taskmanager.service.parserAv;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
public class ParsePhone {

    public void phone(String url) throws IOException, InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        WebElement contacts = driver.findElement(By.cssSelector(".card-contacts-phones-in a"));

        Actions ob = new Actions(driver);
        ob.click(contacts);
        org.openqa.selenium.interactions.Action action = ob.build();
        WebElement phone = driver.findElement(By.className("card-contacts-phones-in"));
        Thread.sleep(2000);
        String phon1 = driver.findElement(By.tagName("b")).getText();
        System.out.println(phon1);
        action.perform();






    }
}
