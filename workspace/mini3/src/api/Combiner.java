package api;

/**
 * Operation that produces a string result from two strings.
 */
public interface Combiner
{
  /**
   * Given two strings, returns a string result.
   * @param first
   *   the first string
   * @param second
   *   the second string
   * @return
   *   string obtained from the two given strings
   */
  String combine(String first, String second);
}