package lab4;
import java.util.Scanner;

public class Multiplier
{
  public static void main(String[] args)
  {
    // Create one scanner to read from System.in
    Scanner scanner = new Scanner(System.in);
	
    // Use the helper method to prompt and return the inputs
    int first = getNextNumber(scanner);
    int second = getNextNumber(scanner);
	
    int result = first * second;
    System.out.println(first + " times " + second + " is " + result);
  }
  
  // Helper method
  private static int getNextNumber(Scanner scanner)
  {
    System.out.print("Enter a number: ");
    int next;
    if (scanner.hasNextInt()) {
    	next = scanner.nextInt();
    } else {
    	next = 1;
    }
    return next;
  }
}