
package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import api.Cell;
import api.CellObserver;
import api.Mark;
import api.Status;
import hw2.Minesweeper;

/**
 * UI for a minesweeper game. Allows the option of animating the flood-fill part
 * of the game.
 */
public class MinesweeperPanel extends JPanel implements CellObserver
{
  /**
   * Size in pixels of the cells for the grid.
   */
  public static final int CELL_SIZE = 40;
  
  /**
   * Font size for text on grid cells.
   */
  public static final int FONT_SIZE = 20;
  
  /**
   * Format string for seconds and mine count.
   */
  private static final String timeFormat = "%4d";

  
  // Swing components
  private JButton hintButton;
  private JCheckBox animationCheckBox;
  private JCheckBox visibleCheckBox;
  private JLabel timeLabel;
  private JLabel mineLabel;
  private Timer timer;
  
  /**
   * The game to be displayed by this panel.
   */
  private Minesweeper game;
  
  /**
   * Delay between updates when animating clearRegion.
   */
  private int sleepTime;
  
  /**
   * Indicates whether or not an animation is currently taking place.
   */
  private volatile boolean animating; // flag shared with helper threads
  
  /**
   * Count of elapsed seconds (not counting time in animation).
   */
  private long currentSeconds;
  
  /**
   * Constructs the component.
   */
  public MinesweeperPanel(Minesweeper game, int sleepTime, JPanel scorePanel)
  {
    this.game = game;
    this.sleepTime = sleepTime;
    addMouseListener(new MyMouseListener());
    
    // components for score panel
    
    int mines = game.getNumMines();
    mineLabel = new JLabel(String.format(timeFormat, mines));
    mineLabel.setBackground(Color.CYAN);
    mineLabel.setOpaque(true);
    mineLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 24));
    scorePanel.add(mineLabel);
    
    visibleCheckBox = new JCheckBox("Visible");
    visibleCheckBox.addActionListener(new VisibleCheckBoxListener());
    scorePanel.add(visibleCheckBox);    
   
    hintButton = new JButton("hint");
    hintButton.addActionListener(new HintButtonListener());
    scorePanel.add(hintButton);

    animationCheckBox = new JCheckBox("Animate");
    animationCheckBox.addActionListener(new AnimateCheckBoxListener());
    scorePanel.add(animationCheckBox);
    
    timeLabel = new JLabel(String.format(timeFormat, 0));
    timeLabel.setBackground(Color.CYAN);
    timeLabel.setOpaque(true);
    timeLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 24));
    scorePanel.add(timeLabel);
    
    // timer to update elapsed seconds
    TimerCallback cb = new TimerCallback();
    timer = new Timer(1000, cb);
    timer.start();
  }

  /**
   * When animation is in effect, receives notification 
   * when a cell status is modified.
   */
  @Override
  public void update(Cell cell)
  {
    if (animating)
    {
      // ok to call from auxiliary thread
      repaint();
      
      // for the count 0 cells, delay for 'sleepTime'
      if (cell.getCount() == 0)
      {
        try
        {
          Thread.sleep(sleepTime);
        }
        catch (InterruptedException ignore)
        {
        }
      }
    }
  }


  @Override
  public void paintComponent(Graphics g)
  {
    // clear background
    g.clearRect(0, 0, getWidth(), getHeight());
    boolean showAll = visibleCheckBox.isSelected();
    
    // time label
    timeLabel.setText(String.format(timeFormat, currentSeconds));
    
    // mine label
    int currentMines = game.getNumMines() - game.getNumFlags();
    mineLabel.setText(String.format(timeFormat, currentMines));
    
    // draw cells
    for (int row = 0; row < game.getRows(); ++row)
    {
      for (int col = 0; col < game.getColumns(); ++col)
      {
        Cell c = game.getCell(row, col);
        
        // fill the cell 
        Color color = getColor(c);
        g.setColor(color);
        g.fillRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        
        // possibly draw some text
        if (showAll || c.getStatus() == Status.REVEALED)
        {
          g.setColor(Color.BLACK);
          if (c.isMine())
          {
            drawText(g, row, col, "x");
          }
          else
          {
            drawText(g, row, col, "" + c.getCount());
          }            
        }
        else if (c.getStatus() == Status.HIDDEN
            && c.getMark() == Mark.QUESTION_MARK)
        {
          g.setColor(Color.BLACK);
          drawText(g, row, col, "?");
        }
        else if (c.getStatus() == Status.HIDDEN && c.getMark() == Mark.FLAG)
        {
          g.setColor(Color.BLACK);
          drawText(g, row, col, "F");
        }
      }
    }

    // draw grid overlay
    g.setColor(Color.WHITE);
    for (int row = 0; row < game.getRows(); ++row)
    {
      for (int col = 0; col < game.getColumns(); ++col)
      {
        g.drawRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
      }
    }

    // possibly draw lines to animate exploring
    if (animating)
    {
      drawArrows(g);
    }
  }

  /**
   * Draws the given string at the center of the cell at row, column.
   * @param g
   * @param row
   * @param col
   * @param text
   */
  private void drawText(Graphics g, int row, int col, String text)
  {
    Font f = new Font(Font.SANS_SERIF, Font.PLAIN, FONT_SIZE);
    g.setFont(f);
    FontMetrics fm = g.getFontMetrics(f);
    // String text = "" + c.getCount();
    int h = fm.getHeight();
    int w = fm.stringWidth(text);
    int x = col * CELL_SIZE + CELL_SIZE / 2 - (w / 2);
    int y = row * CELL_SIZE + CELL_SIZE / 2 + (h / 2) - 2;
    g.drawString(text, x, y);
  }

  /**
   * Returns a color for cells with the given Status.
   * 
   * @param status
   * @return
   */
  private Color getColor(Cell m)
  {
    // dark cyan for cells with value zero
    final Color regionColor = new Color(0, 200, 200);
    
    if (m == null)
      return Color.BLACK;
    Status s = m.getStatus();
    Mark mark = m.getMark();
    if (s == Status.HIDDEN && mark == Mark.NONE)
    {
      return Color.LIGHT_GRAY;
    }
    else if (s == Status.HIDDEN && mark == Mark.FLAG)
    {
      if (m.isMine() && game.isOver())
      {
        return Color.PINK;
      }
      return Color.ORANGE;
    }
    else if (s == Status.HIDDEN && mark == Mark.QUESTION_MARK)
    {
      return Color.YELLOW;
    }
    else if (isExploring(m))
    {
      return Color.GREEN;
    }
    else if (s == Status.SEEN)
    {
      return Color.BLUE;
    }
    else if (s == Status.REVEALED && !m.isMine())
    {
      if (m.getCount() > 0)
        return Color.CYAN;
      else
        return regionColor;
    }
    else if (s == Status.REVEALED && m.isMine())
    {
      return Color.RED;
    }

    return Color.WHITE;
  }

  /**
   * Returns true if c is in one of the EXPLORING states.
   * @param c
   * @return
   */
  private boolean isExploring(Cell c)
  {
    Status s = c.getStatus();
    return (s == Status.EXPLORE_UP || s == Status.EXPLORE_LEFT
        || s == Status.EXPLORE_DOWN || s == Status.EXPLORE_RIGHT);
  }

  /**
   * Draws a line from the center of each cell in EXPLORING state to
   * indicate the direction in which it is exploring.
   * @param g
   */
  private void drawArrows(Graphics g)
  {

    g.setColor(Color.BLACK);
    ((Graphics2D)g).setStroke(new BasicStroke(2.0f));

    for (int row = 0; row < game.getRows(); ++row)
    {
      for (int col = 0; col < game.getColumns(); ++col)
      {
        Cell c = game.getCell(row, col);
        {
          if (isExploring(c))
          {
            int x = col * CELL_SIZE + CELL_SIZE / 2; // - (maze.getColumns() / 2);
            int y = row * CELL_SIZE + CELL_SIZE / 2; // + (maze.getRows() / 2);
            int x2 = x;
            int y2 = y;
            // String text = "";
            switch (c.getStatus())
            {
              case EXPLORE_UP:
                y2 = y - CELL_SIZE;
                break;
              case EXPLORE_LEFT:
                x2 = x - CELL_SIZE;
                break;
              case EXPLORE_DOWN:
                y2 = y + CELL_SIZE;
                break;
              case EXPLORE_RIGHT:
                x2 = x + CELL_SIZE;
                break;
              default:
            }

            g.drawLine(x, y, x2, y2);
          }
        }
      }
    }
  
  }
  
  
  /**
   * Worker thread for running the clearRegion animation.
   */
  private class AnimationWorker extends SwingWorker<Void, Void>
  {
    private int row;
    private int col;
    public AnimationWorker(int r, int c)
    {
      row = r;
      col = c;
    }
    
    @Override
    protected Void doInBackground() throws Exception
    {
      Thread.currentThread().setName("clearRegion worker thread");
      game.play(row, col);
      return null;
    }
    
    @Override
    protected void done()
    {
      animating = false;
    }
    
  }
  
  /**
   * Handler for mouse clicks.
   */
  private class MyMouseListener implements MouseListener
  {

    @Override
    public void mouseClicked(MouseEvent e)
    {
      // ignore clicks if an animation is in progress
      if (!animating)
      {
        final int col = e.getX() / CELL_SIZE;
        final int row = e.getY() / CELL_SIZE;
 
        if (e.getButton() == MouseEvent.BUTTON1)
        {
          Cell c = game.getCell(row, col);
          if (c.getCount() == 0 && sleepTime > 0 && animationCheckBox.isSelected())
          {
            // if it's a cell with count 0, run in worker thread to get animation
            animating = true;
            new AnimationWorker(row, col).execute();
          }
          else
          {
            game.play(row, col);
          }
        }
        else
        {
          // right click
          game.toggleMark(row, col);
        }
      }
      repaint();
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
      // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
      // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
      // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
      // TODO Auto-generated method stub
    }
  }

  /**
   * Handler for hint button.
   */
  private class HintButtonListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent e)
    {
      game.hint();
      repaint();
    }    
  }
  
  /**
   * Handler for changes in the "Visible" check box.
   */
  private class VisibleCheckBoxListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent e)
    {
      repaint();
    }        
  }
  
  /**
   * Handler for changes in the "Animate" check box.
   */
  private class AnimateCheckBoxListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent e)
    {
      if (animating && !animationCheckBox.isSelected())
      {
        animating = false;
      }     
    }        
  }
  
  /**
   * Timer callback updates count of the elapsed seconds, except
   * when animating.
   */
  private class TimerCallback implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent e)
    {
      if (game.isOver())
      {
        timer.stop();
      }
      else if (!animating)
      {
        // this is going to drift...
        currentSeconds += 1;      
        repaint();
      }
    }    
  }
}
