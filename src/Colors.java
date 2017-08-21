// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports.enums;


public final class Colors extends Enum
{

    public static Colors[] values()
    {
        return (Colors[])$VALUES.clone();
    }

    public static Colors valueOf(String s)
    {
        return (Colors)Enum.valueOf(atu/testng/reports/enums/Colors, s);
    }

    private Colors(String s, int i, String s1)
    {
        super(s, i);
        setColor(s1);
    }

    public String getColor()
    {
        return color;
    }

    private void setColor(String s)
    {
        color = s;
    }

    public static final Colors PASS;
    public static final Colors FAIL;
    public static final Colors SKIP;
    private String color;
    private static final Colors $VALUES[];

    static 
    {
        PASS = new Colors("PASS", 0, "#7BB661");
        FAIL = new Colors("FAIL", 1, "#E03C31");
        SKIP = new Colors("SKIP", 2, "#21ABCD");
        $VALUES = (new Colors[] {
            PASS, FAIL, SKIP
        });
    }
}
