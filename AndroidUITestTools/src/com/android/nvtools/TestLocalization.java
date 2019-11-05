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

public class TestLocalization extends UiAutomatorTestCase 
{
	
	public void testLocalization() throws UiObjectNotFoundException, RemoteException, IOException
	{
		UiObject mainframe=new UiObject(new UiSelector().className("android.widget.FrameLayout"));
		//System.out.println(mainframe.getChildCount()+":"+ mainframe.getChild(new UiSelector().index(0)).getClassName());
		System.out.println(mainframe.getChildCount()+":"+ mainframe.getClassName());
		FrameHandler(mainframe, mainframe.getChildCount());
		
		
	}
	private void FrameHandler(UiObject parentObject, int childCount) throws UiObjectNotFoundException
	{
		if(childCount<=0)
		{
			System.out.println("No Child found");
		}
		else
		{
			for(int i=0; i < childCount; i++)
			{
				String child_class= parentObject.getChild(new UiSelector().index(i)).getClassName();
				UiObject parentObject_next=parentObject.getChild(new UiSelector().className(child_class));
				int childCount_next=parentObject_next.getChildCount();
				System.out.println("FrameHandler"+i+"-"+child_class+"-"+childCount_next);
				runAsPerClass(child_class, parentObject_next, childCount_next);
				
			}
		}
	}
	private void ViewHandler(UiObject parentObject, int childCount) throws UiObjectNotFoundException
	{
		if(childCount<=0)
		{
			System.out.println("No Child found");
		}
		else
		{
			for(int i=0; i < childCount; i++)
			{
				//String child_class= parentObject.getChild(new UiSelector().index(i)).getClassName();
				//UiObject parentObject_next=parentObject.getChild(new UiSelector().className(child_class));
				UiObject parentObject_next=parentObject.getChild(new UiSelector().index(i));
				String child_class=parentObject_next.getClassName();
				int childCount_next=parentObject_next.getChildCount();
				System.out.println("ViewHandler"+i+"-"+child_class+"-"+childCount_next);
				runAsPerClass(child_class, parentObject_next, childCount_next);
				
			}
		}
	}
	private void TextViewHandler(UiObject parentObject, int childCount) throws UiObjectNotFoundException
	{
		//System.out.println("TextView Child count:"+childCount);
		if(childCount<=0)
		{
			System.out.println("No Child found");
		}
		else
		{
			for(int i=0; i < childCount; i++)
			{
				//System.out.println("undertextview with child");
				String child_class= parentObject.getChild(new UiSelector().index(i)).getClassName();
				UiObject parentObject_next=parentObject.getChild(new UiSelector().className(child_class));
				int childCount_next=parentObject_next.getChildCount();
				System.out.println("TextViewHandler"+i+"-"+child_class+"-"+childCount_next+"-"+childCount_next);
				runAsPerClass(child_class, parentObject_next, childCount_next);
				
			}
		}
		
	}
	private void ImageButtonHandler(UiObject parentObject, int childCount) throws UiObjectNotFoundException
	{
		if(childCount<=0)
		{
			System.out.println("No Child found");
		}
		else
		{
			for(int i=0; i < childCount; i++)
			{
				String child_class= parentObject.getChild(new UiSelector().index(i)).getClassName();
				UiObject parentObject_next=parentObject.getChild(new UiSelector().className(child_class));
				int childCount_next=parentObject_next.getChildCount();
				System.out.println("ImageButtonHandler"+i+"-"+child_class+"-"+childCount_next);
				runAsPerClass(child_class, parentObject_next, childCount_next);
				
			}
		}
	}
	private void RelativeLayoutHandler(UiObject parentObject, int childCount) throws UiObjectNotFoundException
	{
		if(childCount<=0)
		{
			System.out.println("No Child found");
		}
		else
		{
			for(int i=0; i < childCount; i++)
			{
				String child_class= parentObject.getChild(new UiSelector().index(i)).getClassName();
				UiObject parentObject_next=parentObject.getChild(new UiSelector().className(child_class));
				int childCount_next=parentObject_next.getChildCount();
				System.out.println("RelativeLayHandler"+i+"-"+child_class+"-"+childCount_next);
				runAsPerClass(child_class, parentObject_next, childCount_next);
				
			}
		}
	}
	private void CheckBoxHandler(UiObject parentObject, int childCount) throws UiObjectNotFoundException
	{
		if(childCount<=0)
		{
			System.out.println("No Child found");
		}
		else
		{
			for(int i=0; i < childCount; i++)
			{
				String child_class= parentObject.getChild(new UiSelector().index(i)).getClassName();
				UiObject parentObject_next=parentObject.getChild(new UiSelector().className(child_class));
				int childCount_next=parentObject_next.getChildCount();
				System.out.println("CheckBoxHandler"+i+"-"+child_class+"-"+childCount_next);
				runAsPerClass(child_class, parentObject_next, childCount_next);
				
			}
		}
	}
	private void ListViewHandler(UiObject parentObject, int childCount) throws UiObjectNotFoundException
	{
		if(childCount<=0)
		{
			System.out.println("No Child found");
		}
		else
		{
			for(int i=0; i < childCount; i++)
			{
				String child_class= parentObject.getChild(new UiSelector().index(i)).getClassName();
				UiObject parentObject_next=parentObject.getChild(new UiSelector().className(child_class));
				int childCount_next=parentObject_next.getChildCount();
				System.out.println("ListViewHandler"+i+"-"+child_class+"-"+childCount_next);
				runAsPerClass(child_class, parentObject_next, childCount_next);
				
			}
		}
	}
	private void LinearLayoutHandler(UiObject parentObject, int childCount) throws UiObjectNotFoundException
	{
		if(childCount<=0)
		{
			System.out.println("No Child found");
		}
		else
		{
			for(int i=0; i < childCount; i++)
			{
				//String child_class= parentObject.getChild(new UiSelector().index(i)).getClassName();
				//UiObject parentObject_next=parentObject.getChild(new UiSelector().className(child_class));
				UiObject parentObject_next=parentObject.getChild(new UiSelector().index(i));
				String child_class=parentObject_next.getClassName();
				int childCount_next=parentObject_next.getChildCount();
				System.out.println("LinearLayHandler"+i+"-"+child_class+"-"+childCount_next);
				runAsPerClass(child_class, parentObject_next, childCount_next);
				
			}
		}
	}
	private void runAsPerClass(String classname, UiObject parent_, int childCount_) throws UiObjectNotFoundException
	{
		switch(classname)
		{
		case "android.widget.FrameLayout" :
			FrameHandler(parent_, childCount_);
			break;
		case "android.view.View":
			ViewHandler(parent_, childCount_);
			break;
		case "android.widget.ImageButton":
			ImageButtonHandler(parent_, childCount_);
			break;
		case "android.widget.TextView":
			//System.out.println("Calling TextView, childCount is:"+childCount_);
			TextViewHandler(parent_, childCount_);
			break;
		case "android.widget.RelativeLayout":
			RelativeLayoutHandler(parent_, childCount_);
			break;
		case "android.widget.LinearLayout":
			LinearLayoutHandler(parent_, childCount_);
			break;
		case "android.widget.ListView":
			ListViewHandler(parent_, childCount_);
			break;
		case "android.widget.CheckBox":
			CheckBoxHandler(parent_, childCount_);
			break;
		default:
			System.out.println("Classname not listed");
			break;
		}
	}
}
