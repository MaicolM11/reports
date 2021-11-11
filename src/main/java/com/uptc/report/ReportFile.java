package com.uptc.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReportFile {

    public static List<XSSFSheet> readFile(File file) throws IOException, InvalidFormatException {
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        List<XSSFSheet> sheets = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            sheets.add(workbook.getSheetAt(i));
        }
        workbook.close();
        return sheets;
    }

    public static void writeFile(List<FileFormat> reports) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        int rownum = 0;

            XSSFSheet sheet = workbook.createSheet();
            Row row = sheet.createRow(rownum++);

        FileOutputStream out = new FileOutputStream(new File("howtodoinjava_demo.xlsx"));
        workbook.write(out);
        workbook.close();
        out.close();
    }
}