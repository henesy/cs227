package lab5;
import org.junit.Test;
import static org.junit.Assert.*;

public class BasketballTests {
	
    private static final double EPSILON = 10e-07;
    
    @Test
    public void testInitial()
    {
      Basketball b = new Basketball(5);
      assertEquals(false, b.isDribbleable());
    }

    @Test
    public void testInitialDiameter()
    {
      String msg = "A newly constructed basketball should have the diameter it was constructed with";
      Basketball b = new Basketball(5);
      assertEquals(msg, 5.0, b.getDiameter(), EPSILON);
    }

    @Test
    public void testInflate()
    {
      Basketball b = new Basketball(5);
      b.inflate();
      assertEquals(true, b.isDribbleable());
    }

    @Test
    public void testDiameterAfterInflation()
    {
      Basketball b = new Basketball(5);
      b.inflate();
      assertEquals(5.0, b.getDiameter(), EPSILON);
    }
}
