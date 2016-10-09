package lab3;
import java.util.Random;

/**
 * A RabbitModel is used to simulate the growth
 * of a population of rabbits. 
 */
public class RabbitModel4
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
	 * Random Number Generator for Wabbits
	 */
	private Random rng;
  
  /**
   * Constructs a new RabbitModel.
   */
  public RabbitModel4()
  {
    initPop = population = 2;
    years = 0;
    rng = new Random(1337);
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
    int inc = rng.nextInt(11);
    population += inc;
  }
  
  /**
   * Sets or resets the state of the model to the 
   * initial conditions.
   */
  public void reset()
  {
	population = initPop;
    years = 0;
    rng = new Random(1337);
  }
}
