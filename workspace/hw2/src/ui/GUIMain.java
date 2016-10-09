package ui;

import java.awt.Dimension;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import hw2.Minesweeper;

/**
 * Entry point for creating and starting graphical interface for
 * Minesweeper game.
 * @author smkautz
 */
public class GUIMain
{

  /**
   * Entry point.  Edit here to change the initialization of the game.
   * @param args
   */
  public static void main(String[] args)
  {
    Minesweeper g = new Minesweeper(TextUI.GRID3);
    
    // Use a 200 ms delay when animating the flood fill algorithm.  
    // This has no effect unless the "Animate" check box is checked.
    start(g, 200);
  }

  /**
   * Start up the game on the UI event thread.
   * @param game
   * @param sleepTime
   */
  private static void start(final Minesweeper game, final int sleepTime)
  {
    Runnable r = new Runnable()
    {
      public void run()
      {
        createAndShow(game, sleepTime);
      }
    };
    SwingUtilities.invokeLater(r);
  }
  
  /**
   * Initialize GUI components.  This should only be executed on the GUI
   * event thread.
   * @param game
   * @param sleepTime
   */
  private static void createAndShow(final Minesweeper game, final int sleepTime)
  {
    // create score panel and grid panel
    JPanel scorePanel = new JPanel();
    MinesweeperPanel panel = new MinesweeperPanel(game, sleepTime, scorePanel);

    // set the grid panel to receive notifications for animation
    game.setObserver(panel);
    
    // arrange the two panels vertically
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.add(scorePanel);
    mainPanel.add(panel);

    // create the frame
    JFrame frame = new JFrame("Minesweeper");
    frame.getContentPane().add(mainPanel);

    // give it a nonzero size
    panel.setPreferredSize(new Dimension(game.getColumns() * MinesweeperPanel.CELL_SIZE, game.getRows() * MinesweeperPanel.CELL_SIZE));    
    frame.pack();
    
    // we want to shut down the application if the 
    // "close" button is pressed on the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // make the frame visible and start the UI machinery
    frame.setVisible(true);   

  }
}
