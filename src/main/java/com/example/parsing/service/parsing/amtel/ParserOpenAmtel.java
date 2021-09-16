package com.example.parsing.service.parsing.amtel;

import com.example.parsing.service.readCsv.ReaderForCsv;
import com.example.parsing.service.writeCsv.WriterForOut;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

import java.util.*;
import java.util.concurrent.TimeUnit;

/*import my.parsing.app.connection.WriteInDb;*/


public class ParserOpenAmtel {

    public static void parsing(String link, String linkOut) throws InterruptedException {

        HashMap<Integer, Object> map = ReaderForCsv.readMapa(link);


        System.setProperty("webdriver.gecko.driver", "C:\\parsing\\geckodriver\\geckodriver.exe"); //прописываем адресс для драйвера

        ProfilesIni listProfiles = new ProfilesIni();

        FirefoxProfile profile = listProfiles.getProfile("profileTools");

        FirefoxOptions opt = new FirefoxOptions();
        opt.setProfile(profile);

        WebDriver driver = new FirefoxDriver(opt);


        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.manage().window().maximize();

        List<List<String>> products = new ArrayList<List<String>>();

        int count = 1;

        for (Map.Entry<Integer, Object> mapEntry : map.entrySet()) {

            driver.get("https://amtel.club/");


            // Find the text input element by its name
            WebElement element = driver.findElement(By.id("header-main-search-input"));

            String arr[] = Arrays.toString((Object[]) mapEntry.getValue()).replaceAll("[\\[\\](){}]", "").split(";");

            element.sendKeys(arr[1]);
            //  Thread.sleep(3000);
            element.sendKeys(Keys.ENTER);


            Thread.sleep(3000);


            try {
                WebElement element2 = driver.findElement(By.xpath("//div[contains(text(),'" + arr[0] + "')]"));
                String element3 = String.valueOf(driver.findElement(By.xpath("//h3[contains(text(),'Поиск по номеру')]")));
                if (element3.contains("Поиск по номеру")) {

                    element2.click();
                }

            } catch (Exception e) {
                System.out.println("В поиске один вариант!");
            }

            Thread.sleep(3000);

            List<List<String>> list = AmtelParsArt.emexParsing(driver, arr[1], arr[0]);

            /*            new WriteInDb().writeInDB(list); //запись для базы данных*/

            for (List<String> listAlone : list) {  //Для записи в csv
                new WriterForOut().writeToFile(listAlone, linkOut);
            }


        }


    }


}


