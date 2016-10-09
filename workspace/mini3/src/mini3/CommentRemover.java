package mini3;
import api.Transformation;

public class CommentRemover implements Transformation {
	public CommentRemover() {
		
	}
	
	public String apply(String s) {
		if(s.length() > 1) {
			char lastChar = s.charAt(0);
			int i;
			for(i = 1; i < s.length(); i++) {
				if(s.charAt(i) == '/' && lastChar == '/') {
					return s.substring(0, i-1);
				}
				lastChar = s.charAt(i);
			}
			return s;
		} else {
			return s;
		}
	}
}
