package client.parse;

public class ParsedChat
{
	String source;
	String message;
	
	public ParsedChat(String source, String message)
	{
		this.source = source;
		this.message = message;
	}
	
	public String getSource()
	{
		return source;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public String printOut()
	{
		return source + " : " + message;
	}
}