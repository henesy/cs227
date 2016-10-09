package api;


/**
 * String transformation operation.
 */
public interface Transformation
{
  /**
   * Given a string, returns a new string.
   * @param s
   *   any string
   * @return
   *   resulting string
   */
  String apply(String s);
}

