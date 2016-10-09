package hw3;

/**
 * Super class for most categories to reduce code bulk
 * @author seh
 *
 */
public abstract class Cat implements hw3.api.Category {
	  /**
	   * seems to defeat the purpose of having a superclass if we can't use protected/public
	   */
	  
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
	  public boolean isSatisfiedBy(Hand dice) {
		  return true;
	  }
	  
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
	  public int getPotentialScore(Hand dice) {
		  return 0;
	  }
}
