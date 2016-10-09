package lab2;

public class StringTest {
	public static void main(String [ ] args) {
		String message;
		message = "Hello, world!\n";
		System.out.println(message);
		int theLength = message.length();
		System.out.println(theLength);
		
		char theChar = message.charAt(0);
		System.out.println(theChar);
		
		System.out.println(message.toUpperCase());
		System.out.println(message.substring(0, 5));
		System.out.println(message.replace('o', '*'));
	}
}
