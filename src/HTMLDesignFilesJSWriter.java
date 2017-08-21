// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports.writers;

import atu.testng.reports.enums.Colors;
import atu.testng.reports.enums.ReportLabels;
import atu.testng.reports.utils.Directory;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class HTMLDesignFilesJSWriter
{

    public HTMLDesignFilesJSWriter()
    {
    }

    private static String reduceData(String s, int i)
    {
        int j = 0;
        for(int k = 0; k < s.length(); k++)
            if(s.charAt(k) == ',' && ++j == i)
                s = s.substring(k + 1, s.length());

        return s;
    }

    public static void pieChartJS(int i, int j, int k, int l)
    {
        try
        {
            PrintWriter printwriter = new PrintWriter((new StringBuilder()).append(Directory.RESULTSDir).append(Directory.SEP).append(Directory.RUNName).append(l).append(Directory.SEP).append("pieChart.js").toString());
            printwriter.println((new StringBuilder()).append("$(document).ready(function() {\n    var data = [['").append(ReportLabels.PASS.getLabel()).append("', ").append(i).append("], ['").append(ReportLabels.FAIL.getLabel()).append("', ").append(j).append("], ['").append(ReportLabels.SKIP.getLabel()).append("', ").append(k).append("]];\n").append("    jQuery.jqplot('chart', [data],\n").append("            {seriesColors: [\"").append(Colors.PASS.getColor()).append("\", \"").append(Colors.FAIL.getColor()).append("\", \"").append(Colors.SKIP.getColor()).append("\"],\n").append("                seriesDefaults: {\n").append("                    // Make this a pie chart.\n").append("                    renderer: jQuery.jqplot.PieRenderer,\n").append("                    rendererOptions: {\n").append("                        padding: 15,\n").append("                        sliceMargin: 1,\n").append("                        // Put data labels on the pie slices.\n").append("                        // By default, labels show the percentage of the slice.\n").append("                        showDataLabels: true\n").append("                    }\n").append("                },\n").append("                grid: {borderColor: '#cccccc', background: '#ffffff',\n").append("                    borderWidth: 0, // pixel width of border around grid.\n").append("                    shadow: false // draw a shadow for grid.\n").append("                },\n").append("                legend: {show: true, location: 'e'}\n").append("            }\n").append("    );\n").append("});").toString());
            printwriter.close();
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            filenotfoundexception.printStackTrace();
        }
    }

    public static void barChartJS(String s, String s1, String s2, int i)
    {
        s = s.substring(0, s.lastIndexOf(';')).replace(';', ',').trim();
        s1 = s1.substring(0, s1.lastIndexOf(';')).replace(';', ',').trim();
        s2 = s2.substring(0, s2.lastIndexOf(';')).replace(';', ',').trim();
        int j = 0;
        if(i > 10)
        {
            j = i - 10;
            s = reduceData(s, j);
            s1 = reduceData(s1, j);
            s2 = reduceData(s2, j);
        }
        try
        {
            PrintWriter printwriter = new PrintWriter((new StringBuilder()).append(Directory.RESULTSDir).append(Directory.SEP).append("barChart.js").toString());
            printwriter.println((new StringBuilder()).append("            $(document).ready(function(){\n                var s1 = [").append(s).append("];\n").append("                var s2 = [").append(s1).append("];\n").append("                var s3 = [").append(s2).append("];\n").toString());
            printwriter.print("var ticks = [");
            for(int k = j + 1; k <= i; k++)
            {
                printwriter.print(k);
                if(k != i)
                    printwriter.print(",");
            }

            printwriter.print("];");
            printwriter.println((new StringBuilder()).append("    $.jqplot('bar', [s1, s2, s3], {\n        animate: true,axesDefaults:{min:0,tickInterval: ").append(TICK_INTERVAL).append("").append("},").append("        seriesColors: [\"").append(Colors.PASS.getColor()).append("\", \"").append(Colors.FAIL.getColor()).append("\", \"").append(Colors.SKIP.getColor()).append("\"],\n").append("        stackSeries: false,\n").append("        seriesDefaults: {\n").append("            renderer: $.jqplot.BarRenderer,\n").append("            pointLabels: {show: true}\n").append("            , rendererOptions: {barWidth: 12, barMargin: 25, fillToZero: true}\n").append("        }\n").append("        ,\n").append("        grid: {borderColor: '#ffffff', background: '#ffffff',\n").append("            borderWidth: 0.5, // pixel width of border around grid.\n").append("            shadow: false // draw a shadow for grid.\n").append("        }\n").append("        ,\n").append("        legend: {\n").append("            show: true,\n").append("            location: 'e',\n").append("            placement: 'outside',\n").append("            labels: ['").append(ReportLabels.PASS.getLabel()).append("', '").append(ReportLabels.FAIL.getLabel()).append("', '").append(ReportLabels.SKIP.getLabel()).append("']\n").append("        },\n").append("        axes: {\n").append("            xaxis: {\n").append("                renderer: $.jqplot.CategoryAxisRenderer,\n").append("                ticks: ticks,\n").append("                label: \"").append(ReportLabels.X_AXIS.getLabel()).append("\"\n").append("            }\n").append("            ,\n").append("            yaxis: {\n").append("                label: \"").append(ReportLabels.Y_AXIS.getLabel()).append("\",\n").append("                tickOptions: {\n").append("                    formatString: \"%dtc\"\n").append("                }\n").append("            }\n").append("        }\n").append("    });\n").append("});").toString());
            printwriter.close();
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            filenotfoundexception.printStackTrace();
        }
    }

    public static void lineChartJS(String s, String s1, String s2, int i)
    {
        s = s.substring(0, s.lastIndexOf(';')).replace(';', ',').trim();
        s1 = s1.substring(0, s1.lastIndexOf(';')).replace(';', ',').trim();
        s2 = s2.substring(0, s2.lastIndexOf(';')).replace(';', ',').trim();
        try
        {
            PrintWriter printwriter = new PrintWriter((new StringBuilder()).append(Directory.RESULTSDir).append(Directory.SEP).append("lineChart.js").toString());
            printwriter.println((new StringBuilder()).append("            $(document).ready(function(){\n                var line1 = [").append(s).append("];\n").append("                var line2 = [").append(s1).append("];\n").append("                var line3 = [").append(s2).append("];\n").toString());
            printwriter.print("var ticks = [");
            boolean flag = true;
            if(i == 1)
                flag = false;
            for(int j = ((flag) ? 1 : 0); j <= i; j++)
            {
                printwriter.print(j);
                if(j != i)
                    printwriter.print(",");
            }

            printwriter.print("];");
            printwriter.print((new StringBuilder()).append("$.jqplot('line', [line1, line2, line3], {\n        animate: true,\naxesDefaults:{min:0,tickInterval: ").append(TICK_INTERVAL).append("").append("},").append("        seriesDefaults: {\n").append("            rendererOptions: {\n").append("                smooth: true\n").append("            }\n").append("        },\n").append("        series: [{lineWidth: 1.5, label: '").append(ReportLabels.PASS.getLabel()).append("'},\n").append("            {lineWidth: 1.5, label: '").append(ReportLabels.FAIL.getLabel()).append("'},\n").append("            {lineWidth: 1.5, label: '").append(ReportLabels.SKIP.getLabel()).append("'}],\n").append("        axes: {\n").append("            xaxis: {\n").append("                label: \"").append(ReportLabels.X_AXIS.getLabel()).append("\",\n").append("                ticks: ticks,\n").append("                tickOptions: {\n").toString());
            if(i <= 10)
                printwriter.print("                    formatString: \"%'d Run\"\n");
            else
                printwriter.print("                    formatString: \"%'d \"\n");
            printwriter.print((new StringBuilder()).append("                },\n                pad: 1.2,\n                rendererOptions: {\n                    tickInset: 0.3,\n                    minorTicks: 1\n                }\n            },\n            yaxis: {\n                label: \"").append(ReportLabels.Y_AXIS.getLabel()).append("\"\n").append("                ,tickOptions: {\n").append("                    formatString: \"%'d Tc\"\n").append("                },\n").append("            }\n").append("        },\n").append("        highlighter: {\n").append("            show: true,\n").append("            sizeAdjust: 10,\n").append("            tooltipLocation: 'n',\n").append("            tooltipAxes: 'y',\n").append("            tooltipFormatString: '%d :&nbsp;<b><i><span style=\"color:black;\">").append(ReportLabels.LINE_CHART_TOOLTIP.getLabel()).append("</span></i></b>',\n").append("            useAxesFormatters: false\n").append("        },\n").append("        cursor: {\n").append("            show: true\n").append("        },\n").append("        grid: {background: '#ffffff', drawGridLines: true, gridLineColor: '#cccccc', borderColor: '#cccccc',\n").append("            borderWidth: 0.5, shadow: false},\n").append("        legend: {show: true, placement: 'outside', location: 'e'},\n").append("        seriesColors: [\"").append(Colors.PASS.getColor()).append("\", \"").append(Colors.FAIL.getColor()).append("\", \"").append(Colors.SKIP.getColor()).append("\"]\n").append("    });\n").append("});\n").append("").toString());
            printwriter.close();
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            filenotfoundexception.printStackTrace();
        }
    }

    public static int TICK_INTERVAL = 1;

}
