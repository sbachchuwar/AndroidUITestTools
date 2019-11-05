package com.android.nvtools;

import java.io.IOException;

import android.os.RemoteException;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class GetAppList extends UiAutomatorTestCase 
{
	public String[] GetApps() throws UiObjectNotFoundException, RemoteException, IOException
	{
		int tries=3;
		UiObject AppHandlerView=new UiObject(new UiSelector().packageName("com.example.apphandler"));
		while(!AppHandlerView.exists() & tries>0)
        {
			RunOnShell cmdexitStatus=RunOnShell.runcmd("Open App Handler", "am start com.example.apphandler/.MainActivity".split("\\s+"), 2000);
        	System.out.print("Exit Status of command is:"+cmdexitStatus.ExitValue+"\n");
        	tries-=1;
        }
		
		UiObject AppHandlerTv=new UiObject(new UiSelector().resourceId("com.example.apphandler:id/tv"));
		String AppHandlerText=AppHandlerTv.getText();
		
		int appcount=AppHandlerText.split("-").length-1;
		String[] apps=new String[appcount];
		System.out.println("Appcount is :"+appcount);
		int i=0;
		while(i<appcount)
		{
			apps[i]=AppHandlerText.split("-")[i+1].split("\n")[0];
			System.out.println(apps[i]);
			++i;
		}
		String Clearcmd="pm clear com.example.apphandler";
		RunOnShell clearApp=RunOnShell.runcmd("Clear App from Screen\n", Clearcmd.split("\\s+"), 2000);
		return apps;
	}
	/*public void testGetapps() throws UiObjectNotFoundException, RemoteException, IOException
	{
		GetAppList getAppList=new GetAppList();
		String[] AppNameList=getAppList.GetApps();
	}*/
	
}
