// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports.chart;

import atu.testng.reports.enums.ReportLabels;
import java.awt.Color;
import java.text.DecimalFormat;
import org.jfree.chart.*;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class PieChart
{

    public PieChart()
    {
    }

    public static JFreeChart generate2DPieChart(int i, int j, int k)
    {
        DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
        defaultpiedataset.setValue(ReportLabels.PASS.getLabel(), i);
        defaultpiedataset.setValue(ReportLabels.FAIL.getLabel(), j);
        defaultpiedataset.setValue(ReportLabels.SKIP.getLabel(), k);
        JFreeChart jfreechart = ChartFactory.createPieChart(ReportLabels.PIE_CHART_LABEL.getLabel(), defaultpiedataset, true, true, true);
        PiePlot pieplot = (PiePlot)jfreechart.getPlot();
        pieplot.setCircular(true);
        pieplot.setForegroundAlpha(0.9F);
        pieplot.setBackgroundAlpha(0.9F);
        pieplot.setSectionPaint(ReportLabels.PASS.getLabel(), ChartColor.DARK_GREEN);
        pieplot.setSectionPaint(ReportLabels.FAIL.getLabel(), ChartColor.RED);
        pieplot.setSectionPaint(ReportLabels.SKIP.getLabel(), ChartColor.BLUE);
        pieplot.setExplodePercent(ReportLabels.PASS.getLabel(), 0.050000000000000003D);
        pieplot.setExplodePercent(ReportLabels.FAIL.getLabel(), 0.050000000000000003D);
        pieplot.setExplodePercent(ReportLabels.SKIP.getLabel(), 0.050000000000000003D);
        pieplot.setOutlinePaint(Color.BLACK);
        pieplot.setOutlineVisible(false);
        Color color = new Color(255, 255, 255, 0);
        jfreechart.setBackgroundPaint(color);
        pieplot.setBackgroundPaint(color);
        StandardPieSectionLabelGenerator standardpiesectionlabelgenerator = new StandardPieSectionLabelGenerator("{2}", new DecimalFormat("0"), new DecimalFormat("0%"));
        pieplot.setLabelGenerator(standardpiesectionlabelgenerator);
        return jfreechart;
    }
}
