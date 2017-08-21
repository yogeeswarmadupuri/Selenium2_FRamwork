// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports.writers;

import atu.testng.reports.ATUReports;
import atu.testng.reports.enums.ReportLabels;
import atu.testng.reports.utils.*;
import java.io.PrintWriter;
import java.util.*;
import org.testng.*;
import org.testng.xml.XmlTest;

// Referenced classes of package atu.testng.reports.writers:
//            ReportsPage

public class CurrentRunPageWriter extends ReportsPage
{

    public CurrentRunPageWriter()
    {
    }

    public static void menuLink(PrintWriter printwriter, int i)
    {
        printwriter.println("\n            <tr id=\"container\">\n                <td id=\"menu\">\n                    <ul> \n");
        printwriter.println(" <li class=\"menuStyle\"><a href=\"../../index.html\" >Index</a></li><li style=\"padding-top: 4px;\"><a href=\"../ConsolidatedPage.html\" >Consolidated Page</a></li>\n");
        if(i == 1)
        {
            printwriter.println((new StringBuilder()).append("\n <li class=\"menuStyle\"><a href=\"").append(Directory.RUNName).append(i).append(Directory.SEP).append("CurrentRun.html\" >").append("Run ").append(i).append(" </a></li>\n").toString());
        } else
        {
            int j = 1;
            do
            {
                if(j > i)
                    break;
                if(j == i)
                {
                    printwriter.println((new StringBuilder()).append("\n <li style=\"padding-top: 4px;padding-bottom: 4px;\"><a href=\"").append(Directory.RUNName).append(j).append(Directory.SEP).append("CurrentRun.html\" >").append("Run ").append(j).append(" </a></li>\n").toString());
                    break;
                }
                printwriter.println((new StringBuilder()).append("\n <li class=\"menuStyle\"><a href=\"").append(Directory.RUNName).append(j).append(Directory.SEP).append("CurrentRun.html\" >").append("Run ").append(j).append(" </a></li>\n").toString());
                j++;
            } while(true);
        }
        printwriter.println("\n                    </ul>\n                </td>\n\n");
    }

    public static String getExecutionTime(ITestResult itestresult)
    {
        long l = itestresult.getEndMillis() - itestresult.getStartMillis();
        if(l > 1000L)
        {
            l /= 1000L;
            return (new StringBuilder()).append(l).append(" Sec").toString();
        } else
        {
            return (new StringBuilder()).append(l).append(" Milli Sec").toString();
        }
    }

    public static String getExecutionTime(long l, long l1)
    {
        long l2 = l1 - l;
        if(l2 > 1000L)
        {
            l2 /= 1000L;
            return (new StringBuilder()).append(l2).append(" Sec").toString();
        } else
        {
            return (new StringBuilder()).append(l2).append(" Milli Sec").toString();
        }
    }

