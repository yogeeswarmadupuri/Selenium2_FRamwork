// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports.utils;


public class AuthorDetails
{

    public AuthorDetails()
    {
        authorName = "Unknown";
        creationDate = "Unknown";
        version = "Unknown";
    }

    public String getAuthorName()
    {
        return authorName;
    }

    public void setAuthorName(String s)
    {
        authorName = s;
    }

    public String getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(String s)
    {
        creationDate = s;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String s)
    {
        version = s;
    }

    private String authorName;
    private String creationDate;
    private String version;
}
