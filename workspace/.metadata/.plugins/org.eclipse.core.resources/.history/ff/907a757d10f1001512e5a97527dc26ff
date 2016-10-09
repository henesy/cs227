package lab8;
import java.awt.Point;
import plotter.Plotter;
import plotter.Polyline;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;


public class LinePlotter {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		ArrayList<Polyline> lines = readFile("hello.txt");
		int i;
		Plotter plotter = new Plotter();
		
		for(i = 0; i < lines.size(); i++) {
		    plotter.plot(lines.get(i));
		}
	}
	
	/**
	 * Processes one line and returns a Polyline
	 * @param line
	 * @return
	 */
	private static Polyline procPoly(String line) {
		Scanner words = new Scanner(line);
		words.useDelimiter(" ");
		int width = 1;
		String color;
		
		//if present, set width of line
		if(words.hasNextInt()) {
			width = words.nextInt();
		}
		
		//set color
		color = words.next();
		
		Polyline thisPoly = new Polyline(color, width);
		
		//read in all of the points;
		while(words.hasNextInt()) {
			int a = words.nextInt();
			int b = words.nextInt();
			thisPoly.addPoint(new Point(a, b));
		}
		return thisPoly;
	}
	
	/**
	 * Reads a file and produces an array of polylines from the file
	 * @param filename
	 * @return
	 * @throws FileNotFoundException
	 */
	private static ArrayList<Polyline> readFile(String filename) throws FileNotFoundException{
		// open the file
	    File file = new File(filename);    
	    Scanner scanner = new Scanner(file);
	    ArrayList<Polyline> lst = new ArrayList<Polyline>();  
	    
	    // while there are more lines...
	    while (scanner.hasNextLine())
	    {
	      // get the next line
	      String line = scanner.nextLine();
	      line = line.trim();
	      
	      // process the line
	      if(line.length() > 0 && !line.startsWith("#")) {
	    	  lst.add(procPoly(line));
	      }
	      
	    }
	    
	    // close the file
	    scanner.close();
	    return lst;
	}

}
