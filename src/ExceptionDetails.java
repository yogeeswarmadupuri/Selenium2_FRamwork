// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports.enums;


public final class ExceptionDetails extends Enum
{

    public static ExceptionDetails[] values()
    {
        return (ExceptionDetails[])$VALUES.clone();
    }

    public static ExceptionDetails valueOf(String s)
    {
        return (ExceptionDetails)Enum.valueOf(atu/testng/reports/enums/ExceptionDetails, s);
    }

    public String getExceptionInfo()
    {
        return exceptionInfo;
    }

    private ExceptionDetails(String s, int i, String s1)
    {
        super(s, i);
        exceptionInfo = s1;
    }

    public static final ExceptionDetails org_openqa_selenium_ElementNotVisibleException;
    public static final ExceptionDetails org_openqa_selenium_UnsupportedCommandException;
    public static final ExceptionDetails org_openqa_selenium_remote_UnreachableBrowserException;
    public static final ExceptionDetails org_openqa_selenium_UnhandledAlertException;
    public static final ExceptionDetails org_openqa_selenium_support_ui_UnexpectedTagNameException;
    public static final ExceptionDetails org_openqa_selenium_firefox_UnableToCreateProfileException;
    public static final ExceptionDetails org_openqa_selenium_remote_ScreenshotException;
    public static final ExceptionDetails org_openqa_selenium_remote_SessionTerminatedException;
    public static final ExceptionDetails org_openqa_selenium_StaleElementReferenceException;
    public static final ExceptionDetails org_openqa_selenium_TimeoutException;
    public static final ExceptionDetails org_openqa_selenium_interactions_MoveTargetOutOfBoundsException;
    public static final ExceptionDetails org_openqa_selenium_XPathLookupException;
    public static final ExceptionDetails org_openqa_selenium_InvalidElementStateException;
    public static final ExceptionDetails org_openqa_selenium_interactions_InvalidCoordinatesException;
    public static final ExceptionDetails org_openqa_selenium_IllegalLocatorException;
    public static final ExceptionDetails org_openqa_selenium_chrome_FatalChromeException;
    public static final ExceptionDetails org_openqa_selenium_remote_ErrorHandler_UnknownServerException;
    public static final ExceptionDetails java_lang_AssertionError;
    public static final ExceptionDetails org_openqa_selenium_NoSuchElementException;
    public static final ExceptionDetails org_openqa_selenium_NoAlertPresentException;
    public static final ExceptionDetails org_openqa_selenium_NoSuchFrameException;
    public static final ExceptionDetails org_openqa_selenium_NoSuchWindowException;
    public static final ExceptionDetails org_openqa_selenium_WebDriverException;
    private String exceptionInfo;
    private static final ExceptionDetails $VALUES[];

