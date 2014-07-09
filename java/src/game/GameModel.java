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
    private ChatLog chatLog;
    private GameHistory gameHistory;
    private TurnTracker turnTracker;
    private TradeOffer tradeOffer;
    private int version;
    private int winner;

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

	public ChatLog getChatLog() {
		return chatLog;
	}

	public void setChatLog(ChatLog chatLog) {
		this.chatLog = chatLog;
	}

	public GameHistory getGameHistory() {
		return gameHistory;
	}

	public void setGameHistory(GameHistory gameHistory) {
		this.gameHistory = gameHistory;
	}

	public TurnTracker getTurnTracker() {
		return turnTracker;
	}

	public void setTurnTracker(TurnTracker turnTracker) {
		this.turnTracker = turnTracker;
	}

	public TradeOffer getTradeOffer() {
		return tradeOffer;
	}

	public void setTradeOffer(TradeOffer tradeOffer) {
		this.tradeOffer = tradeOffer;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}
    
    
}
