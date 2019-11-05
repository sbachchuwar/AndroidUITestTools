package com.android.nvtools;

import java.io.IOException;

import android.os.RemoteException;
import android.os.SystemClock;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class FormatAsInternal extends UiAutomatorTestCase 
{
	public void testInternalFormat() throws UiObjectNotFoundException, IOException, RemoteException
	{
		
UiWatcher okCancelWatcher = new UiWatcher() {
    		
    		@Override
    		public boolean checkForCondition() {
    			UiObject ErrorPop=new UiObject(new UiSelector().packageName("android"));
    			if(ErrorPop.exists())
    			{
    				UiObject okButton=new UiObject(new UiSelector().className("android.widget.Button").text("OK"));
    				System.out.println("Some Error has been poped up:");
    				try
    				{
    					UiObject androidPackageError=ErrorPop.getChild(new UiSelector().className("android.widget.TextView"));
    	    			String Error=androidPackageError.getText();
    	    			System.out.println("Error:"+Error);
    	    			okButton.clickAndWaitForNewWindow();
    				}
    				catch (UiObjectNotFoundException e) 
    				{
    					// TODO: handle exception
    					e.printStackTrace();
    				}
    				return (ErrorPop.waitUntilGone(25000));
    			}
    			// TODO Auto-generated method stub
    			return false;
    		}
    	};

    	UiDevice.getInstance().registerWatcher("okCancelWatcher", okCancelWatcher);
    	UiDevice.getInstance().runWatchers();
		UiObject welcome_cling=new UiObject(new UiSelector().resourceId("com.android.launcher3:id/cling_dismiss_longpress_info"));
		if(welcome_cling.exists())
			welcome_cling.clickAndWaitForNewWindow();
		int tries=3;
		//UiObject slowSDWarn=new UiObject(new UiSelector().text("SD card appears to be slow"));
		
    
		UiDevice.getInstance().pressHome();
		UiObject launcherView=new UiObject(new UiSelector().packageName("com.android.launcher3"));
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
        UiObject storageSetting=scrollSettings.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()), "Storage & USB");
        
        storageSetting.clickAndWaitForNewWindow();
        UiObject portableStorage=new UiObject(new UiSelector().text("Portable storage"));
        UiObject Sd_card=new UiObject(new UiSelector().textContains("card"));
        UiObject more_option=new UiObject(new UiSelector().descriptionContains("More options"));
        UiObject sd_settings=new UiObject(new UiSelector().text("Settings"));
        UiObject format_as_internal=new UiObject(new UiSelector().text("Format as internal"));
        if(Sd_card.exists())
        {
        	if(portableStorage.exists())
            {
            	System.out.println("Currently Sd card is formated as portable, Formatting as Internal");
            	
            	Sd_card.clickAndWaitForNewWindow();
            	
            	more_option.click();
            	
            	sd_settings.clickAndWaitForNewWindow();
            	
            	format_as_internal.clickAndWaitForNewWindow();
            	
            	 UiObject next=new UiObject(new UiSelector().text("Next"));
             	UiObject unsupported=new UiObject(new UiSelector().text("Unsupported"));
                 UiObject eraseFormat=new UiObject(new UiSelector().text("Erase & format"));
             	eraseFormat.clickAndWaitForNewWindow();
             	System.out.print("Erasing sdcard..");
             	
             	UiObject moveDataToStorage=new UiObject(new UiSelector().text("Move data to new storage"));
             	//wait for 100 seconds
             	Long currentTime=System.currentTimeMillis();
             	UiObject slowSDWarn=new UiObject(new UiSelector().resourceId("android:id/message"));
             	UiObject okButton=new UiObject(new UiSelector().text("OK"));
         		while(((System.currentTimeMillis())-currentTime)<100000)
         		{
         			//System.out.print("in loop" + currentTime);
         			System.out.print("..");
         			if(moveDataToStorage.exists())
         			{
         				UiObject moveLater=new UiObject(new UiSelector().text("Move later"));
         				moveLater.click();
         				next.clickAndWaitForNewWindow();
         				UiObject done=new UiObject(new UiSelector().text("Done"));
         				done.clickAndWaitForNewWindow();
             				//System.out.print("\n");
         				
             				break;
             			
         			}

         			
         			//if(slowSDWarn.exists() & slowSDWarn.getText().contains("SD card appears to be slow"));
         			if(slowSDWarn.exists())
         			{
         				System.out.println("Slow SD warning appeared");
         				okButton.clickAndWaitForNewWindow();
         			}
         			SystemClock.sleep(5000);
         		
         		}
         		Sd_card.clickAndWaitForNewWindow();
            	//UiObject more_option=new UiObject(new UiSelector().descriptionContains("More options"));
            	more_option.click();
            	//UiObject sd_settings=new UiObject(new UiSelector().text("Settings"));
            	//sd_settings.clickAndWaitForNewWindow();
            	UiObject format_as_portable=new UiObject(new UiSelector().text("Format as portable"));
            	if(!portableStorage.exists() & Sd_card.exists())
            	{
            		System.out.println("SD card formatted as Internal Successfully");
            	}
            	else
            	{
            		assertFalse( "Failed to format as Internal",!format_as_portable.exists());
            	}
            }
        	else
        	{
        		System.out.println("Sdcard is already formatted as Internal ");
        	}
            }
        	
        }
        
       
		//assertTrue("Unable to Move further on Setting SDcard", !portableStorage.exists() );
		
		


    	

 
        
        
        
	
	}

