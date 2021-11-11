package com.uptc.report;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class ReportManager {

    private String path;
    private ProductFormat format;

    public ReportManager(String path) {
        this.path = path;
        this.format = new ProductFormat();
    }

    public void searchReports() {
        File file = new File(path);
        deepSearch(file);
    }

    private void deepSearch(File file) {
        if (file.exists() && file.isDirectory()) {
            Arrays.stream(file.listFiles()).forEach(this::deepSearch);
        } else if (file.exists() && file.isFile() && file.getName().contains(".xlsx")) {
            addBranch(file);
        }
    }

    private void addBranch(File file) {
        try {
            format.appendFile(ReportFile.readFile(file));
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public void generateReport() {
        ReportFile.writeReport(format.getWorkbook(), "generalReport.xlsx");
    }
}