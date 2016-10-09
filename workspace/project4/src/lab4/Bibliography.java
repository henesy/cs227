package lab4;
import java.util.Scanner;

public class Bibliography {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "Dijkstra, Edsger#Go To Statement Considered Harmful#Communications of the ACM#1968#11";
		String s2 = "Levoy, Marc#Display of Surfaces from Volume Data#IEEE Computer Graphics and Applications#1988#8";
		String s3 = "Dean, Jeffrey; Ghemawat, Sanjay#MapReduce: Simplified Data Processing on Large Clusters#Communications of the ACM#2008#51";
		BibItem b1 = genBib(s1);
		BibItem b2 = genBib(s2);
		BibItem b3 = genBib(s3);
	
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(b3);
	}
	
	private static BibItem genBib(String s)
	  {
	    // parse the given string s and return a new BibItem
		Scanner str = new Scanner(s);
		str.useDelimiter("#");
		String a = str.next();
		String t = str.next();
		String j = str.next();
		int y = str.nextInt();
		int v = str.nextInt();
		str.close();
		
		BibItem bib = new BibItem(a, t, j, y, v);
		return bib;
	  }
}

