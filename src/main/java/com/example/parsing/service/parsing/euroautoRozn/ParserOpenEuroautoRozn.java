package com.example.parsing.service.parsing.euroautoRozn;

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


public class ParserOpenEuroautoRozn {

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

        int countOpenFireFox = 0;

        for (Map.Entry<Integer, Object> mapEntry : map.entrySet()) {
            countOpenFireFox++;
            if (countOpenFireFox == 20) {
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

            driver.get("https://euroauto.ru/");


            // Find the text input element by its name
            WebElement element = driver.findElement(By.className("input-form"));

            String arr[] = Arrays.toString((Object[]) mapEntry.getValue()).replaceAll("[\\[\\](){}]", "").split(";");

            element.sendKeys(arr[1]);
            //  Thread.sleep(3000);
            element.sendKeys(Keys.ENTER);

            Thread.sleep(3000);


            try {

                List<WebElement> element2 = driver.findElements(By.className("pre-num-listing-brand"));

                for (WebElement xxx : element2) {
                    if (xxx.getText().indexOf(arr[0]) != -1) {
                        xxx.click();
                        break;
                    }

                }

/*                String element3 = String.valueOf(driver.findElement(By.xpath("//h3[contains(text(),'Выберите каталог')]")));
                if (element3.contains("Поиск по номеру")) {

                    element2.click();
                }*/

            } catch (Exception e) {
                System.out.println("В поиске один вариант!");
            }

            Thread.sleep(3000);

            List<List<String>> list = EuroautoRoznParsArt.emexParsing(driver, arr[1], arr[0]);

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


