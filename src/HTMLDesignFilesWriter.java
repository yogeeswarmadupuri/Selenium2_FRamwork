// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports.writers;

import atu.testng.reports.utils.Directory;
import java.io.*;
import javax.imageio.stream.FileImageOutputStream;

public class HTMLDesignFilesWriter
{

    public HTMLDesignFilesWriter()
    {
    }

    public static void writeCSS()
    {
        copyFile("styles/design.css", Directory.CSSDir);
        copyFile("styles/jquery.jqplot.css", Directory.CSSDir);
        copyFile("styles/jquery-ui.min.css", Directory.CSSDir);
    }

    public static void writeJS()
    {
        copyFile("js/excanvas.js", Directory.JSDir);
        copyFile("js/jqplot.barRenderer.min.js", Directory.JSDir);
        copyFile("js/jqplot.categoryAxisRenderer.min.js", Directory.JSDir);
        copyFile("js/jqplot.highlighter.min.js", Directory.JSDir);
        copyFile("js/jqplot.pieRenderer.min.js", Directory.JSDir);
        copyFile("js/jqplot.pointLabels.min.js", Directory.JSDir);
        copyFile("js/jquery.jqplot.min.js", Directory.JSDir);
        copyFile("js/jquery.min.js", Directory.JSDir);
        copyFile("js/jquery-ui.min.js", Directory.JSDir);
    }

    public static void writeIMG()
    {
        copyImage("images/fail.png", Directory.IMGDir);
        copyImage("images/pass.png", Directory.IMGDir);
        copyImage("images/skip.png", Directory.IMGDir);
        copyImage("images/atu.jpg", Directory.IMGDir);
        copyImage("images/loginfo.png", Directory.IMGDir);
        copyImage("images/logpass.png", Directory.IMGDir);
        copyImage("images/logfail.png", Directory.IMGDir);
        copyImage("images/logwarning.png", Directory.IMGDir);
    }

    private static void copyImage(String s, String s1)
    {
label0:
        {
            File file = new File(s);
            InputStream inputstream = atu/testng/reports/writers/HTMLDesignFilesWriter.getClassLoader().getResourceAsStream(s);
            FileImageOutputStream fileimageoutputstream = null;
            try
            {
                fileimageoutputstream = new FileImageOutputStream(new File((new StringBuilder()).append(s1).append(Directory.SEP).append(file.getName()).toString()));
                for(int i = 0; (i = inputstream.read()) >= 0;)
                    fileimageoutputstream.write(i);

                fileimageoutputstream.close();
            }
            catch(Exception exception1)
            {
                try
                {
                    inputstream.close();
                    fileimageoutputstream.close();
                    Object obj2 = null;
                }
                catch(Exception exception2)
                {
                    inputstream = null;
                    fileimageoutputstream = null;
                    Object obj3 = null;
                }
                break label0;
            }
            finally
            {
                try
                {
                    inputstream.close();
                    fileimageoutputstream.close();
                    Object obj4 = null;
                }
                catch(Exception exception4)
                {
                    Object obj6 = null;
                    Object obj7 = null;
                    Object obj5 = null;
                }
                throw exception3;
            }
            try
            {
                inputstream.close();
                fileimageoutputstream.close();
                Object obj = null;
            }
            catch(Exception exception)
            {
                inputstream = null;
                fileimageoutputstream = null;
                Object obj1 = null;
            }
            break label0;
        }
    }

    private static void copyFile(String s, String s1)
    {
label0:
        {
            File file = new File(s);
            InputStream inputstream = atu/testng/reports/writers/HTMLDesignFilesWriter.getClassLoader().getResourceAsStream(s);
            FileOutputStream fileoutputstream = null;
            try
            {
                fileoutputstream = new FileOutputStream((new StringBuilder()).append(s1).append(Directory.SEP).append(file.getName()).toString());
                for(int i = 0; (i = inputstream.read()) >= 0;)
                    fileoutputstream.write(i);

            }
            catch(FileNotFoundException filenotfoundexception)
            {
                try
                {
                    inputstream.close();
                    fileoutputstream.close();
                    Object obj2 = null;
                }
                catch(Exception exception1)
                {
                    inputstream = null;
                    fileoutputstream = null;
                    Object obj3 = null;
                }
                break label0;
            }
            catch(IOException ioexception)
            {
                try
                {
                    inputstream.close();
                    fileoutputstream.close();
                    Object obj4 = null;
                }
                catch(Exception exception2)
                {
                    inputstream = null;
                    fileoutputstream = null;
                    Object obj5 = null;
                }
                break label0;
            }
            finally
            {
                try
                {
                    inputstream.close();
                    fileoutputstream.close();
                    Object obj6 = null;
                }
                catch(Exception exception4)
                {
                    Object obj8 = null;
                    Object obj9 = null;
                    Object obj7 = null;
                }
                throw exception3;
            }
            try
            {
                inputstream.close();
                fileoutputstream.close();
                Object obj = null;
            }
            catch(Exception exception)
            {
                inputstream = null;
                fileoutputstream = null;
                Object obj1 = null;
            }
            break label0;
        }
    }
}
