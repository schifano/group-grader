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
		
		}

	}
