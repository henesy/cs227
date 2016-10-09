package mini3;

import java.util.ArrayList;

import api.Combiner;
import api.IntCombiner;
import api.Selector;
import api.Transformation;

public class StringList {
	private ArrayList<String> lst;
	
	public StringList() {
		lst = new ArrayList<String>();
	}
	public StringList(ArrayList<java.lang.String> strings) {
		lst = strings;
	}
	
	public StringList(String[] strings) {
		lst = new ArrayList<String>();
		int i;
		for(i = 0; i < strings.length; i++) {
			lst.add(strings[i]);
		}
	}
	
	public void append(String s) {
		lst.add(s);
	}
	
	public StringList filter(Selector selector) {
		ArrayList<String> nlst = new ArrayList<String>();
		StringList sl = new StringList(nlst);
		int i;
		for(i = 0; i < lst.size(); i++) {
			if(selector.select(lst.get(i)))
				sl.append(lst.get(i));
		}
		return sl;
	}
	
	public String get(int index) {
		return lst.get(index);
	}
	
	public StringList map(Transformation transform) {
		ArrayList<String> nlst = new ArrayList<String>();
		StringList sl = new StringList(nlst);
		int i;
		for(i = 0; i < lst.size(); i++) {
			nlst.add(transform.apply(lst.get(i)));
		}
		return sl;
	}
	
	
	public String reduce(Combiner combiner, String initialValue) {
		String tmp = initialValue;
		int i;
		for(i = 0; i < lst.size(); i++) {
			tmp = combiner.combine(tmp, lst.get(i)); //wat?
		}
		return tmp;
	}
	
	public int reduce(IntCombiner combiner, int initialValue) {
		int tmp = initialValue;
		int i;
		for(i = 0; i < lst.size(); i++) {
			tmp = combiner.combine(tmp, lst.get(i)); //wat?
		}
		return tmp;
	}
	
	public int size() {
		return lst.size();
	}
	
	public String toString() {
		String s = "[";
		int i;
		for(i = 0; i < lst.size(); i++) {
			s += lst.get(i);
			if(i != lst.size()-1) {
				s += ", ";
			}
		}
		s += "]";
		return s;
	}
	
}
