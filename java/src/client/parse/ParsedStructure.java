package client.parse;

public class ParsedStructure
{
	int owner;
	int x;
	int y;
	String direction;
	String type;
	
	public ParsedStructure(int owner, int x, int y, String direction, String type)
	{
		this.x = x;
		this.y = y;
		this.type = type;
		this.direction = direction;
		this.owner = owner;
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
	
	public int getOwner()
	{
		return owner;
	}
	
	public String getDirection()
	{
            if(direction.contains("\"")){
                StringBuilder ss = new StringBuilder();
                for(char c:direction.toCharArray()){
                    if(c !='\"'){
                        ss.append(c);
                    }
                }
                direction=ss.toString();
            }
		return direction;
	}
	
	public String printOut()
	{
		return "" + owner + "," + x + "," + y + "," + direction + "," + type;
	}
}