package lab2;

public class AtomTest {
	public static void main(String [ ] args) {
		Atom u238 = new Atom(92, 146, 92);
		
		System.out.println(u238.getAtomicCharge());
		System.out.println(u238.getAtomicMass());
		
		u238.decay();
		
		System.out.println(u238.getAtomicCharge());
		System.out.println(u238.getAtomicMass());	
	}
}
