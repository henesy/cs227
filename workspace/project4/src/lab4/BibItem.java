package lab4;
//import java.util.Scanner;

public class BibItem
{
  private String authors;
  private String title;
  private String journal;
  private int year;
  private int volume;
  
  public BibItem(String givenAuthors,
      String givenTitle,
      String givenJournal,
      int givenYear,
      int givenVolume)
  {
    authors = givenAuthors;
    title = givenTitle;
    journal = givenJournal;
    year = givenYear;
    volume = givenVolume;
  }
  /*public BibItem(String line) {
	  BibItem bib = genBib(line);
	  authors = bib.authors;
	  title = bib.title;
	  journal = bib.journal;
	  year = bib.year;
	  volume = bib.volume;
  }*/
  
  public String toString() {
	  return authors + ". " + title + ". " + journal + ". " + year + ". " + volume + ".";
  }
}
