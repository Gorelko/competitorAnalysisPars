package com.example.parsing.service.parsing.existQuick;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExistQuickParsArt {

    public static List<List<String>> emexParsing(WebDriver driver, String nameArt, String nameBrend) throws InterruptedException {

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date nowTime = new Date();

        List<List<String>> products = new ArrayList<List<String>>();

        try {


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


                } catch (Exception e) {

                }


                products.add(listForProducts);


            }


        } catch (Exception m) {

        }


        return products;

    }
}
