// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports.listeners;

import atu.testng.reports.utils.Directory;
import atu.testng.reports.writers.TestCaseReportsPageWriter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import org.testng.IConfigurationListener2;
import org.testng.ITestResult;

// Referenced classes of package atu.testng.reports.listeners:
//            ATUReportsListener

public class ConfigurationListener
    implements IConfigurationListener2
{

    public ConfigurationListener()
    {
    }

    public void onConfigurationFailure(ITestResult itestresult)
    {
        if(Directory.generateConfigReports)
            failedConfigurations.add(itestresult);
    }

    public void onConfigurationSkip(ITestResult itestresult)
    {
        if(Directory.generateConfigReports)
        {
            ATUReportsListener.createReportDir(itestresult);
            skippedConfigurations.add(itestresult);
        }
    }

    public void onConfigurationSuccess(ITestResult itestresult)
    {
        if(Directory.generateConfigReports)
            passedConfigurations.add(itestresult);
    }

    public static void startConfigurationMethodsReporting(int i)
    {
        startReportingForPassedConfigurations(passedConfigurations, i);
        startReportingForFailedConfigurations(failedConfigurations, i);
        startReportingForSkippedConfigurations(skippedConfigurations, i);
    }

    private static void startReportingForPassedConfigurations(List list, int i)
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
        s = null;
        s = itestresult.getAttribute("reportDir").toString();
        printwriter = new PrintWriter((new StringBuilder()).append(s).append(Directory.SEP).append(itestresult.getName()).append(".html").toString());
        TestCaseReportsPageWriter.header(printwriter, itestresult);
        TestCaseReportsPageWriter.menuLink(printwriter, itestresult, 0);
        TestCaseReportsPageWriter.content(printwriter, itestresult, i);
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

    private static void startReportingForFailedConfigurations(List list, int i)
    {
        PrintWriter printwriter;
        Iterator iterator;
        printwriter = null;
        iterator = list.iterator();
_L1:
        ITestResult itestresult;
        String s;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_165;
        itestresult = (ITestResult)iterator.next();
        s = itestresult.getAttribute("reportDir").toString();
        printwriter = new PrintWriter((new StringBuilder()).append(s).append(Directory.SEP).append(itestresult.getName()).append(".html").toString());
        TestCaseReportsPageWriter.header(printwriter, itestresult);
        TestCaseReportsPageWriter.menuLink(printwriter, itestresult, 0);
        TestCaseReportsPageWriter.content(printwriter, itestresult, i);
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

    private static void startReportingForSkippedConfigurations(List list, int i)
    {
        PrintWriter printwriter;
        Iterator iterator;
        printwriter = null;
        iterator = list.iterator();
_L1:
        ITestResult itestresult;
        String s;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_165;
        itestresult = (ITestResult)iterator.next();
        s = itestresult.getAttribute("reportDir").toString();
        printwriter = new PrintWriter((new StringBuilder()).append(s).append(Directory.SEP).append(itestresult.getName()).append(".html").toString());
        TestCaseReportsPageWriter.header(printwriter, itestresult);
        TestCaseReportsPageWriter.menuLink(printwriter, itestresult, 0);
        TestCaseReportsPageWriter.content(printwriter, itestresult, i);
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

    public void beforeConfiguration(ITestResult itestresult)
    {
        ATUReportsListener.createReportDir(itestresult);
    }

    static List passedConfigurations = new ArrayList();
    static List failedConfigurations = new ArrayList();
    static List skippedConfigurations = new ArrayList();

}
