package lab3;

/**
 * A RabbitModel is used to simulate the growth
 * of a population of rabbits. 
 */
public class RabbitModel
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
	 * the year before the current year
	 */
	private int lastYear;
	
	/**
	 * the year before last year, so y'know, the year before that
	 */
	private int yearBeforeThat;
  
  /**
   * Constructs a new RabbitModel.
   */
  public RabbitModel()
  {
    lastYear = 1;
    yearBeforeThat = 0;
    initPop = population = lastYear + yearBeforeThat;
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
	    int tmpYear = population;
	    population = (lastYear + tmpYear);
	    yearBeforeThat = lastYear;
	    lastYear = tmpYear;
	    System.out.println(tmpYear + ", " + population+ ", " +yearBeforeThat+ ", " +lastYear);
   
   }
  
  /**
   * Sets or resets the state of the model to the 
   * initial conditions.
   */
  public void reset()
  {
	  lastYear = 1;
	    yearBeforeThat = 0;
	    initPop = population = lastYear + yearBeforeThat;
	    years = 0;
  }
}
