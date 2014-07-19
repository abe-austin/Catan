package client.parse;

public class ParsedTile
{
	int x;
	int y;
	String type;
	int numberTile;
	
	public ParsedTile(int x, int y, String type, int numberTile)
	{
		this.x = x;
		this.y = y;
		this.type = type;
		this.numberTile = numberTile;		
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public String getType()
	{
		return type;
	}
	
	public int getNumberTile()
	{
		return numberTile;
	}
	
	public String printOut()
	{
		return "" + x + "," + y + "," + type + "," + numberTile;
	}
}