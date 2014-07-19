package client.parse;

public class ParsedPort
{
	int x;
	int y;
	String type;
	int ratio;
	String direction;
	
	public ParsedPort(String type, int ratio, int x, int y, String direction)
	{
		this.x = x;
		this.y = y;
		this.type = type;
		this.ratio = ratio;
		this.direction = direction;
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
	
	public int getRatio()
	{
		return ratio;
	}
	
	public String getDirection()
	{
		return direction;
	}
	
	public String printOut()
	{
		return "" + type + "," + ratio + "," + direction + "," + x + "," + y;
	}
}