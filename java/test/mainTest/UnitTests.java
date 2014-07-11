package mainTest;

import static org.junit.Assert.*;
import org.junit.*;

public class UnitTests {
	
	@Before
	public void setup() {
	}

	@After
	public void teardown() {
	}
	
	@Test
	public void test_1() {
		assertEquals("OK", "OK");
		assertTrue(true);
		assertFalse(false);
	}
	
	public static void main(String[] args) {
		
		String[] testClasses = new String[] {
			"client.ServerAccessTest",
			"client.ServerPollerTest",
			"game.bank.BankTest",
			"game.cards.CardOwnerTest",
			"game.cards.DevelopmentCardTest",
			"game.cards.ResourceCardTest",
			"game.cards.SpecialCardTest",
			"game.pieces.ChatLogTest",
			"game.pieces.GameHistoryTest",
			"game.pieces.GameModelTest",
			"game.pieces.TradeOfferTest",
			"game.pieces.TurnTrackerTest",
			"player.PlayerTest",
			"player.PointsTest"
		};	
		
		org.junit.runner.JUnitCore.main(testClasses);
	}
}
