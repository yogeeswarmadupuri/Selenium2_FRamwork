// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports;

import atu.testng.reports.exceptions.ATUReporterStepFailedException;
import atu.testng.reports.logging.LogAs;
import atu.testng.reports.utils.Attributes;
import atu.testng.reports.utils.AuthorDetails;
import atu.testng.reports.utils.Directory;
import atu.testng.reports.utils.Platform;
import atu.testng.reports.utils.Steps;
import atu.testng.selenium.reports.CaptureScreen;
import com.google.common.io.Files;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ATUReports
{

    public ATUReports()
    {
    }

    public static void setWebDriver(WebDriver webdriver)
    {
        driver = webdriver;
        Platform.prepareDetails(driver);
    }

    public static WebDriver getWebDriver()
    {
        return driver;
    }

    public static void setAuthorInfo(String s, String s1, String s2)
    {
        AuthorDetails authordetails = new AuthorDetails();
        authordetails.setAuthorName(s);
        authordetails.setCreationDate(s1);
        authordetails.setVersion(s2);
        Reporter.getCurrentTestResult().setAttribute("authorInfo", authordetails);
    }

    public static void setAuthorInfoAtClassLevel(String s, String s1, String s2)
    {
        String s3 = Thread.currentThread().getStackTrace()[2].getClassName();
        AuthorDetails authordetails = new AuthorDetails();
        authordetails.setAuthorName(s);
        authordetails.setCreationDate(s1);
        authordetails.setVersion(s2);
        Attributes.setClassLevelAuthors(s3, authordetails);
    }

    public static void setTestCaseReqCoverage(String s)
    {
        Reporter.getCurrentTestResult().setAttribute("reqCoverage", s);
    }

    private static void stepFailureHandler(ITestResult itestresult, Steps steps, LogAs logas)
    {
        if(logas == LogAs.FAILED)
        {
            buildReportData(steps);
            if(Directory.continueExecutionAfterStepFailed)
                itestresult.setAttribute("passedButFailed", "passedButFailed");
            else
                throw new ATUReporterStepFailedException();
            return;
        } else
        {
            buildReportData(steps);
            return;
        }
    }

    public static void add(String s, LogAs logas, CaptureScreen capturescreen)
    {
        if(capturescreen != null)
            if(capturescreen.isCaptureBrowserPage())
                takeBrowserPageScreenShot();
            else
            if(capturescreen.isCaptureDesktop())
                takeDesktopScreenshot();
            else
            if(capturescreen.isCaptureWebElement())
                takeWebElementScreenShot(capturescreen.getElement());
        Steps steps = new Steps();
        steps.setDescription(s);
        steps.setInputValue("");
        steps.setExpectedValue("");
        steps.setActualValue("");
        steps.setTime(getExecutionTime());
        steps.setLineNum(getLineNumDesc());
        steps.setScreenShot(screenShotNumber);
        steps.setLogAs(logas);
        stepFailureHandler(Reporter.getCurrentTestResult(), steps, logas);
    }

    public static void add(String s, String s1, LogAs logas, CaptureScreen capturescreen)
    {
        if(capturescreen != null)
            if(capturescreen.isCaptureBrowserPage())
                takeBrowserPageScreenShot();
            else
            if(capturescreen.isCaptureDesktop())
                takeDesktopScreenshot();
            else
            if(capturescreen.isCaptureWebElement())
                takeWebElementScreenShot(capturescreen.getElement());
        Steps steps = new Steps();
        steps.setDescription(s);
        steps.setInputValue(s1);
        steps.setExpectedValue("");
        steps.setActualValue("");
        steps.setTime(getExecutionTime());
        steps.setLineNum(getLineNumDesc());
        steps.setScreenShot(screenShotNumber);
        steps.setLogAs(logas);
        stepFailureHandler(Reporter.getCurrentTestResult(), steps, logas);
    }

    public static void add(String s, String s1, String s2, LogAs logas, CaptureScreen capturescreen)
    {
        if(capturescreen != null)
            if(capturescreen.isCaptureBrowserPage())
                takeBrowserPageScreenShot();
            else
            if(capturescreen.isCaptureDesktop())
                takeDesktopScreenshot();
            else
            if(capturescreen.isCaptureWebElement())
                takeWebElementScreenShot(capturescreen.getElement());
        Steps steps = new Steps();
        steps.setDescription(s);
        steps.setInputValue("");
        steps.setExpectedValue(s1);
        steps.setActualValue(s2);
        steps.setTime(getExecutionTime());
        steps.setLineNum(getLineNumDesc());
        steps.setScreenShot(screenShotNumber);
        steps.setLogAs(logas);
        stepFailureHandler(Reporter.getCurrentTestResult(), steps, logas);
    }

    public static void add(String s, String s1, String s2, String s3, LogAs logas, CaptureScreen capturescreen)
    {
        if(capturescreen != null)
            if(capturescreen.isCaptureBrowserPage())
                takeBrowserPageScreenShot();
            else
            if(capturescreen.isCaptureDesktop())
                takeDesktopScreenshot();
            else
            if(capturescreen.isCaptureWebElement())
                takeWebElementScreenShot(capturescreen.getElement());
        Steps steps = new Steps();
        steps.setDescription(s);
        steps.setInputValue(s1);
        steps.setExpectedValue(s2);
        steps.setActualValue(s3);
        steps.setTime(getExecutionTime());
        steps.setLineNum(getLineNumDesc());
        steps.setScreenShot(screenShotNumber);
        steps.setLogAs(logas);
        stepFailureHandler(Reporter.getCurrentTestResult(), steps, logas);
    }

    private static void buildReportData(Steps steps)
    {
        screenShotNumber = null;
        int i = Reporter.getOutput().size() + 1;
        Reporter.getCurrentTestResult().setAttribute((new StringBuilder()).append("STEP").append(i).toString(), steps);
        Reporter.log((new StringBuilder()).append("STEP").append(i).toString());
    }

    private static String getExecutionTime()
    {
        currentExecutionTime = System.currentTimeMillis();
        long l = currentExecutionTime - lastExecutionTime;
        if(l > 1000L)
        {
            l /= 1000L;
            lastExecutionTime = currentExecutionTime;
            return (new StringBuilder()).append(l).append(" Sec").toString();
        } else
        {
            lastExecutionTime = currentExecutionTime;
            return (new StringBuilder()).append(l).append(" Milli Sec").toString();
        }
    }

    private static String getLineNumDesc()
    {
        String s = (new StringBuilder()).append("").append(Thread.currentThread().getStackTrace()[3].getLineNumber()).toString();
        return s;
    }

    private static void takeDesktopScreenshot()
    {
        if(!Directory.takeScreenshot)
            return;
        ITestResult itestresult = Reporter.getCurrentTestResult();
        String s = (new StringBuilder()).append(itestresult.getAttribute("reportDir").toString()).append(Directory.SEP).append(Directory.IMGDIRName).toString();
        screenShotNumber = (new StringBuilder()).append(Reporter.getOutput(Reporter.getCurrentTestResult()).size() + 1).append("").toString();
        File file = new File((new StringBuilder()).append(s).append(Directory.SEP).append(screenShotNumber).append(".PNG").toString());
        try
        {
            Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage bufferedimage = (new Robot()).createScreenCapture(rectangle);
            ImageIO.write(bufferedimage, "PNG", file);
        }
        catch(Exception exception)
        {
            screenShotNumber = null;
        }
    }

    private static void takeBrowserPageScreenShot()
    {
        if(!Directory.takeScreenshot)
            return;
        if(getWebDriver() == null)
        {
            screenShotNumber = null;
            return;
        }
        ITestResult itestresult = Reporter.getCurrentTestResult();
        String s = (new StringBuilder()).append(itestresult.getAttribute("reportDir").toString()).append(Directory.SEP).append(Directory.IMGDIRName).toString();
        screenShotNumber = (new StringBuilder()).append(Reporter.getOutput(Reporter.getCurrentTestResult()).size() + 1).append("").toString();
        File file = new File((new StringBuilder()).append(s).append(Directory.SEP).append(screenShotNumber).append(".PNG").toString());
        try
        {
            WebDriver webdriver;
            if(getWebDriver().getClass().getName().equals("org.openqa.selenium.remote.RemoteWebDriver"))
                webdriver = (new Augmenter()).augment(getWebDriver());
            else
                webdriver = getWebDriver();
            if(webdriver instanceof TakesScreenshot)
            {
                byte abyte0[] = (byte[])((TakesScreenshot)webdriver).getScreenshotAs(OutputType.BYTES);
                Files.write(abyte0, file);
            } else
            {
                screenShotNumber = null;
            }
        }
        catch(Exception exception)
        {
            screenShotNumber = null;
        }
    }

    private static void takeWebElementScreenShot(WebElement webelement)
    {
        if(!Directory.takeScreenshot)
            return;
        if(getWebDriver() == null)
        {
            screenShotNumber = null;
            return;
        }
        ITestResult itestresult = Reporter.getCurrentTestResult();
        String s = (new StringBuilder()).append(itestresult.getAttribute("reportDir").toString()).append(Directory.SEP).append(Directory.IMGDIRName).toString();
        screenShotNumber = (new StringBuilder()).append(Reporter.getOutput(Reporter.getCurrentTestResult()).size() + 1).append("").toString();
        File file = new File((new StringBuilder()).append(s).append(Directory.SEP).append(screenShotNumber).append(".PNG").toString());
        try
        {
            WebDriver webdriver;
            if(getWebDriver().getClass().getName().equals("org.openqa.selenium.remote.RemoteWebDriver"))
                webdriver = (new Augmenter()).augment(getWebDriver());
            else
                webdriver = getWebDriver();
            if(webdriver instanceof TakesScreenshot)
            {
                File file1 = (File)((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                BufferedImage bufferedimage = ImageIO.read(file1);
                Point point = webelement.getLocation();
                int i = webelement.getSize().getWidth();
                int j = webelement.getSize().getHeight();
                BufferedImage bufferedimage1 = bufferedimage.getSubimage(point.getX(), point.getY(), i, j);
                ImageIO.write(bufferedimage1, "PNG", file);
            } else
            {
                screenShotNumber = null;
            }
        }
        catch(Exception exception)
        {
            screenShotNumber = null;
        }
    }

    /**
     * @deprecated Method add is deprecated
     */

    public static void add(String s, boolean flag)
    {
        if(flag)
            takeScreenShot();
        Steps steps = new Steps();
        steps.setDescription(s);
        steps.setInputValue("");
        steps.setExpectedValue("");
        steps.setActualValue("");
        steps.setTime(getExecutionTime());
        steps.setLineNum(getLineNumDesc());
        steps.setScreenShot(screenShotNumber);
        steps.setLogAs(LogAs.PASSED);
        buildReportData(steps);
    }

    /**
     * @deprecated Method add is deprecated
     */

    public static void add(String s, String s1, boolean flag)
    {
        if(flag)
            takeScreenShot();
        Steps steps = new Steps();
        steps.setDescription(s);
        steps.setInputValue(s1);
        steps.setExpectedValue("");
        steps.setActualValue("");
        steps.setTime(getExecutionTime());
        steps.setLineNum(getLineNumDesc());
        steps.setScreenShot(screenShotNumber);
        steps.setLogAs(LogAs.PASSED);
        buildReportData(steps);
    }

    /**
     * @deprecated Method add is deprecated
     */

    public static void add(String s, String s1, String s2, boolean flag)
    {
        if(flag)
            takeScreenShot();
        Steps steps = new Steps();
        steps.setDescription(s);
        steps.setInputValue("");
        steps.setExpectedValue(s1);
        steps.setActualValue(s2);
        steps.setTime(getExecutionTime());
        steps.setLineNum(getLineNumDesc());
        steps.setScreenShot(screenShotNumber);
        steps.setLogAs(LogAs.PASSED);
        buildReportData(steps);
    }

    /**
     * @deprecated Method add is deprecated
     */

    public static void add(String s, String s1, String s2, String s3, boolean flag)
    {
        if(flag)
            takeScreenShot();
        Steps steps = new Steps();
        steps.setDescription(s);
        steps.setInputValue(s1);
        steps.setExpectedValue(s2);
        steps.setActualValue(s3);
        steps.setTime(getExecutionTime());
        steps.setLineNum(getLineNumDesc());
        steps.setScreenShot(screenShotNumber);
        steps.setLogAs(LogAs.PASSED);
        buildReportData(steps);
    }

    private static void takeScreenShot()
    {
        if(!Directory.takeScreenshot)
            return;
        if(getWebDriver() == null)
        {
            screenShotNumber = null;
            return;
        }
        ITestResult itestresult = Reporter.getCurrentTestResult();
        String s = (new StringBuilder()).append(itestresult.getAttribute("reportDir").toString()).append(Directory.SEP).append(Directory.IMGDIRName).toString();
        screenShotNumber = (new StringBuilder()).append(Reporter.getOutput(Reporter.getCurrentTestResult()).size() + 1).append("").toString();
        File file = new File((new StringBuilder()).append(s).append(Directory.SEP).append(screenShotNumber).append(".PNG").toString());
        try
        {
            WebDriver webdriver;
            if(getWebDriver().getClass().getName().equals("org.openqa.selenium.remote.RemoteWebDriver") || (getWebDriver() instanceof RemoteWebDriver))
                webdriver = (new Augmenter()).augment(getWebDriver());
            else
                webdriver = getWebDriver();
            if(webdriver instanceof TakesScreenshot)
            {
                byte abyte0[] = (byte[])((TakesScreenshot)webdriver).getScreenshotAs(OutputType.BYTES);
                Files.write(abyte0, file);
            } else
            {
                screenShotNumber = null;
            }
        }
        catch(Exception exception)
        {
            screenShotNumber = null;
        }
    }

    private static WebDriver driver;
    public static final int MAX_BAR_REPORTS = 10;
    public static final String MESSAGE = "ATU Reporter: Preparing Reports";
    public static String indexPageDescription = "Reports Description";
    public static String currentRunDescription = "Here you can give description about the current Run";
    private static String screenShotNumber;
    private static long lastExecutionTime = 0L;
    private static long currentExecutionTime;
    public static final String EMPTY = "";
    public static final String STEP_NUM = "STEP";
    public static final String PASSED_BUT_FAILED = "passedButFailed";

    static 
    {
        try
        {
            lastExecutionTime = Reporter.getCurrentTestResult().getStartMillis();
        }
        catch(Exception exception)
        {
            lastExecutionTime = ((Long)Attributes.getAttribute("startExecution")).longValue();
        }
    }
}
