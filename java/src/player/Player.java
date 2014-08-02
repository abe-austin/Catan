package player;

import game.cards.CardOwner;
import game.cards.DevelopmentCard;
import game.cards.ResourceCard;
import game.cards.SpecialCard;
import game.pieces.BoardPiece;
import game.pieces.City;
import game.pieces.Road;
import game.pieces.Settlement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.PieceType;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.definitions.SpecialCardType;
import system.User;

public class Player extends CardOwner {
	private CatanColor color;
	private Set<BoardPiece> boardPieces;
	private Points points;
	private int soldiersPlayed;
	private User user;
	private ArrayList<PortType> playerPorts;
	private int index;
	private String username;
	private boolean discarded = false;

	public Player(CatanColor color, String username, int index, User user) {
		this.color = color;
		 this.user = user;
		this.username = username;
		this.index = index;
		soldiersPlayed = 0;
		points = new Points();
		playerPorts = new ArrayList<PortType>();

		boardPieces = new HashSet<BoardPiece>();
		resourceCards = new HashSet<ResourceCard>();
		developmentCards = new HashSet<DevelopmentCard>();
		specialCards = new HashSet<SpecialCard>();

		// initalize board pieces
		for (int i = 0; i < 5; i++)
			boardPieces.add(new Settlement(this));

		for (int i = 0; i < 4; i++)
			boardPieces.add(new City(this));

		for (int i = 0; i < 15; i++)
			boardPieces.add(new Road(this));
	}

	public Player(User user) {
		this.user = user;
		this.username = user.getUsername().getUsername();

		soldiersPlayed = 0;
		points = new Points();
		playerPorts = new ArrayList<PortType>();

		boardPieces = new HashSet<BoardPiece>();
		resourceCards = new HashSet<ResourceCard>();
		developmentCards = new HashSet<DevelopmentCard>();
		specialCards = new HashSet<SpecialCard>();

		// initalize board pieces
		for (int i = 0; i < 5; i++)
			boardPieces.add(new Settlement(this));

		for (int i = 0; i < 4; i++)
			boardPieces.add(new City(this));

		for (int i = 0; i < 15; i++)
			boardPieces.add(new Road(this));
	}

	public Player() {
		soldiersPlayed = 0;
		points = new Points();
		playerPorts = new ArrayList<PortType>();
		boardPieces = new HashSet<BoardPiece>();
		resourceCards = new HashSet<ResourceCard>();
		developmentCards = new HashSet<DevelopmentCard>();
		specialCards = new HashSet<SpecialCard>();

		// initalize board pieces
		for (int i = 0; i < 5; i++)
			boardPieces.add(new Settlement(this));

		for (int i = 0; i < 4; i++)
			boardPieces.add(new City(this));

		for (int i = 0; i < 15; i++)
			boardPieces.add(new Road(this));	}

	/**
	 * Returns an available board piece of a given type
	 * 
	 * @param type
	 *            of BoardPiece
	 * @return BoardPiece that is not active
	 */
	public BoardPiece getAvailableBoardPiece(PieceType type) {
		for (BoardPiece piece : boardPieces) {
			if (piece.getPieceType().equals(type) && !piece.isActive()){
				return piece;
			}
		}
		return null;
	}

	/**
	 * @param type
	 *            of board piece
	 * @return true if player has available piece of type
	 */
	public boolean hasAvailableBoardPiece(PieceType type) {
            return getAvailableBoardPiece(type) != null;
	}
        
        /**
         * @return number of roads on the board
         */
        public int getNumOfRoadsPlayed() {
            int number = 0;
            
            for(BoardPiece piece : boardPieces) {
                if (piece.getPieceType().equals(PieceType.ROAD) && piece.isActive())
                    number++;
            }
            
            return number;
        }

