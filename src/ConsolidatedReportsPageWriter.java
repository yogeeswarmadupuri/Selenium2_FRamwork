// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports.writers;

import atu.testng.reports.enums.ReportLabels;
import atu.testng.reports.utils.Directory;
import java.io.PrintWriter;

// Referenced classes of package atu.testng.reports.writers:
//            ReportsPage

public class ConsolidatedReportsPageWriter extends ReportsPage
{

    public ConsolidatedReportsPageWriter()
    {
    }

    public static void menuLink(PrintWriter printwriter, int i)
    {
        printwriter.println("\n            <tr id=\"container\">\n                <td id=\"menu\">\n                    <ul> \n");
        printwriter.println(" <li class=\"menuStyle\"><a href=\"../index.html\" >Index</a></li>\n");
        if(i == 1)
        {
            printwriter.println((new StringBuilder()).append("\n <li class=\"menuStyle\"><a href=\"").append(Directory.RUNName).append(i).append(Directory.SEP).append("CurrentRun.html\" >").append("Run ").append(i).append(" </a></li>\n").toString());
        } else
        {
            int j = 1;
            do
            {
                if(j > i)
                    break;
                if(j == i)
                {
                    printwriter.println((new StringBuilder()).append("\n <li style=\"padding-top: 4px;padding-bottom: 4px;\"><a href=\"").append(Directory.RUNName).append(j).append(Directory.SEP).append("CurrentRun.html\" >").append("Run ").append(j).append(" </a></li>\n").toString());
                    break;
                }
                printwriter.println((new StringBuilder()).append("\n <li class=\"menuStyle\"><a href=\"").append(Directory.RUNName).append(j).append(Directory.SEP).append("CurrentRun.html\" >").append("Run ").append(j).append(" </a></li>\n").toString());
                j++;
            } while(true);
        }
        printwriter.println("\n                    </ul>\n                </td>\n\n");
    }

    public static void header(PrintWriter printwriter)
    {
        printwriter.println((new StringBuilder()).append("<!DOCTYPE html>\n\n<html>\n    <head>\n        <title>Execution Summary</title>\n\n        <link rel=\"stylesheet\" type=\"text/css\" href=\"../HTML_Design_Files/CSS/design.css\" />\n        <link rel=\"stylesheet\" type=\"text/css\" href=\"../HTML_Design_Files/CSS/jquery.jqplot.css\" />\n        <link rel=\"Stylesheet\" type=\"text/css\" href=\"../HTML_Design_Files/CSS/jquery-ui.min.css\"  />\n\n        <script type=\"text/javascript\" src=\"../HTML_Design_Files/JS/jquery.min.js\"></script>\n        <script type=\"text/javascript\" src=\"../HTML_Design_Files/JS/jquery.jqplot.min.js\"></script>\n        <!--[if lt IE 9]>\n        <script type=\"text/javascript\" src=\"../HTML_Design_Files/JS/excanvas.js\"></script>\n        <![endif]-->\n\n\n        <script type=\"text/javascript\" src=\"../HTML_Design_Files/JS/jqplot.barRenderer.min.js\"></script> \n        <script type=\"text/javascript\" src=\"../HTML_Design_Files/JS/jqplot.categoryAxisRenderer.min.js\"></script>\n        <script type=\"text/javascript\" src=\"../HTML_Design_Files/JS/jqplot.pointLabels.min.js\"></script>\n\n        <script type=\"text/javascript\" src=\"../HTML_Design_Files/JS/jqplot.highlighter.min.js\"></script>\n\n        <script type=\"text/javascript\" src=\"barChart.js\"></script>\n        <script type=\"text/javascript\" src=\"lineChart.js\"></script>\n        <script type=\"text/javascript\" src=\"../HTML_Design_Files/JS/jquery-ui.min.js\"></script>\n\n\n        <script type=\"text/javascript\">\n            $(document).ready(function() {\n                $(\"#tabs\").tabs();\n\n                $('#tabs').bind('tabsshow', function(event, ui) {\n                    if (ui.index === 1 && plot1._drawCount === 0) {\n                        plot1.replot();\n                    }\n                    else if (ui.index === 2 && plot2._drawCount === 0) {\n                        plot2.replot();\n                    }\n                });\n            });\n        </script>\n        \n    </head>\n    <body>\n        <table id=\"mainTable\">\n            <tr id=\"header\" >\n                <td id=\"logo\"><img src=\"../HTML_Design_Files/IMG/").append(ReportLabels.ATU_LOGO.getLabel()).append("\" alt=\"Logo\" height=\"70\" width=\"140\" /> ").append("<br/>").append(ReportLabels.ATU_CAPTION.getLabel()).append("</td>\n").append("                <td id=\"headertext\">\n").append("                    ").append(ReportLabels.HEADER_TEXT.getLabel()).append(" \n").append("<div style=\"padding-right:20px;float:right\"><img src=\"../HTML_Design_Files/IMG/").append(ReportLabels.PROJ_LOGO.getLabel()).append("\" height=\"70\" width=\"140\" /> </i></div>").append("                </td>\n").append("            </tr>\n").append("").toString());
    }

    public static void content(PrintWriter printwriter)
    {
        printwriter.println("<td id=\"content\">\n\n                    <div id=\"tabs\">\n                        <ul>\n                            <li><a href=\"#tabs-1\">Line Chart</a></li>\n                            <li><a href=\"#tabs-2\">Bar Chart</a></li>      \n                        </ul>\n                        <div id=\"tabs-1\" style=\"text-align: left;\">\n                            <p class=\"info\" style=\"text-align: center;color: black;font-size: 14px\">\n                                The following Line chart demonstrates the number of Passed, Failed and Skipped Test Cases\n                                in last 10 Runs\n                            </p>                            \n                            <div id=\"line\" style=\"height: 270px;  width: 85%; margin-top: 20px;color:black;\"></div>\n\n                        </div>\n                        <div id=\"tabs-2\" style=\"text-align: left;\">\n                            <p class=\"info\" style=\"text-align: center;color: black;font-size: 14px\">\n                                The following Bar chart demonstrates the number of Passed, Failed and Skipped Test Cases\n                                in last 10 Runs\n                            </p>\n                            <div id=\"bar\" style=\"margin-top:20px; margin-left:20px; width:85%; height:300px;color:black;\"></div>\n                        </div>    \n                    </div> \n                </td>\n            </tr>");
    }
}
