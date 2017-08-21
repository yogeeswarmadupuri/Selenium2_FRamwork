// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package atu.testng.reports.listeners;

import org.testng.*;

// Referenced classes of package atu.testng.reports.listeners:
//            ATUReportsListener

public class MethodListener
    implements IInvokedMethodListener
{

    public MethodListener()
    {
    }

    public void afterInvocation(IInvokedMethod iinvokedmethod, ITestResult itestresult)
    {
    }

    public void beforeInvocation(IInvokedMethod iinvokedmethod, ITestResult itestresult)
    {
        if(!iinvokedmethod.isConfigurationMethod());
        if(iinvokedmethod.isTestMethod())
        {
            ATUReportsListener.createReportDir(itestresult);
            ATUReportsListener.setPlatfromBrowserDetails(itestresult);
        }
    }
}
