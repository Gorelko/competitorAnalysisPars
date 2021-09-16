package com.example.parsing.service.parsing.adeo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.*;

public class AdeoParsArt {

    public static List<List<String>> emexParsing(WebDriver driver, String nameArt, String nameBrend) {

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date nowTime = new Date();
        String dateString = sdfDate.format(nowTime);

        Set<List<String>> products = new HashSet<>();
        String name = nameArt;


        Document doc = Jsoup.parse(String.valueOf(driver.getPageSource()));

        Elements elementsArt = doc.getElementsByTag("tr");

        for (int i = 6; i < elementsArt.size(); i++) {
            String brand = "";
            String number = "";
            String group = "";

            try {
                brand = elementsArt.get(i).attr("brand").replaceAll(";", ",");
            } catch (Exception e) {

            }
            try {
                number = elementsArt.get(i).attr("pn_clean").replaceAll(";", ",");
            } catch (Exception e) {

            }
            try {
                group = elementsArt.get(i).getElementsByAttributeValue("class", "price-desc").text().replaceAll(";", ",");
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


        List<List<String>> productsList = new ArrayList<>();
        productsList.addAll(products);

        return productsList;

    }
}
