package edu.ilstu;

import java.io.*;

public class GradeApp extends Thread {

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		System.out.println("in main");
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

	}
}
