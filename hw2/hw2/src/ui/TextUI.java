package ui;

import java.util.Scanner;

import hw2.Minesweeper;

/**
 * Simple text-based user interface for the Minesweeper game.
 */
public class TextUI
{
  
  // some sample grids to try
  
  public static final String[] GRID1 = {
    "-x-",
    "--x",
    "---",
  };
  
  public static final String[] GRID2 = {
    "-x--",
    "-x--",
    "x---",
    "--x-",
  };
  
  public static final String[] GRID3 = {
    "----x------",
    "x--x-------",
    "-xxx-------",
    "-x------xxx",
    "---x-------",
    "---x------x",
    "----------x",
    "----------x",
  };
  
  /**
   * Game to be used with this UI.
   */
  private Minesweeper game;
  
  /**
   * Scanner for reading console input.
   */
  private Scanner in;
  
  /**
   * Indicates whether hidden cells should be shown. 
   */
  private boolean revealAll;
  
  /**
   * Entry point.
   * @param args
   */
  public static void main(String[] args)
  {
    Minesweeper g = new Minesweeper(GRID1);
    new TextUI(g).go();
  }
  
  /**
   * Constructs an instance of the user interface with the given game.
   * @param game
   *   an instance of Minesweeper
   */
  public TextUI(Minesweeper givenGame)
  {
    game = givenGame;
    in = new Scanner(System.in);
    revealAll = false;
  }
  
  /**
   * Runs the UI.
   */
  public void go()
  {
    while(!game.isOver())
    {
      printGrid(game.getGridAsStringArray(revealAll));
      System.out.println();
      System.out.println("(mines - flags): " + (game.getNumMines() - game.getNumFlags()));
      System.out.print("Enter rowcolumn, 'f' rowcolumn (flag), 'h' (hint), or 's' (show all) ");
      String text = in.nextLine().trim();
      if (Character.isDigit(text.charAt(0)))
      { 
        doPlay(text);
      }
      else
      {
        char ch = text.charAt(0);
        if (ch == 'f')
        {
          // toggle flag
          doToggleMark(text);
        }
        else if (ch == 'h')
        {
          // hint
          game.hint();
        }
        else if (ch == 's')
        {
          // show all hidden cells
          revealAll = !revealAll;
        }
      }
    }   
    
    if (game.isWon())
    {
      System.out.println("You win!");
      System.out.println("Number of clicks: " + game.getClicks());
    }
    else
    {
      System.out.println("Sorry, you've lost.");
    }
    System.out.println();
    printGrid(game.getGridAsStringArray(true));   
  }

  /**
   * Plays the cell indicated by the given string.
   * For example, "42d" is row 42, column 3.
   * @param text
   *   string in the form rowcolumn
   */
  private void doPlay(String text)
  {
    int row = getRow(text);
    int col = getColumn(text);
    game.play(row, col);
  }
  
  /**
   * Toggles the mark value in the game for the designated cell.
   * The text may be of the form "f 42d" or "f42d" or "f"; in
   * the latter case the player will be prompted to enter a 
   * row and column string.
   * @param text
   *   instruction to toggle the mark for a cell
   */
  private void doToggleMark(String text)
  {
    text = text.substring(1).trim();  // strip off the 'f'
    if (text.length() == 0 || !Character.isDigit(text.charAt(0)))
    {
      System.out.print("Enter rowcolumn to flag: ");
      text = in.nextLine().trim();
    }
    if (Character.isDigit(text.charAt(0)))
    {
      int row = getRow(text);
      int col = getColumn(text);
      game.toggleMark(row, col);
    }

  }
  
  /**
   * Returns the row number from a rowcolumn string.
   * For example, "42d" is row 42, column 3.
   * @param text
   *   string in the form rowcolumn
   * @return
   *   row number
   */
  private int getRow(String text)
  {
    // everything but the last character should be an integer
    return Integer.parseInt(text.substring(0, text.length() - 1).trim());
  }
  
  /**
   * Returns the column number corresponding to a rowcolumn string,
   * where 'a' is column 0. For example, "42d" is row 42, column 3.
   * @param text
   *   string in the form rowcolumn 
   * @return
   *   column number
   */
  private int getColumn(String text)
  {
    return text.charAt(text.length() - 1) - 'a';
  }
  
  /**
   * Display the contents of a string array representing 
   * a game grid on the console, marked with indices in the left and top margins.
   * @param strings
   *   array of strings reprenting a grid
   * @param revealAll
   *   true if cell contents should be shown even if hidden
   */
  public static void printGrid(String[] strings)
  {
    // print a top row with character labels
    System.out.print("      ");
    for (int col = 0; col < strings[0].length() - 1; col += 1)
    {
      char ch = (char)('a' + col);
      System.out.print(ch + " ");
    }
    System.out.println((char)('a' + strings[0].length()));

    for (int row = 0; row < strings.length; row += 1)
    {
      // print a numeric label to the left of each row
      System.out.printf("%4d  ", row);
      String current = strings[row];
      for (int col = 0; col < current.length() - 1; col += 1)
      {
        System.out.print(current.charAt(col) + " ");
      }
      System.out.println(current.charAt(current.length() - 1));
    }
  }
}
