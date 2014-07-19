package client.parse;

public class ParsedLog
{
	String source;
	String message;
	
	public ParsedLog(String source, String message)
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