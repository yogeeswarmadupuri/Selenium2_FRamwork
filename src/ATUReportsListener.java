// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports.listeners;

import atu.testng.reports.ATUReports;
import atu.testng.reports.excel.ExcelReports;
import atu.testng.reports.exceptions.ATUReporterException;
import atu.testng.reports.exceptions.ATUReporterStepFailedException;
import atu.testng.reports.utils.*;
import atu.testng.reports.writers.*;
import atu.testrecorder.ATUTestRecorder;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import org.testng.*;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

// Referenced classes of package atu.testng.reports.listeners:
//            ConfigurationListener

public class ATUReportsListener
    implements ITestListener, IExecutionListener, IReporter, ISuiteListener
{

    public ATUReportsListener()
    {
        runCount = 0;
        passedTests = new ArrayList();
        failedTests = new ArrayList();
        skippedTests = new ArrayList();
    }

    public void onStart(ITestContext itestcontext)
    {
    }

    public void onFinish(ITestContext itestcontext)
    {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult itestresult)
    {
    }

    public void onTestFailure(ITestResult itestresult)
    {
        failedTests.add(itestresult);
    }

    public void onTestSkipped(ITestResult itestresult)
    {
        if(itestresult.getThrowable() instanceof SkipException)
        {
            skippedTests.add(itestresult);
            return;
        } else
        {
            createReportDir(itestresult);
            skippedTests.add(itestresult);
            return;
        }
    }

    public void onTestStart(ITestResult itestresult)
    {
    }

    public void onTestSuccess(ITestResult itestresult)
    {
        try
        {
            if(itestresult.getAttribute("passedButFailed").equals("passedButFailed"))
            {
                itestresult.setStatus(2);
                itestresult.setThrowable(new ATUReporterStepFailedException());
                failedTests.add(itestresult);
                return;
            }
        }
        catch(NullPointerException nullpointerexception) { }
        passedTests.add(itestresult);
    }

    public static void setPlatfromBrowserDetails(ITestResult itestresult)
    {
        Platform.prepareDetails(ATUReports.getWebDriver());
        itestresult.setAttribute(Platform.BROWSER_NAME_PROP, Platform.BROWSER_NAME);
        itestresult.setAttribute(Platform.BROWSER_VERSION_PROP, Platform.BROWSER_VERSION);
    }

    public static void createReportDir(ITestResult itestresult)
    {
        String s = getReportDir(itestresult);
        Directory.mkDirs(s);
        Directory.mkDirs((new StringBuilder()).append(s).append(Directory.SEP).append(Directory.SCREENSHOT_DIRName).toString());
    }

    public static String getRelativePathFromSuiteLevel(ITestResult itestresult)
    {
        String s = itestresult.getTestContext().getSuite().getName();
        String s1 = itestresult.getTestContext().getCurrentXmlTest().getName();
        String s2 = itestresult.getTestClass().getName().replace(".", Directory.SEP);
        String s3 = itestresult.getMethod().getMethodName();
        s3 = (new StringBuilder()).append(s3).append("_Iteration").append(itestresult.getMethod().getCurrentInvocationCount() + 1).toString();
        return (new StringBuilder()).append(s).append(Directory.SEP).append(s1).append(Directory.SEP).append(s2).append(Directory.SEP).append(s3).toString();
    }

    public static String getReportDir(ITestResult itestresult)
    {
        String s = getRelativePathFromSuiteLevel(itestresult);
        itestresult.setAttribute("relativeReportDir", s);
        String s1 = (new StringBuilder()).append(Directory.RUNDir).append(Directory.SEP).append(s).toString();
        itestresult.setAttribute("iteration", Integer.valueOf(itestresult.getMethod().getCurrentInvocationCount() + 1));
        itestresult.setAttribute("reportDir", s1);
        return s1;
    }

    public void setTickInterval(List list, List list1, List list2)
        throws ATUReporterException
    {
        int i = SettingsFile.getHighestTestCaseNumber();
        int j = SettingsFile.getBiggestNumber(new int[] {
            i, list.size(), list1.size(), list2.size()
        });
        int k = j / 10;
        if(k > 1)
            HTMLDesignFilesJSWriter.TICK_INTERVAL = k;
    }

    public void onFinish()
    {
        try
        {
            String s = (new StringBuilder()).append(SettingsFile.get("passedList")).append(passedTests.size()).append(';').toString();
            String s1 = (new StringBuilder()).append(SettingsFile.get("failedList")).append(failedTests.size()).append(';').toString();
            String s2 = (new StringBuilder()).append(SettingsFile.get("skippedList")).append(skippedTests.size()).append(';').toString();
            SettingsFile.set("passedList", s);
            SettingsFile.set("failedList", s1);
            SettingsFile.set("skippedList", s2);
            setTickInterval(passedTests, failedTests, skippedTests);
            HTMLDesignFilesJSWriter.lineChartJS(s, s1, s2, runCount);
            HTMLDesignFilesJSWriter.barChartJS(s, s1, s2, runCount);
            HTMLDesignFilesJSWriter.pieChartJS(passedTests.size(), failedTests.size(), skippedTests.size(), runCount);
            generateIndexPage();
            long l = ((Long)Attributes.getAttribute("startExecution")).longValue();
            generateConsolidatedPage();
            generateCurrentRunPage(passedTests, failedTests, skippedTests, l, System.currentTimeMillis());
            startReportingForPassed(passedTests);
            startReportingForFailed(failedTests);
            startReportingForSkipped(skippedTests);
            if(Directory.generateExcelReports)
                ExcelReports.generateExcelReport((new StringBuilder()).append(Directory.RUNDir).append(Directory.SEP).append("(").append(Directory.REPORTSDIRName).append(") ").append(Directory.RUNName).append(runCount).append(".xlsx").toString(), passedTests, failedTests, skippedTests);
            if(Directory.generateConfigReports)
                ConfigurationListener.startConfigurationMethodsReporting(runCount);
        }
        catch(Exception exception)
        {
            throw new IllegalStateException(exception);
        }
    }

    public void startCreatingDirs(ISuite isuite)
    {
        Directory.mkDirs((new StringBuilder()).append(Directory.RUNDir).append(Directory.SEP).append(isuite.getName()).toString());
        XmlTest xmltest;
        for(Iterator iterator = isuite.getXmlSuite().getTests().iterator(); iterator.hasNext(); Directory.mkDirs((new StringBuilder()).append(Directory.RUNDir).append(Directory.SEP).append(isuite.getName()).append(Directory.SEP).append(xmltest.getName()).toString()))
            xmltest = (XmlTest)iterator.next();

    }

    public void generateIndexPage()
    {
        PrintWriter printwriter = null;
        printwriter = new PrintWriter((new StringBuilder()).append(Directory.REPORTSDir).append(Directory.SEP).append("index.html").toString());
        IndexPageWriter.header(printwriter);
        IndexPageWriter.content(printwriter, ATUReports.indexPageDescription);
        IndexPageWriter.footer(printwriter);
        try
        {
            printwriter.close();
        }
        catch(Exception exception)
        {
            printwriter = null;
        }
        break MISSING_BLOCK_LABEL_97;
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        filenotfoundexception.printStackTrace();
        try
        {
            printwriter.close();
        }
        catch(Exception exception1)
        {
            printwriter = null;
        }
        break MISSING_BLOCK_LABEL_97;
        Exception exception2;
        exception2;
        try
        {
            printwriter.close();
        }
        catch(Exception exception3)
        {
            printwriter = null;
        }
        throw exception2;
    }

    public void generateCurrentRunPage(List list, List list1, List list2, long l, long l1)
    {
        PrintWriter printwriter = null;
        printwriter = new PrintWriter((new StringBuilder()).append(Directory.RUNDir).append(Directory.SEP).append("CurrentRun.html").toString());
        CurrentRunPageWriter.header(printwriter);
        CurrentRunPageWriter.menuLink(printwriter, 0);
        CurrentRunPageWriter.content(printwriter, list, list1, list2, ConfigurationListener.passedConfigurations, ConfigurationListener.failedConfigurations, ConfigurationListener.skippedConfigurations, runCount, l, l1);
        CurrentRunPageWriter.footer(printwriter);
        try
        {
            printwriter.close();
        }
        catch(Exception exception)
        {
            printwriter = null;
        }
        break MISSING_BLOCK_LABEL_137;
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        filenotfoundexception.printStackTrace();
        try
        {
            printwriter.close();
        }
        catch(Exception exception1)
        {
            printwriter = null;
        }
        break MISSING_BLOCK_LABEL_137;
        Exception exception2;
        exception2;
        try
        {
            printwriter.close();
        }
        catch(Exception exception3)
        {
            printwriter = null;
        }
        throw exception2;
    }

    public void generateConsolidatedPage()
    {
        PrintWriter printwriter = null;
        printwriter = new PrintWriter((new StringBuilder()).append(Directory.RESULTSDir).append(Directory.SEP).append("ConsolidatedPage.html").toString());
        ConsolidatedReportsPageWriter.header(printwriter);
        ConsolidatedReportsPageWriter.menuLink(printwriter, runCount);
        ConsolidatedReportsPageWriter.content(printwriter);
        ConsolidatedReportsPageWriter.footer(printwriter);
        try
        {
            printwriter.close();
        }
        catch(Exception exception)
        {
            printwriter = null;
        }
        break MISSING_BLOCK_LABEL_102;
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        filenotfoundexception.printStackTrace();
        try
        {
            printwriter.close();
        }
        catch(Exception exception1)
        {
            printwriter = null;
        }
        break MISSING_BLOCK_LABEL_102;
        Exception exception2;
        exception2;
        try
        {
            printwriter.close();
        }
        catch(Exception exception3)
        {
            printwriter = null;
        }
        throw exception2;
    }

    public void startReportingForPassed(List list)
    {
        PrintWriter printwriter;
        Iterator iterator;
        printwriter = null;
        iterator = list.iterator();
_L1:
        ITestResult itestresult;
        String s;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_168;
        itestresult = (ITestResult)iterator.next();
        s = itestresult.getAttribute("reportDir").toString();
        printwriter = new PrintWriter((new StringBuilder()).append(s).append(Directory.SEP).append(itestresult.getName()).append(".html").toString());
        TestCaseReportsPageWriter.header(printwriter, itestresult);
        TestCaseReportsPageWriter.menuLink(printwriter, itestresult, 0);
        TestCaseReportsPageWriter.content(printwriter, itestresult, runCount);
        TestCaseReportsPageWriter.footer(printwriter);
        try
        {
            printwriter.close();
        }
        catch(Exception exception)
        {
            printwriter = null;
        }
          goto _L1
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        filenotfoundexception.printStackTrace();
        try
        {
            printwriter.close();
        }
        catch(Exception exception1)
        {
            printwriter = null;
        }
          goto _L1
        Exception exception2;
        exception2;
        try
        {
            printwriter.close();
        }
        catch(Exception exception3)
        {
            printwriter = null;
        }
        throw exception2;
    }

    public void startReportingForFailed(List list)
    {
        PrintWriter printwriter;
        Iterator iterator;
        printwriter = null;
        iterator = list.iterator();
_L1:
        ITestResult itestresult;
        String s;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_163;
        itestresult = (ITestResult)iterator.next();
        s = itestresult.getAttribute("reportDir").toString();
        printwriter = new PrintWriter((new StringBuilder()).append(s).append(Directory.SEP).append(itestresult.getName()).append(".html").toString());
        TestCaseReportsPageWriter.header(printwriter, itestresult);
        TestCaseReportsPageWriter.menuLink(printwriter, itestresult, 0);
        TestCaseReportsPageWriter.content(printwriter, itestresult, runCount);
        TestCaseReportsPageWriter.footer(printwriter);
        try
        {
            printwriter.close();
        }
        catch(Exception exception)
        {
            printwriter = null;
        }
          goto _L1
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        try
        {
            printwriter.close();
        }
        catch(Exception exception1)
        {
            printwriter = null;
        }
          goto _L1
        Exception exception2;
        exception2;
        try
        {
            printwriter.close();
        }
        catch(Exception exception3)
        {
            printwriter = null;
        }
        throw exception2;
    }

    public void startReportingForSkipped(List list)
    {
        PrintWriter printwriter;
        Iterator iterator;
        printwriter = null;
        iterator = list.iterator();
_L1:
        ITestResult itestresult;
        String s;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_168;
        itestresult = (ITestResult)iterator.next();
        s = itestresult.getAttribute("reportDir").toString();
        printwriter = new PrintWriter((new StringBuilder()).append(s).append(Directory.SEP).append(itestresult.getName()).append(".html").toString());
        TestCaseReportsPageWriter.header(printwriter, itestresult);
        TestCaseReportsPageWriter.menuLink(printwriter, itestresult, 0);
        TestCaseReportsPageWriter.content(printwriter, itestresult, runCount);
        TestCaseReportsPageWriter.footer(printwriter);
        try
        {
            printwriter.close();
        }
        catch(Exception exception)
        {
            printwriter = null;
        }
          goto _L1
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        filenotfoundexception.printStackTrace();
        try
        {
            printwriter.close();
        }
        catch(Exception exception1)
        {
            printwriter = null;
        }
          goto _L1
        Exception exception2;
        exception2;
        try
        {
            printwriter.close();
        }
        catch(Exception exception3)
        {
            printwriter = null;
        }
        throw exception2;
    }

    public void onExecutionFinish()
    {
        Attributes.setAttribute("endExecution", Long.valueOf(System.currentTimeMillis()));
        if(Directory.recordSuiteExecution)
            try
            {
                recorder.stop();
            }
            catch(Throwable throwable)
            {
                throw new IllegalStateException(throwable);
            }
    }

    private void initChecking()
    {
        try
        {
            Directory.verifyRequiredFiles();
            SettingsFile.correctErrors();
            runCount = Integer.parseInt(SettingsFile.get("run").trim()) + 1;
            SettingsFile.set("run", (new StringBuilder()).append("").append(runCount).toString());
            Directory.RUNDir = (new StringBuilder()).append(Directory.RUNDir).append(runCount).toString();
            Directory.mkDirs(Directory.RUNDir);
            if(Directory.recordSuiteExecution)
                try
                {
                    recorder = new ATUTestRecorder(Directory.RUNDir, "ATU_CompleteSuiteRecording", Boolean.valueOf(false));
                    recorder.start();
                }
                catch(Throwable throwable)
                {
                    throw new IllegalStateException(throwable);
                }
        }
        catch(Exception exception)
        {
            throw new IllegalStateException(exception);
        }
    }

    public void onExecutionStart()
    {
        Attributes.setAttribute("startExecution", Long.valueOf(System.currentTimeMillis()));
        initChecking();
    }

    public void generateReport(List list, List list1, String s)
    {
        for(Iterator iterator = list1.iterator(); iterator.hasNext(); onFinish())
        {
            ISuite isuite = (ISuite)iterator.next();
            Attributes.setSuiteNameMapper(isuite.getName());
            startCreatingDirs(isuite);
        }

    }

    public void onFinish(ISuite isuite)
    {
    }

    public void onStart(ISuite isuite)
    {
    }

    int runCount;
    List passedTests;
    List failedTests;
    List skippedTests;
    private ATUTestRecorder recorder;
    public static boolean suiteStarted = false;

}
