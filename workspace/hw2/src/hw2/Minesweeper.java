//@author Sean Hinchee
package hw2;

import java.util.ArrayList;
import java.util.Random;

import api.Cell;
import api.CellObserver;
import api.Mark;
import api.Status;

/**
 * This class encapsulates the state of a minesweeper game.
 */
public class Minesweeper
{

	/**
	 * the current grid
	 */
	private Cell[][] grid;
	
	/**
	 * the count of clicks to reveal a cell
	 */
	private int numClicks;
	
	/**
	 * listed history of cells which have been revealed
	 */
	private ArrayList<Cell> history;
	
	/**
	 * is the game won?
	 */
	private boolean won;
	
	/**
	 * is it the first move?
	 */
	private boolean first;
	
	/**
	 * number of mines
	 */
	private int numMines;
	
	/**
	 * is the game over?
	 */
	private boolean gameOver;
  
  /**
   * Constructs an instance of the game using the given array
   * of strings to initialize the mine locations. 
   * The jth character of the ith string corresponds to row i, column j
   * of the Cell array.  A <code>GameUtil.MINE_CHAR</code> character 
   * means the corresponding cell will be set as a mine. All 
   * strings in the given array must have the same length.  Initially
   * all cells have status HIDDEN and the counts are correct. 
   * @param descriptor
   *   array of strings representing mine positions
   */
  public Minesweeper(String[] descriptor)
  {
    // TODO
	  /*
	String[] test = {
	    "-x-",
	    "--x",
	    "---",
	};
	*/
	
	grid = GridUtil.createFromStringArray(descriptor);
	//i am not convinced that initCounts works like this.
	GridUtil.initCounts(grid);
	numMines = 0;
	int i;
	for(i = 0; i < grid.length; i++) {
		int j;
		for(j = 0; j < grid[i].length; j++) {
			grid[i][j].setStatus(Status.HIDDEN);
			if(grid[i][j].isMine()) {
				numMines++;
			}
		}
	}
	numClicks = 0;
	won = false;
	history = new ArrayList<Cell>();
	first = true;
	gameOver = false;
  }
  
  /**
   * Constructs an instance of the game of the specified size and
   * specified initial number of mines, using the given <code>Random</code> object
   * to select the mine locations.  The selection is performed in such a way that
   * each cell is equally likely to be selected as one of the mines.
   * Initially all cells have status HIDDEN and the counts are correct.
   * @param rows
   *   number of rows in the grid
   * @param columns
   *   number of columns in the grid
   * @param numberOfMines
   *   number of mines in the grid
   * @param givenRandom
   *   random number generator to use for placing mines and <code>randomMove</code>
   */
  public Minesweeper(int rows, int columns, int numberOfMines, Random givenRandom)
  {
    // TODO
	grid = new Cell[rows][columns];
	numClicks = 0;
	won = false;
	history = new ArrayList<Cell>();
	first = true;
	numMines = numberOfMines;
	gameOver = false;
	
	int i;
	//set hidden
	for(i = 0; i < grid.length; i++) {
		int j;
		for(j = 0; j < grid[i].length; j++) {
			grid[i][j].setStatus(Status.HIDDEN);
		}
	}
	
	//place mines
	for(i = numberOfMines; i > 0; i--) {
		int y = givenRandom.nextInt(rows);
		int x = givenRandom.nextInt(columns);
		grid[y][x].setIsMine(true);
	}
	
	GridUtil.initCounts(grid);

	
  }
  
  /**
   * Returns the number of clicks for revealing a cell that have been made.
   * Note that this number may be smaller than the number of revealed
   * cells.  An operation to toggle the mark of a cell does not count
   * as a click.
   * @return
   *   number of clicks
   */
  public int getClicks()
  {
    // TODO
    return numClicks;
  }
  
  /**
   * Returns the total number of mines in the grid.
   * @return
   *   number of mines
   *   
   */
  public int getNumMines()
  {
    // TODO
    return GridUtil.countAllMines(grid);
  }
  
  /**
   * Returns the total number of cells with mark value FLAG.
   * @return
   *   number of flagged cells
   */
  public int getNumFlags()
  {
    // TODO
    return GridUtil.countAllFlags(grid);
  }
  
