package com.example.parsing.service.parsing.euroautoRozn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EuroautoRoznParsArt {

    public static List<List<String>> emexParsing(WebDriver driver, String nameArt, String nameBrend) {

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date nowTime = new Date();

        List<List<String>> products = new ArrayList<List<String>>();
        String name = nameArt;

        List<WebElement> mainBrand = driver.findElements(By.className("brand-group"));

        for (WebElement elementBrand : mainBrand) {
            List<WebElement> mainStore = elementBrand.findElements(By.className("num-title-block"));

            for (WebElement xxx : mainStore) {

                String brand = "";
                String number = "";
                String group = "";

                String text = xxx.getText().replaceAll("\n", " ");

                try {
                    brand = elementBrand.getAttribute("data-brand").replaceAll(";", ",");
                } catch (Exception e) {

                }

                try {
                    number = text.split(" Деталь на схеме ")[0].replaceAll(";", ",");
                } catch (Exception e) {

                }

                try {
                    group = text.split(" Деталь на схеме ")[1].replaceAll(";", ",");
                } catch (Exception e) {

                }

                List<String> product = new ArrayList<>();
                product.add(nameBrend);
                product.add(nameArt);
                product.add(brand);
                product.add(number);
                product.add(group);
                product.add(sdfDate.format(nowTime));

                products.add(product);

            }

        }


        return products;

    }
}
