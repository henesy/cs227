package mini3;
import api.Combiner;

public class LetterCollector implements Combiner {
	public LetterCollector() {
		
	}
	
	public String combine(String first, String second) {
		String s = first;
		int i;
		for(i = 0; i < second.length(); i++) {
			String tmp = "" + second.charAt(i);
			if(!s.contains(tmp))
				s += tmp;
		}
		
		return s;
	}
}
