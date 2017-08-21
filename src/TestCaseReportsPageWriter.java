// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports.writers;

import atu.testng.reports.enums.*;
import atu.testng.reports.logging.LogAs;
import atu.testng.reports.utils.*;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import org.testng.*;

// Referenced classes of package atu.testng.reports.writers:
//            ReportsPage, CurrentRunPageWriter

public class TestCaseReportsPageWriter extends ReportsPage
{

    public TestCaseReportsPageWriter()
    {
    }

    public static void header(PrintWriter printwriter, ITestResult itestresult)
    {
        printwriter.println((new StringBuilder()).append("<!DOCTYPE html>\n\n<html>\n    <head>\n        <title>Pie Charts</title>\n\n        <link rel=\"stylesheet\" type=\"text/css\" href=\"../").append(getTestCaseHTMLPath(itestresult)).append("HTML_Design_Files/CSS/design.css\" />\n").append("        <link rel=\"stylesheet\" type=\"text/css\" href=\"../").append(getTestCaseHTMLPath(itestresult)).append("HTML_Design_Files/CSS/jquery.jqplot.css\" />\n").append("\n").append("        <script type=\"text/javascript\" src=\"../").append(getTestCaseHTMLPath(itestresult)).append("HTML_Design_Files/JS/jquery.min.js\"></script>\n").append("        <script type=\"text/javascript\" src=\"../").append(getTestCaseHTMLPath(itestresult)).append("HTML_Design_Files/JS/jquery.jqplot.min.js\"></script>\n").append("        <!--[if lt IE 9]>\n").append("        <script language=\"javascript\" type=\"text/javascript\" src=\"../").append(getTestCaseHTMLPath(itestresult)).append("HTML_Design_Files/JS/excanvas.js\"></script>\n").append("        <![endif]-->\n").append("\n").append("        <script language=\"javascript\" type=\"text/javascript\" src=\"../").append(getTestCaseHTMLPath(itestresult)).append("HTML_Design_Files/JS/jqplot.pieRenderer.min.js\"></script>\n").append("        <script language=\"javascript\" type=\"text/javascript\">").append("$(document).ready(function() {").append(" $(\".exception\").hide();").append(" $(\"#showmenu\").show();").append(" $('#showmenu').click(function() {").append(" $('.exception').toggle(\"slide\");").append(" });").append(" });").append("        </script>").append("    </head>\n").append("    <body>\n").append("        <table id=\"mainTable\">\n").append("            <tr id=\"header\" >\n").append("                <td id=\"logo\">").append("<img src=\"../").append(getTestCaseHTMLPath(itestresult)).append("HTML_Design_Files/IMG/").append(ReportLabels.ATU_LOGO.getLabel()).append("\" alt=\"Logo\" height=\"80\" width=\"140\" /> ").append("<br/>").append(ReportLabels.ATU_CAPTION.getLabel()).append("</td>\n").append("                <td id=\"headertext\">\n").append("           ").append(ReportLabels.HEADER_TEXT.getLabel()).append("         \n").append("<div style=\"padding-right:20px;float:right\"><img src=\"../").append(getTestCaseHTMLPath(itestresult)).append("HTML_Design_Files/IMG/").append(ReportLabels.PROJ_LOGO.getLabel()).append("\" height=\"70\" width=\"140\" /></div>").append("                </td>\n").append("            </tr>").toString());
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

    private static String getExceptionDetails(ITestResult itestresult)
    {
        try
        {
            itestresult.getThrowable().toString();
        }
        catch(Throwable throwable)
        {
            return "";
        }
        String s = itestresult.getThrowable().toString();
        String s1 = s;
        if(s.contains(":"))
            s1 = s.substring(0, s.indexOf(":")).trim();
        else
            s = "";
        try
        {
            s1 = getExceptionClassName(s1, s);
            if(s1.equals("Assertion Error"))
            {
                if(s.contains(">"))
                {
                    s1 = (new StringBuilder()).append(s1).append(s.substring(s.indexOf(":"), s.lastIndexOf(">") + 1).replace(">", "\"")).toString();
                    s1 = s1.replace("<", "\"");
                }
                if(itestresult.getThrowable().getMessage().trim().length() > 0)
                    s1 = itestresult.getThrowable().getMessage().trim();
            } else
            if(s.contains("{"))
            {
                s1 = (new StringBuilder()).append(s1).append(s.substring(s.indexOf("{"), s.lastIndexOf("}"))).toString();
                s1 = s1.replace("{\"method\":", " With ").replace(",\"selector\":", " = ");
            } else
            if(s1.equals("Unable to connect Browser") && s.contains("."))
                s1 = (new StringBuilder()).append(s1).append(": Browser is Closed").toString();
            else
            if(s1.equals("WebDriver Exception"))
                s1 = itestresult.getThrowable().getMessage();
        }
        catch(ClassNotFoundException classnotfoundexception) { }
        catch(Exception exception) { }
        s1 = s1.replace(">", "\"");
        s1 = s1.replace("<", "\"");
        return s1;
    }

    private static String getExceptionClassName(String s, String s1)
        throws ClassNotFoundException
    {
        String s2 = "";
        try
        {
            s2 = ExceptionDetails.valueOf(s.trim().replace(".", "_")).getExceptionInfo();
        }
        catch(Exception exception)
        {
            s2 = s;
        }
        return s2;
    }

    public static String getReqCoverageInfo(ITestResult itestresult)
    {
        if(itestresult.getAttribute("reqCoverage") == null)
            return ReportLabels.TC_INFO_LABEL.getLabel();
        return itestresult.getAttribute("reqCoverage").toString();
        Exception exception;
        exception;
        return ReportLabels.TC_INFO_LABEL.getLabel();
    }

    public static void content(PrintWriter printwriter, ITestResult itestresult, int i)
    {
        AuthorDetails authordetails = null;
        try
        {
            authordetails = Attributes.getClassLevelAuthor(itestresult.getTestClass().getRealClass().getName());
        }
        catch(NullPointerException nullpointerexception) { }
        printwriter.println((new StringBuilder()).append("<td id=\"content\">   \n                    <div class=\"info\">\n                        The following table lists down the Sequential Steps during the Run <br/>\nTestCase Name: <b>").append(itestresult.getName()).append("  :  Iteration ").append(itestresult.getAttribute("iteration").toString()).append("</b><br/>").append("                        Time Taken for Executing: <b>").append(getExecutionTime(itestresult)).append("</b> <br/>\n").append("                        Current Run Number: <b>Run ").append(i).append("</b></br>\n").append("Method Type: <b>").append(CurrentRunPageWriter.getMethodType(itestresult)).append("</b></br>").append("                    </div>").toString());
        printwriter.println((new StringBuilder()).append("<div class=\"info\"><br/><b>Requirement Coverage/ TestCase Description</b><br/><br/>").append(getReqCoverageInfo(itestresult)).append("</div>").toString());
        printwriter.println((new StringBuilder()).append("<div class=\"chartStyle summary\" style=\"background-color: #646D7E;width: 30%; height: 200px;\">          \n                        <b>Execution Platform Details</b><br/><br/>\n                        <table>\n                            <tr>\n                                <td>O.S</td>\n                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n                                <td>").append(Platform.OS).append(", ").append(Platform.OS_ARCH).append("Bit, v").append(Platform.OS_VERSION).append("</td>\n").append("                            </tr>\n").append("                            <tr>\n").append("                                <td>Java</td>\n").append("                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n").append("                                <td>").append(Platform.JAVA_VERSION).append("</td>\n").append("                            </tr>\n").append("\n").append("                            <tr>\n").append("                                <td>Hostname</td>\n").append("                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n").append("                                <td>").append(Platform.getHostName()).append("</td>\n").append("                            </tr>\n").append("\n").append("                            <tr>\n").append("                                <td>Selenium</td>\n").append("                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n").append("                                <td>").append(Platform.DRIVER_VERSION).append("</td>\n").append("                            </tr>\n").append("                        </table>  \n").append("                    </div>\n").append("                   ").toString());
        printwriter.println((new StringBuilder()).append(" <div class=\"chartStyle summary\" style=\"background-color: ").append(getColorBasedOnResult(itestresult)).append(";margin-left: 20px; height: 200px;width: 30%; \">\n").append("                        <b>Summary</b><br/><br/>\n").append("                        <table>\n").append("                            <tr>\n").append("                                <td>Status</td>\n").append("                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n").append("                                <td>").append(getResult(itestresult)).append("</td>\n").append("                            </tr>\n").append("                            <tr>\n").append("                                <td>Execution Date</td>\n").append("                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n").append("                                <td>").append(Utils.getCurrentTime()).append("</td>\n").append("                            </tr>\n").append("                            \n").append("\n").append("                            <tr>\n").append("                                <td>Browser</td>\n").append("                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n").append("                                <td>").append(getBrowserName(itestresult)).append(",").append(getBrowserVersion(itestresult)).append("</td>\n").append("                            </tr>\n").append("                        </table> \n").append("                    </div>").toString());
        AuthorDetails authordetails1 = null;
        try
        {
            if(itestresult.getAttribute("authorInfo") == null)
            {
                if(authordetails == null)
                    authordetails1 = new AuthorDetails();
                else
                    authordetails1 = authordetails;
            } else
            {
                authordetails1 = (AuthorDetails)itestresult.getAttribute("authorInfo");
            }
        }
        catch(Exception exception) { }
        printwriter.println((new StringBuilder()).append(" <div class=\"chartStyle summary\" style=\"background-color: #7F525D;margin-left: 20px; height: 200px;width: 30%; \">\n                       <b>Author Info</b><br/><br/>\n                        <table>\n                            <tr>\n                                <td>Author Name</td>\n                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n                                <td>").append(authordetails1.getAuthorName()).append("</td>                                \n").append("                            </tr>\n").append("                            <tr>\n").append("                                <td>Creation Date</td>\n").append("                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n").append("                                <td>").append(authordetails1.getCreationDate()).append("</td>\n").append("                            </tr>\n").append("                            <tr>\n").append("                                <td>Version</td>\n").append("                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n").append("                                <td>").append(authordetails1.getVersion()).append("</td>\n").append("                            </tr>\n").append("                            <tr>\n").append("                                <td>System User</td>\n").append("                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n").append("                                <td>").append(Platform.USER).append("</td>\n").append("                            </tr>\n").append("                        </table> \n").append("                    </div>\n").append("                    ").toString());
        if(itestresult.getStatus() != 3 || itestresult.getStatus() == 3 && (itestresult.getThrowable() instanceof SkipException))
        {
            List list = Reporter.getOutput(itestresult);
            printwriter.println("   <div>\n                        <table class=\"chartStyle\" id=\"tableStyle\" style=\"height:50px; float: left\">\n                            <tr>\n                                <th>S.No</th>\n                                <th>Step Description</th>\n                                <th>Input Value</th>\n                                <th>Expected Value</th>\n                                <th>Actual Value</th>\n                                <th>Time</th>\n                                <th>Line No</th>\n                                <th>Status</th>\n                                <th>Screen shot</th>\n                            </tr>\n                           \n");
            int j = 1;
            if(Reporter.getOutput(itestresult).size() <= 0)
            {
                printwriter.print("<tr>");
                printwriter.print("<td colspan=\"8\"><b>No Steps Available</b></td>");
                printwriter.print("</tr>");
            }
            j = 1;
            for(Iterator iterator = list.iterator(); iterator.hasNext();)
            {
                String s2 = (String)iterator.next();
                Steps steps = null;
                steps = (Steps)itestresult.getAttribute(s2);
                if(steps == null)
                {
                    printwriter.print("<tr>");
                    printwriter.println((new StringBuilder()).append("<td>").append(j).append("</td>").toString());
                    printwriter.print((new StringBuilder()).append("<td style=\"text-align:left\" colspan=\"8\">").append(s2).append("</td></tr>").toString());
                    j++;
                } else
                {
                    printwriter.print("<tr>");
                    printwriter.println((new StringBuilder()).append("<td>").append(j).append("</td>").toString());
                    printwriter.println((new StringBuilder()).append("<td>").append(steps.getDescription()).append("</td>").toString());
                    printwriter.println((new StringBuilder()).append("<td>").append(steps.getInputValue()).append("</td>").toString());
                    printwriter.println((new StringBuilder()).append("<td>").append(steps.getExpectedValue()).append("</td>").toString());
                    printwriter.println((new StringBuilder()).append("<td>").append(steps.getActualValue()).append("</td>").toString());
                    printwriter.println((new StringBuilder()).append("<td>").append(steps.getTime()).append("</td>").toString());
                    printwriter.println((new StringBuilder()).append("<td>").append(steps.getLineNum()).append("</td>").toString());
                    printwriter.println((new StringBuilder()).append("<td>").append(getLogDescription(steps.getLogAs(), itestresult)).append("</td>").toString());
                    try
                    {
                        Integer.parseInt(steps.getScreenShot().trim());
                        printwriter.println((new StringBuilder()).append("<td><a href=\"img/").append(j).append(".PNG").append("\"><img alt=\"No Screenshot\" src=\"img/").append(j).append(".PNG").append("\"/></a></td>").toString());
                    }
                    catch(Exception exception3)
                    {
                        printwriter.println("<td></td>");
                    }
                    printwriter.print("</tr>");
                    j++;
                }
            }

            printwriter.print("\n                        </div></table>  \n");
        }
        if(itestresult.getParameters().length > 0 || itestresult.getStatus() == 3 || itestresult.getStatus() == 2)
        {
            printwriter.println("<div class=\"chartStyle summary\" style=\"color: black;width: 98%; height: 100%; padding-bottom: 30px;\">          \n");
            if(itestresult.getParameters().length > 0)
            {
                printwriter.print("<b>Parameters: </b><br/>");
                Object aobj[] = itestresult.getParameters();
                int k = aobj.length;
                for(int l = 0; l < k; l++)
                {
                    Object obj = aobj[l];
                    printwriter.print((new StringBuilder()).append("Param: ").append(obj.toString()).append("<br/>").toString());
                }

            }
            if(itestresult.getStatus() == 3)
            {
                printwriter.print("<br/><br/>");
                printwriter.println("                      <b>Reason for Skipping</b><br/><br/>\n");
                String as[] = itestresult.getMethod().getGroupsDependedUpon();
                String as1[] = itestresult.getMethod().getMethodsDependedUpon();
                if(as.length > 0)
                {
                    String s = "";
                    String as2[] = as;
                    int i1 = as2.length;
                    for(int k1 = 0; k1 < i1; k1++)
                    {
                        String s3 = as2[k1];
                        s = (new StringBuilder()).append(s).append(s3).append("<br/>").toString();
                    }

                    printwriter.print((new StringBuilder()).append("<b>Depends on Groups: </b><br/>").append(s).toString());
                }
                if(as1.length > 0)
                {
                    String s1 = "";
                    String as3[] = as1;
                    int j1 = as3.length;
                    for(int l1 = 0; l1 < j1; l1++)
                    {
                        String s4 = as3[l1];
                        s1 = (new StringBuilder()).append(s1).append(s4).append("<br/>").toString();
                    }

                    printwriter.print((new StringBuilder()).append("<b>Depends on Methods: </b><br/>").append(s1).append("<br/><br/>").toString());
                }
                try
                {
                    if(itestresult.getThrowable() instanceof SkipException)
                        printwriter.print((new StringBuilder()).append("TestNG Skip Exception: ").append(itestresult.getThrowable().getMessage()).append("<br/><br/>").toString());
                }
                catch(Exception exception2) { }
            } else
            if(itestresult.getStatus() == 2)
            {
                printwriter.println((new StringBuilder()).append("                        <br/><br/><b>Reason for Failure:&nbsp;&nbsp;</b>").append(getExceptionDetails(itestresult)).append("<br/><br/>\n").append("<b id=\"showmenu\">Click Me to Show/Hide the Full Stack Trace</b>").append("<div class=\"exception\">").toString());
                try
                {
                    itestresult.getThrowable().printStackTrace(printwriter);
                }
                catch(Exception exception1) { }
            }
            printwriter.print("</div>");
        }
        printwriter.print("                    \n\n                </td>\n            </tr>");
    }

    public static String getLogDescription(LogAs logas, ITestResult itestresult)
    {
        static class _cls1
        {

            static final int $SwitchMap$atu$testng$reports$logging$LogAs[];

            static 
            {
                $SwitchMap$atu$testng$reports$logging$LogAs = new int[LogAs.values().length];
                try
                {
                    $SwitchMap$atu$testng$reports$logging$LogAs[LogAs.PASSED.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$atu$testng$reports$logging$LogAs[LogAs.FAILED.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$atu$testng$reports$logging$LogAs[LogAs.INFO.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$atu$testng$reports$logging$LogAs[LogAs.WARNING.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
            }
        }

        switch(_cls1..SwitchMap.atu.testng.reports.logging.LogAs[logas.ordinal()])
        {
        case 1: // '\001'
            return (new StringBuilder()).append("<img style=\"height:20px;width:20px;border:none\"  alt=\"PASS\" src=\"../").append(getTestCaseHTMLPath(itestresult)).append("/HTML_Design_Files/IMG/logpass.png\" />").toString();

        case 2: // '\002'
            return (new StringBuilder()).append("<img style=\"height:18px;width:18px;border:none\"  alt=\"FAIL\" src=\"../").append(getTestCaseHTMLPath(itestresult)).append("/HTML_Design_Files/IMG/logfail.png\" />").toString();

        case 3: // '\003'
            return (new StringBuilder()).append("<img style=\"height:20px;width:20px;border:none\" alt=\"INFO\" src=\"../").append(getTestCaseHTMLPath(itestresult)).append("/HTML_Design_Files/IMG/loginfo.png\" />").toString();

        case 4: // '\004'
            return (new StringBuilder()).append("<img style=\"height:20px;width:20px;border:none\"  alt=\"WARNING\" src=\"../").append(getTestCaseHTMLPath(itestresult)).append("/HTML_Design_Files/IMG/logwarning.png\" />").toString();
        }
        return "img";
    }

    public static String getSkippedDescription(ITestResult itestresult)
    {
        String as[] = itestresult.getMethod().getGroupsDependedUpon();
        String as1[] = itestresult.getMethod().getMethodsDependedUpon();
        String s = "";
        String as2[] = as;
        int i = as2.length;
        for(int j = 0; j < i; j++)
        {
            String s2 = as2[j];
            s = (new StringBuilder()).append(s).append(s2).toString();
        }

        String s1 = "";
        String as3[] = as1;
        int k = as3.length;
        for(int l = 0; l < k; l++)
        {
            String s3 = as3[l];
            s1 = (new StringBuilder()).append(s1).append(s3).toString();
        }

        return "";
    }

    private static String getBrowserName(ITestResult itestresult)
    {
        return itestresult.getAttribute(Platform.BROWSER_NAME_PROP).toString();
        Exception exception;
        exception;
        return "";
    }

    private static String getBrowserVersion(ITestResult itestresult)
    {
        return itestresult.getAttribute(Platform.BROWSER_VERSION_PROP).toString();
        Exception exception;
        exception;
        return "";
    }

    private static String getColorBasedOnResult(ITestResult itestresult)
    {
        if(itestresult.getStatus() == 1)
            return Colors.PASS.getColor();
        if(itestresult.getStatus() == 2)
            return Colors.FAIL.getColor();
        if(itestresult.getStatus() == 3)
            return Colors.SKIP.getColor();
        else
            return Colors.PASS.getColor();
    }

    private static String getResult(ITestResult itestresult)
    {
        if(itestresult.getStatus() != 1)
            break MISSING_BLOCK_LABEL_36;
        if(itestresult.getAttribute("passedButFailed").equals("passedButFailed"))
            return "Failed";
        return "Passed";
        Exception exception;
        exception;
        return "Passed";
        if(itestresult.getStatus() == 2)
            return "Failed";
        if(itestresult.getStatus() == 3)
            return "Skipped";
        else
            return "Unknown";
    }

    public static String getTestCaseHTMLPath(ITestResult itestresult)
    {
        String s = itestresult.getAttribute("reportDir").toString();
        s = s.substring(s.indexOf(Directory.RESULTSDir) + (Directory.RESULTSDir.length() + 1));
        String as[] = s.replace(Directory.SEP, "##@##@##").split("##@##@##");
        s = "";
        for(int i = 0; i < as.length; i++)
            s = (new StringBuilder()).append(s).append("../").toString();

        return s;
    }

    public static void menuLink(PrintWriter printwriter, ITestResult itestresult, int i)
    {
        getTestCaseHTMLPath(itestresult);
        printwriter.println("\n            <tr id=\"container\">\n                <td id=\"menu\">\n                    <ul> \n");
        printwriter.println((new StringBuilder()).append(" <li class=\"menuStyle\"><a href=\"../").append(getTestCaseHTMLPath(itestresult)).append("index.html\" >Index</a></li>").append("<li style=\"padding-top: 4px;\"><a href=\"").append(getTestCaseHTMLPath(itestresult)).append("ConsolidatedPage.html\" >Consolidated Page</a></li>\n").toString());
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
}
