package com.example.parsing.service.parsing.parts66;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.*;

public class PartsParsArt {

    public static List<List<String>> autodocParsing(WebDriver driver, String nameArt, String nameBrend) throws InterruptedException {

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date nowTime = new Date();
        String dateString = sdfDate.format(nowTime);

        Set<List<String>> products = new HashSet<>();

        try {


            String name = nameArt;

            Thread.sleep(3000);


            Document doc = Jsoup.parse(String.valueOf(driver.getPageSource()));

            Elements elements = doc.getElementsByAttributeValue("class", "filter-search-container");
            Element elementAnalog = elements.get(0);

            Elements elementsAnalog = elementAnalog.getElementsByAttributeValueStarting("class", "search-row");


            for (Element xxx : elementsAnalog) {

                String brand = "";
                String number = "";
                String group = "";

                try {
                    brand = String.valueOf(xxx).split("data-filter-brand=\"")[1].split("\" ")[0];
                } catch (Exception e) {

                }

                try {
                    number = String.valueOf(xxx).split("data-filter-article=\"")[1].split("\" ")[0];
                } catch (Exception e) {

                }

                try {
                    group = String.valueOf(xxx).split("search-col__spare_info\" data-tmp=\"")[1].split("\">")[0].replaceAll(";", ",");
                } catch (Exception e) {

                }

                if (!brand.equals("")) {
                    List<String> product = new ArrayList<>();
                    product.add(nameBrend);
                    product.add(nameArt);
                    product.add(brand);
                    product.add(number);
                    product.add(group);
                    product.add(dateString);

                    products.add(product);
                }


            }

        } catch (Exception m) {

        }

        List<List<String>> productsList = new ArrayList<>();
        productsList.addAll(products);


        return productsList;


    }
}
