package com.android.nvtools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.os.SystemClock;

public class RunOnShell {

	int ExitValue;
	String cmdOutput;
	public RunOnShell(int ExitValue, String cmdOutput)
	{
		this.ExitValue=ExitValue;
		this.cmdOutput=cmdOutput;
		
	}
	
	 public String getCmdOutput()
	    {
	    	return cmdOutput;
	    }
	    
	    public int getExitValuet()
	    {
	    	return ExitValue;
	    }
	
	public static RunOnShell runcmd(String cmdName, String[] cmd, int timeout) throws IOException
	{
		// TODO Auto-generated method stub
		StringBuffer outputBuffer = new StringBuffer();
		Process process=Runtime.getRuntime().exec(cmd); //executes the command
		int exitStatus=-1;
		BufferedReader stdoutReader=new BufferedReader(new InputStreamReader(process.getInputStream())); //Reads output of the process
		//bufferedReader.readLine();
		String line = "";
	    while ((line = stdoutReader.readLine())!= null) 
	    {
	    	outputBuffer.append(line);
	    }
	    //System.out.print(cmdName+":\n"+outputBuffer);
	    
	    
	    while(timeout!=0)
	    {
	    	try
	    	{
	    		exitStatus=process.exitValue(); //If Succeed timeout will exit but if fail it will get caught in Catch & while will continue
	    		break;
	    	}
	    	catch (IllegalThreadStateException e) 
	    	{
				// TODO: handle exception
	    		SystemClock.sleep(500);
	    		timeout-=500;
			}
	    }
	    
	  //If Process doesn't end by itself, just destroy it
	    try{
	    	process.exitValue();
	    }
	    catch (IllegalThreadStateException e) 
	    {
			// TODO: handle exception
	    	process.destroy();
		}
		
		//int ExitStatus=1;
		String output=outputBuffer.toString();
		RunOnShell OuputObj=new RunOnShell(exitStatus, output);
		return OuputObj;
	}

}
