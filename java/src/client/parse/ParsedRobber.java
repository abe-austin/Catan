package client.parse;

public class ParsedRobber
{
	int x;
	int y;
	
	public ParsedRobber(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public String printOut()
	{
		return "" + x + "," + y;
	}
}