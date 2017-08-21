// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports.logging;


public final class LogAs extends Enum
{

    public static LogAs[] values()
    {
        return (LogAs[])$VALUES.clone();
    }

    public static LogAs valueOf(String s)
    {
        return (LogAs)Enum.valueOf(atu/testng/reports/logging/LogAs, s);
    }

    private LogAs(String s, int i)
    {
        super(s, i);
    }

    public static final LogAs FAILED;
    public static final LogAs PASSED;
    public static final LogAs INFO;
    public static final LogAs WARNING;
    private static final LogAs $VALUES[];

    static 
    {
        FAILED = new LogAs("FAILED", 0);
        PASSED = new LogAs("PASSED", 1);
        INFO = new LogAs("INFO", 2);
        WARNING = new LogAs("WARNING", 3);
        $VALUES = (new LogAs[] {
            FAILED, PASSED, INFO, WARNING
        });
    }
}
