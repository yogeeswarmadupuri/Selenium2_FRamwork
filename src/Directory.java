// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports.utils;

import atu.testng.reports.ATUReports;
import atu.testng.reports.enums.RecordingFor;
import atu.testng.reports.enums.ReportLabels;
import atu.testng.reports.exceptions.ATUReporterException;
import atu.testng.reports.writers.HTMLDesignFilesWriter;
import java.io.*;
import java.util.Properties;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

// Referenced classes of package atu.testng.reports.utils:
//            SettingsFile

public class Directory
{

    public Directory()
    {
    }

    public static void init()
        throws ATUReporterException
    {
        if(getCustomProperties() != null)
        {
            Properties properties = new Properties();
            try
            {
                properties.load(new FileReader(getCustomProperties()));
                String s = properties.getProperty("atu.reports.dir").trim();
                String s1 = properties.getProperty("atu.proj.header.text").trim();
                logo = properties.getProperty("atu.proj.header.logo").trim();
                String s2 = properties.getProperty("atu.proj.description").trim();
                String s3 = properties.getProperty("atu.reports.takescreenshot").trim();
                String s4 = properties.getProperty("atu.reports.configurationreports").trim();
                String s5 = properties.getProperty("atu.reports.excel").trim();
                String s6 = properties.getProperty("atu.reports.continueExecutionAfterStepFailed").trim();
                String s7 = properties.getProperty("atu.reports.recordExecution").trim();
                try
                {
                    if(s1 != null && s1.length() > 0)
                        ReportLabels.HEADER_TEXT.setLabel(s1);
                    if(s3 != null && s3.length() > 0)
                        try
                        {
                            takeScreenshot = Boolean.parseBoolean(s3);
                        }
                        catch(Exception exception) { }
                    if(s4 != null && s4.length() > 0)
                        try
                        {
                            generateConfigReports = Boolean.parseBoolean(s4);
                        }
                        catch(Exception exception1) { }
                    if(s5 != null && s5.length() > 0)
                        try
                        {
                            generateExcelReports = Boolean.parseBoolean(s5);
                        }
                        catch(Exception exception2) { }
                    if(s6 != null && s6.length() > 0)
                        try
                        {
                            continueExecutionAfterStepFailed = Boolean.parseBoolean(s6);
                        }
                        catch(Exception exception3) { }
                    if(s7 != null && s7.length() > 0)
                        try
                        {
                            RecordingFor recordingfor = RecordingFor.valueOf(s7.toUpperCase());
                            if(recordingfor == RecordingFor.SUITE)
                                recordSuiteExecution = true;
                            else
                            if(recordingfor == RecordingFor.TESTMETHOD)
                                recordTestMethodExecution = true;
                        }
                        catch(Throwable throwable) { }
                    if(s2 != null && s2.length() > 0)
                        ATUReports.indexPageDescription = s2;
                    if(s != null && s.length() > 0)
                    {
                        REPORTSDir = s;
                        REPORTSDIRName = (new File(REPORTSDir)).getName();
                        RESULTSDir = (new StringBuilder()).append(REPORTSDir).append(SEP).append("Results").toString();
                        HTMLDESIGNDIRName = "HTML_Design_Files";
                        HTMLDESIGNDir = (new StringBuilder()).append(REPORTSDir).append(SEP).append(HTMLDESIGNDIRName).toString();
                        CSSDIRName = "CSS";
                        CSSDir = (new StringBuilder()).append(HTMLDESIGNDir).append(SEP).append(CSSDIRName).toString();
                        IMGDIRName = "IMG";
                        IMGDir = (new StringBuilder()).append(HTMLDESIGNDir).append(SEP).append(IMGDIRName).toString();
                        JSDIRName = "JS";
                        JSDir = (new StringBuilder()).append(HTMLDESIGNDir).append(SEP).append(JSDIRName).toString();
                        RUNName = "Run_";
                        RUNDir = (new StringBuilder()).append(RESULTSDir).append(SEP).append(RUNName).toString();
                        SETTINGSFile = (new StringBuilder()).append(RESULTSDir).append(SEP).append("Settings.properties").toString();
                    }
                }
                catch(Exception exception4)
                {
                    throw new ATUReporterException(exception4.toString());
                }
            }
            catch(FileNotFoundException filenotfoundexception)
            {
                throw new ATUReporterException("The Path for the Custom Properties file could not be found");
            }
            catch(IOException ioexception)
            {
                throw new ATUReporterException("Problem Occured while reading the ATU Reporter Config File");
            }
        }
    }

    public static void mkDirs(String s)
    {
        File file = new File(s);
        if(!file.exists())
            file.mkdirs();
    }

    public static boolean exists(String s)
    {
        File file = new File(s);
        return file.exists();
    }

