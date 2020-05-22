package com.practice.excel;

import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Copyright © 广州禾信仪器股份有限公司. All rights reserved.
 *
 * @Author hxsdd-20
 * @Date 2020/5/22 15:20
 * @Version 1.0
 */
public class ExcelUtil {
    private XSSFSheet sheet;

    /**
     * 构造函数，初始化excel数据
     * @param filePath  excel路径
     * @param sheetName sheet表名
     */
    ExcelUtil(String filePath,String sheetName){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
            //获取sheet
//            sheet = sheets.getSheet(sheetName);
            sheet=sheets.getSheetAt(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据行和列的索引获取单元格的数据
     * @param row
     * @param column
     * @return
     */
    public String getExcelDateByIndex(int row,int column){
        XSSFRow row1 = sheet.getRow(row);
        String cell = row1.getCell(column).toString();
        return cell;
    }

    /**
     * 根据某一列值为“******”的这一行，来获取该行第x列的值
     * @param caseName
     * @param currentColumn 当前单元格列的索引
     * @param targetColumn 目标单元格列的索引
     * @return
     */
    public String getCellByCaseName(String caseName,int currentColumn,int targetColumn){
        String operateSteps="";
        //获取行数
        int rows = sheet.getPhysicalNumberOfRows();
        for(int i=0;i<rows;i++){
            XSSFRow row = sheet.getRow(i);
            String cell = row.getCell(currentColumn).toString();
            if(cell.equals(caseName)){
                operateSteps = row.getCell(targetColumn).toString();
                break;
            }
        }
        return operateSteps;
    }

    //打印excel数据
    public void readExcelUtil(){
        //获取行数
        int rows = sheet.getPhysicalNumberOfRows();
        for(int i=0;i<rows;i++){
            //获取列数
            XSSFRow row = sheet.getRow(i);
            int columns = row.getPhysicalNumberOfCells();
            for(int j=0;j<columns;j++){
                String cell = row.getCell(j).toString();
                System.out.println(cell);
            }
        }
    }

    public String excelTime(Cell cell){
        String guarantee_time = null;
        if(DateUtil.isCellDateFormatted(cell)){
            //用于转化为日期格式
            Date d = cell.getDateCellValue();
//	             System.err.println(d.toString());
//	             DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            guarantee_time = formater.format(d);
        }
        return guarantee_time;
    }
    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        // 判断数据的类型
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: // 数字
                //如果为时间格式的内容
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    //注：format格式 yyyy-MM-dd hh:mm:ss 中小时为12小时制，若要24小时制，则把小h变为H即可，yyyy-MM-dd HH:mm:ss
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    cellValue=sdf.format(HSSFDateUtil.getJavaDate(cell.
                            getNumericCellValue())).toString();
                    break;
                } else {
                    HSSFDataFormatter dataFormatter = new HSSFDataFormatter();
                    String cellFormatted = dataFormatter.formatCellValue(cell);
                    cellValue=cellFormatted;
                }
                break;
            case Cell.CELL_TYPE_STRING: // 字符串
//                log.info("==字符串类型==");
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: // Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: // 公式
//                log.info("==公式=="+cell.getCellStyle().getDataFormatString());
                if (cell.getCellStyle().getDataFormatString().indexOf("%") != -1) {
                    cellValue=cell.getNumericCellValue()*100+"%";
                }
                break;
            case Cell.CELL_TYPE_BLANK: // 空值
                cellValue = null;
                break;
            case Cell.CELL_TYPE_ERROR: // 故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }

    //测试方法
    public static void main(String[] args){
        ExcelUtil sheet1 = new ExcelUtil("F:\\wechatfile\\WeChat Files\\echo520jun\\FileStorage\\File\\2020-05\\走航插值test.csv.xlsx", "username");
//        //获取第二行第4列
//        String cell2 = sheet1.getExcelDateByIndex(1, 3);
//        //根据第3列值为“customer23”的这一行，来获取该行第2列的值
//        String cell3 = sheet1.getCellByCaseName("customer23", 2,1);
//        System.out.println(cell2);
//        System.out.println(cell3);
        XSSFSheet sheet = sheet1.sheet;
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();
        for (int i = firstRowNum; i < lastRowNum; i++) {
            XSSFRow row = sheet.getRow(i);
            int j = row.getLastCellNum();
            int k=row.getFirstCellNum();
            for (int l = k; l < j; l++) {
                String excelDateByIndex = sheet1.getExcelDateByIndex(i, l);
                System.out.print(excelDateByIndex+";");
            }
            System.out.println();
        }
    }
}
