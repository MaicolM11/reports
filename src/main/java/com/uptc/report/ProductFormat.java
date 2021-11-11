package com.uptc.report;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ProductFormat {

    private static List<String> SHEETS_LABELS = Arrays.asList("Electrodomesticos", "Vestuario", "Licores", "Alimentos");
    private static String[] PRODUCT_HEADERS = new String[] { "Departamento", "Ciudad", "Subtipo", "Nombre producto",
            "Proveedor", "Cantidad", "Observaciones" };
    private static String[] ELECTRONIC_HEADERS = new String[] { "Departamento", "Ciudad", "Referencia", "Subtipo",
            "Nombre producto", "Proveedor", "Cantidad", "Observaciones" };

    private XSSFWorkbook report;

    public ProductFormat() {
        report = new XSSFWorkbook();
        setHeaders();
    }

    private void setHeaders() {
        for (int i = 0; i < SHEETS_LABELS.size(); i++) {
            XSSFSheet sheetI = report.createSheet(SHEETS_LABELS.get(i));
            XSSFRow createRow = sheetI.createRow(0);
            String[] temp = (i == 0) ? ELECTRONIC_HEADERS : PRODUCT_HEADERS;
            for (int j = 0; j < temp.length; j++) {
                createRow.createCell(j).setCellValue(temp[j]);
            }
        }
    }

    public void appendFile(List<XSSFSheet> sheets) {
        String[] sucursalData = getData(sheets.remove(0));

        for (int i = 0; i < sheets.size(); i++) {
            Iterator<Row> iterator = sheets.get(i).rowIterator();
            iterator.next();

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                if (!currentRow.getCell(2).getStringCellValue().isBlank()) {
                    int lastRow = report.getSheetAt(i).getLastRowNum() + 1;
                    XSSFRow rowI = report.getSheetAt(i).createRow(lastRow);
                    rowI.createCell(0).setCellValue(sucursalData[0]);
                    rowI.createCell(1).setCellValue(sucursalData[1]);
                    for (int j = 0; j < currentRow.getLastCellNum(); j++) {
                        Cell cell = currentRow.getCell(j);
                        if(cell.getCellType() == CellType.NUMERIC) 
                            rowI.createCell(j + 2).setCellValue(cell.getNumericCellValue());                        
                        else
                            rowI.createCell(j + 2).setCellValue(cell.getStringCellValue());                        
                    }
                } 
            }

        }

    }

    private String[] getData(XSSFSheet sheet) {
        String department = sheet.getRow(0).getCell(1).getStringCellValue();
        String city = sheet.getRow(1).getCell(1).getStringCellValue();
        return new String[] { department, city };
    }

    public XSSFWorkbook getWorkbook() {
        return this.report;
    }

}