    static 
    {
        org_openqa_selenium_ElementNotVisibleException = new ExceptionDetails("org_openqa_selenium_ElementNotVisibleException", 0, "Element Not Visible");
        org_openqa_selenium_UnsupportedCommandException = new ExceptionDetails("org_openqa_selenium_UnsupportedCommandException", 1, "Un Supported Command");
        org_openqa_selenium_remote_UnreachableBrowserException = new ExceptionDetails("org_openqa_selenium_remote_UnreachableBrowserException", 2, "Unable to connect Browser");
        org_openqa_selenium_UnhandledAlertException = new ExceptionDetails("org_openqa_selenium_UnhandledAlertException", 3, "An Alert Box caused problem");
        org_openqa_selenium_support_ui_UnexpectedTagNameException = new ExceptionDetails("org_openqa_selenium_support_ui_UnexpectedTagNameException", 4, "Invalid TagName");
        org_openqa_selenium_firefox_UnableToCreateProfileException = new ExceptionDetails("org_openqa_selenium_firefox_UnableToCreateProfileException", 5, "Unable To Create profile");
        org_openqa_selenium_remote_ScreenshotException = new ExceptionDetails("org_openqa_selenium_remote_ScreenshotException", 6, "Unable to take Screenshot");
        org_openqa_selenium_remote_SessionTerminatedException = new ExceptionDetails("org_openqa_selenium_remote_SessionTerminatedException", 7, "Session Expired");
        org_openqa_selenium_StaleElementReferenceException = new ExceptionDetails("org_openqa_selenium_StaleElementReferenceException", 8, "Element No More Available");
        org_openqa_selenium_TimeoutException = new ExceptionDetails("org_openqa_selenium_TimeoutException", 9, "Time Out Error");
        org_openqa_selenium_interactions_MoveTargetOutOfBoundsException = new ExceptionDetails("org_openqa_selenium_interactions_MoveTargetOutOfBoundsException", 10, "Error due to Moving Out of window Size");
        org_openqa_selenium_XPathLookupException = new ExceptionDetails("org_openqa_selenium_XPathLookupException", 11, "InValid XPath");
        org_openqa_selenium_InvalidElementStateException = new ExceptionDetails("org_openqa_selenium_InvalidElementStateException", 12, "Invalid Element State");
        org_openqa_selenium_interactions_InvalidCoordinatesException = new ExceptionDetails("org_openqa_selenium_interactions_InvalidCoordinatesException", 13, "Invalid Co-ordinates");
        org_openqa_selenium_IllegalLocatorException = new ExceptionDetails("org_openqa_selenium_IllegalLocatorException", 14, "The Specified (By.) Locater is illegal");
        org_openqa_selenium_chrome_FatalChromeException = new ExceptionDetails("org_openqa_selenium_chrome_FatalChromeException", 15, "Fatal Chrome Error");
        org_openqa_selenium_remote_ErrorHandler_UnknownServerException = new ExceptionDetails("org_openqa_selenium_remote_ErrorHandler_UnknownServerException", 16, "Server Error");
        java_lang_AssertionError = new ExceptionDetails("java_lang_AssertionError", 17, "Assertion Error");
        org_openqa_selenium_NoSuchElementException = new ExceptionDetails("org_openqa_selenium_NoSuchElementException", 18, "No such Element exists");
        org_openqa_selenium_NoAlertPresentException = new ExceptionDetails("org_openqa_selenium_NoAlertPresentException", 19, "Alert Not Present");
        org_openqa_selenium_NoSuchFrameException = new ExceptionDetails("org_openqa_selenium_NoSuchFrameException", 20, "Frame Does Not Exist");
        org_openqa_selenium_NoSuchWindowException = new ExceptionDetails("org_openqa_selenium_NoSuchWindowException", 21, "Unable to Identify Window");
        org_openqa_selenium_WebDriverException = new ExceptionDetails("org_openqa_selenium_WebDriverException", 22, "WebDriver Exception");
        $VALUES = (new ExceptionDetails[] {
            org_openqa_selenium_ElementNotVisibleException, org_openqa_selenium_UnsupportedCommandException, org_openqa_selenium_remote_UnreachableBrowserException, org_openqa_selenium_UnhandledAlertException, org_openqa_selenium_support_ui_UnexpectedTagNameException, org_openqa_selenium_firefox_UnableToCreateProfileException, org_openqa_selenium_remote_ScreenshotException, org_openqa_selenium_remote_SessionTerminatedException, org_openqa_selenium_StaleElementReferenceException, org_openqa_selenium_TimeoutException, 
            org_openqa_selenium_interactions_MoveTargetOutOfBoundsException, org_openqa_selenium_XPathLookupException, org_openqa_selenium_InvalidElementStateException, org_openqa_selenium_interactions_InvalidCoordinatesException, org_openqa_selenium_IllegalLocatorException, org_openqa_selenium_chrome_FatalChromeException, org_openqa_selenium_remote_ErrorHandler_UnknownServerException, java_lang_AssertionError, org_openqa_selenium_NoSuchElementException, org_openqa_selenium_NoAlertPresentException, 
            org_openqa_selenium_NoSuchFrameException, org_openqa_selenium_NoSuchWindowException, org_openqa_selenium_WebDriverException
        });
    }
}
