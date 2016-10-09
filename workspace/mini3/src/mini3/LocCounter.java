package mini3;
//import api.Combiner;
import api.IntCombiner;

public class LocCounter implements IntCombiner {
	public LocCounter() {
		
	}
	
	public int combine(int n, String s) {
		boolean codeLine = true;
		
		String tmp = s.trim();
		
		//is comment line?
		//System.out.println("comment");
		if(tmp.startsWith("//"))
			codeLine = false;
		//System.out.println(codeLine);

		if(tmp.startsWith("/*"))
			codeLine = false;
		//System.out.println(codeLine);
		
		//is blank line? might need more checking?
			
		//System.out.println("blank");
		if(tmp.startsWith("\n"))
			codeLine = false;
		//System.out.println(codeLine);
		
		//is curly brace?
		//System.out.println("brace");
		if(tmp.startsWith("{"))
			codeLine = false;
		//System.out.println(codeLine);
		
		//is blank?
		//System.out.println("blank no nl");
		if(tmp.length() < 1)
			codeLine = false;
		//System.out.println(codeLine);
		
		if(codeLine) {
			return n+1;
		} else {
			return n;
		}
	}
	
}