    public static void content(PrintWriter printwriter, List list, List list1, List list2, List list3, List list4, List list5, int i, 
            long l, long l1)
    {
        int j = list.size() + list1.size() + list2.size();
        printwriter.println((new StringBuilder()).append("<td id=\"content\">\n                    <div class=\"info\">\n                        The following pie chart demonstrates the percentage of Passed, Failed and Skipped Test Cases<br/>\n                        Time Taken for Executing below Test Cases: <b>").append(getExecutionTime(l, l1)).append("</b> <br/>\n").append("                        Current Run Number: <b>Run ").append(i).append("</b>\n").append("                    </div>\n").append("<div class=\"info\">").append("<br/>").append("<b>Run Description</b><br/><br/>").append(ATUReports.currentRunDescription).append("</div>").append("                    <div>\n").append("                        <div class=\"chartStyle summary\" style=\"width: 32%;background-color: #3B9C9C;\">\n").append("                            <b>Summary</b><br/><br/>\n").append("                            <table>\n").append("                            <tr>\n").append("                                <td>Execution Date</td>\n").append("                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n").append("                                <td>").append(Utils.getCurrentTime()).append("</td>\n").append("                            </tr>\n").append("                            <tr>\n").append("                                <td>Total Test Cases</td>\n").append("                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n").append("                                <td>").append(j).append("</td>\n").append("                            </tr>\n").append("                            <tr>\n").append("                                <td>Passed</td>\n").append("                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n").append("                                <td>").append(list.size()).append("</td>\n").append("                            </tr>\n").append("                            \n").append("                            <tr>\n").append("                                <td>Failed</td>\n").append("                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n").append("                                <td>").append(list1.size()).append("</td>\n").append("                            </tr>\n").append("\n").append("                            <tr>\n").append("                                <td>Skipped</td>\n").append("                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n").append("                                <td>").append(list2.size()).append("</td>\n").append("                            </tr>\n").append("                        </table> \n").append("                        </div>").append("                        <div class=\"chartStyle\" style=\"text-align: left;margin-left: 30px;float: left;width: 60%;\">                        \n").append("                            <div id=\"chart\" style=\"height:300px;color:black;\"></div>\n").append("                        </div>\n").append("                    </div>\n").append("                    <div>\n").toString());
        if(Directory.recordSuiteExecution)
            printwriter.println((new StringBuilder()).append("<p id=\"showmenu\">Click Me to Show/Hide the Execution Video</p><div id=\"video\" class=\"video\"><object classid=\"clsid:9BE31822-FDAD-461B-AD51-BE1D1C159921\" codebase=\"http://downloads.videolan.org/pub/videolan/vlc/latest/win32/axvlc.cab\" width=\"400\" height=\"300\" id=\"vlc\" events=\"True\">  <param name=\"Src\" value=\"Recording").append(Directory.SEP).append("ATU_CompleteSuiteRecording").append(".mov\"></param>").append("  <param name=\"ShowDisplay\" value=\"True\" ></param>").append("    <param name=\"AutoLoop\" value=\"no\"></param>").append("    <param name=\"AutoPlay\" value=\"no\"></param>").append("    <embed type=\"application/x-google-vlc-plugin\" name=\"vlcfirefox\" autoplay=\"no\" loop=\"no\" width=\"99%\"  height=\"100%\" target=\"").append("Recording").append(Directory.SEP).append("ATU_CompleteSuiteRecording").append(".mov\"></embed>").append(" </object>").append("</div>").toString());
        else
            printwriter.println("<p id=\"showmenu\">No Video Recording Available</p>");
        printwriter.println("<div style=\"float:left;  color: #585858; font-size: 14px;\">\t<select id=\"tcFilter\" class=\"filter\">\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"all\">All Methods</option>\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"tests\">Test Methods</option>\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"pass\">Passed Test Cases</option>\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"fail\">Failed Test Cases</option>\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"skip\">Skipped Test Cases</option>\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"config\">Configuration Methods</option>\n\t\t\t\t\t</select>\tFilter Methods &nbsp;</div>");
        printwriter.println("<div style=\"float:left;  color: #585858; font-size: 14px;\">\t<select id=\"suiteFilter\" class=\"filter\">\n<option class=\"filterOption\" value=\"all\">All Suites</option>\n");
        String s;
        for(Iterator iterator = Attributes.getSuiteNameMapperMap().keySet().iterator(); iterator.hasNext(); printwriter.println((new StringBuilder()).append("<option class=\"filterOption\" value=\"").append(Attributes.getSuiteNameMapperMap().get(s)).append("\">").append(s).append("</option>\n").toString()))
            s = (String)iterator.next();

        printwriter.println("</select>Filter Suites&nbsp;&nbsp;</div>");
        printwriter.println("                        <table id=\"tableStyle\" class=\"chartStyle\" style=\"height:50px; float: left\">\n                            <tr>\n                                <th>Suite Name</th>\n                                <th>Package Name</th>\n                                <th>Class Name</th>\n                                <th>Method Type</th>\n                                <th>Test Case Name</th>\n<th>Iteration</th>                                <th>Time</th>\n                                <th style=\"width: 7%\">Status</th>\n                            </tr>\n");
        writePassedData(printwriter, list, i);
        writeFailedData(printwriter, list1, i);
        writeSkippedData(printwriter, list2, i);
        writePassedData(printwriter, list3, i);
        writeFailedData(printwriter, list4, i);
        writeSkippedData(printwriter, list5, i);
        printwriter.print("                        </table>\n                    </div>\n                </td>\n            </tr>");
    }

