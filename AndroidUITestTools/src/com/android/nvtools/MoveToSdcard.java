/*
Purpose of the test is to stress MoveToSdcard feature in Android.
Contact bachchuwarsuraj@gmail.com for any concern.
*/
package com.android.nvtools;

import java.io.IOException;

import android.os.RemoteException;
import android.os.SystemClock;
import android.provider.Contacts.Intents.UI;
import android.util.Log;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class MoveToSdcard extends UiAutomatorTestCase 
{
	public void testMoveApp() throws UiObjectNotFoundException, RemoteException, IOException
	{
		UiObject welcome_cling=new UiObject(new UiSelector().resourceId("com.android.launcher3:id/cling_dismiss_longpress_info"));
		if(welcome_cling.exists())
			welcome_cling.clickAndWaitForNewWindow();
		int tries=3;
		//int cmdexitStatus=-1;
	    
		UiDevice.getInstance().pressHome();
		UiObject launcherView=new UiObject(new UiSelector().packageName("com.android.launcher3"));
		/*
		if(!launcherView.exists())
		{
			UiDevice.getInstance().pressBack();
			UiDevice.getInstance().pressBack();
			UiDevice.getInstance().pressBack();
		}*/
		while(!launcherView.exists())
		{
			System.out.println("Clearing app");
		
			String currentPackage=UiDevice.getInstance().getCurrentPackageName();
			String clearPackage="pm clear "+currentPackage;
			RunOnShell clearApp=RunOnShell.runcmd("Clear App from Screen\n", clearPackage.split("\\s+"), 2000);
        	System.out.print("Command Output:"+clearApp.cmdOutput+"\n");
		}
	    //UiObject settingsView=new UiObject(new UiSelector().packageName("com.android.settings"));
	    UiObject settingsView=new UiObject(new UiSelector().packageName("com.android.settings"));
	    
        while(!settingsView.exists() & tries>0)
        {
        	RunOnShell cmdexitStatus=RunOnShell.runcmd("Open Settings App", "am start com.android.settings".split("\\s+"), 2000);
        	System.out.print("Exit Status of command is:"+cmdexitStatus.ExitValue+"\n");
        	tries-=1;
        }
        assertTrue( "Failed to open Settings app after 3 tries",settingsView.exists());
        
        
        
        
        UiScrollable scrollSettings=new UiScrollable(new UiSelector().scrollable(true));
        scrollSettings.setAsVerticalList();
        UiObject AppOption=scrollSettings.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()), "Apps");
        
        AppOption.clickAndWaitForNewWindow();
        
        //UiObject SettingsAppView=new UiObject(new UiSelector().text("Downloaded"));
        UiObject SettingsAppView=new UiObject(new UiSelector().text("Apps"));
        //SettingsAppView.getContentDescription()
        assertTrue("Apps Setting is not available", SettingsAppView.exists());
        
        //Start moving all apps available in Downloaded tab
        
        UiScrollable AppsView=new UiScrollable(new UiSelector().resourceId("android:id/list"));
        
        //System.out.print("No of Child elements are:"+AppsView.getChildCount());
        UiObject MoveSDOption=new UiObject(new UiSelector().text("Move to SD card"));
        UiObject MoveSDOK=new UiObject(new UiSelector().text("OK"));
        UiObject MoveToTabletOption=new UiObject(new UiSelector().text("Move to tablet"));
        
        GetAppList getAppList=new GetAppList();
		String[] AppNameList=getAppList.GetApps();
		//int appCount=AppsView.getChildCount();
		int appCount=AppNameList.length-1;
		UiObject StorageUsed=new UiObject(new UiSelector().text("Storage used"));
    	UiObject ResSummary=new UiObject(new UiSelector().resourceId("android:id/summary"));
    	UiObject internalStorage=new UiObject(new UiSelector().text("Internal storage"));
    			//new UiObject(new UiSelector().text("Internal storage"));
    	//UiObject allButtons=new UiObject(new UiSelector().resourceId("com.android.settings:id/button"));
    	//UiObject changeStorageObject=allButtons.getChild(new UiSelector().text("Change"));
    	UiObject changeStorageObject=new UiObject(new UiSelector().text("Change"));
    
    	UiObject changeStorageView=new UiObject(new UiSelector().text("Change storage"));
    	UiObject storage_option_view=new UiObject(new UiSelector().className("android.widget.ListView"));
    	UiObject sdcard_option=storage_option_view.getChild(new UiSelector().index(1));
        for(int i=0; i<appCount; i++)
        {
        	System.out.println(AppNameList[i]+":Moving to sdcard");
        	//UiObject TestApp=AppsView.getChildByInstance(new UiSelector().className(android.widget.GridLayout.class.getName()), i);
        	///UiObject TestApp=new UiObject(new UiSelector().text(AppNameList[i]));
        	UiObject TestApp=scrollSettings.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()), AppNameList[i]);
        	//UiObject AppInSettingsName=TestApp.getChild(new UiSelector().resourceId("com.android.settings:id/app_name"));
        	//String AppName=AppInSettingsName.getText();
        	
        	TestApp.clickAndWaitForNewWindow();
        	UiObject AppStorage=new UiObject(new UiSelector().text("Storage"));
        	//(AppStorage.exists()!=true)?assertTrue("No Storage option available in ap Settings", AppStorage.exists()):i=1;
        	if(AppStorage.exists())
        	{
        		AppStorage.clickAndWaitForNewWindow();
        	}else{
        		assertTrue("No Storage option available in ap Settings",AppStorage.exists());
        	}
        		
        	
        	
        	//UiObject TestApp=changeStorageView.getChildByInstance(new UiSelector().className(android.widget.GridLayout.class.getName()), i);
        	if(StorageUsed.exists())
        	{
        		if(internalStorage.exists())
        		{
        			//Move app to sdcard
        			changeStorageObject.clickAndWaitForNewWindow();
        			if(changeStorageView.exists())
        			{
        				sdcard_option.clickAndWaitForNewWindow();
        				UiObject moveButton=new UiObject(new UiSelector().resourceId("com.android.settings:id/suw_navbar_next"));
        				moveButton.clickAndWaitForNewWindow();
        				
        				Long currentTime=System.currentTimeMillis();
                    	System.out.print("Moving...");
                		while(((System.currentTimeMillis())-currentTime)<100000)
                		{
                			//System.out.print("in loop" + currentTime);
                			System.out.print("..");
                			if(StorageUsed.exists())
                			{
                				//System.out.print("exists\n");
        	        			if(!internalStorage.exists())
        	        			{
        	        				System.out.print(AppNameList[i]+" App Successfully moved to SD card\n");
        	        				break;
        	        			}
                			}
                			SystemClock.sleep(5000);
                		
                		}
        			}
        		}
        		else
        		{
        			System.out.print(AppNameList[i]+": app already moved to SD card \n");
        		}
        	}
        	else
        	{
        		assertTrue("Seems, Sdcard is not inserted",StorageUsed.exists());
        	}
        	assertTrue(AppNameList[i]+" App failed to move to SD card", !internalStorage.exists());
        	
        	
        	
        	
        	/*
        	
        	assertTrue("Seems SD card is not inserted", MoveToTabletOption.exists());
        	if(MoveToTabletOption.isEnabled())
        	{
        		System.out.print(AppNameList[i]+":Move to Tablet Option is available, seems app already moved to SD card \n");
        	}
        	else
        	{
        		if(MoveSDOption.isEnabled())
        		{
        			MoveSDOption.clickAndWaitForNewWindow();
                	MoveSDOK.clickAndWaitForNewWindow();
                	
                	Long currentTime=System.currentTimeMillis();
                	System.out.print("Moving...");
            		while(((System.currentTimeMillis())-currentTime)<100000)
            		{
            			//System.out.print("in loop" + currentTime);
            			System.out.print("..");
            			if(MoveToTabletOption.exists() )
            			{
            				//System.out.print("exists\n");
    	        			if(MoveToTabletOption.isEnabled())
    	        			{
    	        				System.out.print(AppNameList[i]+" App Successfully moved to SD card\n");
    	        				break;
    	        			}
            			}
            			SystemClock.sleep(5000);
            		
            		}
            		assertFalse(AppNameList[i]+" App failed to move to SD card", MoveSDOption.isEnabled());
        		}
            	
        	}
        	*/

        	UiDevice.getInstance().pressBack();
        	UiDevice.getInstance().pressBack();
        }

	}

    
    
    
}
