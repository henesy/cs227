//@author Sean Hinchee
package hw2;

import java.util.ArrayList;

import api.Cell;
import api.Mark;
import api.Status;

/**
 * Utility class for an implementation of Minesweeper.  This class
 * contains methods for examining and updating a 2D array of Cell
 * objects.
 */
public class GridUtil
{
  /**
   * Marker character for initializing a 2D array of cells from
   * an array of Strings.
   */
  public static final char MINE_CHAR = 'x';

  /**
   * Creates a 2D array of Cells from an array of strings, where
   * each string corresponds to one row of the returned array.
   * The jth character of the ith string corresponds to row i, column j
   * of the Cell array.  A MINE_CHAR character means the corresponding
   * cell will be set as a mine. All strings in the given array
   * must have the same length. This method does not calculate the
   * counts for the cells.  Initially all cells have status HIDDEN, 
   * count 0, and mark value NONE.
   * @param descriptor
   *    array of strings from which to construct the Cell array
   * @return
   *    2D array of Cells
   */
  public static Cell[][] createFromStringArray(String[] descriptor)
  {
    int width = descriptor[0].length();
    int height = descriptor.length;
    Cell[][] grid = new Cell[height][width];
    for (int row = 0; row < height; row += 1)
    {
      for (int col = 0; col < width; col += 1)
      {
        grid[row][col] = new Cell(row, col);
        if (descriptor[row].charAt(col) == MINE_CHAR)
        {
          grid[row][col].setIsMine(true);
        }
      }
    }
    return grid;
  }

  /**
   * Converts a grid into an array of strings.  Each row of the grid
   * corresponds to one string. There is one character for each cell.
   * If <code>revealAll</code> is false, then all HIDDEN cells are
   * are displayed as '-', 'f', or '?' depending on whether the mark value
   * is NONE, FLAG, or QUESTION_MARK. Revealed mines are displayed with MINE_CHAR,
   * and other revealed cells are displayed as the count.  
   * <p>
   * if <code>revealAll</code> is true, then all mines are displayed as
   * MINE_CHAR and all other non-mines are displayed as the count.
   *
   * @param grid
   *   2D array of Cells
   * @param revealAll
   *   true if hidden values should be shown, false otherwise
   * @return
   *   array of strings representing the grid
   */
  public static String[] convertToStringArray(Cell[][] grid, boolean revealAll)
  {
    String[] ret = new String[grid.length];
    for (int row = 0; row < grid.length; row += 1)
    {
      String current = "";
      for (int col = 0; col < grid[0].length; col += 1)
      {
        Cell c = grid[row][col];
        if (c.getStatus() == Status.HIDDEN && !revealAll)
        {
          if (c.getMark() == Mark.FLAG)
          {
            current += "f";
          }
          else if (c.getMark() == Mark.QUESTION_MARK)
          {
            current += "?";          
          }
          else
          {
            current += "-";
          }
        }
        else
        {
          if (c.isMine())
          {
            current += MINE_CHAR;
          }
          else
          {
            current += "" + c.getCount();
          }
        }
      }
      ret[row] = current;
    }
    return ret;
  }
  
  /**
   * Initialize the count value for each cell in the given 
   * 2D array.  The count for a non-mine is the number of neighboring 
   * cells (left, right, top, bottom, and diagonal) that are mines.
   * The count for a mine is -1.
   * @param grid
   *   2D array of Cells
   */
  public static void initCounts(Cell[][] grid)
  {
    // TODO
	int i;
	for(i = 0; i < grid.length; i++) {
		int j;
		for(j = 0; j < grid[i].length; j++) {
			if(grid[i][j].isMine()) {
				grid[i][j].setCount(-1);
			} else {
				grid[i][j].setCount(countNeighboringMines(grid, i, j));
			}
		}
	}
  }

