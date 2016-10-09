package lab3;

/**
 * A RabbitModel is used to simulate the growth
 * of a population of rabbits. 
 */
public class RabbitModel1
{
  // TODO - add instance variables as needed
	/**
	 * counter for the population of rabbits
	 */
	private int population;
	
	/**
	 * the cached initial population setting for the rabbits
	 */
	private int initPop;
	
	/**
	 * counter for the number of years the rabbits have "existed"
	 */
	private int years;
  
  /**
   * Constructs a new RabbitModel.
   */
  public RabbitModel1()
  {
    initPop = population = 2;
    years = 0;
  }  
 
  /**
   * Returns the current number of rabbits.
   * @return
   *   current rabbit population
   */
  public int getPopulation()
  {
    return population;
  }
  
  /**
   * Updates the population to simulate the
   * passing of one year.
   */
  public void simulateYear()
  {
    years++;
    population++;
  }
  
  /**
   * Sets or resets the state of the model to the 
   * initial conditions.
   */
  public void reset()
  {
	population = initPop;
    years = 0;
  }
}
