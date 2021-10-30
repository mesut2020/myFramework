package utilities;

import io.cucumber.java.Scenario;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtility {

    private static Workbook workBook;
    private static Sheet workSheet;
    private static String path;

    public ExcelUtility(String path, String sheetName) {//This Constructor is to open and access the excel file

        this.path = path;

        try {
            FileInputStream fileInputStream = new FileInputStream(path); // Opening the Excel file
            workBook = WorkbookFactory.create(fileInputStream); // accessing the workbook
            workSheet = workBook.getSheet(sheetName); //getting the worksheet
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int columnCount() {
        return workSheet.getRow(0).getLastCellNum(); //getting the number of columns in first Row
    }

    public static int rowCount() {
        return workSheet.getLastRowNum() + 1; // get the number of Rows
    }

    /**
     * @param rowNum
     * @param colNum
     * @return value of cell
     */
    public static String getCellData(int rowNum, int colNum) {
        Cell cell;
        try {
            cell = workSheet.getRow(rowNum).getCell(colNum);
            String cellData = cell.toString();
            return cellData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return all data in a Worksheet as a two-dimensional Array
     */
    public static String[][] getDataArray() {
        String[][] data = new String[rowCount()][columnCount()];
        for (int i = 0; i < rowCount(); i++) {
            for (int j = 0; j < columnCount(); j++) {
                String value = getCellData(i, j);
                data[i][j] = value;
            }
        }
        return data;
    }

    //This will get the list of the data in the excel file
    //This is a list of map. This takes the data as string and will return the data as a Map of String
    public static List<Map<String, String>> getDataList() {

        List<String> columnHeaders = getColumnHeaders();
        List<Map<String, String>> data = new ArrayList<>();

        for (int i = 1; i < rowCount(); i++) {
            Row row = workSheet.getRow(i);
            // creating map of the row using the column and value
            // key=columnHeaders, value=cell
            Map<String, String> rowMap = new HashMap<String, String>();

            for (Cell cell : row) {
                int columnIndex = cell.getColumnIndex();
                rowMap.put(columnHeaders.get(columnIndex), cell.toString());
            }
            data.add(rowMap);
        }
        return data;
    }

    //==============going to the first row and reading each cell one by one==================//
    public static List<String> getColumnHeaders() {
        List<String> columnHeaders = new ArrayList<>();
        for (Cell cell : workSheet.getRow(0)) {
            columnHeaders.add(cell.toString());
        }
        return columnHeaders;
    }

    //=========   Set a cell with value according to RowNumber and ColumnNumber   ===============//
    public static void setCellData(String value, int rowNum, int colNum) {
        Cell cell;
        Row row;
        try {
            row = workSheet.getRow(rowNum);
            cell = row.getCell(colNum);
            if (cell == null) {//if there is no value, create a cell.
                cell = row.createCell(colNum);
                cell.setCellValue(value);
            } else {
                cell.setCellValue(value);
            }
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            workBook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //=========   Set a cell with value according to RowNumber and ColumnHeader   ===============//
    public static void setCellData(String value, String columnName, int row) {
        int column = getColumnHeaders().indexOf(columnName);
        setCellData(value, row, column);
    }

    public static List<List<String>> getListData(String path, String sheetName, int columnCount) {

        List<List<String>> lists = new ArrayList<>();

        try {
            FileInputStream inputStream = new FileInputStream(path);
            workBook = WorkbookFactory.create(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        workSheet = workBook.getSheet(sheetName);

        int rowCount = workSheet.getPhysicalNumberOfRows();

        for (int i = 0; i < rowCount; i++) {
            Row row = workSheet.getRow(i);
            List<String> listsrow = new ArrayList<>();
            int cellCount = row.getPhysicalNumberOfCells();

            if (cellCount < columnCount)
                columnCount = cellCount;

            for (int j = 0; j < columnCount; j++) {
                Cell cell = row.getCell(j);
                listsrow.add(j, cell.toString());
            }
            lists.add(listsrow);
        }
        return lists;
    }

    public static void writeScenarioToExcel(Scenario scenario) {

        path = "src/main/resources/RunnedScenarios.xlsx";
        String sheetName = "Results";
        String time = DateUtil.todaysDateAndTime();
        String browser = Driver.threadBrowserName.get();

        File f = new File(path);

        if (!f.exists()) {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet(sheetName);

            Row row = sheet.createRow(0);

            Cell cell = row.createCell(0);
            cell.setCellValue("No");
            cell = row.createCell(1);
            cell.setCellValue("Scenario");
            cell = row.createCell(2);
            cell.setCellValue("Status of scenario");
            cell = row.createCell(3);
            cell.setCellValue("Date and Time");
            cell = row.createCell(4);
            cell.setCellValue("Browser");

            row = sheet.createRow(1);
            cell = row.createCell(0);
            cell.setCellValue(1);
            cell = row.createCell(1);
            cell.setCellValue(scenario.getName());
            cell = row.createCell(2);
            cell.setCellValue(scenario.getStatus().toString());
            cell = row.createCell(3);
            cell.setCellValue(time);
            cell = row.createCell(4);
            cell.setCellValue(browser);

            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(path);
                workbook.write(outputStream); // write workbook in memeory to file
                workbook.close(); // clean workbook in memory
                outputStream.close(); // close file

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Workbook workbook = null;
            Sheet sheet = null;
            try {
                FileInputStream inputStream = new FileInputStream(path);
                workbook = WorkbookFactory.create(inputStream);
                sheet = workbook.getSheet(sheetName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int numberOfRow = sheet.getPhysicalNumberOfRows();
            Row row = sheet.createRow(numberOfRow);

            Cell cell = row.createCell(0);
            cell.setCellValue(numberOfRow);
            cell = row.createCell(1);
            cell.setCellValue(scenario.getName());
            cell = row.createCell(2);
            cell.setCellValue(scenario.getStatus().toString());
            cell = row.createCell(3);
            cell.setCellValue(time);
            cell = row.createCell(4);
            cell.setCellValue(browser);

            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(path);
                workbook.write(outputStream); // write workbook in memeory to file
                workbook.close(); // clean workbook in memory
                outputStream.close(); // close file

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
