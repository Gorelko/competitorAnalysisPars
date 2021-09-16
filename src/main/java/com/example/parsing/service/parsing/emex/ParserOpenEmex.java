package com.example.parsing.service.parsing.emex;

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


public class ParserOpenEmex {


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

        int countOpenFireFox = 0;

        for (Map.Entry<Integer, Object> mapEntry : map.entrySet()) {
            countOpenFireFox++;
            if (countOpenFireFox == 35) {
                countOpenFireFox = 0;
                driver.quit();

                Thread.sleep(1500);

                System.setProperty("webdriver.gecko.driver", "C:\\parsing\\geckodriver\\geckodriver.exe"); //прописываем адресс для драйвера
                listProfiles = new ProfilesIni();
                profile = listProfiles.getProfile("profileTools");
                opt = new FirefoxOptions();
                opt.setProfile(profile);
                driver = new FirefoxDriver(opt);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                driver.manage().window().maximize();

            }


            if (count == Integer.parseInt(countPer)) {
                Thread.sleep(Integer.parseInt(timePer) * 1000 * 60);
                count = 0;
            }
            count++;


            Thread.sleep(Integer.parseInt(intervalZap) * 1000);

            String arr[] = Arrays.toString((Object[]) mapEntry.getValue()).replaceAll("[\\[\\](){}]", "").split(";");

            driver.get("https://emex.ru/f?detailNum=" + arr[1] + "&packet=-1");

            WebElement element = driver.findElement(By.id("detail-num-input"));


/*            Thread.sleep(3000);
            element.sendKeys(arr[1]);
            Thread.sleep(3000);
            element.sendKeys(Keys.ENTER);
            Thread.sleep(3000);*/


            try {


                WebElement element2 = driver.findElement(By.xpath("//div[contains(text(),'" + arr[0] + "')]"));

                String element3 = String.valueOf(driver.findElement(By.xpath("//div[contains(text(),'Найдено несколько совпадений')]")));
                if (element3.contains("Найдено несколько совпадений")) {
                    element2.click();

                }

            } catch (Exception e) {
                System.out.println("В поиске один вариант!");
            }


            List<List<String>> list = EmexParsArt.emexParsing(driver, arr[1], arr[0]);
            /*            new WriteInDb().writeInDB(list); //запись для базы данных*/

            for (List<String> listAlone : list) {  //Для записи в csv


                String allInform = "";
                for (int j = 0; j < listAlone.size(); j++) {
                    if (j != listAlone.size() - 1) {
                        allInform = allInform + ";" + listAlone.get(j);
                    } else {
                        allInform = allInform + ";" + listAlone.get(j) + ";";
                    }
                }

                List<String> productList2 = new ArrayList<>();
                productList2.add(allInform);


                new WriterForOut().writeToFile(productList2, linkOut);


            }


        }


    }
}