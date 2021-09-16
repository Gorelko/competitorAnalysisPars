package com.example.parsing.service.writeCsv;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class WriterForOut {

    private static final String newLine = System.getProperty("line.separator");

    public synchronized static void writeToFile(List<String> msg, String linkOut) {


        String fileName = linkOut;
        PrintWriter printWriter = null;
        File file = new File(fileName);
        try {
            if (!file.exists()) file.createNewFile();
            printWriter = new PrintWriter(new FileOutputStream(fileName, true));
            printWriter.write(newLine + msg);
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.flush();
                printWriter.close();
            }
        }
    }


}
