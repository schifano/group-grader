package edu.ilstu;

import java.io.*;

public class GradeApp extends Thread
{
	

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		Course processingCourse;

		MainGUIScreen screen = new MainGUIScreen();
		while(screen.getXMLFile() == null)
		{	
		System.out.println("looping");
		; // Do nothing to avoid null file exception	
		}
		
			FileInputStream fin = null;
			try 
			{
				fin = new FileInputStream(screen.getXMLFile());
				System.out.println("after XML loading");
				processingCourse = CourseXMLParser.readCourse(fin);
				System.out.println(processingCourse);
			} 
			catch (FileNotFoundException e) 
			{
				// TODO Auto-generated catch block
				System.out.println("File not found");
			}
			
	}
}
