package hw3.api;

import hw3.Hand;

/**
 * Abstract representation of a scoring category for a dice game
 * such as Yahtzee.  Each scoring category has a method <code>isSatisfiedBy</code>
 * that can determine, given a <code>Hand</code>, whether the criteria for the category 
 * can potentially be satisfied by the hand. There is also a method
 * <code>getPotentialScore</code> that determines what the potential score 
 * would be for a given hand.  <strong>These two methods do not modify the
 * state of the category.</strong>
 * <p>
 * 
 * A scoring category can also be set to a be <em>filled</em> by
 * calling the <code>fill()</code> method, 
 * meaning that a hand being used to fill the category in the game is 
 * permanently saved.  After being filled, <code>getHand</code> always returns
 * the hand that was used to fill the category and <code>getScore</code>
 * always returns the score for that hand.
 * @author smkautz
 */
public interface Category
{
  /**
   * Determines whether this category is filled.
   * @return
   *   true if this category has a fixed hand and score, 
   *   false otherwise
   */
  public boolean isFilled();
  
  /**
   * Returns the score for this category, or zero if the 
   * category is not <em>filled</em>.
   * @return
   *   score for the category or zero if not filled
   */
  public int getScore();  

  /**
   * Returns the hand that was used to fill this category, or null if
   * not <em>filled</em>.  
   * @return
   *   hand filling this category, or null if not filled
   */
  public Hand getHand(); 
  
  /**
   * Returns the name for this category.
   * @return
   *   name for this category
   */
  public String getDisplayName();
  
  /**
   * Permanently sets the hand being used to fill
   * this category.  The score is set to the value of
   * <code>getPotentialScore</code> for the given hand.
   * Throws <code>IllegalStateException</code> if 
   * the category has already been filled or if the
   * given hand is not <em>complete</em> (as defined
   * by the <code>Hand.isComplete</code> method).
   * @param dice
   *   hand to be used to satisfy this category
   * @throws IllegalStateException
   *   if the given hand is not <em>complete</em> (according
   *   to the <code>isComplete()</code> method of Hand) or
   *   if this category is already filled
   */
  public void fill(Hand dice);
  
  /**
   * Determines whether the given hand satisfies the defined
   * criteria for this scoring category. The criteria are determined
   * by the concrete type implementing the interface.
   * This method does not modify the state of this category.
   * @param dice
   *   hand to check
   * @return
   *   true if the given hand satisfies the defined criteria for the
   *   category, false otherwise
   */
  public boolean isSatisfiedBy(Hand dice);
  
  /**
   * Returns the potential score that would result from 
   * using the given hand to fill this category.
   * Always returns zero if the <code>isSatisfiedBy()</code> 
   * method returns false for the given hand.
   * This method does not modify the state of this category.
   * @param dice
   *   hand to check
   * @return
   *   potential score for the given hand
   */
  public int getPotentialScore(Hand dice);
  
  
}
