package player;

import static org.junit.Assert.*;
import game.cards.DevelopmentCard;
import game.cards.SpecialCard;
import game.pieces.BoardPiece;

import org.junit.Test;

import player.Player;
import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.PieceType;
import shared.definitions.SpecialCardType;

public class PlayerTest {

	@Test
	public void testGetAvaliableBoardPiece(PieceType type) {
		Player player = new Player(Red.color, User new User());
		BoardPiece results = player.getAvailiableBoardPiece(City);
		assertEquals(!null, results);
	}

	@Test
	public void testHasAvaiableBoardPiece(PieceType type) {
		Player player = new Player(Red.color, User new User());
		boolean results = player.hasAvaiableBoardPiece(City);
		assertEquals(true, results);
	}

	@Test
	public void testGiveDevelopmentCard(DevCardType devCardType) {
		Player player = new Player(Red.color, User new User());
		DevelopmentCard developmentCard = new DevelopmentCard(MONOPOLY);
		
		player.addDevelopmentCard(developmentCard);
		player.giveDevelopmentCard(MONOPOLY);
		assertEquals(0, player.getDevelopmentCards().size());
	}
	
	@Test
	public void testAddSpecialCard(SpecialCard specialCard) {
		Player player = new Player(Red.color, User new User());
		SpecialCard specialCard = new SpecialCard(LARGEST_ROAD);
		
		//add special card
		player.addSpecialCard(specialCard);
		assertEquals(2, player.getPoints());
		
		//give special card
		player.giveSpecialCard(LARGEST_ROAD);
		assertEquals(0, player.getPoints());
	}

	@Test
	public void testAddPoint() {
		Player player = new Player(Red.color, User new User());
		player.addPoint();
		assertEqauls(1, player.getPoints());
	}

	@Test
	public void testGetSoldiersPlayed() {
		Player player = new Player(Red.color, User new User());
		
		//no soldiers
		assertEquals(0, player.getSoldiersPlayed());
		
		//soldiers
		
		assertEquals(1, player.getSoldiersPlayed());
	}

	@Test
	public int testGetHandSize() {
		Player player = new Player(Red.color, User new User());
		
		//no cards
		assertEquals(0, player.getHandSize());
		
		//cards
		ResourceCard resourceCard = new ResourceCard(WOOD);
		player.addResourceCard(resourceCard);
		assertEquals(1, player.getHandSize());
		
		//after using card
		player.giveResourceCard(WOOD);
		assertEquals(0, player.getHandSize());
	}

	@Test
	public boolean testEquals(Object other) {
		User user = new User();
		User otherUser = new User();
		Player player = new Player(Red.color, user);
		Player samePlayer = new Player(Red.color, user);
		Player otherPlayer = new Player(Red.color, otherUser);
		
		//same player
		assertTrue(player.equals(samePlayer));
		
		//different players 
		assertFalse(player.equals(otherPlayer));
	}
}

