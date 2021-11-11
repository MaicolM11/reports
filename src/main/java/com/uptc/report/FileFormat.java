package com.uptc.report;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class FileFormat {

    protected static String[] ReportLabels = {"Departamento","Ciudad"};
    protected static String[] SheetLabels = {"Electrodomesticos","Vestuario","Licores","Alimentos"};
    protected static String[] ProductLabels = {"Subtipo","Nombre producto","Proveedor","Cantidad","Observaciones"};
    protected static String[] ElectronicLabels = {"Subtipo","Nombre producto","Referencia","Proveedor","Cantidad","Observaciones"};
    private List formatedProductList;
    private List<String> headers;

    public FileFormat(List<XSSFSheet> readFile) {
        this.formatedProductList = new ArrayList<>();
        this.headers = new ArrayList<>();
        format(readFile);
    }

    private void format(List<XSSFSheet> readFile) {
        formatReportHeaders(readFile.get(0));
        for (int i = 1; i < 4; i++) {
            formatProducts(readFile.get(i));
        }
    }

    private  void formatProducts(XSSFSheet sheet) {
        ArrayList<ArrayList<ArrayList<String>>> headers = new ArrayList<>();

        Iterator<Row> iterator = sheet.iterator();
        iterator.next();

        ArrayList<ArrayList<String>> products = new ArrayList<>();
        while (iterator.hasNext()) {
            Iterator<Cell> cellIterator = iterator.next().cellIterator();
            ArrayList<String> cells = new ArrayList<>();
            while (cellIterator.hasNext()) {
                cells.add(cellIterator.next().getStringCellValue());
            }
            products.add(cells);
        }
        headers.add(products);
        formatedProductList = headers;
    }

    private void formatReportHeaders(XSSFSheet sheet) {
        ArrayList<String> headers = new ArrayList<>();
        headers.add(sheet.getRow(0).getCell(1).getStringCellValue());
        headers.add(sheet.getRow(1).getCell(1).getStringCellValue());
        headers.add(sheet.getRow(2).getCell(1).getStringCellValue());

        this.headers = headers;
    }

    public List getFormatedProductList() {
        return formatedProductList;
    }

    public List<String> getHeaders() {
        return headers;
    }
}
