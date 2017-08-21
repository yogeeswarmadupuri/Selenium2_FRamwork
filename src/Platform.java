// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.openqa.selenium.*;
import org.openqa.selenium.internal.BuildInfo;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Platform
{

    public Platform()
    {
    }

    public static String getHostName()
    {
        return InetAddress.getLocalHost().getHostName();
        UnknownHostException unknownhostexception;
        unknownhostexception;
        return "Unknown";
    }

    public static void prepareDetails(WebDriver webdriver)
    {
        BROWSER_VERSION = "";
        BROWSER_NAME = "UnKnown";
        if(webdriver == null)
        {
            BROWSER_VERSION = "";
            BROWSER_NAME = "UnKnown";
            return;
        }
        try
        {
            String s = (String)((JavascriptExecutor)webdriver).executeScript("return navigator.userAgent;", new Object[0]);
            if(s.contains("MSIE"))
            {
                BROWSER_VERSION = s.substring(s.indexOf("MSIE") + 5, s.indexOf("Windows NT") - 2);
                BROWSER_NAME = "Internet Explorer";
            } else
            if(s.contains("Firefox/"))
            {
                BROWSER_VERSION = s.substring(s.indexOf("Firefox/") + 8);
                BROWSER_NAME = "Mozilla Firefox";
            } else
            if(s.contains("Chrome/"))
            {
                BROWSER_VERSION = s.substring(s.indexOf("Chrome/") + 7, s.lastIndexOf("Safari/"));
                BROWSER_NAME = "Google Chrome";
            } else
            if(s.contains("AppleWebKit") && s.contains("Version/"))
            {
                BROWSER_VERSION = s.substring(s.indexOf("Version/") + 8, s.lastIndexOf("Safari/"));
                BROWSER_NAME = "Apple Safari";
            } else
            if(s.startsWith("Opera/"))
            {
                BROWSER_VERSION = s.substring(s.indexOf("Version/") + 8);
                BROWSER_NAME = "Opera";
            } else
            {
                return;
            }
        }
        catch(Exception exception)
        {
            try
            {
                getCapabilitiesDetails(webdriver);
            }
            catch(Exception exception1)
            {
                return;
            }
            return;
        }
        getCapabilitiesDetails(webdriver);
        BROWSER_VERSION = (new StringBuilder()).append("v").append(BROWSER_VERSION).toString();
        return;
    }

    private static void getCapabilitiesDetails(WebDriver webdriver)
    {
        Capabilities capabilities = ((RemoteWebDriver)webdriver).getCapabilities();
        BROWSER_NAME = capabilities.getBrowserName();
        BROWSER_VERSION = capabilities.getVersion();
    }

    private static BuildInfo driverInfo;
    public static final String DRIVER_VERSION;
    public static final String DRIVER_REVISION;
    public static final String USER = System.getProperty("user.name");
    public static final String OS = System.getProperty("os.name");
    public static final String OS_ARCH = System.getProperty("os.arch");
    public static final String OS_VERSION = System.getProperty("os.version");
    public static final String JAVA_VERSION = System.getProperty("java.version");
    public static String BROWSER_NAME = "Unknown";
    public static String BROWSER_VERSION = "";
    public static String BROWSER_NAME_PROP = "BrowserName";
    public static String BROWSER_VERSION_PROP = "BrowserVersion";

    static 
    {
        driverInfo = new BuildInfo();
        DRIVER_VERSION = driverInfo.getReleaseLabel();
        DRIVER_REVISION = driverInfo.getBuildRevision();
    }
}
