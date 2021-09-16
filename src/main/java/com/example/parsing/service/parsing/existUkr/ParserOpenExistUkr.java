package com.example.parsing.service.parsing.existUkr;

import com.example.parsing.service.readCsv.ReaderForCsv;
import com.example.parsing.service.writeCsv.WriterForOut;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

import java.util.*;
import java.util.concurrent.TimeUnit;

/*import my.parsing.app.connection.WriteInDb;*/


public class ParserOpenExistUkr {

    public static void parsing(String link, String linkOut, String intervalZap, String countPer, String timePer) throws InterruptedException {

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

        int count = 0;

        for (Map.Entry<Integer, Object> mapEntry : map.entrySet()) {


            if (count == Integer.parseInt(countPer)) {
                Thread.sleep(Integer.parseInt(timePer) * 1000 * 60);
                count = 0;
            }
            count++;


            Thread.sleep(Integer.parseInt(intervalZap) * 1000);


            driver.get("https://exist.ua/");


            // Find the text input element by its name
            WebElement element = driver.findElement(By.className("new-search-toggler-body"));

            String arr[] = Arrays.toString((Object[]) mapEntry.getValue()).replaceAll("[\\[\\](){}]", "").split(";");

            //  element.sendKeys(arr[1]);
            //  Thread.sleep(3000);
            element.click();

            WebElement element1 = driver.findElement(By.className("multi-search-input"));
            element1.sendKeys(arr[1]);

            try {


                WebElement containerAll = driver.findElement(By.xpath("//strong[contains(text(),'" + arr[0] + "')]"));
                System.out.println("111");
                System.out.println(containerAll.getText());
                containerAll.click();
                Thread.sleep(2000);


                List<List<String>> list = ExistUkrParsArt.emexParsing(driver, arr[1], arr[0]);

                /*            new WriteInDb().writeInDB(list); //запись для базы данных*/

                for (List<String> listAlone : list) {  //Для записи в csv
                    new WriterForOut().writeToFile(listAlone, linkOut);
                }


            } catch (Exception e) {

            }


        }


    }


}


