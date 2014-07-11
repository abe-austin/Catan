import static org.junit.Assert.*;

import org.junit.Test;

import player.Player;


public class PointsTest {

	@Test
	  public void testPoints() {
		Player player = new Player(Red.color, User new User());
		
		//test addPoint
		player.getPoints().addPoint();
		assertEquals(1, player.getPoints());
		
		//test subTractPoint
		player.getPoints().subtractPoint();
		assertEquals(0, player.getPoints());
      }
}
