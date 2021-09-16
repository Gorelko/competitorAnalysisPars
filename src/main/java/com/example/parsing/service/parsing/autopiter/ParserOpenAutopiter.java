package com.example.parsing.service.parsing.autopiter;

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


public class ParserOpenAutopiter {

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

            driver.get("https://autopiter.ru/");
            Thread.sleep(1000);

            // Find the text input element by its name
            WebElement element = driver.findElement(By.name("query"));

            String arr[] = Arrays.toString((Object[]) mapEntry.getValue()).replaceAll("[\\[\\](){}]", "").split(";");

            element.sendKeys(arr[1]);
            //  Thread.sleep(3000);
            element.sendKeys(Keys.ENTER);

            Thread.sleep(3000);


            try {
                List<WebElement> listElements = driver.findElements(By.className("IndividualTableRow__row___3eAvr"));

                for (WebElement elementList : listElements) {
                    if (elementList.getText().indexOf(arr[0]) != -1) {
                        elementList.click();
                    }
                }

            } catch (Exception e) {
                //    System.out.println("");
            }

            Thread.sleep(1000);


            try {
                while (true) {
                    WebElement elementNextProduct = driver.findElement(By.className("AppraiseBrandTable__moreLink___3O31g"));
                    elementNextProduct.click();
                }
            } catch (Exception e) {

            }


            List<List<String>> list = AutopiterParsArt.autodocParsing(driver, arr[1], arr[0]);

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





