// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports.excel;

import atu.testng.reports.enums.ReportLabels;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.ITestResult;

public class ExcelStyler
{

    public ExcelStyler()
    {
    }

    public static void setSheetTabColor(Sheet sheet, ITestResult itestresult)
    {
        if(itestresult.getStatus() == 2)
            ((XSSFSheet)sheet).setTabColor(IndexedColors.RED.index);
        else
        if(itestresult.getStatus() == 1)
            ((XSSFSheet)sheet).setTabColor(IndexedColors.GREEN.index);
        else
            ((XSSFSheet)sheet).setTabColor(IndexedColors.BLUE.index);
    }

    public static void setSheetTabColor(Sheet sheet)
    {
        ((XSSFSheet)sheet).setTabColor(SHEET_COLOR);
    }

    public static void setBorderLine(Workbook workbook, Row row)
    {
        for(int i = 0; i < 8; i++)
        {
            Cell cell = row.getCell(i);
            CellStyle cellstyle = workbook.createCellStyle();
            if(i == 6 || i == 7)
                cellstyle = cell.getCellStyle();
            cellstyle.setBorderBottom((short)1);
            cellstyle.setBottomBorderColor(HEADER_BG_COLOR);
            cell.setCellStyle(cellstyle);
        }

    }

    public static CellStyle setHeaderCellStyle(Workbook workbook, Cell cell)
    {
        Font font = workbook.createFont();
        font.setColor(FONT_COLOR);
        CellStyle cellstyle = workbook.createCellStyle();
        cellstyle.setFillForegroundColor(HEADER_BG_COLOR);
        cellstyle.setFillPattern((short)1);
        cellstyle.setBorderRight((short)1);
        cellstyle.setRightBorderColor(IndexedColors.WHITE.getIndex());
        cellstyle.setFont(font);
        cellstyle.setAlignment((short)2);
        cellstyle.setVerticalAlignment((short)1);
        cell.setCellStyle(cellstyle);
        return cellstyle;
    }

    static CellStyle setColor(Workbook workbook, short word0, short word1)
    {
        Font font = workbook.createFont();
        font.setColor(word1);
        CellStyle cellstyle = workbook.createCellStyle();
        cellstyle.setFillForegroundColor(word0);
        cellstyle.setFillPattern((short)1);
        cellstyle.setBorderRight((short)1);
        cellstyle.setRightBorderColor(IndexedColors.WHITE.getIndex());
        cellstyle.setFont(font);
        cellstyle.setAlignment((short)2);
        return cellstyle;
    }

    static void setResultCellStyle(Workbook workbook, Cell cell, int i)
    {
        if(i == 2)
            cell.setCellStyle(setColor(workbook, RESULT_FAIL_COLOR, FONT_COLOR));
        else
        if(i == 1)
            cell.setCellStyle(setColor(workbook, RESULT_PASS_COLOR, FONT_COLOR));
        else
        if(i == 3)
            cell.setCellStyle(setColor(workbook, RESULT_SKIP_COLOR, FONT_COLOR));
    }

    static void setResultCellStyle(Workbook workbook, Cell cell, ITestResult itestresult)
    {
        if(itestresult.getStatus() == 2)
        {
            cell.setCellValue(ReportLabels.FAIL.getLabel());
            cell.setCellStyle(setColor(workbook, RESULT_FAIL_COLOR, FONT_COLOR));
        } else
        if(itestresult.getStatus() == 1)
        {
            cell.setCellValue(ReportLabels.PASS.getLabel());
            cell.setCellStyle(setColor(workbook, RESULT_PASS_COLOR, FONT_COLOR));
        } else
        if(itestresult.getStatus() == 3)
        {
            cell.setCellValue(ReportLabels.SKIP.getLabel());
            cell.setCellStyle(setColor(workbook, RESULT_SKIP_COLOR, FONT_COLOR));
        }
    }

    public static final short RESULT_PASS_COLOR;
    public static final short RESULT_FAIL_COLOR;
    public static final short RESULT_SKIP_COLOR;
    public static final short FONT_COLOR;
    public static final short HEADER_BG_COLOR;
    public static final short FOOTER_BG_COLOR;
    public static final short HEADER_HEIGHT = 420;
    public static final short SHEET_COLOR;

    static 
    {
        RESULT_PASS_COLOR = IndexedColors.GREEN.getIndex();
        RESULT_FAIL_COLOR = IndexedColors.RED.getIndex();
        RESULT_SKIP_COLOR = IndexedColors.ROYAL_BLUE.getIndex();
        FONT_COLOR = IndexedColors.WHITE.getIndex();
        HEADER_BG_COLOR = IndexedColors.GREY_50_PERCENT.getIndex();
        FOOTER_BG_COLOR = IndexedColors.DARK_TEAL.getIndex();
        SHEET_COLOR = IndexedColors.DARK_TEAL.getIndex();
    }
}
