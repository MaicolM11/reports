package com.uptc.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReportFile {

    public static List<XSSFSheet> readFile(File file) throws IOException, InvalidFormatException {
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        List<XSSFSheet> sheets = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            sheets.add(workbook.getSheetAt(i));
        }
//        workbook.close();
        return sheets;
    }

    public static void writeReport(XSSFWorkbook wb, String path){
        try  {
            OutputStream fileOut = new FileOutputStream(path);
            wb.write(fileOut);
        } catch(IOException e){
        }
    }
    
}