    private static void writePassedData(PrintWriter printwriter, List list, int i)
    {
        String s = "pass";
        String s1;
        ITestResult itestresult;
        for(Iterator iterator = list.iterator(); iterator.hasNext(); printwriter.print((new StringBuilder()).append("                            <tr class=\"all ").append(s1).append("\">\n").append("                                <td><a href=\"").append(getTestCaseHTMLPath(itestresult, i)).append("\">").append(getSuiteName(itestresult)).append("</a></td>\n").append("                                <td><a href=\"").append(getTestCaseHTMLPath(itestresult, i)).append("\">").append(getPackageName(itestresult)).append("</a></td>\n").append("                                <td><a href=\"").append(getTestCaseHTMLPath(itestresult, i)).append("\">").append(getClassName(itestresult)).append("</a></td>\n").append("                                <td><a href=\"").append(getTestCaseHTMLPath(itestresult, i)).append("\">").append(getMethodType(itestresult)).append("</a></td>\n").append("                                <td><a href=\"").append(getTestCaseHTMLPath(itestresult, i)).append("\">").append(getTestCaseName(itestresult)).append("</a></td>\n").append("                                <td><a href=\"").append(getTestCaseHTMLPath(itestresult, i)).append("\">").append(getIteration(itestresult)).append("</a></td>\n").append("                                <td><a href=\"").append(getTestCaseHTMLPath(itestresult, i)).append("\">").append(getExecutionTime(itestresult)).append("</a></td>\n").append("                                <td><img  style=\"border: none; width: 25px\" src=\"../../HTML_Design_Files/IMG/pass.png\"></td>\n").append("                            </tr>\n").toString()))
        {
            itestresult = (ITestResult)iterator.next();
            s1 = (new StringBuilder()).append("pass ").append(getSuiteNameMapper(itestresult)).toString();
            if(!itestresult.getMethod().isTest())
                s1 = (new StringBuilder()).append("config ").append(getSuiteNameMapper(itestresult)).toString();
        }

    }

    private static void writeFailedData(PrintWriter printwriter, List list, int i)
    {
        String s = "fail";
        String s1;
        ITestResult itestresult;
        for(Iterator iterator = list.iterator(); iterator.hasNext(); printwriter.print((new StringBuilder()).append("                            <tr class=\"all ").append(s1).append("\">\n").append("                                <td><a href=\"").append(getTestCaseHTMLPath(itestresult, i)).append("\">").append(getSuiteName(itestresult)).append("</a></td>\n").append("                              <td><a href=\"").append(getTestCaseHTMLPath(itestresult, i)).append("\">").append(getPackageName(itestresult)).append("</a></td>\n").append("                                <td><a href=\"").append(getTestCaseHTMLPath(itestresult, i)).append("\">").append(getClassName(itestresult)).append("</a></td>\n").append("                                <td><a href=\"").append(getTestCaseHTMLPath(itestresult, i)).append("\">").append(getMethodType(itestresult)).append("</a></td>\n").append("                                <td><a href=\"").append(getTestCaseHTMLPath(itestresult, i)).append("\">").append(getTestCaseName(itestresult)).append("</a></td>\n").append("                                <td><a href=\"").append(getTestCaseHTMLPath(itestresult, i)).append("\">").append(getIteration(itestresult)).append("</a></td>\n").append("                                <td><a href=\"").append(getTestCaseHTMLPath(itestresult, i)).append("\">").append(getExecutionTime(itestresult)).append("</a></td>\n").append("                                <td><img  style=\"border: none;width: 25px\" src=\"../../HTML_Design_Files/IMG/fail.png\"></td>\n").append("                            </tr>\n").toString()))
        {
            itestresult = (ITestResult)iterator.next();
            s1 = (new StringBuilder()).append("fail ").append(getSuiteNameMapper(itestresult)).toString();
            if(!itestresult.getMethod().isTest())
                s1 = (new StringBuilder()).append("config ").append(getSuiteNameMapper(itestresult)).toString();
        }

    }

    private static void writeSkippedData(PrintWriter printwriter, List list, int i)
    {
        String s = "skip";
        String s1;
        ITestResult itestresult;
        for(Iterator iterator = list.iterator(); iterator.hasNext(); printwriter.print((new StringBuilder()).append("                            <tr class=\"all ").append(s1).append("\">\n").append("                                <td><a href=\"").append(getTestCaseHTMLPath(itestresult, i)).append("\">").append(getSuiteName(itestresult)).append("</a></td>\n").append("                                  <td><a href=\"").append(getTestCaseHTMLPath(itestresult, i)).append("\">").append(getPackageName(itestresult)).append("</a></td>\n").append("                                <td><a href=\"").append(getTestCaseHTMLPath(itestresult, i)).append("\">").append(getClassName(itestresult)).append("</a></td>\n").append("                                <td><a href=\"").append(getTestCaseHTMLPath(itestresult, i)).append("\">").append(getMethodType(itestresult)).append("</a></td>\n").append("                                <td><a href=\"").append(getTestCaseHTMLPath(itestresult, i)).append("\">").append(getTestCaseName(itestresult)).append("</a></td>\n").append("                                <td><a href=\"").append(getTestCaseHTMLPath(itestresult, i)).append("\">").append(getIteration(itestresult)).append("</a></td>\n").append("                                <td><a href=\"").append(getTestCaseHTMLPath(itestresult, i)).append("\">").append(getExecutionTime(itestresult)).append("</a></td>\n").append("                                <td><img  style=\" border: none;width: 25px\" src=\"../../HTML_Design_Files/IMG/skip.png\"></td>\n").append("                            </tr>\n").toString()))
        {
            itestresult = (ITestResult)iterator.next();
            s1 = (new StringBuilder()).append("skip ").append(getSuiteNameMapper(itestresult)).toString();
            if(!itestresult.getMethod().isTest())
                s1 = (new StringBuilder()).append("config ").append(getSuiteNameMapper(itestresult)).toString();
        }

    }

