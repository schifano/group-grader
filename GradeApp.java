package edu.ilstu;

import java.io.*;

public class GradeApp extends Thread
{
	private Course processingCourse;

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		Thread delay = new Thread();
        delay.start();

		MainGUIScreen screen = new MainGUIScreen();
		synchronized(delay)
		{
			try
			{
				delay.wait();
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		{
			FileInputStream fin = null;

			
			try 
			{
				fin = new FileInputStream(screen.getXMLFile());
				processingCourse = CourseXMLParser.readCourse(fin);
			} 
			catch (FileNotFoundException e) 
			{
				// TODO Auto-generated catch block
				System.out.println("File not f)ound")
			}
			
			System.out.println(course);
		}

	}
}
