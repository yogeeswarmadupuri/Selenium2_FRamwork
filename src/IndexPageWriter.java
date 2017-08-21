// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports.writers;

import atu.testng.reports.enums.ReportLabels;
import java.io.PrintWriter;

// Referenced classes of package atu.testng.reports.writers:
//            ReportsPage

public class IndexPageWriter extends ReportsPage
{

    public IndexPageWriter()
    {
    }

    public static void header(PrintWriter printwriter)
    {
        printwriter.println((new StringBuilder()).append("<!DOCTYPE html>\n\n<html>\n    <head>\n        <title>Execution Summary</title>\n\n        <link rel=\"stylesheet\" type=\"text/css\" href=\"HTML_Design_Files/CSS/design.css\" />\n\n    </head>\n    <body>\n        <table id=\"mainTable\">\n            <tr id=\"header\" >\n                <td id=\"logo\"><img src=\"HTML_Design_Files/IMG/").append(ReportLabels.ATU_LOGO.getLabel()).append("\" alt=\"Logo\" height=\"70\" width=\"140\" /> ").append("<br/>").append(ReportLabels.ATU_CAPTION.getLabel()).append("</td>\n").append("                <td id=\"headertext\">\n").append(ReportLabels.HEADER_TEXT.getLabel()).append("\n").append("<div style=\"padding-right:20px;float:right\"><img src=\"HTML_Design_Files/IMG/").append(ReportLabels.PROJ_LOGO.getLabel()).append("\" height=\"70\" width=\"140\" /> </i></div>").append("                </td>\n").append("            </tr>\n").append("\n").append("            <tr id=\"container\">\n").append("                <td id=\"menu\">\n").append("\n").append("                    <ul>\n").append("                        <li class=\"menuStyle\"><a href=\"Results/ConsolidatedPage.html\" >Consolidated Reports</a></li>                        \n").append("                    </ul>\n").append("                </td>").toString());
    }

    public static void content(PrintWriter printwriter, String s)
    {
        printwriter.println("<td id=\"content\">\n");
        printwriter.print(s);
        printwriter.println("                </td>\n            </tr>");
    }
}
