package mini3;

import api.Combiner;
import api.IntCombiner;
import api.Selector;
import api.Transformation;
import mini3.CommentRemover;
import mini3.LetterCollector;
import mini3.LineNumberer;
import mini3.LocCounter;
import mini3.NonCommentLineSelector;
import mini3.StringList;

// examples of map, filter, reduce
public class StringListTest
{
  public static void main(String[] args)
  {    
    // Note we can try out a class like Reverser all by itself without
    // StringList
    // (see below for Reverser implementation)
    Transformation t = new Reverser();
    String reversed = t.apply("banana");
    System.out.println(reversed);

    String[] arr = {"So", "long", "and", "thanks", "for", "all", "the", "fish"};
    StringList sl = new StringList(arr);   
    
    // reverse all strings in the string list
    // - prints [os, gnol, dna, sknaht, rof, lla, eht, hsif]
    StringList result = sl.map(new Reverser());
    System.out.println(result); 
    
    // length of longest string
    // - prints 6
    System.out.println(sl.reduce(new MaxLengthFinder(), 0));  
    
    // longest string
    // - prints "thanks"
    System.out.println(sl.reduce(new MaxFinder(), ""));
    
    // strings that start with the letter f
    // - prints [for, fish]
    result = sl.filter(new StartingLetterSelector("f"));
    System.out.println(result); 
    
    // since map() and filter() return another StringList, we can 
    // "chain" operations together.
    // - here we find the reverse of the longest word starting with 'f', prints "hsif"
    System.out.println(sl
        .filter(new StartingLetterSelector("f"))
        .map(new Reverser())
        .reduce(new MaxFinder(), ""));
    
    System.out.println();
    
    // list of strings to try out the comment remover and loc counter...
    String[] test2 = {
      "// Our first Java program",
      "public class HelloPrinter",
      "{",
      "  // Prints a greeting on the console",
      "  public static void main(String[] args)",
      "  {",
      "    System.out.println(\"Hello, world! \");",
      "    System.out.println();  // prints empty line",
      "  }",
      "}  // braces have to match",
      ""
    };    
    StringList programList = new StringList(test2);
    
    // filter out all the lines that are comments
    StringList noCommentLines = programList.filter(new NonCommentLineSelector());
    for (int i = 0; i < noCommentLines.size(); i += 1)
    {
      System.out.println(noCommentLines.get(i));
    }
    System.out.println();
    
    // filter out lines that are comments AND remove the end-of-line comments
    StringList noComments = programList.filter(new NonCommentLineSelector()).map(new CommentRemover());
    for (int i = 0; i < noComments.size(); i += 1)
    {
      System.out.println(noComments.get(i));
    }
    System.out.println();

    // add line numbers
    StringList numbered = programList.map(new LineNumberer());
    for (int i = 0; i < numbered.size(); i += 1)
    {
      System.out.println(numbered.get(i));
    }
    System.out.println();

    // count lines of code
    int locCount = programList.reduce(new LocCounter(), 0);
    System.out.println("Lines of code: " + locCount);
    System.out.println();
    
    // collect characters used
    String letters = sl.reduce(new LetterCollector(), "");
    System.out.println(letters);
  }
}


// Some examples. Note that these have "package-private" scope
// just to keep the example code compact, since this way we don't
// have to put each one in its own file.  The required
// classes for miniassignment 3 need to be declared public and 
// each needs to be in its own file.

class Reverser implements Transformation
{
  @Override
  public String apply(String s)
  {
    String result = "";
    for (int i = 0; i < s.length(); i += 1)
    {
      result = s.charAt(i) + result;
    }
    return result;
  } 
}

class StartingLetterSelector implements Selector
{
  private String start;
  public StartingLetterSelector(String givenStart)
  {
    start = givenStart.toLowerCase();
  }
  
  public boolean select(String s)
  {
    return s.toLowerCase().startsWith(start);
  }
}

class MaxFinder implements Combiner
{
  @Override
  public String combine(String s, String t)
  {
    if (t.length() > s.length())
    {
      return t;
    }
    else
    {
      return s;
    }
  } 
}

class MaxLengthFinder implements IntCombiner
{
  public int combine(int i, String s)
  {
    return Math.max(i, s.length());
  }
}

