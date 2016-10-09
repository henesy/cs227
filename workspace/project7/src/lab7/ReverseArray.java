package lab7;

import java.util.Arrays;

/**
 * Class containing some utilities for manipulating arrays.
 */
public class ReverseArray
{
  public static void main(String[] args)
  {
    int[] test = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    System.out.println("Before: " + Arrays.toString(test));
    reverse(test);
    System.out.println("After:  " + Arrays.toString(test));
    
    //Exercise 3
    int[] nums = {-1, 0, 4, 5, 6, -7};
    switchHalves(nums);
  }
  
  /**
   * Reverses the contents of the given array.
   * @param arr
   */
  public static void reverse(int[] arr)
  {
    int front = 0;
    int rear = arr.length - 1;
    while (front < rear)
    {
      swap(arr, front, rear);
      
      // move indices towards the center
      front += 1;
      rear -= 1;
    }
  }
 
  public static void swap(int[] arr, int i, int j)
  {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;  
  }
  
  //Exercise 3
  public static void switchHalves(int[] numbers) {
	  int len = numbers.length;
	  int[] res = new int[len];
	  int i, i2;
	  
	  if(len % 2 == 0) {
		  //even
		  int[] h1 = new int[len/2];
		  int[] h2 = new int[len/2];
		  
		  for(i = 0, i2 = 0; i < len; i++) {
			  if(i < len/2) {
				  h1[i] = numbers[i];
			  } else {
				  h2[i2] = numbers[i];
				  i2++;
			  }
		  }

		  for(i = 0, i2 = 0; i < res.length; i++) {
			  if(i < res.length/2) {
				  res[i] = h2[i];
			  } else {
				  res[i] = h1[i2];
				  i2++;
			  }
		  }
		  
	  } else {
		  //odd
		  int[] h1 = new int[len/2];
		  int[] h2 = new int[len/2];
		  
		  for(i = 0; i < len/2; i++) {
			  h1[i] = numbers[i];
		  }
		  for(i = i+1, i2 = 0; i < len; i++, i2++) {
			  h2[i2] = numbers[i];
		  }
		  System.out.printf("%d %d\n", len/2, i);
		  for(i = 0; i < len/2; i++) {
			  res[i] = h2[i];
		  }
		  for(i = i+1, i2 = 0; i < len; i++, i2++) {
			  res[i] = h1[i2];
		  }
		  res[len/2] = numbers[len/2];
	  }
	  
	  int j;
	  for(j = 0; j < res.length; j++) {
		  System.out.printf("%d ", res[j]);
	  }
	  System.out.println("");
  }
  
}