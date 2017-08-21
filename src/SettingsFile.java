// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports.utils;

import atu.testng.reports.exceptions.ATUReporterException;
import java.io.*;
import java.util.Properties;

// Referenced classes of package atu.testng.reports.utils:
//            Directory

public class SettingsFile
{

    public SettingsFile()
    {
    }

    public static void initSettingsFile()
        throws ATUReporterException
    {
        create(Directory.SETTINGSFile);
        set("run", "0");
        set("passedList", "");
        set("failedList", "");
        set("skippedList", "");
    }

    public static void create(String s)
        throws ATUReporterException
    {
        File file = new File(s);
        try
        {
            if(!file.exists())
            {
                file.createNewFile();
            } else
            {
                file.delete();
                file.createNewFile();
            }
        }
        catch(IOException ioexception)
        {
            throw new ATUReporterException("Unable To Create Required Files for Custom Reports");
        }
    }

    public static void open()
        throws ATUReporterException
    {
        properties = new Properties();
        try
        {
            properties.load(new FileReader(Directory.SETTINGSFile));
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            throw new ATUReporterException("Settings File Not Available");
        }
        catch(IOException ioexception)
        {
            throw new ATUReporterException("Unable To Create Required Files for Custom Reports");
        }
    }

    public static void close()
        throws ATUReporterException
    {
        Exception exception;
        try
        {
            properties.store(new FileWriter(Directory.SETTINGSFile), "");
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            throw new ATUReporterException("Settings File Not Available");
        }
        catch(IOException ioexception)
        {
            throw new ATUReporterException("Unable To Create Required Files for Custom Reports");
        }
        finally
        {
            properties = null;
        }
        properties = null;
        break MISSING_BLOCK_LABEL_54;
        throw exception;
    }

    public static void correctErrors()
        throws NumberFormatException, ATUReporterException
    {
        int i = Integer.parseInt(get("run"));
        int j = get("passedList").split(";").length;
        int k = get("failedList").split(";").length;
        int l = get("skippedList").split(";").length;
        if(isFirstParamBig(i, j, k, l))
        {
            int i1 = i - j;
            String s = get("passedList");
            for(int j1 = 0; j1 < i1; j1++)
                s = (new StringBuilder()).append(s).append(0).append(';').toString();

            set("passedList", s);
            i1 = i - k;
            String s1 = get("failedList");
            for(int k1 = 0; k1 < i1; k1++)
                s1 = (new StringBuilder()).append(s1).append(0).append(';').toString();

            set("failedList", s1);
            i1 = i - l;
            String s2 = get("skippedList");
            for(int l1 = 0; l1 < i1; l1++)
                s2 = (new StringBuilder()).append(s2).append(0).append(';').toString();

            set("skippedList", s2);
            return;
        }
        if(isFirstParamBig(j, i, k, l))
            return;
        if(isFirstParamBig(k, j, i, l))
            return;
        if(isFirstParamBig(l, j, k, i))
            return;
        else
            return;
    }

    private static boolean isFirstParamBig(int i, int j, int k, int l)
    {
        return i > j && i > k && i > l;
    }

    public static int getHighestTestCaseNumber()
        throws ATUReporterException
    {
        String as[] = get("passedList").split(";");
        String as1[] = get("failedList").split(";");
        String as2[] = get("skippedList").split(";");
        int ai[] = getIntArrayFromStringArray(as);
        int ai1[] = getIntArrayFromStringArray(as1);
        int ai2[] = getIntArrayFromStringArray(as2);
        int i = getBiggestNumber(ai);
        int j = getBiggestNumber(ai1);
        int k = getBiggestNumber(ai2);
        int l = getBiggestNumber(new int[] {
            i, j, k
        });
        return l;
    }

    public static int getBiggestNumber(int ai[])
    {
        int i = ai[0];
        for(int j = 1; j < ai.length; j++)
            if(ai[j] > i)
                i = ai[j];

        return i;
    }

    public static int[] getIntArrayFromStringArray(String as[])
    {
        int ai[] = new int[as.length];
        for(int i = 0; i < as.length; i++)
            ai[i] = Integer.parseInt(as[i]);

        return ai;
    }

    public static String get(String s)
        throws ATUReporterException
    {
        open();
        String s1 = properties.getProperty(s);
        close();
        return s1;
    }

    public static void set(String s, String s1)
        throws ATUReporterException
    {
        open();
        properties.setProperty(s, s1);
        close();
    }

    private static Properties properties;
}
