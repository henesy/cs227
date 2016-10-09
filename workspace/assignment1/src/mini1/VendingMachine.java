package mini1;

public class VendingMachine extends java.lang.Object {
	/**
	 * The number of quarters in the machine
	 */
	private int quarters;
	
	/**
	 * The number of dimes in the machine
	 */
	private int dimes;
	
	/**
	 * The number of nickels in the machine
	 */
	private int nickels;
	
	/**
	 * The total number of purchases made from the machine
	 */
	private int purchases;
	
	/**
	 * The balance of the current transaction in place within the machine
	 */
	private int balance;
	
	/**
	 * Constructs an object VendingMachine which extends java.lang.Object and takes in values for the amount of coins to be pre-loaded
	 * @param givenQuarters
	 * The given number of quarters in the machine
	 * @param givenDimes
	 * The given number of dimes in the machine
	 * @param givenNickels
	 * The given number of nickels in the machine
	 */
	public VendingMachine(int givenQuarters, int givenDimes, int givenNickels) {
		quarters = givenQuarters;
		dimes = givenDimes;
		nickels = givenNickels;
		purchases = 0;
		balance = 0;
	}
	
	/**
	 * Returns the balance of this machine (amount of money currently available to purchase something).
	 * @return
	 * The balance of the transaction in cents
	 */
	public int getBalance() {
		return balance;
	}
	
	/**
	 * Returns the total number of items that have ever been purchased from this machine.
	 * @return
	 * The total purchases made on the machine
	 */
	public int getCount() {
		return purchases;
	}
	
	/**
	 * Returns the number of dimes in this machine.
	 * @return
	 * The total number of dimes
	 */
	public int getDimes() {
		return dimes;
	}
	
	/**
	 * Returns the number of nickels in this machine.
	 * @return
	 * The number of nickels
	 */
	public int getNickels() {
		return nickels;
	}
	
	/**
	 * Returns the number of quarters in this machine.
	 * @return
	 * The number of quarters
	 */
	public int getQuarters() {
		return quarters;
	}
	
	/**
	 * Returns the total value of all coins in this machine.
	 * @return
	 * The value of the contained coins in cents
	 */
	public int getTotalValue() {
		return 25*quarters + 10*dimes + 5*nickels;
	}
	
	/**
	 * Adds the given number of dimes to this machine and increases the balance by the combined value of the coins.
	 * @param howmany
	 * The number of dimes to insert
	 */
	public void insertDimes(int howmany) {
		dimes += howmany;
		balance += 10*howmany;
	}
	
	/**
	 * Adds the given number of nickels to this machine and increases the balance by the combined value of the coins.
	 * @param howmany
	 * The number of nickels to add
	 */
	public void insertNickels(int howmany) {
		nickels += howmany;
		balance += 5*howmany;
	}
	
	/**
	 * Adds the given number of quarters to this machine and increases the balance by the combined value of the coins.
	 * @param howmany
	 * The number of quarters to add
	 */
	public void insertQuarters(int howmany) {
		quarters += howmany;
		balance += 25*howmany;
	}
	
	/**
	 * Simulates purchasing an item using the current balance.
	 * @param cost
	 * The cost of the item being purchased
	 */
	public void purchase(int cost) {
		makeChange(balance - cost);
		purchases++;
	}
	
	/**
	 * Reduces balance to zero, and reduces the nickels, dimes, and quarters by the amount of the current balance, using the same algorithm that is used for returning change.
	 */
	public void cancel() {
		makeChange(balance);
	}
	
	/**
	 * Makes and modifies change and sets balance to 0
	 * @param targ
	 * The target number to decrement from
	 */
	private void makeChange(int target) {
		int desiredQuarters = target / 25;
		int numQuarters = Math.min(quarters, desiredQuarters);
		int amnt = target - 25*numQuarters;
		
		int desiredDimes = amnt / 10;
		int numDimes = Math.min(dimes, desiredDimes);
		amnt -= 10*numDimes;
		
		int desiredNickels = amnt / 5;
		int numNickels = Math.min(nickels, desiredNickels);
		amnt -= 5*numNickels;
		
		quarters -= numQuarters;
		dimes -= numDimes;
		nickels -= numNickels;
		
		balance = 0;
	}
	
	
	/**
	 * Returns a short string representation of the number of coins in this machine, in the form "q, d, n", where q is the number of quarters, d is the number of dimes, and n is the number of nickels.
	 * @return
	 * A string of coins in q, d, n format
	 */
	public java.lang.String toString() {
		return quarters + ", " + dimes + ", " + nickels;
	}
	
}
