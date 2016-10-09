package api;

/**
 * Operation that produces an integer result from an integer and a string.
 */
public interface IntCombiner
{
  /**
   * Given an integer and a string, returns an integer result.
   * @param num
   *   any integer
   * @param s
   *   any string
   * @return
   *   integer result obtained from the given integer and string
   */
  int combine(int num, String s);
}