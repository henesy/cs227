package lab7;
import java.util.Random;

public class DeckTest
{
  public static void main(String[] args)
  {
    Random rand = new Random(42);  // "seed" the random number generator with a value we choose
    Deck deck = new Deck(rand);    // construct the deck to use that generator
    Card[] hand = deck.select(5);
    System.out.println(Card.toString(hand));
  }
}