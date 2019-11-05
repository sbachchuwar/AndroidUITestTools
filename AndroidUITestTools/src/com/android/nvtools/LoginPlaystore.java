package com.android.nvtools;

import java.io.IOException;

import android.os.RemoteException;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class LoginPlaystore extends UiAutomatorTestCase 
{
	public void testLogin() throws UiObjectNotFoundException, RemoteException, IOException
	{
		int tries=3;
		UiObject settingView=new UiObject(new UiSelector().packageName("com.android.settings"));
		while(!settingView.exists() && tries>0)
		{
			RunOnShell cmdexitStatus=RunOnShell.runcmd("Open Settings App", "am start com.android.settings".split("\\s+"), 2000);
        	System.out.print("Exit Status of command is:"+cmdexitStatus.ExitValue+"\n");
        	tries-=1;
		}
		
		assertTrue("Unable to Open Settings app after 3 tries", settingView.exists());
		
		UiScrollable scrollSetting=new UiScrollable(new UiSelector().scrollable(true));
		scrollSetting.setAsVerticalList();
		UiObject accountOption=scrollSetting.getChild(new UiSelector().text("Accounts"));
		
		accountOption.clickAndWaitForNewWindow();
		
		UiObject addAccount= new UiObject(new UiSelector().text("Add account"));
		if(addAccount.exists())
		{
			System.out.println("Adding new account");
			addAccount.clickAndWaitForNewWindow();
			UiObject GoogleOption=new UiObject(new UiSelector().text("Google"));
			if(GoogleOption.exists())
				GoogleOption.click();
			
			
		}
	}
	
	
}
