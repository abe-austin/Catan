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
        assertTrue(result!=null);
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
    }

    /**
     * Test of getPlayers method, of class GameModel.
     */
    @Test
    public void testGetPlayers() {
        System.out.println("getPlayers");
        GameModel instance = new GameModel();
        Player[] expResult = new Player[4];
        Player[] result = instance.getPlayers();
        assertArrayEquals(expResult, result);
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
    }

    /**
     * Test of getBank method, of class GameModel.
     */
    @Test
    public void testGetBank() {
        System.out.println("getBank");
        GameModel instance = new GameModel();
        Bank result = instance.getBank();
        assertTrue(result!=null);
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
        assertTrue(result!=null);
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
    }

    /**
     * Test of getTurnTracker method, of class GameModel.
     */
    @Test
    public void testGetTurnTracker() {
        System.out.println("getTurnTracker");
        GameModel instance = new GameModel();
        TurnTracker expResult = new TurnTracker();
        TurnTracker result = instance.getTurnTracker();
        assertTrue(result!=null);
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
    }

    /**
     * Test of getTradeOffer method, of class GameModel.
     */
    @Test
    public void testGetTradeOffer() {
        System.out.println("getTradeOffer");
        GameModel instance = new GameModel();
        TradeOffer expResult = new TradeOffer();
        TradeOffer result = instance.getTradeOffer();
        assertTrue(result!=null);
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
    }

    /**
     * Test of getWinner method, of class GameModel.
     */
    @Test
    public void testGetWinner() {
        System.out.println("getWinner");
        GameModel instance = new GameModel();
        int expResult = -1;
        int result = instance.getWinner();
        assertEquals(expResult, result);
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
    }
}