package com.example.parsing.service.parsing.amtel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AmtelParsArt {

    public static List<List<String>> emexParsing(WebDriver driver, String nameArt, String nameBrend) {

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date nowTime = new Date();

        List<List<String>> products = new ArrayList<List<String>>();
        String name = nameArt;
        String nameMainStore = "Основной склад";

        List<WebElement> mainStore = driver.findElements(By.id("block-content-lc"));
        for (WebElement elementMainStrore : mainStore) {

            List<WebElement> brendsNameMainStore = elementMainStrore.findElements(By.className("old-appraise-item-brand"));
            List<WebElement> brendsArticleMainStore = elementMainStrore.findElements(By.className("old-appraise-item-num"));
            List<WebElement> brendsArtDescriptMainStore = elementMainStrore.findElements(By.className("old-appraise-item-description"));
            List<WebElement> brendsArtCountMainStore = elementMainStrore.findElements(By.className("old-appraise-item-quantity"));
            List<WebElement> brendsPriceMainStore = elementMainStrore.findElements(By.className("old-appraise-item-price"));

            for (int i = 0; i < brendsNameMainStore.size(); i++) {

                List<String> listForProducts = new ArrayList<String>();

                if (!brendsNameMainStore.get(i).getText().equals("Производитель")) {
                    listForProducts.add(nameBrend);
                    listForProducts.add(name);
                    listForProducts.add(brendsNameMainStore.get(i).getText().replaceAll(",", ";"));
                    //                   System.out.println(brendsNameMainStore.get(i).getText());
                }
                if (!brendsArticleMainStore.get(i).getText().equals("Ориг. номер")) {
                    listForProducts.add(brendsArticleMainStore.get(i).getText().replaceAll(",", ";"));
                    //                     System.out.println(brendsArticleMainStore.get(i).getText());
                }

                if (!brendsArtDescriptMainStore.get(i).getText().equals("Наименование")) {
                    listForProducts.add(brendsArtDescriptMainStore.get(i).getText().replaceAll(",", ";"));
                    //             System.out.println(brendsArtDescriptMainStore.get(i).getText());
                }

                if (!brendsArtCountMainStore.get(i).getText().equals("Кол-во")) {
                    listForProducts.add(brendsArtCountMainStore.get(i).getText().replaceAll(",", ";"));
                    //                System.out.println(brendsArtCountMainStore.get(i).getText());
                }

                if (!brendsPriceMainStore.get(i).getText().equals("Цена")) {
                    listForProducts.add(brendsPriceMainStore.get(i).getText().replaceAll("a", ";"));
                    //                System.out.println(brendsPriceMainStore.get(i).getText());
                    listForProducts.add(nameMainStore);

                    listForProducts.add(sdfDate.format(nowTime));

                    products.add(listForProducts);
                }

            }
        }


        String nameRetailStore = "Розничная сеть";
        List<WebElement> retailStore = driver.findElements(By.id("block-content-ldc"));
        for (WebElement elementRetailStore : retailStore) {

            List<WebElement> brendsNameRetailStore = elementRetailStore.findElements(By.className("brand-info-open"));
            List<WebElement> brendsArticleRetailStore = elementRetailStore.findElements(By.className("old-appraise-item-num"));
            List<WebElement> brendsArtDescriptRetailStore = elementRetailStore.findElements(By.className("old-appraise-item-description"));
            List<WebElement> brendsArtCountRetailStore = elementRetailStore.findElements(By.className("old-appraise-item-quantity"));
            List<WebElement> brendsPriceRetailStore = elementRetailStore.findElements(By.className("old-appraise-item-price"));

            for (int i = 0; i < brendsNameRetailStore.size(); i++) {

                List<String> listForProducts = new ArrayList<String>();

                listForProducts.add(nameBrend);
                listForProducts.add(name);

                listForProducts.add(brendsNameRetailStore.get(i).getText().replaceAll(",", ";"));
                //       System.out.println(brendsNameRetailStore.get(i).getText());
                listForProducts.add(brendsArticleRetailStore.get(i).getText().replaceAll(",", ";"));
                //    System.out.println(brendsArticleRetailStore.get(i).getText());
                listForProducts.add(brendsArtDescriptRetailStore.get(i).getText().replaceAll(",", ";"));
                //     System.out.println(brendsArtDescriptRetailStore.get(i).getText());
                listForProducts.add(brendsArtCountRetailStore.get(i).getText().replaceAll(",", ";"));
                //   System.out.println(brendsArtCountRetailStore.get(i).getText());
                listForProducts.add(brendsPriceRetailStore.get(i).getText().replaceAll(",", ";"));
                //  System.out.println(brendsPriceRetailStore.get(i).getText());
                listForProducts.add(nameRetailStore);

                listForProducts.add(sdfDate.format(nowTime));

                products.add(listForProducts);

            }

        }


        String nameOrderParts = "Новые запчасти под заказ";
        List<WebElement> orderParts = driver.findElements(By.id("block-content-supplier"));
        for (WebElement elementOrderParts : orderParts) {

            List<WebElement> brendsNameOrderStore = elementOrderParts.findElements(By.className("old-appraise-item-brand"));
            List<WebElement> brendsArticleOrderStore = elementOrderParts.findElements(By.className("old-appraise-item-num"));
            List<WebElement> brendsArtDescriptOrderStore = elementOrderParts.findElements(By.className("old-appraise-item-description"));
            List<WebElement> brendsArtCountOrderStore = elementOrderParts.findElements(By.className("old-appraise-item-quantity"));
            List<WebElement> brendsPriceOrderStore = elementOrderParts.findElements(By.className("old-appraise-item-price"));

            for (int i = 0; i < brendsNameOrderStore.size(); i++) {

                List<String> listForProducts = new ArrayList<String>();

                if (!brendsNameOrderStore.get(i).getText().equals("Производитель")) {
                    listForProducts.add(nameBrend);
                    listForProducts.add(name);
                    listForProducts.add(brendsNameOrderStore.get(i).getText().replaceAll(",", ";"));
                }

                if (!brendsArticleOrderStore.get(i).getText().equals("Номер")) {
                    listForProducts.add(brendsArticleOrderStore.get(i).getText().replaceAll(",", ";"));
                }

                if (!brendsArtDescriptOrderStore.get(i).getText().equals("Наименование")) {
                    listForProducts.add(brendsArtDescriptOrderStore.get(i).getText().replaceAll(",", ";"));
                }

                if (!brendsArtCountOrderStore.get(i).getText().equals("Кол-во")) {
                    listForProducts.add(brendsArtCountOrderStore.get(i).getText().replaceAll(",", ";"));
                }

                if (!brendsPriceOrderStore.get(i).getText().equals("Цена")) {
                    listForProducts.add(brendsPriceOrderStore.get(i).getText().replaceAll("a", " "));
                    listForProducts.add(nameOrderParts);

                    listForProducts.add(sdfDate.format(nowTime));

                    products.add(listForProducts);
                }


            }

        }

/*    String nameUsedParts = "Б/у запчасти: доставка через оптовый отдел";
    List<WebElement> usedParts = driver.findElements(By.id("block-content-used"));
        for (WebElement elementUsedParts : usedParts) {

        List<WebElement> brendsNameUsedStore = elementUsedParts.findElements(By.className("old-appraise-item-brand"));
        List<WebElement> brendsArticleUsedStore = elementUsedParts.findElements(By.className("old-appraise-item-used-num"));
        List<WebElement> brendsArtDescriptUsedStore = elementUsedParts.findElements(By.className("old-appraise-item-description"));
        List<WebElement> brendsPriceUsedStore = elementUsedParts.findElements(By.className("old-appraise-item-price"));


        for (int i = 0; i < brendsArticleUsedStore.size(); i++) {

            List<String> listForProducts = new ArrayList<String>();



            if (!brendsNameUsedStore.get(i).getText().equals("Производитель")){
                listForProducts.add(nameBrend);
                listForProducts.add(name);
                listForProducts.add(brendsNameUsedStore.get(i).getText().replaceAll(",",";"));

                if (!brendsArticleUsedStore.get(i-1).findElement(By.className("old-appraise-item-used-num-orig")).getText().equals("Номер")){
                    listForProducts.add(brendsArticleUsedStore.get(i-1).findElement(By.className("old-appraise-item-used-num-orig")).getText().replaceAll(",",";"));
                }

            }


            if (!brendsArtDescriptUsedStore.get(i).getText().equals("Наименование")){
                listForProducts.add(brendsArtDescriptUsedStore.get(i).getText().replaceAll(",",";"));
                listForProducts.add("1 шт");
            }

            if (!brendsPriceUsedStore.get(i).getText().equals("Цена")){
                listForProducts.add(brendsPriceUsedStore.get(i).getText().replaceAll(",",";"));
                listForProducts.add(nameUsedParts);

                listForProducts.add(sdfDate.format(nowTime));

                products.add(listForProducts);
            }



        }

    }*/

/*        for(List<String> product : products ){

            new WriterForOut().writeToFile(product);

                 System.out.println(product);

        }*/

        return products;

    }
}