  /**
   * Returns the number of rows in the grid.
   * @return
   *   number of rows in the grid
   */
  public int getRows()
  {
    // TODO
    return grid.length;
  }
  
  /**
   * Returns the number of columns in the grid.
   * @return 
   *   number of columns in the grid
   */
  public int getColumns()
  {
    // TODO
	//hopefully everything is a square
    return grid[0].length;
  }
  
  /**
   * Returns the cell at the specified position.
   * <p>
   * NOTE: The caller of this method should normally not modify
   * the returned cell.
   * 
   * @param row
   *   given position row
   * @param col
   *   given position column
   * @return
   *   cell at the given position
   */
  public Cell getCell(int row, int col)
  {
    // TODO
	if(row >= 0 && row < grid.length && col >=0 && col < grid[row].length) {
		return grid[row][col];
	} else {
		return null;
	}
  }
  
  /**
   * Returns this game's grid as an array of strings, according
   * to the conventions described in 
   * <code>GridUtil.convertToStringArray</code>.
   * @param revealAll
   *   true if hidden cell values should be shown
   * @return
   *   array of strings representing this game's grid
   */
  
  public String[] getGridAsStringArray(boolean revealAll)
  {
    // TODO
	String[] str = GridUtil.convertToStringArray(grid, revealAll);
    return str;
  }
  
  /**
   * Returns a reference to the list of all revealed cells, in 
   * the order they were revealed.
   * <p>
   * NOTE: The caller of this method should normally not modify
   * the returned list or the cells it contains.
   * @return
   *   list of all revealed cells
   */
  public ArrayList<Cell> getHistory()
  {
    // TODO
    return history;
  }
  
  
  /**
   * Returns true if the game is over, false otherwise.
   * The game is over if the player attempts to reveal a mine,
   * or if all non-mine cells are revealed. 
   * @return
   *   true if the game is over, false otherwise
   */
  public boolean isOver()
  {
    // TODO
	  //actual function?
	if(isWon())
		return true;
	else
		return gameOver;
    //return gameOver;
  }
  
  /**
   * Toggle the mark value on the cell at the given 
   * position.  The values should cycle through
   * <code>Mark.NONE</code>, <code>Mark.FLAG</code>, and
   * <code>Mark.QUESTION_MARK</code>, in that order.
   * @param row
   *   given position row
   * @param col
   *   given position column
   */
  public void toggleMark(int row, int col)
  {
    // TODO
	//grid[row][col].getMark()
	if(grid[row][col].getMark() == Mark.NONE) {
		grid[row][col].setMark(Mark.FLAG);
	} else if(grid[row][col].getMark() == Mark.FLAG) {
		grid[row][col].setMark(Mark.QUESTION_MARK);
	} else if(grid[row][col].getMark() == Mark.QUESTION_MARK) {
		grid[row][col].setMark(Mark.NONE);
	}
  }
  
  /**
   * Processes a selection by the player to reveal the cell at the 
   * given position.  In general revealing a mine
   * should end the game, and revealing a cell with
   * count zero should initiate a call to 
   * <code>GridUtil.clearRegion</code>.  However, a special
   * case is made for the first selection: if the player
   * selects a mine as the first move, the cell is first converted
   * to a non-mine and the count is adjusted, and then the 
   * selection is processed normally.  This method does
   * nothing if the game is over.
   * @param row
   *   given position row
   * @param col
   *   given position column
   */
  public void play(int row, int col)
  {
    // TODO
	  if(!gameOver) {
		if(first && grid[row][col].isMine()) {
			//if we click a mine as the first move
			grid[row][col].setIsMine(false);
			grid[row][col].setStatus(Status.REVEALED);
			if(grid[row][col].getCount() == 0)
				GridUtil.clearRegion(grid, row, col, history);
			first = false;
		} else if(first && !grid[row][col].isMine()) {
			//not a mine on the first move
			grid[row][col].setStatus(Status.REVEALED);
			//grid[row][col].setIsMine(false);
			if(grid[row][col].getCount() == 0)
				GridUtil.clearRegion(grid, row, col, history);
			first = false;
		} else if(grid[row][col].isMine()) {
			//game ends
			grid[row][col].setStatus(Status.REVEALED);
			GridUtil.revealAllMines(grid);
			gameOver = true;
		} else {
			grid[row][col].setStatus(Status.REVEALED);
			if(grid[row][col].getCount() == 0)
				GridUtil.clearRegion(grid, row, col, history);
			isWon();
		}
		history.add(grid[row][col]);
		numClicks++;
	  }
  }
  
