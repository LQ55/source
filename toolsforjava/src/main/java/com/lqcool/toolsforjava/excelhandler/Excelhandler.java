package com.lqcool.toolsforjava.excelhandler;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @Auther: LQ
 * @Description: 一个excel处理工具
 */
public class Excelhandler {
    /**
     * 读取Excel的方法
     * @param excelFile
     */
    public static void readExcel(File excelFile) {
        try {
            FileInputStream fis = new FileInputStream(excelFile);
//			Workbook workbook = new HSSFWorkbook(fis);// 创建文件对象，excel2003之前
            Workbook workbook = new XSSFWorkbook(fis);// 创建文件对象，多样支持
            int sheetNums = workbook.getNumberOfSheets();
            for(int i = 0; i < sheetNums; i ++){//循环取出工作表进行处理
                Sheet sheet = workbook.getSheetAt(i);
                handleSheet(sheet);
            }
            // 首先要创建一个原始Excel文件的输出流对象！
            FileOutputStream out = new FileOutputStream(excelFile);
            // 将最新的 Excel 文件写入到文件输出流中，更新文件信息！
            workbook.write(out);
            // 执行 flush 操作， 将缓存区内的信息更新到文件上
            out.flush();
            out.close();
            System.out.println("成功处理文件[" +excelFile.getAbsolutePath()+ "]");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理sheet
     * @param sheet
     */
    public static void handleSheet(Sheet sheet) {
        String sheetName = sheet.getSheetName();
        int rowNums = sheet.getLastRowNum();
        for (int i = 0; i < rowNums; i ++){
            Row curRow = sheet.getRow(i);
            if(curRow == null){
                continue;
            }
            int cellNums = curRow.getLastCellNum();
            for(int j = 0; j < cellNums; j ++){
                Cell curCell = null;
                try {
                    curCell = curRow.getCell(j);
                }catch (Exception e){
                    continue;
                }
                if(curCell == null){
                    continue;
                }
                String cellValue = getCellVal(curCell);
                System.out.println("{"+cellValue+"}");
                if(curCell.getCellType() == curCell.CELL_TYPE_NUMERIC){
                    //产生随机系数
                    double randomNum = Math.random();
                    curCell.setCellValue(randomNum*curCell.getNumericCellValue());
                }
            }
        }

    }

    /**
     * 获取该cell的值
     * @param cell
     */
    public static String getCellVal(Cell cell) {

        String cellValue = "";

        if (cell != null) {
            // 以下是判断数据的类型
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC: // 数字
                    cellValue = cell.getNumericCellValue() + "";
                    break;

                case Cell.CELL_TYPE_STRING: // 字符串
                    cellValue = cell.getStringCellValue();
                    break;

                case Cell.CELL_TYPE_BOOLEAN: // Boolean
                    cellValue = cell.getBooleanCellValue() + "";
                    break;

                case Cell.CELL_TYPE_FORMULA: // 公式
                    cellValue = cell.getCellFormula() + "";
                    break;

                case Cell.CELL_TYPE_BLANK: // 空值
                    cellValue = "";
                    break;

                case Cell.CELL_TYPE_ERROR: // 故障
                    cellValue = "非法字符";
                    break;

                default:
                    cellValue = "未知类型";
                    break;
            }
        }
        return cellValue;
    }
}
