// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.selenium.reports;

import org.openqa.selenium.WebElement;

public class CaptureScreen
{
    public static final class ScreenshotOf extends Enum
    {

        public static ScreenshotOf[] values()
        {
            return (ScreenshotOf[])$VALUES.clone();
        }

        public static ScreenshotOf valueOf(String s)
        {
            return (ScreenshotOf)Enum.valueOf(atu/testng/selenium/reports/CaptureScreen$ScreenshotOf, s);
        }

        public static final ScreenshotOf BROWSER_PAGE;
        public static final ScreenshotOf DESKTOP;
        private static final ScreenshotOf $VALUES[];

        static 
        {
            BROWSER_PAGE = new ScreenshotOf("BROWSER_PAGE", 0);
            DESKTOP = new ScreenshotOf("DESKTOP", 1);
            $VALUES = (new ScreenshotOf[] {
                BROWSER_PAGE, DESKTOP
            });
        }

        private ScreenshotOf(String s, int i)
        {
            super(s, i);
        }
    }


    public CaptureScreen(WebElement webelement)
    {
        captureBrowserPage = false;
        captureDesktop = false;
        captureWebElement = false;
        element = null;
        if(webelement != null)
        {
            setCaptureWebElement(true);
            setElement(webelement);
        }
    }

    public CaptureScreen(ScreenshotOf screenshotof)
    {
        captureBrowserPage = false;
        captureDesktop = false;
        captureWebElement = false;
        element = null;
        if(screenshotof == ScreenshotOf.BROWSER_PAGE)
            setCaptureBrowserPage(true);
        else
        if(screenshotof == ScreenshotOf.DESKTOP)
            setCaptureDesktop(true);
    }

    public boolean isCaptureBrowserPage()
    {
        return captureBrowserPage;
    }

    public void setCaptureBrowserPage(boolean flag)
    {
        captureBrowserPage = flag;
    }

    public boolean isCaptureDesktop()
    {
        return captureDesktop;
    }

    public void setCaptureDesktop(boolean flag)
    {
        captureDesktop = flag;
    }

    public boolean isCaptureWebElement()
    {
        return captureWebElement;
    }

    public void setCaptureWebElement(boolean flag)
    {
        captureWebElement = flag;
    }

    public WebElement getElement()
    {
        return element;
    }

    public void setElement(WebElement webelement)
    {
        element = webelement;
    }

    private boolean captureBrowserPage;
    private boolean captureDesktop;
    private boolean captureWebElement;
    private WebElement element;
}
