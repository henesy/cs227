package mini3;
import api.Transformation;

public class LineNumberer implements Transformation {
	private int lineNum;
	
	public LineNumberer() {
		lineNum = 1;
	}
	
	public String apply(String s) {
		String pre = "     ";
		pre = lineNum + pre;
		lineNum++;
		return pre.substring(0, 5) + s;
	}
}
