package com.example.parsing.controller;

import com.example.parsing.service.parsing.adeo.ParserOpenAdeo;
import com.example.parsing.service.parsing.amtel.ParserOpenAmtel;
import com.example.parsing.service.parsing.autodoc.ParserOpenAutodoc;
import com.example.parsing.service.parsing.autopiter.ParserOpenAutopiter;
import com.example.parsing.service.parsing.emex.ParserOpenEmex;
import com.example.parsing.service.parsing.euroauto.ParserOpenEuroauto;
import com.example.parsing.service.parsing.euroautoRozn.ParserOpenEuroautoRozn;
import com.example.parsing.service.parsing.exist.ParserOpenExist;
import com.example.parsing.service.parsing.existQuick.ParserOpenExistQuick;
import com.example.parsing.service.parsing.existQuickWithPass.ParserOpenExistQuickWithPass;
import com.example.parsing.service.parsing.existUkr.ParserOpenExistUkr;
import com.example.parsing.service.parsing.existWithPass.ParserOpenExistWithPass;
import com.example.parsing.service.parsing.parts66.ParserOpenParts;
import com.example.parsing.service.uploadsFiles.FilesOnDisk;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Controller
public class ParsingController {


    @Value("${upload.path}")
    private String uploadPath;


    @GetMapping("/parsing")
    public String parsingPage(
            Map<String, Object> model
    ) {

        FilesOnDisk filesOnDisk = new FilesOnDisk();

        model.put("arrUploadsFiles", filesOnDisk.outFilesOnDisk(uploadPath));

        return "parsing";
    }


    @PostMapping("/parsing")
    public String add(
            Map<String, Object> model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

/*            String uuidFile = UUID.randomUUID().toString();
            System.out.println(uuidFile);
            String resultFilename = uuidFile + "." + file.getOriginalFilename();*/
            String resultFilename = file.getOriginalFilename();
            System.out.println(resultFilename);

            file.transferTo(new File(uploadPath + "/" + resultFilename));


        }
        FilesOnDisk filesOnDisk = new FilesOnDisk();

        model.put("arrUploadsFiles", filesOnDisk.outFilesOnDisk(uploadPath));

        return "parsing";
    }

    @PostMapping("/startparsing")
    public String startParsing(
            @RequestParam(name = "arrUploadsFiles") String arrUploadsFiles,
            @RequestParam(name = "fileLinkCsvOut") String fileLinkCsvOut,
            @RequestParam(name = "siteSelect", required = false, defaultValue = "None") String siteSelect,

            @RequestParam(name = "intervalZap") String intervalZap,
            @RequestParam(name = "countPer") String countPer,
            @RequestParam(name = "timePer") String timePer,

            Map<String, Object> model

    ) throws InterruptedException {
        FilesOnDisk filesOnDisk = new FilesOnDisk();
        model.put("arrUploadsFiles", filesOnDisk.outFilesOnDisk(uploadPath));


        if (siteSelect.equals("Amtel")) {

            try {
                ParserOpenAmtel.parsing(uploadPath + "/" + arrUploadsFiles, fileLinkCsvOut + "\\amtelParsing.csv");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } else if (siteSelect.equals("Emex")) {

            try {
                ParserOpenEmex.parsing(uploadPath + "/" + arrUploadsFiles, fileLinkCsvOut + "\\emexParsing.csv", intervalZap, countPer, timePer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        } else if (siteSelect.equals("EuroautoOpt")) {

            try {
                ParserOpenEuroauto.parsing(uploadPath + "/" + arrUploadsFiles, fileLinkCsvOut + "\\euroautoOptParsing.csv");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        } else if (siteSelect.equals("Exist")) {

            try {
                ParserOpenExist.parsing(uploadPath + "/" + arrUploadsFiles, fileLinkCsvOut + "\\existParsing.csv", intervalZap, countPer, timePer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        } else if (siteSelect.equals("ExistUkr")) {


            try {
                ParserOpenExistUkr.parsing(uploadPath + "/" + arrUploadsFiles, fileLinkCsvOut + "\\existUkrParsing.csv", intervalZap, countPer, timePer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        } else if (siteSelect.equals("None")) {

            return "parsing";

        } else if (siteSelect.equals("AutoDoc")) {

            try {
                ParserOpenAutodoc.parsing(uploadPath + "/" + arrUploadsFiles, fileLinkCsvOut + "\\autodocParsing.csv");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } else if (siteSelect.equals("Parts66")) {

            try {
                ParserOpenParts.parsing(uploadPath + "/" + arrUploadsFiles, fileLinkCsvOut + "\\parts66Parsing.csv");
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }

        } else if (siteSelect.equals("Autopiter")) {

            try {
                ParserOpenAutopiter.parsing(uploadPath + "/" + arrUploadsFiles, fileLinkCsvOut + "\\autopiterParsing.csv");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } else if (siteSelect.equals("ExistQuick")) {

            try {
                ParserOpenExistQuick.parsing(uploadPath + "/" + arrUploadsFiles, fileLinkCsvOut + "\\existQuickParsing.csv", intervalZap, countPer, timePer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } else if (siteSelect.equals("Euroauto")) {

            try {
                ParserOpenEuroautoRozn.parsing(uploadPath + "/" + arrUploadsFiles, fileLinkCsvOut + "\\euroautoParsing.csv");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } else if (siteSelect.equals("Adeo")) {

            try {
                ParserOpenAdeo.parsing(uploadPath + "/" + arrUploadsFiles, fileLinkCsvOut + "\\adeoParsing.csv");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } else if (siteSelect.equals("ExistQuickWithPass")) {

            try {
                ParserOpenExistQuickWithPass.parsing(uploadPath + "/" + arrUploadsFiles, fileLinkCsvOut + "\\existQuickParsingWithPass.csv", intervalZap, countPer, timePer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } else if (siteSelect.equals("ExistWithPass")) {

            try {
                ParserOpenExistWithPass.parsing(uploadPath + "/" + arrUploadsFiles, fileLinkCsvOut + "\\existParsingWithPass.csv", intervalZap, countPer, timePer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        return "parsing";
    }


}
