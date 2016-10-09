package mini3;
import java.lang.Character;

import api.Selector;

public class NonCommentLineSelector implements Selector {
	public NonCommentLineSelector() {
		
	}
	
	
	public boolean select(String s) {
		int i;
		for(i = 0; i < s.length(); i++) {
			if(!Character.isWhitespace(s.charAt(i))) {
				if(s.charAt(i) == '/' && s.charAt(i+1) == '/') {
					return false;
				}
				break;
			}
		}
		return true;
	}

}
