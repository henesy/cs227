

import java.util.Random;
import java.util.Scanner;

import hw3.Hand;
import hw3.YahtzeeGame;
import hw3.api.Category;

/**
 * Text-based user interface for a dice game such as Yahtzee.
 * @author smkautz
 */
public class TextUIMain
{
  /**
   * Game instance for this UI.
   */
  private YahtzeeGame game;
  
  /**
   * Random number generator to be used in the game.
   */
  private Random rand;
  
  /**
   * Scanner to read from standard input.
   */
  private Scanner in;
  
  /**
   * Entry point.
   * 
   * @param args
   * @throws NoSuchCategoryException
   */
  public static void main(String[] args)
  {

      // Add a seed to make your dice throws reproducible for development
      Random rand = new Random();
      
      // Choose a predefined game, or make up your own
      YahtzeeGame g = GameFactory.createDefault();
      //Game g = ExampleGameFactory.createShortGame();
      //Game g = ExampleGameFactory.createReallyTinyGame();
      
      TextUIMain ui = new TextUIMain(g, rand);
      ui.runGame();

  }

  /**
   * Constructs a UI instance for the given game using the given
   * random number generator.
   * @param g
   *   game to use in this UI
   * @param rand
   *   random number generator to use in the game
   */
  public TextUIMain(YahtzeeGame g, Random rand)
  {
    game = g;
    this.rand = rand;
    in = new Scanner(System.in);
  }
  
  /**
   * Run the main loop for an instance of the game.
   */
  public void runGame()
  {
    // welcome
    System.out.println("Welcome to CS227 Yahtzee");
    System.out.println("------------------------");
    System.out.println();
    
    // main loop
    while (!isGameOver())
    {
      doOneTurn();
    }
    
    // display final results
    System.out.println();
    System.out.println("Final results");
    System.out.println("-------------");
    printCategories(null);
  }
  
  /**
   * Execute the logic for one turn of the game.
   */
  private void doOneTurn()
  {
    Hand dice = game.createNewHand();
    printCategories(null);
    
    
    // Continue rolling dice until all dice are completed
    boolean first = true;
    do
    {
      doRollDice(dice, first);
      first = false;
      System.out.println();
      printCategories(dice);
      System.out.println();
      System.out.println("You rolled   " + dice.toString());
      System.out.println();
      
      // if there are still available dice, let the player choose
      // which ones to keep or free
      if (dice.getAvailableDice().length > 0)
      {
        chooseDice(dice);
      }
    } while (dice.getAvailableDice().length > 0);
    
    System.out.println();
    System.out.println("Completed roll: " + dice);
    
    // finally, player selects which category
    chooseCategory(dice);
  }
  
  /**
   * Prints the score categories for the game; if dice is not null,
   * includes potential scores for the current roll of the dice.
   * @param dice
   *   hand representing current state of the dice, possibly null
   */
  private void printCategories(Hand dice)
  {
    
    // predefine a bunch of format strings to line things up in columns
    String format1 = "%2d) %5d %-15s";        // shows possible score
    String format2 = "%2d)   --- %-15s%5d ";  // shows actual score after category name
    String totalFormat1 = "%25s-----\n";      // 25 spaces and then a dashed line for total
    String totalFormat2 = "%25s%5d\n";        // print a total

    System.out.println();
    if (dice == null)
    {
      System.out.println("Current scores:");
    }
    else
    {
      System.out.println("Potential scores for this roll:");
    }
    Category[] cats = getCategories();
    for (int i = 0; i < cats.length; ++i)
    {
      String name = cats[i].getDisplayName();
      if (cats[i].isFilled())
      {
        int actualScore = cats[i].getScore();
        System.out.printf(format2, i, name, actualScore);
        System.out.println(cats[i].getHand());
      }
      else
      {
        int potentialScore = 0;
        if (dice != null)
        {
          potentialScore = cats[i].getPotentialScore(dice);
        }
        System.out.printf(format1, i, potentialScore, name);
        System.out.println();
      }
    }
    System.out.printf(totalFormat1, "");
    System.out.printf(totalFormat2, "SCORE:", game.getScore());
  }
  
  
  /**
   * Allows a user to select a category in which to score the current
   * roll of the dice.  (Categories are represented in the console
   * window as numbers.)
   * @param dice
   *   current roll of the dice
   */
  private void chooseCategory(Hand dice)
  {
    System.out.print("Select category: ");
    boolean valid = false;
    Category[] cats = getCategories();
    while (!valid)
    {
      try
      {
        int response = Integer.parseInt(in.nextLine());
        if (response >= 0 && response < cats.length && !cats[response].isFilled())
        {
          cats[response].fill(dice);
          valid = true;
        }
      }
      catch (NumberFormatException e)
      {
        // fall through
      }
      if (!valid)
      {
        System.out.print("Please enter a valid category number: ");
      }
    }   
  }
  
  /**
   * Allows a user to select which dice should be added to those
   * completed and which should be rolled again.
   * @param dice
   *   current roll of the dice
   */
  private void chooseDice(Hand dice)
  {
    boolean valid = false;
    while (!valid)
    {
      System.out.println("Press ENTER to roll available dice, or:");
      System.out.println("a) keep all");
      System.out.println("b) select dice to keep");
      System.out.println("c) select dice to free");
      System.out.print("Your choice: ");
      String response = in.nextLine().trim().toLowerCase();
      if (response.startsWith("a"))
      {
        dice.keepAll();
        valid = true;
      }
      else if (response.length() == 0)
      {
        // nothing else to do 
        valid = true;
      }
      else if (response.startsWith("b"))
      {
        System.out.print("Enter dice values to keep (separated by spaces): ");
        String line = in.nextLine();
        Scanner temp = new Scanner(line);
        while(temp.hasNextInt())
        {
          dice.keep(temp.nextInt());
        }
        System.out.println("You now have " + dice.toString());
        System.out.println();
      }
      else if (response.startsWith("c"))
      {
        System.out.print("Enter dice values to free (separated by spaces): ");
        String line = in.nextLine();
        Scanner temp = new Scanner(line);
        while(temp.hasNextInt())
        {
          dice.free(temp.nextInt());
        }
        System.out.println("You now have " + dice.toString());
        System.out.println();
      }

      else
      {
        System.out.println("Please enter a, b, or c, or just press ENTER to roll available dice");
      }
    }
  }
  
  private void doRollDice(Hand dice, boolean waitForEnterKey)
  {
    if (waitForEnterKey)
    {
      System.out.print("Press ENTER to roll the dice...");
      in.nextLine();
    }
    
    // pretend something is happening...
    int iters = rand.nextInt(30) + 20;
    for (int i = 0; i < iters; ++i)
    {
      System.out.print(".");
      try
      {
        Thread.sleep(10);
      }
      catch (InterruptedException ignore)
      {
        
      }
    }
    System.out.println();
    
    // ...now, really roll the dice
    dice.roll(rand);
  }
  
  /**
   * Determines whether the current game is over.
   * @return
   *   true if the game is over, false otherwise
   */
  private boolean isGameOver()
  {
    Category[] cats = getCategories();
    for (Category cat : cats)
    {
      if (!cat.isFilled())
      {
        return false;
      }
    }
    return true;     
  }
  
  /**
   * Helper method returns an array of all categories in the game.
   * @return
   *   array of scoring categories
   */
  private Category[] getCategories()
  {
    Category[] cats = game.getCategories().toArray(new Category[game.getCategories().size()]);
    return cats;
  }
  

}
