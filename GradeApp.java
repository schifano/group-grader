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
		Thread delay = new Thread();
        delay.start();

		MainGUIScreen screen = new MainGUIScreen();
		while(screen.getXMLFile() == null)
		{
			System.out.println("Sitting on the dock of the bay");
			System.out.println("watching the CPU cycles roll away, oooh");
			
		}
		System.out.println("it worked");

			FileInputStream fin = null;
			try 
			{
				System.out.println("Check 4");
				fin = new FileInputStream(screen.getXMLFile());
				System.out.println("Check 5");
				processingCourse = CourseXMLParser.readCourse(fin);
				System.out.println("Check 6");
				System.out.println(processingCourse);
			} 
			catch (FileNotFoundException e) 
			{
				// TODO Auto-generated catch block
				System.out.println("File not found");
			}
			
	}
}
