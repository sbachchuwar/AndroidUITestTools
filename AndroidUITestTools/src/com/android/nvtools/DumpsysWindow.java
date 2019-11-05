package com.android.nvtools;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.RemoteException;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class DumpsysWindow extends UiAutomatorTestCase 
{
	public void testDumpsys() throws UiObjectNotFoundException, RemoteException, IOException
	{
		RunOnShell getWindow=RunOnShell.runcmd("Clear Settings App from Screen", "dumpsys window".split("\\s+"), 2000);
		//System.out.print("output:"+getWindow.cmdOutput);
		Pattern pattern=Pattern.compile("\n");
		Pattern focpatt=Pattern.compile("mCurrentFocus");
		String[] PackageName=pattern.split(getWindow.cmdOutput);
		//BufferedReader r = new BufferedReader(PackageName);
		for(String each:PackageName)
		{
			Matcher m = focpatt.matcher(each);
			//System.out.print("\napp:"+each+"\n");
			while (m.find()) {
		        // Simplest method:
		        // System.out.println(m.group(0));

		        // Get the starting position of the text
		        int start = m.start(0);
		        // Get ending position
		        int end = m.end(0)+20;
		        // Print whatever matched.
		        // Use CharacterIterator.substring(offset, end);
		        //System.out.println(each.substring(start, end));
		        
		       System.out.println(m.group(1));
		      }
		}
	}
}
