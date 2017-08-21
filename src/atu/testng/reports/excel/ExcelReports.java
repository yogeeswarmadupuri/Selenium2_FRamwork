// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports.excel;

import atu.testng.reports.chart.PieChart;
import atu.testng.reports.utils.Directory;
import atu.testng.reports.utils.Steps;
import atu.testng.reports.writers.CurrentRunPageWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.JFreeChart;
import org.testng.ITestResult;
import org.testng.Reporter;

// Referenced classes of package atu.testng.reports.excel:
//            ExcelStyler

public class ExcelReports
{
    public static class ExcelChart
    {

        private static byte[] writeChartToByteStream(JFreeChart jfreechart)
        {
            java.awt.image.BufferedImage bufferedimage = jfreechart.createBufferedImage(450, 450);
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            try
            {
                ImageIO.write(bufferedimage, "png", bytearrayoutputstream);
            }
            catch(IOException ioexception)
            {
                ioexception.printStackTrace();
            }
            byte abyte0[] = bytearrayoutputstream.toByteArray();
            return abyte0;
        }

        public static byte[] writeChartToExcel(int i, int j, int k)
        {
            return writeChartToByteStream(PieChart.generate2DPieChart(i, j, k));
        }

        public ExcelChart()
        {
        }
    }


    public ExcelReports()
    {
    }

    public static void generateExcelReport(String s, List list, List list1, List list2)
    {
        FileOutputStream fileoutputstream;
        Sheet sheet;
        fileoutputstream = null;
        Object obj2 = null;
        sheet = null;
        fileoutputstream = new FileOutputStream(s);
        XSSFWorkbook xssfworkbook = new XSSFWorkbook();
        testSummary(xssfworkbook, sheet, list, list1, list2);
        testCaseList(xssfworkbook, sheet, list, list1, list2);
        testStepDescription(xssfworkbook, sheet, list);
        testStepDescription(xssfworkbook, sheet, list1);
        xssfworkbook.write(fileoutputstream);
        fileoutputstream.close();
        Object obj6 = null;
        Object obj3 = null;
        fileoutputstream = null;
        break MISSING_BLOCK_LABEL_128;
        IOException ioexception;
        ioexception;
        try
        {
            fileoutputstream.close();
        }
        catch(IOException ioexception1) { }
        Object obj7 = null;
        Object obj4 = null;
        Object obj = null;
        break MISSING_BLOCK_LABEL_128;
        Exception exception;
        exception;
        Object obj8 = null;
        Object obj5 = null;
        Object obj1 = null;
        throw exception;
    }

    private static void addPieChartTOExcel(Workbook workbook, Sheet sheet, List list, List list1, List list2)
    {
        byte abyte0[] = ExcelChart.writeChartToExcel(list.size(), list1.size(), list2.size());
        int i = workbook.addPicture(abyte0, 6);
        CreationHelper creationhelper = workbook.getCreationHelper();
        Drawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor clientanchor = creationhelper.createClientAnchor();
        clientanchor.setCol1(0);
        clientanchor.setRow1(6);
        Picture picture = drawing.createPicture(clientanchor, i);
        picture.resize();
    }

    private static void testSummary(Workbook workbook, Sheet sheet, List list, List list1, List list2)
    {
        sheet = workbook.createSheet("TestSummary");
        Row row = sheet.createRow(0);
        row.setHeight((short)420);
        Cell cell = row.createCell(0);
        cell.setCellValue("Total Test Cases");
        ExcelStyler.setHeaderCellStyle(workbook, cell);
        cell = row.createCell(1);
        cell.setCellValue(list.size() + list1.size() + list2.size());
        ExcelStyler.setHeaderCellStyle(workbook, cell);
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue("Test Cases Passed");
        ExcelStyler.setResultCellStyle(workbook, cell, 1);
        cell = row.createCell(1);
        cell.setCellValue(list.size());
        ExcelStyler.setResultCellStyle(workbook, cell, 1);
        row = sheet.createRow(2);
        cell = row.createCell(0);
        cell.setCellValue("Test Cases Failed");
        ExcelStyler.setResultCellStyle(workbook, cell, 2);
        cell = row.createCell(1);
        cell.setCellValue(list1.size());
        ExcelStyler.setResultCellStyle(workbook, cell, 2);
        row = sheet.createRow(3);
        cell = row.createCell(0);
        cell.setCellValue("Test Cases Skipped");
        ExcelStyler.setResultCellStyle(workbook, cell, 3);
        cell = row.createCell(1);
        cell.setCellValue(list2.size());
        ExcelStyler.setResultCellStyle(workbook, cell, 3);
        addPieChartTOExcel(workbook, sheet, list, list1, list2);
        sheet.autoSizeColumn(0);
        ExcelStyler.setSheetTabColor(sheet);
    }

