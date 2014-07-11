package player;

import static org.junit.Assert.*;
import game.cards.DevelopmentCard;
import game.cards.ResourceCard;
import game.cards.SpecialCard;
import game.pieces.BoardPiece;

import org.junit.Test;

import player.Player;
import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.PieceType;
import shared.definitions.SpecialCardType;
import system.User;
import shared.definitions.*;

public class PlayerTest {
	
	/**
	 * tests getAvailableBoard Piece of class Player
	 */
	@Test
	public void testGetAvailableBoardPiece() {
		User user = new User();
		Player player = new Player(CatanColor.RED, user);
		BoardPiece results = player.getAvailableBoardPiece(PieceType.CITY);
		assertEquals(results.getPieceType(), PieceType.CITY);
	}

	/**
	 * tests hasAvailableBoardPiece of class player
	 */
	@Test
	public void testHasAvailableBoardPiece() {
		User user = new User();
		Player player = new Player(CatanColor.RED, user);
		boolean results = player.hasAvailableBoardPiece(PieceType.CITY);
		assertEquals(true, results);
	}

	/**
	 * tests giveDevelopmentCard of class player
	 */
	@Test
	public void testGiveDevelopmentCard() {
		User user = new User();
		Player player = new Player(CatanColor.RED, user);
		DevelopmentCard developmentCard = new DevelopmentCard(DevCardType.MONOPOLY);
		
		player.addDevelopmentCard(developmentCard);
		player.giveDevelopmentCard(DevCardType.MONOPOLY);
		assertEquals(0, player.getDevelopmentCards().size());
	}
	
	/**
	 * tests addSpecialCard and giveSpecialCard of class player
	 */
	@Test
	public void testSpecialCard() {
		User user = new User();
		Player player = new Player(CatanColor.RED, user);
		SpecialCard specialCard = new SpecialCard(SpecialCardType.LONGEST_ROAD);
		
		//add special card
		player.addSpecialCard(specialCard);
		assertEquals(2, player.getPoints());
		
		//give special card
		player.giveSpecialCard(SpecialCardType.LONGEST_ROAD);
		assertEquals(0, player.getPoints());
	}

	/**
	 * tests addPoint of class player
	 */
	@Test
	public void testAddPoint() {
		User user = new User();
		Player player = new Player(CatanColor.RED, user);
		
		player.addPoint();
		assertEquals(1, player.getPoints());
	}

	/**
	 * tests getSoldiersPlayed of class player
	 */
	@Test
	public void testGetSoldiersPlayed() { //fix me!
		User user = new User();
		Player player = new Player(CatanColor.RED, user);
		
		//no soldiers
		assertEquals(0, player.getSoldiersPlayed());
		
		//soldiers
		DevelopmentCard developmentCard = new DevelopmentCard(DevCardType.SOLDIER);
		player.addDevelopmentCard(developmentCard);
		assertEquals(1, player.getSoldiersPlayed());
	}

	/**
	 * test getHandSize of class player
	 */
	@Test
	public void testGetHandSize() {
		User user =  new User();
		Player player = new Player(CatanColor.RED, user);
		
		//no cards
		assertEquals(0, player.getHandSize());
		
		//cards
		ResourceCard resourceCard = new ResourceCard(ResourceType.WOOD);
		player.addResourceCard(resourceCard);
		assertEquals(1, player.getHandSize());
		
		//after using card
		player.giveResourceCard(ResourceType.WOOD);
		assertEquals(0, player.getHandSize());
	}

	/**
	 * tests Equals of class player
	 */
	@Test
	public void testEquals() {
		User user = new User();
		User otherUser = new User();
		Player player = new Player(CatanColor.RED, user);
		Player samePlayer = new Player(CatanColor.RED, user);
		Player otherPlayer = new Player(CatanColor.RED, otherUser);
		
		//same player
		assertTrue(player.equals(samePlayer));
		
		//different players 
		assertFalse(player.equals(otherPlayer));
	}
}
