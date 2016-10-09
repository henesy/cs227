package lab5;
import balloon4.Balloon;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class BalloonTests {
	
	private Balloon b;
	
	@Before
	public void setup() {
		b = new Balloon(5);
	}
	
	@Test
	public void testInitial() {
		assertEquals("Already popped balloon", false, b.isPopped());
		assertEquals("Unexpected radius", 0, b.getRadius());
	}
	
	@Test
	public void testRadius() {
		assertEquals("Unexpected radius", 0, b.getRadius());
		b.blow(3);
		assertEquals("Unexpected radius", 3, b.getRadius());
		assertEquals(false, b.isPopped());
	}
	
	@Test
	public void testFullBlow() {
		b.blow(5);
		assertEquals("Balloon improperly inflates", 5, b.getRadius());
	}
	
	@Test
	public void testBlow() {
		b.blow(4);
		assertEquals("Balloon improperly inflates", 4, b.getRadius());
	}
	
	@Test
	public void testBigBlow() {
		b.blow(6);
		assertEquals("Balloon is too stretchy", true, b.isPopped());
	}
	
	@Test
	public void testDeflate() {
		b.blow(5);
		assertEquals("Balloon improperly inflates", 5, b.getRadius());
		b.deflate();
		assertEquals("Balloon improperly deflates", false, b.isPopped());
	}
	
	@Test
	public void testPopping() {
		b.pop();
		assertEquals("Indestructible balloon", true, b.isPopped());
		assertEquals("Balloon does not decompress", 0, b.getRadius());
	}
	
	@Test
	public void testRefilling() {
		b.blow(2);
		assertEquals(2, b.getRadius());
		assertEquals(false, b.isPopped());
		b.deflate();
		assertEquals(0, b.getRadius());
		assertEquals(false, b.isPopped());
		b.blow(2);
		assertEquals(2, b.getRadius());
		assertEquals(false, b.isPopped());
	}
	
	@Test
	public void testFillAfterOverfill() {
		b.blow(6);
		assertEquals(true, b.isPopped());
		b.blow(3);
		assertEquals(true, b.isPopped());
		assertEquals(0, b.getRadius());
	}
	
	@Test
	public void testFillAfterPop() {
		b.blow(5);
		assertEquals(false, b.isPopped());
		b.pop();
		assertEquals(true, b.isPopped());
		b.blow(3);
		assertEquals(true, b.isPopped());
		assertEquals(0, b.getRadius());
	}
	
	/*
	@Test
	public void testNegatives() {
		b.blow(-1);
		//assertEquals(0, b.getRadius());
		b.blow(3);
		assertEquals(3, b.getRadius());
		b.deflate();
		assertEquals(false, b.isPopped());
	}
	*/
	
	@Test
	public void testSuccessiveBlows() {
		b.blow(0);
		assertEquals(0, b.getRadius());
		b.blow(1);
		assertEquals(1, b.getRadius());
		b.blow(2);
		assertEquals(3, b.getRadius());
		assertEquals(false, b.isPopped());
	}
}
