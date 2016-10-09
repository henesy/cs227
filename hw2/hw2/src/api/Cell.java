package api;

/**
 * Data type for one cell in a Minesweeper game.  A cell is designated
 * either as a mine or a non-mine.  A cell has a <em>count</em>, which for non-mines
 * is normally set to be the number of neighboring mines.  A cell has a 
 * <em>status</em>, normally either HIDDEN or REVEALED.  However, there are
 * five other intermediate status values that may be used to support animation
 * of a flood fill operation.  In addition to the status there is also a
 * <em>mark</em> value, which is one of NONE, FLAG, or QUESTION_MARK.
 */
public class Cell
{
  /**
   * Status of this cell.
   */
  private Status status;
  
  /**
   * Mark value of this cell.
   */
  private Mark mark;
  
  /**
   * Count of neighboring mines for this cell.
   */
  private int count; 
  
  /**
   * True if the cell is a mine, false otherwise.
   */
  private boolean mine;
  
  /**
   * Row in which this cell is located.
   */
  private int row;
  
  /**
   * Column in which this cell is located.
   */
  private int col;
  
  /**
   * Observer to receive notifications if the cell's status changes.
   */
  private CellObserver observer;
  
  /**
   * Constructs a cell to be located at the given row and column.
   * @param givenRow
   *   row where this cell will be located
   * @param givenCol
   *   column where this cell will be located
   */
  public Cell(int givenRow, int givenCol)
  {
    status = Status.HIDDEN;
    mark = Mark.NONE;
    row = givenRow;
    col = givenCol;
  }
  
  /**
   * Returns the row number for this cell.
   * @return
   *   row for the cell
   */
  public int getRow()
  {
    return row;
  }
  
  /**
   * Returns the column number for this cell.
   * @return
   *   column for this cell
   */
  public int getCol()
  {
    return col;
  }
  
  /**
   * Sets an observer to be notified if the cell's status changes.
   * @param givenObserver
   *   observer to be notified if the cell's status changes
   */
  public void setObserver(CellObserver givenObserver)
  {
    observer = givenObserver;
  }
  
  /**
   * Sets whether or not this cell is a mine.
   * @param isMine
   *   true if the cell is a mine, false otherwise
   */
  public void setIsMine(boolean isMine)
  {
    mine = isMine;
  }
  
  /**
   * Returns whether this cell is a mine.
   * @return
   *   true if this cell is a mine, false otherwise
   */
  public boolean isMine()
  {
    return mine;
  }
  
  /**
   * Sets the count for this cell.  The count for a non-mine is normally
   * set to the number of neighboring cells that are mines, and the 
   * count for a mine is set to -1.
   * @param givenCount
   *   value to set as the count
   */
  public void setCount(int givenCount)
  {
    count = givenCount;
  }
  
  /**
   * Returns the count for this cell.
   * @return
   *   count for this cell
   */
  public int getCount()
  {
    return count;
  }
 
  /**
   * Returns the mark value for this cell.
   * @return
   *   mark value for this cell
   */
  public Mark getMark()
  {
    return mark;
  }
  
  /**
   * Sets the mark value for this cell.
   * @param givenMark
   *   mark value for this cell
   */
  public void setMark(Mark givenMark)
  {
    mark = givenMark;
  }
  
  /**
   * Returns the status for this cell.
   * @return
   *   status for this cell
   */
  public Status getStatus()
  {
    return status;
  }
  
  /**
   * Sets the status for this cell. This method also notifies the observer
   * if there is one.
   * @param givenStatus
   *   status for this cell
   */
  public void setStatus(Status givenStatus)
  {  
    status = givenStatus;
    if (observer != null)
    {
      observer.update(this);
    }
  }
  
  @Override
  public boolean equals(Object obj)
  {
    if (obj != null && obj.getClass() == this.getClass())
    {
      Cell other = (Cell) obj;
      return this.row == other.row &&
          this.col == other.col &&
          this.mine == other.mine &&
          this.count == other.count &&
          this.status == other.status &&
          this.mark == other.mark;
    }
    return false;
  }
  
}
