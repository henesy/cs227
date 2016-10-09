package lab2;
/**
 * Model of an Atom for, assumedly, simulation purposes
 */
public class Atom {
	/**
	 * The number of protons, neutrons, and electrons within the Atom
	 */
	private int protons, neutrons, electrons;
	
	/**
	 * Constructs a basic atom from the given values for protons, neutrons, and electrons
	 * @param givenProtons, givenNeutrons, givenElectrons
	 * the number of a given molecule within the Atom
	 */
	public Atom(int givenProtons, int givenNeutrons, int givenElectrons) {
		protons = givenProtons;
		neutrons = givenNeutrons;
		electrons = givenElectrons;
	}
	
	/**
	 * Calculates the Atom's atomic mass
	 * @return 
	 * the number of protons and neutrons added to evaluate atomic mass
	 */
	public int getAtomicMass() {
		return protons + neutrons;
	}
	
	/**
	 * Calculates the Atom's atomic charge
	 * @return
	 * the charge calculated by subtracting electrons from protons
	 */
	public int getAtomicCharge() {
		return protons - electrons;
	}
	
	/**
	 * Reduces the proton and neutron Atom values by 2
	 */
	public void decay() {
		protons -= 2;
		neutrons -= 2;
	}
	
}
