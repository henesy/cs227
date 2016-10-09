package lab6;
/**
 * This class models a piggy bank.  A piggy bank can hold a
 * fixed maximum number of coins.  Once the number of coins reaches
 * the maximum, the piggy bank is full and no more coins can be added.
 * It is possible to <em>shake</em> the piggy bank to remove a single coin,
 * or you can <em>smash</em> it to get all the coins out at once.
 * However, once it has been smashed, you can no longer put any coins in it.
 */
public class PiggyBank
{
  private int maxCoins;
  private int coins;
  private boolean busted;
  
  /**
   * Constructs an empty <code>PiggyBank</code> that can hold
   * the given maximum number of coins.
   * @param maxCoins
   *   maximum number of coins this <code>PiggyBank</code> can hold.
   */
  public PiggyBank(int maxCoins)
  {
    this.maxCoins = maxCoins;
  }
  
  /**
   * Adds a coin to this <code>PiggyBank</code> if possible; 
   * does nothing if it is already full or smashed.
   */
  public void addCoin()
  {
    if (coins < maxCoins && !busted)
    {
      coins += 1;
    }
  }
  
  /**
   * Returns the number of coins in this <code>PiggyBank</code>.
   * @return
   *   number of coins
   */
  public int getNumCoins()
  {
    return coins;
  }
  
  /**
   * Returns true if this <code>PiggyBank</code> is full.
   * @return
   *   true if full, false otherwise
   */  
  public boolean isFull()
  {
    return coins == maxCoins;
  }
  
  /**
   * Returns true if this <code>PiggyBank</code> is smashed.
   * @return
   *   true if smashed, false otherwise
   */
  public boolean isSmashed()
  {
    if (busted == true)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  
  /**
   * Smashes this <code>PiggyBank</code> and returns the
   * number of coins that it contained.
   * @return
   *   number of coins before being smashed
   */
  public int smash()
  {
    int temp = coins;
    coins = 0;
    busted = true;
    return temp;
  }
}
