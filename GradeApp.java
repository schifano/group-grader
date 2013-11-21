package edu.ilstu;

import java.io.FileNotFoundException;


public class GradeApp extends Thread {

	/**
	 * This class will start all the processing for the user to 
	 * enter and store grades into a Course. 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		MainGUIScreen screen = new MainGUIScreen();
		screen.buildScreen();
		
		}

	}
