package lab8;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LineNumberer2
{
  public static void main(String[] args) throws FileNotFoundException
  {
    File file = new File("story.txt");
    Scanner scanner = new Scanner(file);
    int lineCount = 1;

    while (scanner.hasNextLine())
    {
      String line = scanner.nextLine();
      System.out.print(lineCount + " ");
      System.out.println(line);
      lineCount += 1;
    }
    scanner.close();
  }
}