package com.example.parsing.service.readCsv;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.HashMap;

public class ReaderForCsv {

    public static HashMap<Integer, Object> readMapa(String link) {

        HashMap<Integer, Object> map = new HashMap<Integer, Object>();

        try {
            //csv file containing data
            String strFile = link;
            CSVReader reader = new CSVReader(new FileReader(strFile));
            String[] nextLine;
            int lineNumber = 0;
            while ((nextLine = reader.readNext()) != null) {
                lineNumber++;


                map.put(lineNumber, nextLine);

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return map;

    }

}
