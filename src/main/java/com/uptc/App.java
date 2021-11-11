package com.uptc;

import java.io.IOException;

import com.uptc.report.ReportManager;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        ReportManager manager = new ReportManager("/home/maicol/Documentos/software2/reports/src/main/resources/");
        manager.searchReports();
        manager.generateReport();
    }
}
