package game;

import game.bank.Bank;
import game.board.BoardModel;
import java.util.HashSet;
import java.util.Set;
import player.Player;
import system.User;

/**
 *
 * @author Kevin MacMaster
 */
public class GameModel {
    private BoardModel board;
    private Set<Player> players;
    private Bank bank;

    public GameModel() {
        board = new BoardModel();
        bank = new Bank();
        players = new HashSet<Player>();
    }

    /**
     *
     * @param user to check for
     * @return true if user is in game
     */
    public boolean hasUser(User user) {
        return false;
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
    public Set<Player> getPlayers() {
        return players;
    }

    /**
     * @param players the players to set
     */
    public void addPlayers(Player aPlayer) {
        this.players.add(aPlayer);
    }

    /**
     * @return the bank
     */
    public Bank getBank() {
        return bank;
    }
    
}
