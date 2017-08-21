// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports.utils;

import java.io.IOException;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

class TestNGXMLReader
{

    TestNGXMLReader()
    {
    }

    public static void read(String s)
    {
        DocumentBuilderFactory documentbuilderfactory = DocumentBuilderFactory.newInstance();
        try
        {
            DocumentBuilder documentbuilder = documentbuilderfactory.newDocumentBuilder();
            Document document = documentbuilder.parse(s);
            document.getDocumentElement().normalize();
            NodeList nodelist = document.getElementsByTagName("atu");
            if(nodelist.getLength() > 0)
            {
                Node node = nodelist.item(0);
                if(node.getNodeType() == 1)
                {
                    Element element = (Element)node;
                    NodeList nodelist1 = element.getElementsByTagName("dir");
                    Object obj;
                    if(nodelist1.getLength() > 0)
                        obj = ((Element)nodelist1.item(0)).getAttribute("value");
                    obj = element.getElementsByTagName("header");
                    if(((NodeList) (obj)).getLength() > 0)
                    {
                        String s1 = ((Element)((NodeList) (obj)).item(0)).getAttribute("text");
                        String s2 = ((Element)((NodeList) (obj)).item(0)).getAttribute("logo");
                    }
                }
            }
        }
        catch(ParserConfigurationException parserconfigurationexception) { }
        catch(IOException ioexception) { }
        catch(SAXException saxexception) { }
    }
}
