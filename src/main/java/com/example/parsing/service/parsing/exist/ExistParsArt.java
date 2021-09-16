package com.example.parsing.service.parsing.exist;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExistParsArt {

    public static List<List<String>> emexParsing(WebDriver driver, String nameArt, String nameBrend) throws InterruptedException {

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date nowTime = new Date();

        List<List<String>> products = new ArrayList<List<String>>();
        String name = nameArt;
        List<String> listLink = new ArrayList<>();

        List<WebElement> containerAll = driver.findElements(By.className("row-container"));

        for (WebElement xxx : containerAll) {

            String brand = "";
            String number = "";
            String group = "";

            List<String> listForProducts = new ArrayList<String>();

            try {
                WebElement artName = xxx.findElement(By.className("art"));
                WebElement artNumber = xxx.findElement(By.className("partno"));
                WebElement artDescript = xxx.findElement(By.className("descr"));
                //WebElement artPrice = xxx.findElement(By.className("price"));


                try {
                    brand = artName.getText().replaceAll(";", ",");
                } catch (Exception e) {

                }

                try {
                    number = artNumber.getText().replaceAll(";", ",");
                } catch (Exception e) {

                }

                try {
                    group = artDescript.getText().replaceAll(";", ",");
                } catch (Exception e) {

                }

                listForProducts.add(nameBrend);
                listForProducts.add(name);
                listForProducts.add(brand);
                listForProducts.add(number);
                listForProducts.add(group);
                listForProducts.add(sdfDate.format(nowTime));


                String art = artNumber.getText();   //для раскрытия карточек продукта


                if (art.indexOf("..") != -1) {

                    String link = artDescript.getAttribute("href");

                    listLink.add(link);



/*                    Thread.sleep(1000);

                    System.out.println(driver.switchTo().activeElement().findElement(By.tagName("h1")).getText());
                    System.out.println(driver.switchTo().activeElement().findElement(By.tagName("h1")).getText());

                    String artNNN = driver.switchTo().frame(1).findElement(By.tagName("h1")).getText();
                    Thread.sleep(2000);
                    System.out.println(artNNN);

                    listForProducts.add(artNNN);

                    driver.switchTo().defaultContent();

                    WebElement closeBtn = driver.findElement(By.id("iframeheader"));

                    closeBtn.click();

                    Thread.sleep(3000);*/

                }

 /*                String art = artNumber.getText().substring(2);   //для раскрытия карточек продукта
                art = art.substring(0, art.length() - 2);
               if (art.equals("......")){
                    artDescript.click();

                    System.out.println("Проба");

                    Thread.sleep(3000);



*//*
                    System.out.println(driver.switchTo().activeElement().findElement(By.tagName("h1")).getText());
                    System.out.println(driver.switchTo().activeElement().findElement(By.tagName("h1")).getText());*//*


                        String artNNN = driver.switchTo().frame(1).findElement(By.tagName("h1")).getText();
                    Thread.sleep(2000);
                        System.out.println(artNNN);



                    listForProducts.add(artNNN);

                    driver.switchTo().defaultContent();





                    WebElement closeBtn = driver.findElement(By.id("iframeheader"));

                    closeBtn.click();

                    Thread.sleep(3000);
                } else {
                    listForProducts.add(artNumber.getText().replaceAll(",", ";"));
                }*/


            } catch (Exception e) {

            }


            if (number.indexOf("..") != -1) {

            } else {
                products.add(listForProducts);
            }


        }


        for (String xxx : listLink) {
            //System.out.println(xxx);
            driver.get(xxx);
            Thread.sleep(1000);

            List<String> listForProducts = new ArrayList<String>();

            WebElement element1 = driver.findElement(By.id("ctl00_b_ctl00_hlMainLink"));
            WebElement element2 = driver.findElement(By.id("ctl00_b_ctl00_hlAbout"));
            WebElement element3 = driver.findElement(By.className("subtitle"));

            String brandNew = element2.getText().replaceAll("О компании \"", "").replaceAll("\"", "");
            String artNew = element1.getText().replace(brandNew + " ", "");

            listForProducts.add(nameBrend);
            listForProducts.add(name);
            listForProducts.add(brandNew);
            listForProducts.add(artNew);
            listForProducts.add(element3.getText().replaceAll(";", ","));
            listForProducts.add(sdfDate.format(nowTime));

            products.add(listForProducts);
            //System.out.println(element1.getText());

        }


        return products;

    }
}
