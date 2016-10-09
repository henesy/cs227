package lab7;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class ArrayExample3
{
  public static void main(String[] args)
  {
    String s = "3 5 7 9 12";
    int[] result = readNumbers(s);
    System.out.println(Arrays.toString(result));
    
    //Exercise 1
    int[] nums = {-1, 0, 1, 2, -3, 4};
    int[] poss = getPositiveNumbers(nums);
    int i;
    for(i = 0; i < poss.length; i++) {
    	System.out.printf("%d ", poss[i]);
    }
    System.out.println("");
    
    //Exercise 2
    int[] rands = randomExperiment(10, 1000);
    for(i = 0; i < rands.length; i++) {
    	System.out.printf("%d: %d ", i, rands[i]);
    }
    System.out.println("");
  }
  
  public static int[] readNumbers(String text)
  {
    Scanner scanner = new Scanner(text);
    int count = 0;
    while (scanner.hasNextInt())
    {
      scanner.nextInt();
      count +=1;
    }
    
    int[] nums = new int[count];
    scanner = new Scanner(text);
    int index = 0;
    while (scanner.hasNextInt())
    {
      int num = scanner.nextInt();
      nums[index] = num;
      index += 1;
    }
    return nums;  
  }
  
	//Exercise 1
	public static int[] getPositiveNumbers(int[] numbers) {
		int i, j, cnt = 0;
		for(i = 0; i < numbers.length; i++) {
			if(numbers[i] > 0) {
				cnt++;
			}
		}
		int[] pos;
		pos = new int[cnt];
		for(i = 0, j = 0; i < numbers.length; i++) {
			if(numbers[i] > 0) {
				pos[j] = numbers[i];
				j++;
			}
		}
		return pos;
	}
	
	//Exercise 2
	public static int[] randomExperiment(int max, int iters) {
		int[] nums = new int[max];
		int i;
		Random rng = new Random(24);
		for(i = 0; i < iters; i++) {
			int rand = rng.nextInt(max);
			nums[rand]++;
		}
		return nums;
	}
}



