/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.bank.Bank;
import game.board.BoardModel;
import org.junit.Test;
import static org.junit.Assert.*;
import player.Player;
import system.User;

/**
 *
 * @author cjkirk09
 */
public class GameModelTest {
    
    public GameModelTest() {
    }

    /**
     * Test of hasUser method, of class GameModel.
     */
    @Test
    public void testHasUser() {
        System.out.println("hasUser");
        User user = null;
        GameModel instance = new GameModel();
        boolean expResult = false;
        boolean result = instance.hasUser(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBoard method, of class GameModel.
     */
    @Test
    public void testGetBoard() {
        System.out.println("getBoard");
        GameModel instance = new GameModel();
        BoardModel expResult = null;
        BoardModel result = instance.getBoard();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBoard method, of class GameModel.
     */
    @Test
    public void testSetBoard() {
        System.out.println("setBoard");
        BoardModel board = null;
        GameModel instance = new GameModel();
        instance.setBoard(board);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayers method, of class GameModel.
     */
    @Test
    public void testGetPlayers() {
        System.out.println("getPlayers");
        GameModel instance = new GameModel();
        Player[] expResult = null;
        Player[] result = instance.getPlayers();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPlayers method, of class GameModel.
     */
    @Test
    public void testAddPlayers() {
        System.out.println("addPlayers");
        Player player = null;
        int location = 0;
        GameModel instance = new GameModel();
        instance.addPlayers(player, location);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBank method, of class GameModel.
     */
    @Test
    public void testGetBank() {
        System.out.println("getBank");
        GameModel instance = new GameModel();
        Bank expResult = null;
        Bank result = instance.getBank();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGameHistory method, of class GameModel.
     */
    @Test
    public void testGetGameHistory() {
        System.out.println("getGameHistory");
        GameModel instance = new GameModel();
        GameHistory expResult = null;
        GameHistory result = instance.getGameHistory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGameHistory method, of class GameModel.
     */
    @Test
    public void testSetGameHistory() {
        System.out.println("setGameHistory");
        GameHistory gameHistory = null;
        GameModel instance = new GameModel();
        instance.setGameHistory(gameHistory);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTurnTracker method, of class GameModel.
     */
    @Test
    public void testGetTurnTracker() {
        System.out.println("getTurnTracker");
        GameModel instance = new GameModel();
        TurnTracker expResult = null;
        TurnTracker result = instance.getTurnTracker();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTurnTracker method, of class GameModel.
     */
    @Test
    public void testSetTurnTracker() {
        System.out.println("setTurnTracker");
        TurnTracker turnTracker = null;
        GameModel instance = new GameModel();
        instance.setTurnTracker(turnTracker);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTradeOffer method, of class GameModel.
     */
    @Test
    public void testGetTradeOffer() {
        System.out.println("getTradeOffer");
        GameModel instance = new GameModel();
        TradeOffer expResult = null;
        TradeOffer result = instance.getTradeOffer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTradeOffer method, of class GameModel.
     */
    @Test
    public void testSetTradeOffer() {
        System.out.println("setTradeOffer");
        TradeOffer tradeOffer = null;
        GameModel instance = new GameModel();
        instance.setTradeOffer(tradeOffer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVersion method, of class GameModel.
     */
    @Test
    public void testGetVersion() {
        System.out.println("getVersion");
        GameModel instance = new GameModel();
        int expResult = 0;
        int result = instance.getVersion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVersion method, of class GameModel.
     */
    @Test
    public void testSetVersion() {
        System.out.println("setVersion");
        int version = 0;
        GameModel instance = new GameModel();
        instance.setVersion(version);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWinner method, of class GameModel.
     */
    @Test
    public void testGetWinner() {
        System.out.println("getWinner");
        GameModel instance = new GameModel();
        int expResult = 0;
        int result = instance.getWinner();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWinner method, of class GameModel.
     */
    @Test
    public void testSetWinner() {
        System.out.println("setWinner");
        int winner = 0;
        GameModel instance = new GameModel();
        instance.setWinner(winner);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayers method, of class GameModel.
     */
    @Test
    public void testSetPlayers() {
        System.out.println("setPlayers");
        Player[] players = null;
        GameModel instance = new GameModel();
        instance.setPlayers(players);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBank method, of class GameModel.
     */
    @Test
    public void testSetBank() {
        System.out.println("setBank");
        Bank bank = null;
        GameModel instance = new GameModel();
        instance.setBank(bank);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}