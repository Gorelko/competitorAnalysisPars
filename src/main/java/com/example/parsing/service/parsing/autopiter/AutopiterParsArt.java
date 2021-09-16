package com.example.parsing.service.parsing.autopiter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AutopiterParsArt {

    public static List<List<String>> autodocParsing(WebDriver driver, String nameArt, String nameBrend) throws InterruptedException {

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date nowTime = new Date();

        List<List<String>> products = new ArrayList<List<String>>();

        try {


            String name = nameArt;

            Thread.sleep(3000);

            Document doc = Jsoup.parse(String.valueOf(driver.getPageSource()));

            Elements elements = doc.getElementsByAttributeValue("class", "Group__content___ZdnLK");

            Elements elementsBrand = doc.getElementsByAttributeValue("class", "Brand__root___3oK7J");

            List<String> listBrand = new ArrayList<>();
            for (Element xxx : elementsBrand) {
                //System.out.println(String.valueOf(xxx).split("tabindex=\"0\">")[1].split("</span>")[0]);
                listBrand.add(String.valueOf(xxx).split("tabindex=\"0\">")[1].split("</span>")[0]);
            }

            for (Element xxx : elements) {
/*            System.out.println(String.valueOf(xxx).split("/")[3].toUpperCase());
            System.out.println(String.valueOf(xxx).split("title=\"")[1].split("\">")[0].toUpperCase());
            System.out.println(String.valueOf(xxx).split("<span>")[1].split("</span>")[0].replaceAll(";",","));*/
                String brand = "";
                String number = "";
                String group = "";

                List<String> product = new ArrayList<>();

                try {
                    for (String brandEnter : listBrand) {
                        //System.out.println(brandEnter);
                        String brand1 = brandEnter.toUpperCase().replaceAll("[^A-Za-zА-Яа-я0-9]", "");
                        String brand2 = String.valueOf(xxx).split("/")[3].toUpperCase().replaceAll("[^A-Za-zА-Яа-я0-9]", "");
                        if (brand1.equals(brand2)) {
                            brand = brandEnter.toUpperCase();
                            //System.out.println(brand);
                            break;
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    number = String.valueOf(xxx).split("title=\"")[1].split("\">")[0].toUpperCase();
                } catch (Exception e) {

                }

                try {
                    group = String.valueOf(xxx).split("<span>")[1].split("</span>")[0].replaceAll(";", ",");
                } catch (Exception e) {

                }

                product.add(nameBrend);
                product.add(nameArt);
                product.add(brand);
                product.add(number);
                product.add(group);
                product.add(sdfDate.format(nowTime));

                products.add(product);
            }

        } catch (Exception m) {

        }


        return products;


    }
}
