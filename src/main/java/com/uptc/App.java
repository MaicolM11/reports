package com.uptc;

import com.uptc.report.ReportManager;


public class App {

    public static void main(String[] args) {
        ReportManager manager = new ReportManager("/home/sebscam/Documents/reports/src/main/resources");
        manager.searchReports();
        manager.generateReport();
    }

}
