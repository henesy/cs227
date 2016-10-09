package mini2;
import java.util.Scanner;


public class FunWithLoops extends java.lang.Object {
	
	
	/**
	 * Returns the number of iterations required until n is equal to 1, where each iteration updates n in the following way:
	 * @param n
	 * iterations
	 * @return
	 * number of iterations utilized
	 */
	public static int findStoppingTime(int n) {
		if(n > 0) {
			int i;
			for(i = 0; n != 1; i++) {
				if(n % 2 == 0) {
					//even
					n /= 2;
				} else {
					n = (n*3) + 1;
				}
			}
			return i;
		} else {
			return -1;
		}
	}
	
	/**
	 * Returns a string obtained by alternating characters from two given strings, starting with the first character of the first string.
	 * @param s
	 * @param t
	 * @return
	 */
	public static String interleaveWithRuns(String s, String t) {
		int i, j;
		String combi = "";
		//Scanner sc1 = new Scanner(s);
		//Scanner sc2 = new Scanner(t);
		int len1 = s.length(), len2 = t.length();
		for(i = 0, j = 0; i < len1 || j < len2; i++, j++) {
			if(i < len1) {
				combi += s.charAt(i);
			}
			if(j < len2) {
				combi += t.charAt(j);
			}
		}
		return combi;
	}
	
	/**
	 * Given a string of text containing numbers separated by commas, returns true if the numbers form an arithmetic sequence (a sequence in which each value differs from the previous one by a fixed amount).
	 * @param text
	 * @return
	 */
	public static boolean isArithmetic(String text) {
		if(!(text.equals(""))) {
			Scanner sc = new Scanner(text);
			sc.useDelimiter(",");
			int i, lastNum = -1, lastDifference = -1;
			boolean differenceUnset = true, numUnset = true;
			for(; sc.hasNext() || sc.hasNextInt() ;) {
				if(sc.hasNextInt()) {
					int curNum = sc.nextInt();
					//is a number
					if(!numUnset) {
						//normal
						if(!differenceUnset) {
							//normal
							int curDifference = curNum - lastNum;
							if(curDifference != lastDifference) {
								return false;
							}
							//lastDifference = curDifference;
						} else {
							//no existing difference
							lastDifference = curNum - lastNum;
							differenceUnset = false;
						}
						lastNum = curNum;
					} else {
						//no existing previous number
						lastNum = curNum;
						numUnset = false;
					}
				} else if(sc.hasNext()) {
					//if it's a not-number up next
					return false;
				}
			}
			return true;
		} else {
			return true;
		}
	}
	

	/**
	 * Returns the length of the longest consecutive run of the same character in a string s.
	 * @param s
	 * @return
	 */
	public static int longestRun(String s) {
		/*
		int i, recordRun = 0, curRun = 0;
		char lastChar = s.charAt(0);
		for(i = 1; i < s.length(); i++) {
			char curChar = s.charAt(i);
			if(curChar == lastChar) {
				//repeat
				curRun++;
			}
			if(curRun > recordRun) {
				//new record!
				recordRun = curRun;
				curRun = 0;
			}
			System.out.println(curRun);
			System.out.println(lastChar + " " + curChar + " " );
			lastChar = curChar;
		}
		return recordRun + 1;
		*/
		int i, record = 0;
		for(i = 0; i < s.length(); i++) {
			char curChar = s.charAt(i);
			int count = charCount(s, curChar);
			if(count > record) {
				record = count;
			}
		}
		return record;
	}
	
	private static int charCount(String s, char toMatch) {
		int i, count = 0;
		for(i = 0; i < s.length(); i++) {
			if(s.charAt(i) == toMatch) {
				//System.out.println(count);
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Returns a string similar to the given string with all runs of consecutive, repeated characters removed.
	 * @param s
	 * @return
	 */
	public static String removeMultipleLetters(String s) {
		if(s.length() > 1) {
			int i;
			String okString = "";
			char lastChar = s.charAt(0);
			okString += lastChar;
			for(i = 1; i < s.length(); i++) {
				char curChar = s.charAt(i);
				
				if(curChar != lastChar) {
					//not a repeat
					okString += curChar;
				}
				lastChar = curChar;
			}
			return okString;
		} else {
			return s;
		}
	}
	
	/**
	 * Reverses the order of words in a given string, where a "word" is any set of adjacent characters separated by whitespace.
	 * @param s
	 * @return
	 */
	public static String reverseWords(String s) {
		if(s.contains(" ")) {
			int i;
			String toAdd = "", toReturn = "";
			boolean inWord = false;
			for(i = 0; i < s.length(); i++) {
				char curChar = s.charAt(i);
				if(curChar != ' ') {
					//is not whitespace
					inWord = true;
					//toAdd = curChar + toAdd;
					toAdd = toAdd + curChar;
				} else {
					//is whitespace
					inWord = false;
					toReturn = toAdd + toReturn;
					toAdd = "";
					toReturn = curChar + toReturn;
				}
			}
			if(inWord) {
				//if whitespace didn't clean up
				toReturn = toAdd + toReturn;
			}
			return toReturn;
		} else {
			return s;
		}
	}
	
	/**
	 * Given a string, prints n lines of output as illustrated below, where n is the length of the string.
	 * @param s
	 */
	public static void triangleWord(String s) {
		int i;
		int spaces = s.length()-1;
		for(i = 0; i < s.length(); i++) {
			int j, k;
			//number of spaces to print
			for(j = 0; j < spaces; j++) {
				System.out.printf(" ");
			}
			for(;j < s.length(); j++) {
				System.out.printf("%c", s.charAt(j));
			}
			System.out.println("");
			spaces--;
		}
	}
}
