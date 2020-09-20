package by.taravsky.taskmanager.service.parserAv;

import by.taravsky.taskmanager.model.Auto;
import by.taravsky.taskmanager.model.Filter;
import by.taravsky.taskmanager.repo.AutoRepository;
import by.taravsky.taskmanager.repo.FilterRepository;
import by.taravsky.taskmanager.service.toSender.SaveAutoInSender;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ParseAvPrivate {

    @Autowired
    private FilterRepository filterRepository;
    @Autowired
    private SaveAutoInSender saveAutoInSender;
    @Autowired
    private SaveAvToAuto saveAvToAuto;

    public void HtmlToSet () throws IOException, InterruptedException{


        Document doc = Jsoup.connect("https://cars.av.by/search?sort=date")
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();
        Thread.sleep(1500);
        Document doc2 =  Jsoup.connect("https://cars.av.by/search/page/2?sort=date")
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        doc = (Document) doc.append(String.valueOf(doc2));


        Elements carElements = doc.getElementsByAttributeValue("class", "listing-item");


        carElements.forEach(carElement -> {

            Auto auto = new Auto();

            String url = carElement.getElementsByAttributeValue("class", "listing-item-image-in")
                    .select("a[href]")
                    .attr("href");
            auto.setUrl(url);

           // try { parsePhone.phone(url); } catch (IOException | InterruptedException e) { e.printStackTrace(); }

            String[] brandAndModel = url.split("/");
            auto.setBrand(brandAndModel[3]);
            auto.setModel(brandAndModel[4]);

            String year = carElement.select( "div.listing-item-price > span" ).text();
            auto.setYear(Integer.parseInt(year));

            String cost = carElement.select( "div.listing-item-price > small" )
                    .text()
                    .replace(" ","");
            auto.setCost(Long.parseLong(cost));

            String city = carElement.select( "p.listing-item-location")
                    .text();
            auto.setCity(city);

            String description = carElement.select( "div.listing-item-desc")
                    .text();
            String[] descriptionArray = description.split(",");

            if (descriptionArray[2].equals(" электро")) {
                String engine = descriptionArray[2].replace(" ", "");
                auto.setEngine(engine);

                String transmission = descriptionArray[1].replace(" ", "");
                auto.setTransmission(transmission);

                String body = descriptionArray[3].replace(" ", "");
                auto.setBody(body);

                String mileage = descriptionArray[4]
                        .replace(" км", "")
                        .replace(" ", "")
                        .replace("миль","");
                auto.setMileage(Long.parseLong(mileage));

            } else {
                String transmission = descriptionArray[1].replace(" ", "");
                auto.setTransmission(transmission);

                String body = descriptionArray[4].replace(" ", "");
                auto.setBody(body);

                String mileage = descriptionArray[5]
                        .replace(" км", "")
                        .replace(" ", "")
                        .replace("миль","");
                auto.setMileage(Long.parseLong(mileage));

                String capacity = descriptionArray[2].replace("л.","").replace(" ", "");
                auto.setCapacity(Float.parseFloat(capacity));

                String engine = descriptionArray[3].replace(" ", "");
                auto.setEngine(engine);
            }

            saveAvToAuto.saveToAuto(url, auto);
        });

    }
}
