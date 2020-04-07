package core;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Documents {

    public static ArrayList<ArrayList<String>> readFile(File f) {
        String path = f.getAbsolutePath();
        if(path.endsWith(".xls")) return readXLS(f);
        return null;
    }
    /*
    public static ArrayList<ArrayList<String>> readXLSX(File f) {
        try {
            ArrayList<ArrayList<String>> rowList = new ArrayList<>();
            ArrayList<String> colList;
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(f));
            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFRow row;
            XSSFCell cell;
            String value;
            for(int i = sheet.getFirstRowNum(),//获得第一行的序号
                rowCount = 0,//当前读取行数的计数
                limit = sheet.getPhysicalNumberOfRows();//总行数
                rowCount < limit; i++) {
                row = sheet.getRow(i);
                colList = new ArrayList<>();
                if(row == null) {//读取到空行
                    if(i != sheet.getPhysicalNumberOfRows()) {
                        rowList.add(colList);
                    }
                    continue;
                } else {
                    rowCount++;
                }
                int cell_start = row.getFirstCellNum();
                int cell_last = row.getLastCellNum();
                for(int j = cell_start; j <= cell_last; j++) {
                    cell = row.getCell(j);
                    if(cell == null) {
                        if(j != row.getLastCellNum()) {
                            colList.add("");
                        }
                        continue;
                    }
                    switch (cell.getCellTypeEnum()) {
                        case BLANK:
                            value = "";
                            break;
                        case STRING:
                            value = cell.getStringCellValue();
                            break;
                        case BOOLEAN:
                            value = String.valueOf(cell.getBooleanCellValue());
                            break;
                        case NUMERIC:
                            value = String.valueOf((int) cell.getNumericCellValue());
                            break;
                        default:
                            value = cell.toString();
                            break;
                    }
                    colList.add(value);
                    System.out.println("row:" + i + " col:" + j + " "+ value.replace(" ", "").replace("\n", ""));
                }
                rowList.add(colList);
            }
            return rowList;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    */
    public static ArrayList<ArrayList<String>> readXLS(File f) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        try {
            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(f));
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row;
            ArrayList<String> cache;
            int col;
            for (int i=0; i<sheet.getPhysicalNumberOfRows(); i++) {
                cache = new ArrayList<String>();
                row = sheet.getRow(i);
                if(row == null) continue;
                col = 0;
                for(Cell c : row) {
                    switch(c.getCellTypeEnum()) {
                        case BLANK:
                            cache.add("");
                            break;
                        case NUMERIC:
                            cache.add(String.valueOf((int)c.getNumericCellValue()));
                            break;
                        case BOOLEAN:
                            cache.add(String.valueOf(c.getBooleanCellValue()));
                            break;
                        case STRING:
                            cache.add(c.getStringCellValue());
                            break;
                        default:
                            cache.add(c.toString());
                    }
                    System.out.println("row:" + i + " col:" + col + " " + c.toString().replace(" ", "").replace("\n", ""));
                    col++;
                }
                result.add(cache);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
