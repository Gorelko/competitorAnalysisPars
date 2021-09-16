package com.example.parsing.service.parsing.emex;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmexParsArt {

    public static List<List<String>> emexParsing(WebDriver driver, String nameArt, String brendName) {

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date nowTime = new Date();

        List<List<String>> products = new ArrayList<List<String>>();
        String name = nameArt;
        String nameBrend = brendName;

        String nameMainStore = "Портал EMEX (нет инфы по складам)";

/*        List<WebElement> openInformation = driver.findElements(By.xpath("//span[contains(text(),'Остальные предложения')]"));

        int countOpenPosition = 0;


        for (WebElement openInform : openInformation) {

            countOpenPosition++;

            if (openInform.getText().equals("Остальные предложения"))
                try {
                    openInform.click();
                    //
                } catch (Exception e) {
                    System.out.println("Нажатие кнопки Остальные предложения невозможно!");
                }

            if (countOpenPosition == 35) {
                break;
            }
        }*/
        List<List<String>> allPosition = new ArrayList<List<String>>();

        try {


            List<WebElement> mainStoreFenox = driver.findElements(By.className("expandable-list"));


            for (WebElement elementMainStrore : mainStoreFenox) {

                WebElement brendsName = elementMainStrore.findElement(By.className("col")).findElement(By.className("detail-make"));
                WebElement brendsArt = elementMainStrore.findElement(By.className("col")).findElement(By.className("detail-numbers"));
                WebElement brendsDiscriptions = elementMainStrore.findElement(By.className("one-string"));

                String art[] = brendsArt.getText().split("\\r?\\n");

                String brendsNameStr = brendsName.getText();
                String brendsArtStr = art[0];
                String brendsDiscriptionsStr = brendsDiscriptions.getText();

                List<WebElement> brendsCount = elementMainStrore.findElements(By.className("rows-wrap"));
                for (WebElement count : brendsCount) {

                    List<WebElement> brendsCountRows = count.findElements(By.className("row"));

                    try {
                        for (WebElement brendsCountRow : brendsCountRows) {

                            List<WebElement> info = brendsCountRow.findElements(By.className("col"));

                            List<String> informForCount = new ArrayList<String>();

                            if (!info.get(3).getText().equals("")) {

                                informForCount.add(nameBrend);
                                informForCount.add(name);
                                informForCount.add(brendsNameStr);
                                informForCount.add(brendsArtStr);
                                informForCount.add(brendsDiscriptionsStr.replaceAll(";", ","));

/*                            informForCount.add(info.get(1).getText());


                            informForCount.add(info.get(3).getText());


                            informForCount.add(nameMainStore);*/

                                informForCount.add(sdfDate.format(nowTime));

                                allPosition.add(informForCount);

                            }


                        }
                    } catch (Exception e) {

                    }


                }

            }


            List<WebElement> mainStore = driver.findElements(By.className("analog-small-group"));


            for (WebElement elementMainStrore : mainStore) {

                WebElement brendsName = elementMainStrore.findElement(By.className("col")).findElement(By.className("detail-make"));
                WebElement brendsArt = elementMainStrore.findElement(By.className("col")).findElement(By.className("detail-numbers"));
                WebElement brendsDiscriptions = elementMainStrore.findElement(By.className("one-string"));

                String art[] = brendsArt.getText().split("\\r?\\n");

                String brendsNameStr = brendsName.getText();
                String brendsArtStr = art[0];
                String brendsDiscriptionsStr = brendsDiscriptions.getText();

                List<WebElement> brendsCount = elementMainStrore.findElements(By.className("rows-wrap"));
                for (WebElement count : brendsCount) {

                    List<WebElement> brendsCountRows = count.findElements(By.className("row"));

                    for (WebElement brendsCountRow : brendsCountRows) {

                        List<WebElement> info = brendsCountRow.findElements(By.className("col"));

                        List<String> informForCount = new ArrayList<String>();

                        if (!info.get(3).getText().equals("")) {

                            informForCount.add(nameBrend);
                            informForCount.add(name);
                            informForCount.add(brendsNameStr);
                            informForCount.add(brendsArtStr);
                            informForCount.add(brendsDiscriptionsStr.replaceAll(";", ","));

/*
                        informForCount.add(info.get(1).getText());


                        informForCount.add(info.get(3).getText());


                        informForCount.add(nameMainStore);
*/

                            informForCount.add(sdfDate.format(nowTime));

                            allPosition.add(informForCount);

                        }


                    }

                }

            }

        } catch (Exception m) {

        }

        return allPosition;

    }
}