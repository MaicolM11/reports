package com.uptc.report;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ReportManager {

    private String path;
    private List report;

    public ReportManager(String path) {
        this.path = path;
        this.report = new ArrayList<>();
    }

    public void searchReports() {
        File file = new File(path);
        deepSearch(file);
    }

    private void deepSearch(File file){
        if (file.exists() && file.isDirectory()) {
           Arrays.stream(file.listFiles()).forEach(this::deepSearch);
        } else if (file.exists() && file.isFile() && file.getName().contains(".xlsx")) {
            addBranch(file);
        }
    }

    private void addBranch(File file) {
        try {
            List<XSSFSheet> readFile = ReportFile.readFile(file);
            ProductFormat.format(readFile);
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public void generateReport() {
       // write excel -> report
    }
    
}