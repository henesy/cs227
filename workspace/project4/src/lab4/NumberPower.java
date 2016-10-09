package lab4;
import java.util.Scanner;

public class NumberPower {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		
		int num1 = getNextNumber(in);
		int num2 = getNextNumber(in);
		int res = (int) Math.pow(num1, num2);
		
		System.out.printf("%d ^ %d = %d", num1, num2, res);
		
	}

	private static int getNextNumber(Scanner scanner) {
		System.out.print("Enter a number: ");
		int num;
		if (scanner.hasNextInt()) {
			num = scanner.nextInt();
		} else {
			num = 1;
			scanner.next();
		}
		
		return num;
	}
}
