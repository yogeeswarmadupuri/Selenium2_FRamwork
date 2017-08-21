// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils
{

    public Utils()
    {
    }

    public static String getCurrentTime()
    {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
        Date date = new Date();
        return simpledateformat.format(date);
    }
}
