package com.example.parsing.service.parsing.parts66;

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

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;


/*import my.parsing.app.connection.WriteInDb;*/


public class ParserOpenParts {

    public static void parsing(String link, String linkOut) throws InterruptedException, IOException {

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


            String arr[] = Arrays.toString((Object[]) mapEntry.getValue()).replaceAll("[\\[\\](){}]", "").split(";");

            driver.get("https://parts66.ru/search.html?article=" + arr[1] + "&sort___search_results_by=final_price&brand=" + arr[0]);
            Thread.sleep(1000);

            try {

                WebElement elementBtn = driver.findElements(By.className("btn")).get(0);
                //System.out.println(elementBtn.getText());
                if (elementBtn.getText().indexOf("Войти") != -1) {
                    elementBtn.click();
                    WebElement elementLogin = driver.findElement(By.id("userlogin"));
                    WebElement elementPass = driver.findElement(By.id("userpassword"));
                    elementLogin.sendKeys("AntonBK");
                    elementPass.sendKeys("12345rnz");
                    elementPass.sendKeys(Keys.ENTER);
                }


            } catch (Exception e) {

            }

            // Find the text input element by its name


            List<List<String>> list = PartsParsArt.autodocParsing(driver, arr[1], arr[0]);

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