    private static void testCaseList(Workbook workbook, Sheet sheet, List list, List list1, List list2)
    {
        sheet = workbook.createSheet("TestSuite");
        Row row = sheet.createRow(0);
        row.setHeight((short)420);
        Cell cell = row.createCell(0);
        cell.setCellValue("S.No");
        ExcelStyler.setHeaderCellStyle(workbook, cell);
        cell = row.createCell(1);
        cell.setCellValue("Package");
        ExcelStyler.setHeaderCellStyle(workbook, cell);
        sheet.autoSizeColumn(1);
        cell = row.createCell(2);
        cell.setCellValue("ClassName");
        ExcelStyler.setHeaderCellStyle(workbook, cell);
        sheet.autoSizeColumn(2);
        cell = row.createCell(3);
        cell.setCellValue("TestCase Name");
        ExcelStyler.setHeaderCellStyle(workbook, cell);
        sheet.autoSizeColumn(3);
        cell = row.createCell(4);
        cell.setCellValue("Iteration");
        ExcelStyler.setHeaderCellStyle(workbook, cell);
        sheet.autoSizeColumn(4);
        cell = row.createCell(5);
        cell.setCellValue("Time Taken");
        ExcelStyler.setHeaderCellStyle(workbook, cell);
        sheet.autoSizeColumn(5);
        cell = row.createCell(6);
        cell.setCellValue("Result");
        ExcelStyler.setHeaderCellStyle(workbook, cell);
        cell = row.createCell(7);
        cell.setCellValue("ATU_TestCase_ID");
        CellStyle cellstyle = ExcelStyler.setHeaderCellStyle(workbook, cell);
        cellstyle.setBorderRight((short)1);
        cellstyle.setRightBorderColor(ExcelStyler.HEADER_BG_COLOR);
        cell.setCellStyle(cellstyle);
        sheet.autoSizeColumn(7);
        int i = 1;
        i = writeSummaryData(workbook, sheet, list, i);
        i = writeSummaryData(workbook, sheet, list1, i);
        i = writeSummaryData(workbook, sheet, list2, i);
        Row row1 = sheet.getRow(i - 1);
        ExcelStyler.setBorderLine(workbook, row1);
        ExcelStyler.setSheetTabColor(sheet);
    }

    private static int writeSummaryData(Workbook workbook, Sheet sheet, List list, int i)
    {
        list.size();
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            ITestResult itestresult = (ITestResult)iterator.next();
            Row row = sheet.createRow(i);
            Cell cell = row.createCell(0);
            cell.setCellValue(i);
            setHyperLink(itestresult, workbook, cell);
            cell = row.createCell(1);
            cell.setCellValue(CurrentRunPageWriter.getPackageName(itestresult));
            setHyperLink(itestresult, workbook, cell);
            cell = row.createCell(2);
            cell.setCellValue(CurrentRunPageWriter.getClassName(itestresult));
            setHyperLink(itestresult, workbook, cell);
            cell = row.createCell(3);
            cell.setCellValue(CurrentRunPageWriter.getTestCaseName(itestresult));
            setHyperLink(itestresult, workbook, cell);
            cell = row.createCell(4);
            cell.setCellValue(CurrentRunPageWriter.getIteration(itestresult));
            setHyperLink(itestresult, workbook, cell);
            cell = row.createCell(5);
            cell.setCellValue(CurrentRunPageWriter.getExecutionTime(itestresult));
            setHyperLink(itestresult, workbook, cell);
            cell = row.createCell(6);
            ExcelStyler.setResultCellStyle(workbook, cell, itestresult);
            setHyperLink(itestresult, workbook, cell);
            cell = row.createCell(7);
            cell.setCellValue((new StringBuilder()).append("ATU_TC_").append(atuTC_ID).toString());
            setHyperLink(itestresult, workbook, cell);
            CellStyle cellstyle = workbook.createCellStyle();
            cellstyle.setBorderRight((short)1);
            cellstyle.setRightBorderColor(ExcelStyler.HEADER_BG_COLOR);
            cell.setCellStyle(cellstyle);
            atuTC_ID++;
            i++;
        }

