package edu.ilstu;



import java.io.*;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;


/**
 * This program shows a simple example of writing a parser in Java to parse an XML file.
 * Run this program and step through it to learn how to use it
 * @author ashesh
 *
 */

public class CourseXMLParser
{
	public static Course readCourse(InputStream in)
	{
		SAXParserFactory factory = SAXParserFactory.newInstance(); 
		SAXParser parser=null;
		Course course = null;
		try {
			parser = factory.newSAXParser();
		} 
		catch (ParserConfigurationException e) 
		{	
			e.printStackTrace();
			return null;
		} 
		catch (SAXException e) 
		{
			e.printStackTrace();
			return null;
		}
		CourseXMLHandler handler = new CourseXMLHandler();
		

		try 
		{
			parser.parse(in,handler); //this method call starting the parsing
			//exceptions will be thrown if the file is not valid XML
		} 
		catch (SAXException e) 
		{
			e.printStackTrace();
			return null;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return null;
		}
		
		//get the number of tags read from the handler, and print it
		course = handler.getCourse();
		return course;
	}
}


class CourseXMLHandler extends DefaultHandler 
{
	
	private String data;
	private Stack<String> names;
	private Course course;
	private int numTags; //as an example to have the handler return something
	private int status; //0 is default, 1 is evaluated, 2 is estimated
	
	
	/**
	 * This standard method (which is overridden here) is called before the first tag is read. Initialize stuff here.
	 */
	@Override
	public void startDocument()
	{
		data = "";
		course = null;
		names = new Stack<String>();
		status = 0;
	}
	
	/**
	 * This standard method (which is overridden here) is called after the last tag has been read. Finish up here.
	 */
	@Override
	public void endDocument()
	{
		System.out.println("-----Parsing has ended------");
	}
	
	/**
	 * This standard method (which is overridden here) is called every time a starting tag is read, including single tags
	 * You can look for specific tags and do some processing
	 */
	@Override
	public void startElement(String uri,String localName,String qName,Attributes attributes)
	{
		if (qName.equals("category"))
		{
			String name = "";
			for (int i=0;i<attributes.getLength();i++)
			{
				if (attributes.getQName(i).equals("name"))
				{
					name = attributes.getValue(i);					
				}
			}
			if (name.length()>0)
			{
				//add a new Category object to the current top of stack
				if (names.isEmpty())
						course = Course.getInstance(name);
				else
					course.addCategory(names.peek(),new Category(name));
				names.push(name);
			}			
		}
		else if (qName.equals("course"))
		{
			String name = "";
			for (int i=0;i<attributes.getLength();i++)
			{
				if (attributes.getQName(i).equals("name"))
				{
					name = attributes.getValue(i);					
				}
			}
			if (name.length()>0)
			{
				//create the course
				course = Course.getInstance(name);
				names.push(name);
			}			
		}
		else if (qName.equals("item"))
		{
			String name="";
			double outOf=0,weight=0;
			
			for (int i=0;i<attributes.getLength();i++)
			{
				if (attributes.getQName(i).equals("name"))
				{
					name = attributes.getValue(i);					
				}
				else if (attributes.getQName(i).equals("outof"))
				{
					outOf = Double.parseDouble(attributes.getValue(i));
				}
				else if (attributes.getQName(i).equals("weight"))
				{
					weight = Double.parseDouble(attributes.getValue(i));
				}
			}
			
			course.addItem(names.peek(),new GradeItem(name,weight,outOf));
			names.push(name);
		}	
		else if (qName.equals("points"))
		{
			
			for (int i=0;i<attributes.getLength();i++)
			{
				if (attributes.getQName(i).equals("status"))
				{
					String value = attributes.getValue(i);
					if (value.equals("evaluated"))
						status = 1;
					else if (value.equals("estimated"))
						status = 2;
				}
			}
			data = "";
		}
	}
		
	/**
	 * This standard method (which is overridden here) is called every time a starting tag is read, including single tags
	 * You can look for specific tags and do some processing
	 */
	@Override
	public void endElement(String uri,String localName,String qName)
	{
		if (qName.equals("points"))
		{
			float points = Float.parseFloat(data);
			
			if (status == 1)
			{
				course.setEvaluatedPoints(names.peek(),points);
			}
			else if (status == 2)
			{
				course.setEstimatedPoints(names.peek(), points);
			}
		}
		else if (qName.equals("item"))
		{
			names.pop();
		}
		else if (qName.equals("category"))
		{
			names.pop();
		}

	}
	
	 public void characters(char ch[], int start, int length) throws SAXException 
	 {
		 if (data.length()>0)
			 data = data + "" + new String(ch,start,length);
		 else
			 data = new String(ch,start,length);
	 }
	
	 public Course getCourse()
	 {
		 return course;
	 }
	

}
