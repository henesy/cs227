package lab8;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class LineNumberer
{
  public static void main(String[] args) throws FileNotFoundException
  {
    Scanner in = new Scanner(System.in);
    File inFile = new File("U:/cs227/workspace/project7/src/lab7/Deck.java");
	Scanner scanner = new Scanner(inFile);
    //PrintWriter scanner = new PrintWriter(inFile);
    File outFile = new File("lnumberer_text.txt");
    PrintWriter out = new PrintWriter(outFile);
    int lineCount = 1;

    
    if (outFile.exists())
    {
      System.out.print("File already exists, ok to overwrite (y/n)? ");
      if (!in.nextLine().startsWith("y"))
      {
    	scanner.close();
    	out.close();
    	in.close();
        return;
      }
    }
    
    while (scanner.hasNextLine())
    {
      String line = scanner.nextLine();
      line = lineCount + " " + line;
      //System.out.println(line);
      out.println(line);
      lineCount += 1;
    }
    
    out.close();
    scanner.close();
    in.close();
    System.out.println("Done");
  }
}
