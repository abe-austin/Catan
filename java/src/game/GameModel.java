package game;

import game.bank.Bank;
import game.board.BoardModel;
import player.Player;
import system.User;

/**
 *
 * @author Kevin and Cory :)
 */
public class GameModel {
    private BoardModel board;
    private Player[] players;
    private Bank bank;
    private User user;
    //private ChatLog chatLog; ---gameHistory has a chatLog
    private GameHistory gameHistory;
    private TurnTracker turnTracker;
    private TradeOffer tradeOffer;
    private int version;
    private int winner;
    private int id;
    private boolean checkDiscard;
    private String gameName;

    public GameModel() {
        board = new BoardModel(this.getPlayers());
        bank = new Bank();
        players = new Player[4];
        gameHistory = new GameHistory();
        turnTracker = new TurnTracker();
        tradeOffer = new TradeOffer();
        version = 0;
        winner = -1;
        checkDiscard = false;
        gameName = "";
        id = -1;
    }
    
    
    public void buildBoard() {
    	board.constructWorld();
    }
    

    /**
     *
     * @param user to check for
     * @return true if user is in game
     */
    public boolean hasUser(User user) {
        return false;
    }

    public void setGameName(String name) {
        gameName = name;
    }
    
    public String getGameName() {
        return gameName;
    }
    
    public void setGameId(int id) {
        this.id = id;
    }
    
    public int getGameId() {
        return id;
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
    public Player[] getPlayers() {
        return players;
    }

    /**
     * @param player the players to set
     */
    public void addPlayers(Player player, int location) {
        this.players[location] = player;
    }

    /**
     * @return the bank
     */
    public Bank getBank() {
        return bank;
    }

//	public ChatLog getChatLog() {
//		return chatLog;
//	}
//
//	public void setChatLog(ChatLog chatLog) {
//		this.chatLog = chatLog;
//	}

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
	
	public void incrementVersion() {
		this.version++;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
		board.setPlayers(players);
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isRandomHexes() {
		return board.isRandomHexes();
	}

	public void setRandomHexes(boolean randomHexes) {
		board.setRandomHexes(randomHexes);
	}

	public boolean isRandomNumbers() {
		return board.isRandomNumbers();
	}

	public void setRandomNumbers(boolean randomNumbers) {
		board.setRandomNumbers(randomNumbers);
	}

	public boolean isRandomPorts() {
		return board.isRandomPorts();
	}

	public void setRandomPorts(boolean randomPorts) {
		board.setRandomPorts(randomPorts);
	}

    /**
     * @return the checkDiscard
     */
    public boolean isCheckDiscard() {
        return checkDiscard;
    }

    /**
     * @param checkDiscard the checkDiscard to set
     */
    public void setCheckDiscard(boolean checkDiscard) {
        this.checkDiscard = checkDiscard;
    }
}
