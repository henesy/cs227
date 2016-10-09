import java.util.Arrays;

import api.Cell;
import hw2.GridUtil;
import ui.TextUI;

public class GridTest
{
  public static void main(String[] args)
  {
    // string descriptor for a grid with mine locations
    String[] test = {
      "-x-",
      "--x",
      "---",
    };

    // creates a grid from the string descriptor
    Cell[][] grid = GridUtil.createFromStringArray(test);
    
    // convert back, with hidden information displayed
    String[] result = GridUtil.convertToStringArray(grid, true);
    for (String row : result)
    {
      System.out.println(row);
    }      
    System.out.println();
    
    // can also use the TextUI.printGrid method, which is prettier
    TextUI.printGrid(result);
    System.out.println();

    // After implementing initCounts, try this
    GridUtil.initCounts(grid);
    String[] actual = GridUtil.convertToStringArray(grid, true);
    TextUI.printGrid(actual);
    System.out.println();
    
    // A simple unit test
    String[] expected = {
      "1x2",
      "12x",
      "011",
    };
    System.out.println(Arrays.equals(expected, actual));
    
  }


}
