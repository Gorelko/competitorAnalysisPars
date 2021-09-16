package com.example.parsing.service.uploadsFiles;

import java.io.File;
import java.util.*;

public class FilesOnDisk {

    public String[] outFilesOnDisk(String path) {

        System.out.println(321);
        System.out.println(path);
        System.out.println(321);
        File myFolder = new File(path);
        File[] files = myFolder.listFiles();

        Map<String, String> pathForFileUpdate = new HashMap<>();
        List<String> listNamesOfFiles = new ArrayList<>();

        try {
            for (int i = 0; i < files.length; i++) {

                String[] nameFile = files[i].toString().split("\\\\");

                for (int x = nameFile.length - 1; ; ) {

                    pathForFileUpdate.put(nameFile[x], files[i].toString());
                    listNamesOfFiles.add(nameFile[x]);

                    break;

                }

            }
        } catch (Exception e) {
            System.out.println("Ошибка вывода списка файлов в директории сервера!");
        }


/*        for (Map.Entry<String, String> entry : pathForFileUpdate.entrySet()) {
            System.out.println(entry.getKey() + "   " + entry.getValue());
        }*/

        String[] arrNamesOfFiles = listNamesOfFiles.toArray(new String[]{});

        System.out.println(Arrays.toString(arrNamesOfFiles));


        return arrNamesOfFiles;
    }

}
