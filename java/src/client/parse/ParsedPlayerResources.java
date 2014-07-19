package client.parse;

public class ParsedPlayerResources
{
	int brick;
	int wood;
	int sheep;
	int wheat;
	int ore;
	
	public ParsedPlayerResources(int brick, int wood, int sheep, int wheat, int ore)
	{
		this.brick = brick;
		this.wood = wood;
		this.sheep = sheep;
		this.wheat = wheat;
		this.ore = ore;
	}
	
	public int getBrick()
	{
		return brick;
	}
	
	public int getWood()
	{
		return wood;
	}
	
	public int getSheep()
	{
		return sheep;
	}
	
	public int getWheat()
	{
		return wheat;
	}
	
	public int getOre()
	{
		return ore;
	}
}