    public static String getSuiteName(ITestResult itestresult)
    {
        return itestresult.getTestContext().getSuite().getName();
    }

    public static String getSuiteNameMapper(ITestResult itestresult)
    {
        return Attributes.getSuiteNameMapper(itestresult.getTestContext().getSuite().getName());
    }

    public static String getTestCaseHTMLPath(ITestResult itestresult, int i)
    {
        String s = itestresult.getAttribute("reportDir").toString();
        int j = (new StringBuilder()).append(Directory.RUNName).append(i).toString().length();
        String s1 = s.substring(s.indexOf((new StringBuilder()).append(Directory.RUNName).append(i).toString()) + (j + 1));
        return (new StringBuilder()).append(s1).append(Directory.SEP).append(getTestCaseName(itestresult)).append(".html").toString();
    }

    public static String getPackageName(ITestResult itestresult)
    {
        return itestresult.getTestClass().getRealClass().getPackage().getName();
        NullPointerException nullpointerexception;
        nullpointerexception;
        return "";
    }

    public static String getClassName(ITestResult itestresult)
    {
        return itestresult.getTestClass().getRealClass().getSimpleName();
    }

    public static String getIteration(ITestResult itestresult)
    {
        return itestresult.getAttribute("iteration").toString();
    }

    public static String getTestCaseName(ITestResult itestresult)
    {
        return itestresult.getName();
    }

    public static String getReportDir(ITestResult itestresult)
    {
        String s = itestresult.getTestContext().getSuite().getName();
        String s1 = itestresult.getTestContext().getCurrentXmlTest().getName();
        String s2 = itestresult.getTestClass().getName().replace(".", Directory.SEP);
        String s3 = itestresult.getMethod().getMethodName();
        s3 = (new StringBuilder()).append(s3).append("_Iteration").append(itestresult.getMethod().getCurrentInvocationCount()).toString();
        String s4 = (new StringBuilder()).append(s).append(Directory.SEP).append(s1).append(Directory.SEP).append(s2).append(Directory.SEP).append(s3).toString();
        return s4;
    }

    public static String getMethodType(ITestResult itestresult)
    {
        ITestNGMethod itestngmethod = itestresult.getMethod();
        if(itestngmethod.isAfterClassConfiguration())
            return "After Class";
        if(itestngmethod.isAfterGroupsConfiguration())
            return "After Groups";
        if(itestngmethod.isAfterMethodConfiguration())
            return "After Method";
        if(itestngmethod.isAfterSuiteConfiguration())
            return "After Suite";
        if(itestngmethod.isAfterTestConfiguration())
            return "After Test";
        if(itestngmethod.isBeforeClassConfiguration())
            return "Before Class";
        if(itestngmethod.isBeforeGroupsConfiguration())
            return "Before Groups";
        if(itestngmethod.isBeforeMethodConfiguration())
            return "Before Method";
        if(itestngmethod.isBeforeSuiteConfiguration())
            return "Before Suite";
        if(itestngmethod.isBeforeTestConfiguration())
            return "Before Test";
        if(itestngmethod.isTest())
            return "Test Method";
        else
            return "Unknown";
    }