  /**
   * Returns whether or not the game has been won.
   * @return
   *   true if the game is won, false otherwise
   */
  public boolean isWon()
  {
    // TODO
	if(GridUtil.areAllCellsRevealed(grid)) {
		won = true;
		GridUtil.flagAllMines(grid);
	}
    return won;
  }
  
  /**
   * Iterates over the history of revealed cells, in reverse order, to find a
   * neighboring cell that is still hidden and has count greater than zero, 
   * and then reveals the first such cell found.  If the history is empty or no such cell
   * exists, this method does nothing. (The latter can only occur if all remaining hidden
   * non-mine cells have count zero.)
   */
  public void hint()
  {
    // TODO
	if(history.size() > 0) {
		int i;
		for(i = history.size()-1; i >= 0; i--) {
			int y = history.get(i).getRow(), x = history.get(i).getCol();
			int ty = y, tx = x;
			//up
			ty += 1;
			if(ty < grid.length && grid[ty][tx].getCount() > 0 && grid[ty][tx].getStatus() == Status.HIDDEN) {
				grid[ty][tx].setStatus(Status.REVEALED);
				break;
			}
			ty = y;
			tx = x;
			//down
			ty -= 1;
			if(ty >= 0 && grid[ty][tx].getCount() > 0 && grid[ty][tx].getStatus() == Status.HIDDEN) {
				grid[ty][tx].setStatus(Status.REVEALED);
				break;
			}
			ty = y;
			tx = x;
			//left
			tx -= 1;
			if(tx >= 0 && grid[ty][tx].getCount() > 0 && grid[ty][tx].getStatus() == Status.HIDDEN) {
				grid[ty][tx].setStatus(Status.REVEALED);
				break;
			}
			ty = y;
			tx = x;
			//right
			tx += 1;
			if(tx < grid[ty].length && grid[ty][tx].getCount() > 0 && grid[ty][tx].getStatus() == Status.HIDDEN) {
				grid[ty][tx].setStatus(Status.REVEALED);
				break;
			}
			ty = y;
			tx = x;
			//tl
			tx -= 1;
			ty += 1;
			if(tx >= 0 && ty < grid.length && grid[ty][tx].getCount() > 0 && grid[ty][tx].getStatus() == Status.HIDDEN) {
				grid[ty][tx].setStatus(Status.REVEALED);
				break;
			}
			ty = y;
			tx = x;
			//tr
			tx += 1;
			ty += 1;
			if(ty < grid.length && tx < grid[ty].length && grid[ty][tx].getCount() > 0 && grid[ty][tx].getStatus() == Status.HIDDEN) {
				grid[ty][tx].setStatus(Status.REVEALED);
				break;
			}
			ty = y;
			tx = x;
			//bl
			tx -= 1;
			ty -= 1;
			if(ty >= 0 && tx >= 0 && grid[ty][tx].getCount() > 0 && grid[ty][tx].getStatus() == Status.HIDDEN) {
				grid[ty][tx].setStatus(Status.REVEALED);
				break;
			}
			ty = y;
			tx = x;
			//br
			tx += 1;
			ty -= 1;
			if(ty >= 0 && tx < grid[ty].length && grid[ty][tx].getCount() > 0 && grid[ty][tx].getStatus() == Status.HIDDEN) {
				grid[ty][tx].setStatus(Status.REVEALED);
				break;
			}
			ty = y;
			tx = x;
		}
	}
  }
  
  
  /**
   * Calls setObserver with the given <code>CellObserver</code> on every cell 
   * of the grid.
   * @param observer
   *   reference to a <code>CellObserver</code>
   */
  public void setObserver(CellObserver observer)
  {
    // TODO
	int i;
	for(i = 0; i < grid.length; i++) {
		int j;
		for(j = 0; j < grid[i].length; j++) {
			grid[i][j].setObserver(observer);
		}
	}
  }
}
