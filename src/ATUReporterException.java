// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports.exceptions;


public class ATUReporterException extends Exception
{

    public ATUReporterException()
    {
    }

    public ATUReporterException(String s)
    {
        message = s;
    }

    public String toString()
    {
        return (new StringBuilder()).append("[ATU Custom Reporter Exception] ").append(message).toString();
    }

    private String message;
}
