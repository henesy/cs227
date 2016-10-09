package lab3;
import java.util.Random;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String s = "Hello";
		//char c = s.charAt(10);
		//System.out.println(s);
		//part 1
		System.out.printf("The sequence's millionth # is: %d\n", 1000000 % 7);
		//part 3
		String s = "42";
		int x = Integer.parseInt(s);
		System.out.println(x);
		/*String s2 = "beepboop";
		int x2 = Integer.parseInt(s2);
		System.out.println(x2);*/
		//part 4
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		int tst = Integer.MAX_VALUE;
		tst++;
		System.out.println(tst);
		//part 5
		Random rand = new Random(137);
		System.out.println(rand.nextInt(6));
		System.out.println(rand.nextInt(6));
		System.out.println(rand.nextInt(6));
		System.out.println(rand.nextInt(6));
	}

}