  /**
   * Counts the number of neighbors of the given position
   * (left, right, top, bottom, and diagonal) that are mines.
   * @param grid
   *   2D array of Cells
   * @param givenRow
   *   given position row
   * @param givenCol
   *   given position column
   * @return
   *   number of neighbors that are mines
   */
  public static int countNeighboringMines(Cell[][] grid, int givenRow, int givenCol)
  {
	  int y = givenRow, x = givenCol;
		int ty = y, tx = x, cnt = 0;
		//up
		ty += 1;
		if(ty < grid.length && grid[ty][tx].isMine()) {
			cnt++;
		}
		ty = y;
		tx = x;
		//down
		ty -= 1;
		if(ty >= 0 && grid[ty][tx].isMine()) {
			cnt++;
		}
		ty = y;
		tx = x;
		//left
		tx -= 1;
		if(tx >= 0 && grid[ty][tx].isMine()) {
			cnt++;
		}
		ty = y;
		tx = x;
		//right
		tx += 1;
		if(tx < grid[ty].length && grid[ty][tx].isMine()) {
			cnt++;
		}
		ty = y;
		tx = x;
		//tl
		tx -= 1;
		ty += 1;
		if(tx >= 0 && ty < grid.length && grid[ty][tx].isMine()) {
			cnt++;
		}
		ty = y;
		tx = x;
		//tr
		tx += 1;
		ty += 1;
		if(ty < grid.length && tx < grid[ty].length && grid[ty][tx].isMine()) {
			cnt++;
		}
		ty = y;
		tx = x;
		//bl
		tx -= 1;
		ty -= 1;
		if(ty >= 0 && tx >= 0 && grid[ty][tx].isMine()) {
			cnt++;
		}
		ty = y;
		tx = x;
		//br
		tx += 1;
		ty -= 1;
		if(ty >= 0 && tx < grid[ty].length && grid[ty][tx].isMine()) {
			cnt++;
		}
		ty = y;
		tx = x;
	/*
	if(cnt == 2) {
		System.out.printf("x%d, y%d\n", x, y);
	}
	*/
    return cnt;
  }

  
  /**
   * Determines whether all the non-mine cells have status
   * REVEALED.
   * @param grid
   *   2D array of Cells
   * @return
   *   true if all non-mine cells are revealed, false otherwise
   */
  public static boolean areAllCellsRevealed(Cell[][] grid)
  {
    // TODO
	boolean allRevealed = true;
	int i;
	for(i = 0; i < grid.length; i++) {
		int j;
		for(j = 0; j < grid.length; j++) {
			if(!grid[i][j].isMine()) {
				if(grid[i][j].getStatus() != Status.REVEALED) {
					allRevealed = false;
				}
			}
		}
	}
	
    return allRevealed;
  }

  /**
   * Sets all mine cells to have status REVEALED.
   * Other cells are not modified.
   * @param grid
   *   2D array of Cells
   */
  public static void revealAllMines(Cell[][] grid)
  {
    // TODO
	int i;
	for(i = 0; i < grid.length; i++) {
		int j;
		for(j = 0; j < grid[i].length; j++) {
			if(grid[i][j].isMine()) {
				grid[i][j].setStatus(Status.REVEALED);
			}
		}
	}
  }

  /**
   * Returns the total number of mines in the given array.
   * @param grid
   *   2D array of Cells
   * @return
   *   number of mines in the array
   */
  public static int countAllMines(Cell[][] grid)
  {
    // TODO
	int i, cnt = 0;
	for(i = 0; i < grid.length; i++) {
		int j;
		for(j = 0; j < grid[i].length; j++) {
			if(grid[i][j].isMine()) {
				cnt++;
			}
		}
	}
    return cnt;
  }

  /**
   * Returns the total number of cells marked as FLAG in the given array.
   * @param grid
   *   2D array of Cells
   * @return
   *   number of flagged cells
   */
  public static int countAllFlags(Cell[][] grid)
  {
    // TODO
	int i, cnt = 0;
	for(i = 0; i < grid.length; i++) {
		int j;
		for(j = 0; j < grid[i].length; j++) {
			if(grid[i][j].getMark() == Mark.FLAG) {
				cnt++;
			}
		}
	}
    return cnt;
  }

  /**
   * Returns one neighbor (left, right, top, bottom, diagonal) 
   * of the given position that is hidden and has count greater than zero
   * or null if there is no such cell.
   * @param grid
   *   2D array of Cells
   * @param givenRow
   *   given position row
   * @param givenCol
   *   given position column
   * @return
   *   a hidden, non-mine cell that neighbors the given position
   */
  public static Cell findOneHiddenNeighbor(Cell[][] grid, int givenRow, int givenCol)
  {
    // TODO
	/* this is disgustingly repetitive */
	int y = givenCol, x = givenRow;
	int ty = y, tx = x;
	//up
	ty += 1;
	if(ty < grid.length && grid[ty][tx].getStatus() == Status.HIDDEN && grid[ty][tx].getCount() > 0) {
		return grid[ty][tx];
	}
	ty = y;
	tx = x;
	//down
	ty -= 1;
	if(ty >= 0 && grid[ty][tx].getStatus() == Status.HIDDEN && grid[ty][tx].getCount() > 0){
		return grid[ty][tx];
	}
	ty = y;
	tx = x;
	//left
	tx -= 1;
	if(tx >= 0 && grid[ty][tx].getStatus() == Status.HIDDEN && grid[ty][tx].getCount() > 0){
		return grid[ty][tx];
	}
	ty = y;
	tx = x;
	//right
	tx += 1;
	if(tx < grid[ty].length && grid[ty][tx].getStatus() == Status.HIDDEN && grid[ty][tx].getCount() > 0){
		return grid[ty][tx];
	}
	ty = y;
	tx = x;
	//tl
	tx -= 1;
	ty += 1;
	if(tx >= 0 && ty < grid.length && grid[ty][tx].getStatus() == Status.HIDDEN && grid[ty][tx].getCount() > 0){
		return grid[ty][tx];
	}
	ty = y;
	tx = x;
	//tr
	tx += 1;
	ty += 1;
	if(ty < grid.length && tx < grid[ty].length && grid[ty][tx].getStatus() == Status.HIDDEN && grid[ty][tx].getCount() > 0){
		return grid[ty][tx];
	}
	ty = y;
	tx = x;
	//bl
	tx -= 1;
	ty -= 1;
	if(ty >= 0 && tx >= 0 && grid[ty][tx].getStatus() == Status.HIDDEN && grid[ty][tx].getCount() > 0){
		return grid[ty][tx];
	}
	ty = y;
	tx = x;
	//br
	tx += 1;
	ty -= 1;
	if(ty >= 0 && tx < grid[ty].length && grid[ty][tx].getStatus() == Status.HIDDEN && grid[ty][tx].getCount() > 0){
		return grid[ty][tx];
	}
	ty = y;
	tx = x;
    return null;
  }

