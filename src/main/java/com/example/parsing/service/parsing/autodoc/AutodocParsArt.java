package com.example.parsing.service.parsing.autodoc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AutodocParsArt {

    public static List<List<String>> autodocParsing(WebDriver driver, String nameArt, String nameBrend) throws InterruptedException {


        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date nowTime = new Date();

        List<List<String>> products = new ArrayList<List<String>>();
        String name = nameArt;

        try {


            Thread.sleep(3000);

            Document doc = Jsoup.parse(String.valueOf(driver.getPageSource()));

            Elements elements = doc.getElementsByAttributeValue("id", "analogs");
            Element elementAnalog = elements.get(0);

            Elements elementsAnalog = elementAnalog.getElementsByAttributeValue("class", "pointer title");


            for (Element xxx : elementsAnalog) {
                String brand = "";
                String number = "";
                String group = "";

                List<String> product = new ArrayList<>();

                try {
                    brand = String.valueOf(xxx).split("\">")[String.valueOf(xxx).split("\">").length - 2].split("</a></b> ")[0];
                } catch (Exception e) {

                }

                try {
                    number = String.valueOf(xxx).split("\">")[String.valueOf(xxx).split("\">").length - 2].split("</a></b> ")[1].split("\n")[0].trim();
                } catch (Exception e) {

                }

                try {
                    group = String.valueOf(xxx).split("\">")[String.valueOf(xxx).split("\">").length - 1].split("\n")[1].split(" </div>")[0].trim().replace("\n", "");
                } catch (Exception e) {

                }

                product.add(nameBrend);
                product.add(nameArt);
                product.add(brand);
                product.add(number);
                product.add(group);
                product.add(sdfDate.format(nowTime));

                products.add(product);
            }


        } catch (Exception m) {

        }


        return products;


    }
}
