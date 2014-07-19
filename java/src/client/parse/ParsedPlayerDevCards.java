package client.parse;

public class ParsedPlayerDevCards
{
	String type;
	int yearOfPlenty;
	int monopoly;
	int soldier;
	int roadBuilding;
	int monument;
	
	public ParsedPlayerDevCards(String type, int yearOfPlenty, int monopoly, int soldier, int roadBuilding, int monument)
	{
		this.type = type;
		this.yearOfPlenty = yearOfPlenty;
		this.monopoly = monopoly;
		this.soldier = soldier;
		this.roadBuilding = roadBuilding;
		this.monument = monument;
	}
	
	public int getYearOfPlenty()
	{
		return yearOfPlenty;
	}
	
	public int getMonopoly()
	{
		return yearOfPlenty;
	}
	
	public int getSoldier()
	{
		return soldier;
	}
	
	public int getRoadBuilding()
	{
		return roadBuilding;
	}
	
	public int getMonument()
	{
		return monument;
	}
	
	public String getType()
	{
		return type;
	}
}