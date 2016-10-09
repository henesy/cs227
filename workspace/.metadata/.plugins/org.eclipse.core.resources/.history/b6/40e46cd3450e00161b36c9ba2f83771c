

import hw3.AllButOneOfAKind;
import hw3.AllButTwoOfAKind;
import hw3.AllOfAKind;
import hw3.Chance;
import hw3.CountOccurrences;
import hw3.FullHouse;
import hw3.LargeStraight;
import hw3.SmallStraight;
import hw3.YahtzeeGame;
import hw3.api.Category;

/**
 * Utility methods for creating various examples of Yahtzee
 * games.
 */
public class GameFactory
{
  /**
   * Creates and returns a very small version of Yahtzee.
   * There is just one category, which is to roll as many 
   * sixes as possible.
   * @return
   *   tiny game
   */
  public static YahtzeeGame createReallyTinyGame()
  {
    YahtzeeGame game = new YahtzeeGame(5, 6, 3);
    //Category category = new CountOccurrences("Sixes", 6);
    //game.addCategory(category);
    return game;
  }  
  
  
  /**
   * Creates and returns a short version of Yahtzee. There are 
   * four dice with values 1 through 4. 
   * @return
   *   small game 
   */
  public static YahtzeeGame createShortGame()
  {
    Category[] categories = {
//        new AllButOneOfAKind("3 of a kind"),
//        new FullHouse("Full House", 10),
//        new SmallStraight("Small Straight", 20),
//        new LargeStraight("Large Straight", 30),
//        new AllOfAKind("Yahtzee", 40)
      };    

    YahtzeeGame game = new YahtzeeGame(4, 4, 3);
    for (int i = 0; i < categories.length; ++i)
    {
      game.addCategory(categories[i]);
    }
    
    return game;
  }  
  
  /**
   * Creates and returns a default game based based on more or less standard
   * scoring categories for Yahtzee.
   * @return
   *   game based on standard rules
   */
  public static YahtzeeGame createDefault()
  {
    Category[] cats = {
//        new CountOccurrences("Aces", 1),
//        new CountOccurrences("Twos", 2),
//        new CountOccurrences("Threes", 3),
//        new CountOccurrences("Fours", 4),
//        new CountOccurrences("Fives", 5),
//        new CountOccurrences("Sixes", 6),     
//        new AllButTwoOfAKind("3 of a kind"),
//        new AllButOneOfAKind("4 of a kind"),
//        new FullHouse("Full House", 25),
//        new SmallStraight("Small Straight", 30),
//        new LargeStraight("Large Straight", 40),
//        new AllOfAKind("Yahtzee", 50),
//        new Chance("Chance")
      };
    
    YahtzeeGame game = new YahtzeeGame(5, 6, 3);
    for (Category c : cats)
    {
      game.addCategory(c);
    }
    
    return game;
  }
}