    public static void verifyRequiredFiles()
        throws ATUReporterException
    {
        init();
        mkDirs(REPORTSDir);
        if(!exists(RESULTSDir))
        {
            mkDirs(RESULTSDir);
            SettingsFile.initSettingsFile();
        }
        if(!exists(HTMLDESIGNDir))
        {
            mkDirs(HTMLDESIGNDir);
            mkDirs(CSSDir);
            mkDirs(JSDir);
            mkDirs(IMGDir);
            HTMLDesignFilesWriter.writeCSS();
            HTMLDesignFilesWriter.writeIMG();
            HTMLDesignFilesWriter.writeJS();
        }
        if(logo != null && logo.length() > 0)
        {
            String s = (new File(logo)).getName();
            if(!(new File((new StringBuilder()).append(IMGDir).append(SEP).append(s).toString())).exists())
                copyImage(logo);
            ReportLabels.PROJ_LOGO.setLabel(s);
        }
    }

    private static void copyImage(String s)
        throws ATUReporterException
    {
label0:
        {
            File file = new File(s);
            if(!file.exists())
                return;
            FileImageInputStream fileimageinputstream = null;
            FileImageOutputStream fileimageoutputstream = null;
            try
            {
                fileimageinputstream = new FileImageInputStream(new File(s));
                fileimageoutputstream = new FileImageOutputStream(new File((new StringBuilder()).append(IMGDir).append(SEP).append(file.getName()).toString()));
                for(int i = 0; (i = fileimageinputstream.read()) >= 0;)
                    fileimageoutputstream.write(i);

                fileimageoutputstream.close();
            }
            catch(Exception exception1)
            {
                try
                {
                    fileimageinputstream.close();
                    fileimageoutputstream.close();
                    Object obj2 = null;
                }
                catch(Exception exception2)
                {
                    fileimageinputstream = null;
                    fileimageoutputstream = null;
                    Object obj3 = null;
                }
                break label0;
            }
            finally
            {
                try
                {
                    fileimageinputstream.close();
                    fileimageoutputstream.close();
                    Object obj4 = null;
                }
                catch(Exception exception4)
                {
                    Object obj6 = null;
                    Object obj7 = null;
                    Object obj5 = null;
                }
                throw exception3;
            }
            try
            {
                fileimageinputstream.close();
                fileimageoutputstream.close();
                Object obj = null;
            }
            catch(Exception exception)
            {
                fileimageinputstream = null;
                fileimageoutputstream = null;
                Object obj1 = null;
            }
            break label0;
        }
    }

    private static String getCustomProperties()
    {
        return System.getProperty("atu.reporter.config");
    }

    public static final String ATU_VERSION = "v5.5 BETA";
    public static final String CURRENTDir = System.getProperty("user.dir");
    public static final String SEP = System.getProperty("file.separator");
    public static String REPORTSDIRName;
    public static String REPORTSDir;
    public static String RESULTSDir;
    public static String HTMLDESIGNDIRName;
    public static String HTMLDESIGNDir;
    public static String CSSDIRName;
    public static String CSSDir;
    public static String IMGDIRName;
    public static String IMGDir;
    public static String JSDIRName;
    public static String JSDir;
    public static String RUNName;
    public static String RUNDir;
    public static String SETTINGSFile;
    public static final char JS_SETTINGS_DELIM = 59;
    public static final String REPO_DELIM = "##@##@##";
    public static final char JS_FILE_DELIM = 44;
    public static final String MENU_LINK_NAME = "Run ";
    public static final String SCREENSHOT_TYPE = "PNG";
    public static final String SCREENSHOT_EXTENSION = ".PNG";
    private static String logo = null;
    public static String SCREENSHOT_DIRName = "img";
    public static boolean generateConfigReports = true;
    public static boolean generateExcelReports = false;
    public static boolean takeScreenshot = false;
    public static boolean continueExecutionAfterStepFailed = false;
    public static boolean recordExecutionAvailable = false;
    public static boolean recordSuiteExecution = false;
    public static boolean recordTestMethodExecution = false;
    public static final String MAIN_RECORD_FILE_NAME = "ATU_CompleteSuiteRecording";

    static 
    {
        REPORTSDIRName = "ATU Reports";
        REPORTSDir = (new StringBuilder()).append(CURRENTDir).append(SEP).append(REPORTSDIRName).toString();
        RESULTSDir = (new StringBuilder()).append(REPORTSDir).append(SEP).append("Results").toString();
        HTMLDESIGNDIRName = "HTML_Design_Files";
        HTMLDESIGNDir = (new StringBuilder()).append(REPORTSDir).append(SEP).append(HTMLDESIGNDIRName).toString();
        CSSDIRName = "CSS";
        CSSDir = (new StringBuilder()).append(HTMLDESIGNDir).append(SEP).append(CSSDIRName).toString();
        IMGDIRName = "IMG";
        IMGDir = (new StringBuilder()).append(HTMLDESIGNDir).append(SEP).append(IMGDIRName).toString();
        JSDIRName = "JS";
        JSDir = (new StringBuilder()).append(HTMLDESIGNDir).append(SEP).append(JSDIRName).toString();
        RUNName = "Run_";
        RUNDir = (new StringBuilder()).append(RESULTSDir).append(SEP).append(RUNName).toString();
        SETTINGSFile = (new StringBuilder()).append(RESULTSDir).append(SEP).append("Settings.properties").toString();
    }
}
