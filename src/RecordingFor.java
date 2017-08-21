// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports.enums;


public final class RecordingFor extends Enum
{

    public static RecordingFor[] values()
    {
        return (RecordingFor[])$VALUES.clone();
    }

    public static RecordingFor valueOf(String s)
    {
        return (RecordingFor)Enum.valueOf(atu/testng/reports/enums/RecordingFor, s);
    }

    private RecordingFor(String s, int i)
    {
        super(s, i);
    }

    public static final RecordingFor TESTMETHOD;
    public static final RecordingFor SUITE;
    public static final RecordingFor NONE;
    private static final RecordingFor $VALUES[];

    static 
    {
        TESTMETHOD = new RecordingFor("TESTMETHOD", 0);
        SUITE = new RecordingFor("SUITE", 1);
        NONE = new RecordingFor("NONE", 2);
        $VALUES = (new RecordingFor[] {
            TESTMETHOD, SUITE, NONE
        });
    }
}
