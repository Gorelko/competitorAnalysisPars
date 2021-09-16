package com.example.parsing.service.parsing.exist;

import com.example.parsing.service.parsing.existQuick.ExistQuickParsArt;
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


public class ParserOpenExist {

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

        int countPosition = 0;

        driver.get("https://exist.ru/");

        if (countPosition >= 0 && countPosition < 85) {

            WebElement elementEnter = driver.findElement(By.id("pnlLogin"));

            try {
                WebElement quit = driver.findElement(By.id("logout"));
                if (quit.getText().indexOf("Выход") != -1) {
                    quit.click();
                }
                elementEnter = driver.findElement(By.id("pnlLogin"));
                elementEnter.click();
            } catch (Exception b) {
                elementEnter.click();
            }

            WebElement elementLogin = driver.findElement(By.id("login"));
            WebElement elementPass = driver.findElement(By.id("pass"));

            elementLogin.sendKeys("9818574491");
            elementPass.sendKeys("y3cfooC");

            WebElement elementBtn = driver.findElement(By.id("btnLogin"));
            elementBtn.submit();
            Thread.sleep(2000);
        }


        for (Map.Entry<Integer, Object> mapEntry : map.entrySet()) {

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

                driver.get("https://exist.ru/");

                if (countPosition >= 0 && countPosition < 80) {

                    WebElement elementEnter = driver.findElement(By.id("pnlLogin"));

                    try {
                        WebElement quit = driver.findElement(By.id("logout"));
                        if (quit.getText().indexOf("Выход") != -1) {
                            quit.click();
                        }
                        elementEnter = driver.findElement(By.id("pnlLogin"));
                        elementEnter.click();

                    } catch (Exception b) {
                        elementEnter.click();
                    }

                    WebElement elementLogin = driver.findElement(By.id("login"));
                    WebElement elementPass = driver.findElement(By.id("pass"));

                    elementLogin.sendKeys("9818574491");
                    elementPass.sendKeys("y3cfooC");

                    WebElement elementBtn = driver.findElement(By.id("btnLogin"));
                    elementBtn.submit();
                    Thread.sleep(2000);
                } else if (countPosition >= 80 && countPosition < 160) {

                    WebElement elementEnter = driver.findElement(By.id("pnlLogin"));
                    elementEnter.click();

                    try {
                        WebElement quit = driver.findElement(By.id("logout"));
                        if (quit.getText().indexOf("Выход") != -1) {
                            quit.click();
                        }
                        elementEnter = driver.findElement(By.id("pnlLogin"));
                        elementEnter.click();
                    } catch (Exception b) {

                    }

                    WebElement elementLogin = driver.findElement(By.id("login"));
                    WebElement elementPass = driver.findElement(By.id("pass"));

                    elementLogin.sendKeys("+79090937262");/////////////////////////
                    elementPass.sendKeys("qwert123456");

                    WebElement elementBtn = driver.findElement(By.id("btnLogin"));
                    elementBtn.submit();
                    Thread.sleep(2000);
                } else if (countPosition >= 160 && countPosition < 240) {

                    WebElement elementEnter = driver.findElement(By.id("pnlLogin"));
                    elementEnter.click();

                    try {
                        WebElement quit = driver.findElement(By.id("logout"));
                        if (quit.getText().indexOf("Выход") != -1) {
                            quit.click();
                        }
                        elementEnter = driver.findElement(By.id("pnlLogin"));
                        elementEnter.click();
                    } catch (Exception b) {

                    }

                    WebElement elementLogin = driver.findElement(By.id("login"));
                    WebElement elementPass = driver.findElement(By.id("pass"));

                    elementLogin.sendKeys("+79090937266");
                    elementPass.sendKeys("qwert123456");

                    WebElement elementBtn = driver.findElement(By.id("btnLogin"));
                    elementBtn.submit();
                    Thread.sleep(2000);
                } else if (countPosition >= 240 && countPosition < 320) {

                    WebElement elementEnter = driver.findElement(By.id("pnlLogin"));
                    elementEnter.click();

                    try {
                        WebElement quit = driver.findElement(By.id("logout"));
                        if (quit.getText().indexOf("Выход") != -1) {
                            quit.click();
                        }
                        elementEnter = driver.findElement(By.id("pnlLogin"));
                        elementEnter.click();
                    } catch (Exception b) {

                    }

                    WebElement elementLogin = driver.findElement(By.id("login"));
                    WebElement elementPass = driver.findElement(By.id("pass"));

                    elementLogin.sendKeys("+79090937268");
                    elementPass.sendKeys("qwert123456");

                    WebElement elementBtn = driver.findElement(By.id("btnLogin"));
                    elementBtn.submit();
                    Thread.sleep(2000);
                } else if (countPosition >= 320 && countPosition < 400) {

                    WebElement elementEnter = driver.findElement(By.id("pnlLogin"));
                    elementEnter.click();

                    try {
                        WebElement quit = driver.findElement(By.id("logout"));
                        if (quit.getText().indexOf("Выход") != -1) {
                            quit.click();
                        }
                        elementEnter = driver.findElement(By.id("pnlLogin"));
                        elementEnter.click();
                    } catch (Exception b) {

                    }

                    WebElement elementLogin = driver.findElement(By.id("login"));
                    WebElement elementPass = driver.findElement(By.id("pass"));

                    elementLogin.sendKeys("+79090937269");
                    elementPass.sendKeys("qwert123456");

                    WebElement elementBtn = driver.findElement(By.id("btnLogin"));
                    elementBtn.submit();
                    Thread.sleep(2000);
                } else if (countPosition >= 400 && countPosition < 480) {

                    WebElement elementEnter = driver.findElement(By.id("pnlLogin"));
                    elementEnter.click();

                    try {
                        WebElement quit = driver.findElement(By.id("logout"));
                        if (quit.getText().indexOf("Выход") != -1) {
                            quit.click();
                        }
                        elementEnter = driver.findElement(By.id("pnlLogin"));
                        elementEnter.click();
                    } catch (Exception b) {

                    }

                    WebElement elementLogin = driver.findElement(By.id("login"));
                    WebElement elementPass = driver.findElement(By.id("pass"));

                    elementLogin.sendKeys("+79090937773");
                    elementPass.sendKeys("qwert123456");

                    WebElement elementBtn = driver.findElement(By.id("btnLogin"));
                    elementBtn.submit();
                    Thread.sleep(2000);
                } else if (countPosition >= 480 && countPosition < 560) {

                    WebElement elementEnter = driver.findElement(By.id("pnlLogin"));
                    elementEnter.click();

                    try {
                        WebElement quit = driver.findElement(By.id("logout"));
                        if (quit.getText().indexOf("Выход") != -1) {
                            quit.click();
                        }
                        elementEnter = driver.findElement(By.id("pnlLogin"));
                        elementEnter.click();
                    } catch (Exception b) {

                    }

                    WebElement elementLogin = driver.findElement(By.id("login"));
                    WebElement elementPass = driver.findElement(By.id("pass"));

                    elementLogin.sendKeys("+79090937262");
                    elementPass.sendKeys("qwert123456");

                    WebElement elementBtn = driver.findElement(By.id("btnLogin"));
                    elementBtn.submit();
                    Thread.sleep(2000);
                }


            }

