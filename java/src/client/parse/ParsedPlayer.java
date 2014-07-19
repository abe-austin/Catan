package client.parse;

public class ParsedPlayer
{
	ParsedPlayerResources playerResources;
	ParsedPlayerDevCards oldCards;
	ParsedPlayerDevCards newCards;
	int roads;
	int cities;
	int settlements;
	int soldiers;
	int victoryPoints;
	int monuments;
	boolean playedDevCard;
	boolean discarded;
	int playerID;
	int playerIndex;
	String name;
	String color;
	
	public ParsedPlayer(ParsedPlayerResources playerResources, ParsedPlayerDevCards oldCards, ParsedPlayerDevCards newCards, 
			int roads, int cities, int settlements, int soldiers, int victoryPoints, int monuments, boolean playedDevCard,
			boolean discarded, int playerID, int playerIndex, String name, String color)
	{
		this.playerResources = playerResources;
		this.oldCards = oldCards;
		this.newCards = newCards;
		this.roads = roads;
		this.cities = cities;
		this.settlements = settlements;
		this.soldiers = soldiers;
		this.victoryPoints = victoryPoints;
		this.monuments = monuments;
		this.playedDevCard = playedDevCard;
		this.discarded = discarded;
		this.playerID = playerID;
		this.playerIndex = playerIndex;
		this.name = name;
		this.color = color;		
	}
	
	public ParsedPlayerResources getPlayerResources()
	{
		return playerResources;
	}
	
	public ParsedPlayerDevCards getOldCards()
	{
		return oldCards;
	}
	
	public ParsedPlayerDevCards getNewCards()
	{
		return newCards;
	}
	
	public int getRoads()
	{
		return roads;
	}
	
	public int getCities()
	{
		return cities;
	}
	
	public int getSettlements()
	{
		return settlements;
	}
	
	public int getSoldiers()
	{
		return soldiers;
	}
	
	public int getVictoryPoints()
	{
		return victoryPoints;
	}
	
	public int getMonuments()
	{
		return monuments;
	}
	
	public boolean getPlayedDevCard()
	{
		return playedDevCard;
	}
	
	public boolean getDiscarded()
	{
		return discarded;
	}
	
	public int getPlayedID()
	{
		return playerID;
	}
	
	public int getPlayedIndex()
	{
		return playerIndex;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getColor()
	{
		return color;
	}
}