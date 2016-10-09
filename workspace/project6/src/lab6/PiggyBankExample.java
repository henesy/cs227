package lab6;

public class PiggyBankExample
{

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    PiggyBank piggy = new PiggyBank(5);
    piggy.addCoin();
    piggy.addCoin();
    System.out.println("Should contain 2 coins: " + piggy.getNumCoins());
    System.out.println("Should not be smashed: " + piggy.isSmashed());
    piggy.addCoin();
    System.out.println("Should now contain 3 coins: " + piggy.getNumCoins());
  }

}