            if (countOpenFireFox != 20) {
                driver.get("https://exist.ru/");
            }

            countOpenFireFox++;
            countPosition++;


            if (count == Integer.parseInt(countPer)) {
                //Thread.sleep(Integer.parseInt(timePer)*1000*60);
                count = 0;
            }
            count++;


            Thread.sleep(Integer.parseInt(intervalZap) * 1000);

            WebElement element = driver.findElement(By.id("pcode"));

            String arr[] = Arrays.toString((Object[]) mapEntry.getValue()).replaceAll("[\\[\\](){}]", "").split(";");
            Thread.sleep(2000);
            element.sendKeys(arr[1]);
            //  Thread.sleep(3000);
            element.sendKeys(Keys.ENTER);

            // Thread.sleep(2000);
            try {

                WebElement elementCat = driver.findElement(By.id("cat-wrapper"));

                List<WebElement> elementCatList = driver.findElements(By.tagName("b"));

                for (WebElement xxx : elementCatList) {

                    if (xxx.getText().indexOf(arr[0]) != -1) {

                        xxx.click();

                        continue;

                    }

                }


            } catch (Exception e) {
                System.out.println("В поиске один вариант!");
            }

            Thread.sleep(2000);


            List<List<String>> list = ExistParsArt.emexParsing(driver, arr[1], arr[0]);

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


