// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports.enums;


public final class ReportLabels extends Enum
{

    public static ReportLabels[] values()
    {
        return (ReportLabels[])$VALUES.clone();
    }

    public static ReportLabels valueOf(String s)
    {
        return (ReportLabels)Enum.valueOf(atu/testng/reports/enums/ReportLabels, s);
    }

    private ReportLabels(String s, int i, String s1)
    {
        super(s, i);
        setLabel(s1);
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String s)
    {
        label = s;
    }

    public static final ReportLabels HEADER_TEXT;
    public static final ReportLabels PASS;
    public static final ReportLabels FAIL;
    public static final ReportLabels SKIP;
    public static final ReportLabels X_AXIS;
    public static final ReportLabels Y_AXIS;
    public static final ReportLabels LINE_CHART_TOOLTIP;
    public static final ReportLabels ATU_LOGO;
    public static final ReportLabels ATU_CAPTION;
    public static final ReportLabels PROJ_LOGO;
    public static final ReportLabels PROJ_CAPTION;
    public static final ReportLabels PIE_CHART_LABEL;
    public static final ReportLabels TC_INFO_LABEL;
    private String label;
    private static final ReportLabels $VALUES[];

    static 
    {
        HEADER_TEXT = new ReportLabels("HEADER_TEXT", 0, "ATU Graphical Reports for Selenium -  TestNG");
        PASS = new ReportLabels("PASS", 1, "Passed");
        FAIL = new ReportLabels("FAIL", 2, "Failed");
        SKIP = new ReportLabels("SKIP", 3, "Skipped");
        X_AXIS = new ReportLabels("X_AXIS", 4, "Run Number");
        Y_AXIS = new ReportLabels("Y_AXIS", 5, "TC Number");
        LINE_CHART_TOOLTIP = new ReportLabels("LINE_CHART_TOOLTIP", 6, "Test Cases");
        ATU_LOGO = new ReportLabels("ATU_LOGO", 7, "atu.jpg");
        ATU_CAPTION = new ReportLabels("ATU_CAPTION", 8, "<i style=\"float:left;padding-left:20px;font-size:12px\">Reflections of Visionary Minds</i>");
        PROJ_LOGO = new ReportLabels("PROJ_LOGO", 9, "");
        PROJ_CAPTION = new ReportLabels("PROJ_CAPTION", 10, "");
        PIE_CHART_LABEL = new ReportLabels("PIE_CHART_LABEL", 11, "Test Cases Percent Distribution");
        TC_INFO_LABEL = new ReportLabels("TC_INFO_LABEL", 12, "Requirement Coverage/Build Info/Cycle - Description");
        $VALUES = (new ReportLabels[] {
            HEADER_TEXT, PASS, FAIL, SKIP, X_AXIS, Y_AXIS, LINE_CHART_TOOLTIP, ATU_LOGO, ATU_CAPTION, PROJ_LOGO, 
            PROJ_CAPTION, PIE_CHART_LABEL, TC_INFO_LABEL
        });
    }
}