        return i;
    }

    private static void setHyperLink(ITestResult itestresult, Workbook workbook, Cell cell)
    {
        if(itestresult.getStatus() != 3)
        {
            CreationHelper creationhelper = workbook.getCreationHelper();
            Hyperlink hyperlink = creationhelper.createHyperlink(2);
            hyperlink.setAddress((new StringBuilder()).append("'ATU_TC_").append(atuTC_ID).append("'!A1").toString());
            itestresult.setAttribute("testID", Integer.valueOf(atuTC_ID));
            cell.setHyperlink(hyperlink);
        }
    }

    private static void setHyperLink(String s, Workbook workbook, Cell cell)
    {
        CreationHelper creationhelper = workbook.getCreationHelper();
        Hyperlink hyperlink = creationhelper.createHyperlink(2);
        hyperlink.setAddress((new StringBuilder()).append("'").append(s).append("'!A1").toString());
        cell.setHyperlink(hyperlink);
    }

    private static void testStepDescription(Workbook workbook, Sheet sheet, List list)
    {
        Cell cell;
        for(Iterator iterator = list.iterator(); iterator.hasNext(); cell.setCellStyle(ExcelStyler.setColor(workbook, ExcelStyler.FOOTER_BG_COLOR, ExcelStyler.FONT_COLOR)))
        {
            ITestResult itestresult = (ITestResult)iterator.next();
            sheet = workbook.createSheet((new StringBuilder()).append("ATU_TC_").append(itestresult.getAttribute("testID").toString()).toString());
            ExcelStyler.setSheetTabColor(sheet, itestresult);
            Row row = sheet.createRow(0);
            row.setHeight((short)420);
            cell = row.createCell(0);
            cell.setCellValue("S.No");
            ExcelStyler.setHeaderCellStyle(workbook, cell);
            cell = row.createCell(1);
            cell.setCellValue("Step Description");
            ExcelStyler.setHeaderCellStyle(workbook, cell);
            sheet.autoSizeColumn(1);
            cell = row.createCell(2);
            cell.setCellValue("Input Value");
            ExcelStyler.setHeaderCellStyle(workbook, cell);
            sheet.autoSizeColumn(2);
            cell = row.createCell(3);
            cell.setCellValue("Expected value");
            ExcelStyler.setHeaderCellStyle(workbook, cell);
            sheet.autoSizeColumn(3);
            cell = row.createCell(4);
            cell.setCellValue("Actual Value");
            ExcelStyler.setHeaderCellStyle(workbook, cell);
            sheet.autoSizeColumn(4);
            cell = row.createCell(5);
            cell.setCellValue("Time Taken");
            ExcelStyler.setHeaderCellStyle(workbook, cell);
            sheet.autoSizeColumn(5);
            cell = row.createCell(6);
            cell.setCellValue("Line No");
            ExcelStyler.setHeaderCellStyle(workbook, cell);
            cell = row.createCell(7);
            cell.setCellValue("Screenshot");
            ExcelStyler.setHeaderCellStyle(workbook, cell);
            sheet.autoSizeColumn(7);
            List list1 = Reporter.getOutput(itestresult);
            int i = 1;
            for(Iterator iterator1 = list1.iterator(); iterator1.hasNext();)
            {
                String s = (String)iterator1.next();
                Steps steps = (Steps)itestresult.getAttribute(s);
                Row row2 = sheet.createRow(i);
                if(steps == null)
                {
                    cell = row2.createCell(0);
                    cell.setCellValue(s);
                } else
                {
                    cell = row2.createCell(0);
                    cell.setCellValue(i);
                    cell = row2.createCell(1);
                    cell.setCellValue(steps.getDescription());
                    cell = row2.createCell(2);
                    cell.setCellValue(steps.getInputValue());
                    cell = row2.createCell(3);
                    cell.setCellValue(steps.getExpectedValue());
                    cell = row2.createCell(4);
                    cell.setCellValue(steps.getActualValue());
                    cell = row2.createCell(5);
                    cell.setCellValue(steps.getTime());
                    cell = row2.createCell(6);
                    cell.setCellValue(steps.getLineNum());
                    cell = row2.createCell(7);
                    try
                    {
                        Integer.parseInt(steps.getScreenShot().trim());
                        CreationHelper creationhelper = workbook.getCreationHelper();
                        Hyperlink hyperlink = creationhelper.createHyperlink(4);
                        String s1 = (new StringBuilder()).append(itestresult.getAttribute("relativeReportDir")).append(Directory.SEP).append(Directory.SCREENSHOT_DIRName).toString();
                        s1 = s1.replace(" ", "%20").replace(Directory.SEP, "/");
                        s1 = (new StringBuilder()).append(s1).append("/").append(i).append(".PNG").toString();
                        hyperlink.setAddress(s1);
                        cell.setCellValue("Screenshot");
                        cell.setHyperlink(hyperlink);
                    }
                    catch(Exception exception) { }
                    i++;
                }
            }

            Row row1 = sheet.createRow(i);
            cell = row1.createCell(0);
            cell.setCellValue("Go To TestSuite Sheet");
            setHyperLink("TestSuite", workbook, cell);
            cell = row1.createCell(1);
            cell = row1.createCell(2);
            cell = row1.createCell(3);
            cell = row1.createCell(4);
            cell = row1.createCell(5);
            cell = row1.createCell(6);
            cell = row1.createCell(7);
            int j = sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 7));
            cell = sheet.getRow(i).getCell(j - 1);
        }

    }

    public static final String TEST_ID_PREFIX = "ATU_TC_";
    public static int atuTC_ID = 1;

}
