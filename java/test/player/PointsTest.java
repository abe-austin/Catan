package player;

import static org.junit.Assert.*;
import org.junit.Test;


public class PointsTest {

	/**
	 * test addPoint and subtractPoint of class Points
	 */
	@Test
	  public void testPoints() {
		Points points = new Points();
		
		//addPoint
		points.addPoint();
		assertEquals(1, points.getPoints());
		
		//subtractPoint
		points.subtractPoint();
		assertEquals(0, points.getPoints());
      }
	
	/**
	 * tests isVictory of class Points
	 */
	@Test
	public void TestisVictory() {
		Points points = new Points();
		
		for(int i = 0; i < 10; i++) {
			points.addPoint();
		}
		
		assertTrue(points.isVictory());
	}
}
