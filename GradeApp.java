package edu.ilstu;

import java.io.*;

public class GradeApp extends Thread {

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		

		MainGUIScreen screen = new MainGUIScreen();
		screen.buildScreen();
		while (screen.getXMLFile() == null) 
		{
			try 
			{
				sleep(2);
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}

		FileInputStream fin = null;
		try {
			Course processingCourse;
			fin = new FileInputStream(screen.getXMLFile());
			System.out.println("after XML loading");
			processingCourse = CourseXMLParser.readCourse(fin);
			screen = new MainGUIScreen();
			screen.buildScreen();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}

	}
}
