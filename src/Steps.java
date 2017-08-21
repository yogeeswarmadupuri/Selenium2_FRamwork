// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports.utils;

import atu.testng.reports.logging.LogAs;

public class Steps
{

    public Steps()
    {
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String s)
    {
        description = s;
    }

    public String getInputValue()
    {
        return inputValue;
    }

    public void setInputValue(String s)
    {
        inputValue = s;
    }

    public String getExpectedValue()
    {
        return expectedValue;
    }

    public void setExpectedValue(String s)
    {
        expectedValue = s;
    }

    public String getActualValue()
    {
        return actualValue;
    }

    public void setActualValue(String s)
    {
        actualValue = s;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String s)
    {
        time = s;
    }

    public String getLineNum()
    {
        return lineNum;
    }

    public void setLineNum(String s)
    {
        lineNum = s;
    }

    public String getScreenShot()
    {
        return screenShot;
    }

    public void setScreenShot(String s)
    {
        screenShot = s;
    }

    public LogAs getLogAs()
    {
        return logAs;
    }

    public void setLogAs(LogAs logas)
    {
        logAs = logas;
    }

    private String description;
    private String inputValue;
    private String expectedValue;
    private String actualValue;
    private String time;
    private String lineNum;
    private String screenShot;
    private LogAs logAs;
}