    public static void header(PrintWriter printwriter)
    {
        printwriter.println("<!DOCTYPE html>\n\n<html>\n    <head>\n        <title>Current Run Reports</title>\n\n        <link rel=\"stylesheet\" type=\"text/css\" href=\"../../HTML_Design_Files/CSS/design.css\" />\n        <link rel=\"stylesheet\" type=\"text/css\" href=\"../../HTML_Design_Files/CSS/jquery.jqplot.css\" />\n\n        <script type=\"text/javascript\" src=\"../../HTML_Design_Files/JS/jquery.min.js\"></script>\n        <script type=\"text/javascript\" src=\"../../HTML_Design_Files/JS/jquery.jqplot.min.js\"></script>\n        <!--[if lt IE 9]>\n        <script language=\"javascript\" type=\"text/javascript\" src=\"../../HTML_Design_Files/JS/excanvas.js\"></script>\n        <![endif]-->\n\n        <script language=\"javascript\" type=\"text/javascript\" src=\"../../HTML_Design_Files/JS/jqplot.pieRenderer.min.js\"></script>\n        <script type=\"text/javascript\" src=\"pieChart.js\"></script>\n");
        printwriter.print("<script language=\"javascript\" type=\"text/javascript\">$(document).ready(function() { $(\".video\").hide();$(\"#showmenu\").show(); $('#showmenu').click(function(){ $('.video').toggle(\"slide\"); }); });</script><style>#showmenu{text-align:center; padding-top:350px;color: #585858; font-size: 14px;}#video{height: 550px;    margin-top: 5px;    width: 97%;    border-style: solid;    border-width: 1px;    border-color: #21ABCD;    /* Shadow for boxes */    -moz-box-shadow: 0 0 10px #CCCCCC;    -ms-box-shadow: 0 0 10px #CCCCCC;    -webkit-box-shadow: 0 0 10px #CCCCCC;    box-shadow: 0 0 10px #CCCCCC;    zoom: 1;    filter: progid:DXImageTransform.Microsoft.Shadow(Color=#cccccc, Strength=2, Direction=0),        progid:DXImageTransform.Microsoft.Shadow(Color=#cccccc, Strength=2, Direction=90),        progid:DXImageTransform.Microsoft.Shadow(Color=#cccccc, Strength=2, Direction=180),        progid:DXImageTransform.Microsoft.Shadow(Color=#cccccc, Strength=2, Direction=270);    background-color: white;}</style>");
        printwriter.println("<script language=\"javascript\" type=\"text/javascript\">\n$(document).ready(function() {\n\t$('#tcFilter').on('change',function(){\n    if($(this).val()=='pass'){\n        $('.pass').show();\n\t\t$('.fail').hide();\n\t\t$('.skip').hide();\n\t\t$('.config').hide();\n    }\n\tif($(this).val()=='fail'){\n        $('.pass').hide();\n\t\t$('.fail').show();\n\t\t$('.skip').hide();\n\t\t$('.config').hide();\n    }\n\t\n\tif($(this).val()=='skip'){\n        $('.pass').hide();\n\t\t$('.fail').hide();\n\t\t$('.skip').show();\n\t\t$('.config').hide();\n    }\n\t\nif($(this).val()=='tests'){ $('.pass').show(); $('.fail').show(); $('.skip').show(); $('.config').hide(); }\tif($(this).val()=='config'){\n        $('.pass').hide();\n\t\t$('.fail').hide();\n\t\t$('.skip').hide();\n\t\t$('.config').show();\n    }\n\t\n\tif($(this).val()=='all'){\n        $('.pass').show();\n\t\t$('.fail').show();\n\t\t$('.skip').show();\n\t\t$('.config').show();\n    }\n  });\n});       \n</script>");
        printwriter.print("<script language=\"javascript\" type=\"text/javascript\">$(document).ready(function() {\t$('#suiteFilter').on('change',function(){if($(this).val()=='all'){      $('.all').show();  }");
        String s;
        for(Iterator iterator = Attributes.getSuiteNameMapperMap().keySet().iterator(); iterator.hasNext(); printwriter.print((new StringBuilder()).append("if($(this).val()=='").append((String)Attributes.getSuiteNameMapperMap().get(s)).append("'){").append("      $('.all').hide();").append("$('.").append((String)Attributes.getSuiteNameMapperMap().get(s)).append("').show();").append("").append(" }").toString()))
            s = (String)iterator.next();

        printwriter.println("  }); });</script>");
        printwriter.println((new StringBuilder()).append("    </head>\n    <body>\n        <table id=\"mainTable\">\n            <tr id=\"header\" >\n                <td id=\"logo\"><img src=\"../../HTML_Design_Files/IMG/").append(ReportLabels.ATU_LOGO.getLabel()).append("\" alt=\"Logo\" height=\"70\" width=\"140\" /> ").append("<br/>").append(ReportLabels.ATU_CAPTION.getLabel()).append("").append("</td>\n").append("                <td id=\"headertext\">\n").append("                    ").append(ReportLabels.HEADER_TEXT.getLabel()).append("<div style=\"padding-right:20px;float:right\"><img src=\"../../HTML_Design_Files/IMG/").append(ReportLabels.PROJ_LOGO.getLabel()).append("\" height=\"70\" width=\"140\" /> </i></div>").append("</td>\n").append("\n").append("            </tr>").toString());
    }
}
