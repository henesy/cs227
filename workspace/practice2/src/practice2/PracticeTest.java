package practice2;
import java.util.Scanner;

public class PracticeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	/* 1 */
	//a
	public static int doubleAvg(double[] arr) {
		int avg = 0, i;
		for(i = 0; i < arr.length; i++) {
			avg += arr[i];
		}
		avg = avg / (i+1);
		return avg;
	}
	
	//b
	public static String longestWord(String str) {
		Scanner scn = new Scanner(str);
		String longest = "";
		while(scn.hasNext()) {
			String tstr = scn.next();
			if(tstr.length() > longest.length())
				longest = tstr;
		}
		scn.close();
		return longest;
	}
	
	//c
	public static String insertHashes(String str) {
		int i;
		String nStr = "";
		for(i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if(!Character.isAlphabetic(c)) {
				c = '#';
			}
			nStr += c;
		}
		return nStr;
	}
	
	//d
	

}
