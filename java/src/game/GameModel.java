package game;

import game.bank.Bank;
import game.board.BoardModel;
import java.util.HashSet;
import java.util.Set;
import player.player;

/**
 *
 * @author Kevin MacMaster
 */
public class GameModel {
    private BoardModel board;
    private Set<player> players;
    private Bank bank;

    public GameModel() {
        board = new BoardModel();
        bank = new Bank();
        players = new HashSet<player>();
    }

    /**
     * @return the board
     */
    public BoardModel getBoard() {
        return board;
    }

    /**
     * @param board the board to set
     */
    public void setBoard(BoardModel board) {
        this.board = board;
    }

    /**
     * @return the players
     */
    public Set<player> getPlayers() {
        return players;
    }

    /**
     * @param players the players to set
     */
    public void addPlayers(player aPlayer) {
        this.players.add(aPlayer);
    }

    /**
     * @return the bank
     */
    public Bank getBank() {
        return bank;
    }
    
}
