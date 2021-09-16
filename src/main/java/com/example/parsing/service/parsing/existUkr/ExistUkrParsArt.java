package com.example.parsing.service.parsing.existUkr;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExistUkrParsArt {

    public static List<List<String>> emexParsing(WebDriver driver, String nameArt, String nameBrend) throws InterruptedException {

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date nowTime = new Date();

        List<List<String>> products = new ArrayList<List<String>>();
        String name = nameArt;

        List<WebElement> containerAll = driver.findElements(By.className("nested-row-0"));

        System.out.println("333");
        System.out.println(containerAll.get(0).getText());

        for (WebElement xxx : containerAll) {

            List<String> listForProducts = new ArrayList<String>();

            try {


                WebElement artName = xxx.findElement(By.className("trademark"));

                // List<WebElement> artNumber = artName.findElements(By.className("name"));
                WebElement artNumber = xxx.findElement(By.tagName("a"));
                WebElement artCount = xxx.findElement(By.className("quantity"));
                WebElement artPrice = xxx.findElement(By.className("price"));


                listForProducts.add(nameBrend);
                System.out.println(nameBrend);

                listForProducts.add(name);
                System.out.println(name);

                listForProducts.add(artName.getText().replaceAll(",", ";"));
                System.out.println(artName.getText().replaceAll(",", ";"));

                listForProducts.add(artNumber.getText());
                System.out.println(artNumber.getText());

                listForProducts.add("Описание");
                System.out.println("Описание");

                listForProducts.add(artCount.getText().replaceAll(",", ";"));
                System.out.println(artCount.getText().replaceAll(",", ";"));

                listForProducts.add(artPrice.getText().replaceAll(",", ";"));
                System.out.println(artPrice.getText().replaceAll(",", ";"));


            } catch (Exception e) {

            }


            listForProducts.add("ExistUkr");

            listForProducts.add(sdfDate.format(nowTime));

            products.add(listForProducts);

        }


        return products;

    }
}
