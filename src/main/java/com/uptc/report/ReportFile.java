package com.uptc.report;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReportFile {

    public static List<XSSFSheet> readFile(File file) throws InvalidFormatException, IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        List<XSSFSheet> sheets = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            sheets.add(workbook.getSheetAt(i));
        }
        workbook.close();
        return sheets;     
    }

    public static void writeFile() {
        
    }
}