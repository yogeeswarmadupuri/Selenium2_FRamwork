// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports.writers;

import java.io.PrintWriter;

public class ReportsPage
{

    public ReportsPage()
    {
    }

    public static void footer(PrintWriter printwriter)
    {
        printwriter.println("            <tr id=\"footer\">\n                <td colspan=\"2\">\n                    Best Viewed in &nbsp;\n                    <a href=\"http://www.mozilla.org/en-US/firefox/new/\">Firefox</a> &nbsp;\n                    <a href=\"http://www.apple.com/in/safari/\">Safari</a>&nbsp;\n                    <a href=\"http://www.google.com/chrome/\">Chrome</a>&nbsp;\n                    <a href=\"http://windows.microsoft.com/en-IN/internet-explorer/download-ie\">IE 9 & Above</a>&nbsp;\n                    &nbsp;\nATU Reporter Version: v5.5 BETA                    &nbsp;\n                    Reports by: <a href=\"http://automationtestingutilities.blogspot.in/\">Automation Testing Utilities</a>\n                </td>\n            </tr>\n        </table>\n    </body>\n</html>");
    }
}
