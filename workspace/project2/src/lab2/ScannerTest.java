package lab2;
import java.util.Scanner;

public class ScannerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner parser = new Scanner("Uranium,U,92,92,146");
		parser.useDelimiter(",");

		// Skip over first two fields.  Notice how we can just ignore the value
		// returned by the next() method?
		parser.next();
		parser.next();
		int protonCount = parser.nextInt();
		int electronCount = parser.nextInt();
		int neutronCount = parser.nextInt();

		Atom u238 = new Atom(protonCount, neutronCount, electronCount);
		parser.close();
		System.out.println(u238.getAtomicMass());
	}

}
