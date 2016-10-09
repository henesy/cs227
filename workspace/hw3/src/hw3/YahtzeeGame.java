package hw3;
/**
 * @author Sean Hinchee
 * Code structure is based around the idea that there should be
 * one core base class by which many simple methods are fulfilled,
 * additionally, there was considered a form of superclass for the all but X,
 * however, this was discarded as no clear implementation that truly  saved code
 * and satisfied personal preference was found.
 */
import java.util.ArrayList;

import hw3.api.Category;

/**
 * Game state for a dice game such as Yahtzee. The game consists
 * of a list of <code>Category</code> objects, each of which is responsible
 * for keeping track of the dice used to satisfy it and of its own 
 * contribution to the total score. Clients interact directly with the
 * category objects, which are obtained using method <code>getCategories()</code>.
 * The total score for the game may be obtained via the <code>getScore</code>
 * method.  This class also keeps track of several game
 * attributes: the number of dice being used in the game, the maximum
 * value (number of "sides") of the dice, and the number of times the
 * dice may be rerolled in each round.
 */
public class YahtzeeGame
{
  private int score;
  
  private int numDice;
  
  private int maxDieValue;
  
  private int numRolls;
  
  private ArrayList<Category> cats;
	
  
  /**
   * Constructs a new YahtzeeGame based on the given parameters.  
   * Initially the list of categories is empty.
   * @param numDice
   *   number of dice used in this game
   * @param maxDieValue
   *   maximum face value (number of faces) for each die
   * @param numRolls
   *   number of times the dice can be rolled in each round
   */
  public YahtzeeGame(int numDice, int maxDieValue, int numRolls) 
  {
    // TODO
	score = 0;
	this.numDice = numDice;
	this.maxDieValue = maxDieValue;
	this.numRolls = numRolls;
	cats = new ArrayList<Category>();
  }
  
  /**
   * Adds a scoring category to this game.
   * @param category
   *   scoring category to add
   */
  public void addCategory(Category category)
  {
    // TODO
	cats.add(category);
  }
  
  
  /**
   * Returns the list of categories in this game.
   * @return
   *   list of categories 
   */
  public ArrayList<Category> getCategories()
  {
    // TODO
    return cats;
  }
  
  /**
   * Returns a new Hand corresponding to the number of dice,
   * maximum die value, and number of rolls for this game.
   * Initially all dice in the hand are available to be rolled.
   * @return
   *   new Hand based on this game's parameters
   */
  public Hand createNewHand()
  {
    // TODO
	Hand h = new Hand(numDice, maxDieValue, numRolls);
    return h;
  }
  
  /**
   * Returns the current total score for all categories.
   * @return
   *   total score for all categories
   */
  public int getScore()
  {
    // TODO
    return score;
  }

}
