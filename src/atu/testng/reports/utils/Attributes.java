// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports.utils;

import java.util.HashMap;
import java.util.Map;

// Referenced classes of package atu.testng.reports.utils:
//            AuthorDetails

public class Attributes
{

    public Attributes()
    {
    }

    public static Object getAttribute(String s)
    {
        return attributes.get(s);
    }

    public static void setAttribute(String s, Object obj)
    {
        attributes.put(s, obj);
    }

    public static void makeNull()
    {
        attributes = null;
    }

    public static String getSuiteNameMapper(String s)
    {
        return (String)suiteNameMapper.get(s);
    }

    public static Map getSuiteNameMapperMap()
    {
        return suiteNameMapper;
    }

    public static void setSuiteNameMapper(String s)
    {
        suiteNameMapper.put(s, (new StringBuilder()).append("atuSuiteNameMapper").append(mapperCount++).toString());
    }

    public static void setClassLevelAuthors(String s, AuthorDetails authordetails)
    {
        classLevelAuthors.put(s, authordetails);
    }

    public static AuthorDetails getClassLevelAuthor(String s)
    {
        return (AuthorDetails)classLevelAuthors.get(s);
    }

    private static int mapperCount = 1;
    private static Map attributes = new HashMap();
    private static Map suiteNameMapper = new HashMap();
    private static Map classLevelAuthors = new HashMap();
    public static final String SUITE_CLASS_NAME_MAPPER = "atuSuiteNameMapper";

}
