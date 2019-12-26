package DataDrivenTesting_AddNewEmployee;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class XLUtils {
    public static FileInputStream fi;
    public static FileOutputStream fo;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws;
    public  static XSSFRow row;
    public static XSSFCell cell;


    public static int getRowCount(String exlFile, String exlSheet) throws IOException {
        fi = new FileInputStream(exlFile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(exlSheet);
        int rowCount = ws.getLastRowNum();
        wb.close();
        fi.close();
        return  rowCount;
    }

    public static int getCellCount(String exlFile, String exlSheet, int rowNum) throws IOException{

        fi = new FileInputStream(exlFile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(exlSheet);
        row = ws.getRow(rowNum);
        int cellCount = row.getLastCellNum();
        wb.close();
        fi.close();
        return  cellCount;
    }

    public static String getCellData(String exlFile, String exlSheet, int rowNum, int colNum) throws IOException{
        fi = new FileInputStream(exlFile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(exlSheet);
        row = ws.getRow(rowNum);
        cell = row.getCell(colNum);
        String data;
        try {
            DataFormatter formatter = new DataFormatter();
            String cellData = formatter.formatCellValue(cell);
            return cellData;
        }catch (Exception e){
            data = "";
        }
        wb.close();
        fi.close();
        return  data;
    }

    public static  void setCellData(String exlFile, String exlSheet, int rowNum, int colNum, String data) throws IOException{
        fi = new FileInputStream(exlFile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(exlSheet);
        row = ws.getRow(rowNum);
        cell=row.createCell(colNum);
        cell.setCellValue(data);
        fo=new FileOutputStream(exlFile);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();

    }

}