  /**
   * Sets all mine cells to have mark value <code>Mark.FLAG</code>.
   * @param grid
   *   2D array of Cells
   */
  public static void flagAllMines(Cell[][] grid)
  {
    // TODO
	int i;
	for(i = 0; i < grid.length; i++) {
		int j;
		for(j = 0; j < grid[i].length; j++) {
			grid[i][j].setMark(Mark.FLAG);
		}
	}
  }    
  
  /**
   * Reveals all neighbors of the Cell at the given position that
   * are not mines and have count greater than zero.  If the given <code>ArrayList</code>
   * is non-null, all revealed cells are added to the list, in the order in which
   * they are revealed.
   * @param grid
   *   2D array of Cells
   * @param givenRow
   *   given position row
   * @param givenCol
   *   given position column
   * @param history
   *   list to which revealed cells are added
   */
  public static void revealNeighbors(Cell[][] grid, int givenRow, int givenCol, ArrayList<Cell> history)
  {
    // find the boundary around the given cell; this will normally be a 3x3 
    // region, but we may be against one or both of the borders
    int upper = Math.max(0, givenRow - 1);
    int lower = Math.min(grid.length - 1, givenRow + 1);
    int left = Math.max(0, givenCol - 1);
    int right = Math.min(grid[0].length - 1, givenCol + 1);
    
    // now we can iterate over all cells except for the center one
    for (int row = upper; row <= lower; row += 1)
    {
      for (int col = left; col <= right; col += 1)
      {
        if (!(row == givenRow && col == givenCol))
        {
          Cell d = grid[row][col];
          if (d.getCount() > 0)
          {
            d.setStatus(Status.REVEALED);
            if (history != null)
            {
              history.add(d);
            }
          }
        }
      }
    }
  }

  /**
   * Performs a "flood fill" algorithm to reveal a region of 
   * all reachable cells with count zero, 
   * plus the cells at the boundary of the region, starting at
   * the given position.  If the cell at the given position does not have 
   * count 0, this method does nothing.  If the given <code>ArrayList</code>
   * is non-null, all revealed cells are added to the list, in the order in which
   * they are revealed.
   * @param grid
   *   2D 
   * @param row
   *   initial cell row
   * @param col
   *   initial cell column
   * @param history
   *   list to which revealed cells are added
   *   
   */
  public static void clearRegion(Cell[][] grid, int row, int col, ArrayList<Cell> history)
  {
    // TODO - clear the whole region and its boundary
    Cell c = grid[row][col];
    if (c.getCount() == 0)
    {
      //c.setStatus(Status.REVEALED);
      c.setStatus(Status.SEEN);
      if (history != null)
      {
        history.add(c);
        //expansion
        int ty = row, tx = col;
        //up
        c.setStatus(Status.EXPLORE_UP);
        ty += 1;
        //System.out.printf("%d, %d\n", grid[ty][tx].getStatus(), c.getStatus());
        if(ty < grid.length && grid[ty][tx].getStatus() == Status.HIDDEN) {
        	clearRegion(grid, ty, tx, history);
        }
        	
        ty = row;
        tx = col;
        
        //left
        c.setStatus(Status.EXPLORE_LEFT);
        tx -= 1;
        //System.out.printf("%d, %d\n", grid[ty][tx].getStatus(), c.getStatus());
        if(tx >= 0 && grid[ty][tx].getStatus() == Status.HIDDEN) {
        	clearRegion(grid, ty, tx, history);
        }	
        ty = row;
        tx = col;
        
        //down
        c.setStatus(Status.EXPLORE_DOWN);
        ty -= 1;
        //System.out.printf("%d, %d\n", grid[ty][tx].getStatus(), c.getStatus());
        if(ty >= 0 && grid[ty][tx].getStatus() == Status.HIDDEN) {
        	clearRegion(grid, ty, tx, history);
        }		
        ty = row;
        tx = col;
        
        //right
        c.setStatus(Status.EXPLORE_RIGHT);
        tx += 1;
        //System.out.printf("%d, %d\n", grid[ty][tx].getStatus(), c.getStatus());
        if(tx < grid[ty].length && grid[ty][tx].getStatus() == Status.HIDDEN) {
        	clearRegion(grid, ty, tx, history);
        }
        ty = row;
        tx = col;
        //reveal
        c.setStatus(Status.REVEALED);
        GridUtil.revealNeighbors(grid, row, col, history);
        
      }
    }
  }

}
