package com.uptc;

import com.uptc.report.ReportManager;


public class App {

    public static void main(String[] args) {
        ReportManager manager = new ReportManager("/home/maicol/Documentos/software2/reports/src/main/resources");
        manager.searchReports();
        manager.generateReport();
    }

}
