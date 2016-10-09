package lab9;

import java.io.File;
import java.util.ArrayList;

public class RecursiveDeli
{

  public static void main(String[] args)
  {
    String result = makeDigitSandwich(4);
    System.out.println(result);
    
    //checkpoint 1
    System.out.println(getPyramidCount(2)); //should be 140
    //checkpoint 2(1)
    int[] arr = {0, 1, 5, 2, 3, 4};
    System.out.println(getMaxInt(arr, 0)); //should be 5
    File file = new File("U:/cs227/workspace/project9/src/lab9");
    System.out.println(findFiles(file));
    System.out.println(countBytes(file));
  }

  public static String makeDigitSandwich(int outerDigit)
  {
    if (outerDigit == 0)
    {
      // Base case
      return "0";
    }
    else
    {
      // Recursive case
      String middle = makeDigitSandwich(outerDigit - 1); 
      String result = outerDigit + middle + outerDigit;
      return result;
    }
  }
  
  //checkpoint 1
  private static int getPyramidCount(int n) {
	  int cnt = 0;
	  if(n > 0)
	  	return cnt = (getPyramidCount(n-1) + n*n);
	  else
		  return cnt;
  }
  
  //checkpoint 2(1)
  private static int getMaxInt(int[] arr, int max) {
	  return getMaxInt(arr, max, 0, arr.length-1);
  }
  
  private static int getMaxInt(int[] arr, int max, int start, int end) {
	  if(start == end) {
		  if(arr[start] > max) {
			  return arr[start];
		  } else {
			  return max;
		  }
	  } else {
		  int mid = (start + end) / 2;
		  int leftMax = getMaxInt(arr, max, start, mid);
		  int rightMax = getMaxInt(arr, max, mid+1, end);
		  max = Math.max(leftMax, Math.max(max, rightMax));
		  return max;
	  }
  }
  
  //checkpoint 2(2)
  public static long countBytes(File f) {
	  if(f.isDirectory()) {
		  long fsize = 0;
		  File[] files = f.listFiles();
		  
		  for(int i = 0; i < files.length; i++)
			  fsize += countBytes(files[i]);
		
		  return fsize;
	  } else {
		  return f.length();
	  }
  }
  
  //checkpoint 2(3)
  public static ArrayList<String> findFiles(File file)
  {
    // create an empty array list...
    ArrayList<String> arr = new ArrayList<String>();
    
    // pass it into the recursive method
    findFilesRec(file, arr);
    
    // and return the filled-up ArrayList
    return arr;
  }

  // recursive helper method
  private static void findFilesRec(File file, ArrayList<String> list) {
	  if(file.isDirectory()) {
		  File[] files = file.listFiles();
		  for(int i = 0; i < files.length; i++) {
			  findFilesRec(files[i], list);
		  }
	  } else {
		  if(file.getName().endsWith(".java")) {
			  list.add(file.getName());
		  }		  
	  }
  }
  
}