	/**
	 * In addition to Card Owner functionality this keeps track of soldier cards
	 * played
	 * 
	 * @post The number of soldiers played has now been incremented by one
	 */
	@Override
	public DevelopmentCard giveDevelopmentCard(DevCardType devCardType) {
		DevelopmentCard card = super.giveDevelopmentCard(devCardType);

		if (devCardType.equals(DevCardType.SOLDIER) && card != null)
			soldiersPlayed++;

		return null;
	}

	/**
	 * In addition to Card Owner functionality this keeps track of points that
	 * come from special cards
	 * 
	 * @post The players points have now been decremented by the special card's
	 *       specified value
	 */
	@Override
	public SpecialCard giveSpecialCard(SpecialCardType specialCardType) {
		SpecialCard card = super.giveSpecialCard(specialCardType);

		if (card != null) {
			points.subtractPoint();
			points.subtractPoint();
		}

		return card;
	}

	/**
	 * In addition to Card Owner functionality this keeps track of points that
	 * come from special cards
	 * 
	 * @post The players points have now been incremented by the the special
	 *       card's specified value
	 */
	@Override
	public void addSpecialCard(SpecialCard specialCard) {
		super.addSpecialCard(specialCard);
		points.addPoint();
		points.addPoint();
	}

	public int hasResourceNumber(ResourceType resourceType) {
		int number = 0;
		for (ResourceCard card : resourceCards) {
			if (card.getResourceType().equals(resourceType))
				number++;
		}

		return number;
	}

	public void addPort(PortType port) {
		playerPorts.add(port);
	}

	public ArrayList<PortType> getPorts() {
		return playerPorts;
	}

	/**
	 * @return the color
	 */
	public CatanColor getColor() {
		return color;
	}

	/**
	 * @return the points
	 */
	public int getPoints() {
		return points.getPoints();
	}

	/**
	 * @return true if 10 or more points false otherwise
	 */
	public boolean isVictory() {
		return points.isVictory();
	}

	/**
	 * Adds point to player
	 */
	public void addPoint() {
		points.addPoint();
	}

	/**
	 * @return the soldiersPlayed
	 */
	public int getSoldiersPlayed() {
		return soldiersPlayed;
	}

	/**
	 * @return the number of cards in hand (resources)
	 */
	public int getHandSize() {
		return resourceCards.size();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Set<BoardPiece> getBoardPieces() {
		return boardPieces;
	}

	public void setBoardPieces(Set<BoardPiece> boardPieces) {
		this.boardPieces = boardPieces;
	}

	public ArrayList<PortType> getPlayerPorts() {
		return playerPorts;
	}

	public void setPlayerPorts(ArrayList<PortType> playerPorts) {
		this.playerPorts = playerPorts;
	}

	public void setColor(CatanColor color) {
		this.color = color;
	}

	public void setPoints(Points points) {
		this.points = points;
	}

	public void setSoldiersPlayed(int soldiersPlayed) {
		this.soldiersPlayed = soldiersPlayed;
	}

	public void setResourceCards(HashSet<ResourceCard> resourceCards) {
		this.resourceCards = resourceCards;

	}

	public void setDevelopmentCards(HashSet<DevelopmentCard> developmentCards) {
		this.developmentCards = developmentCards;
	}

	public void setSpecialCards(HashSet<SpecialCard> specialCards) {
		this.specialCards = specialCards;

	}
	
	public void setPorts(ArrayList<PortType> playerPorts) {
		this.playerPorts = playerPorts;
	}

	@Override
	public String toString() {
		return getUsername();
	}

	public boolean equals(Object other) {
		if (other == this)
			return true;
		if (other == null)
			return false;
		if (getClass() != other.getClass())
			return false;

		Player p = (Player) other;
		if (!this.toString().equals(p.toString()))
			return false;

		return color.equals(p.getColor());
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the discarded
	 */
	public boolean hasDiscarded() {
		return discarded;
	}

	/**
	 * @param discarded
	 *            the discarded to set
	 */
	public void setDiscarded(boolean discarded) {
		this.discarded = discarded;
	}
}