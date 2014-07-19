package client.parse;

public class ParsedTurnTracker
{
	String status;
	int currentTurn;
	int longestRoad;
	int largestArmy;
	
	public ParsedTurnTracker(String status, int currentTurn, int longestRoad, int largestArmy)
	{
		this.status = status;
		this.currentTurn = currentTurn;
		this.longestRoad = longestRoad;
		this.largestArmy = largestArmy;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public int getCurrentTurn()
	{
		return currentTurn;
	}
	
	public int getLongestRoad()
	{
		return longestRoad;
	}
	
	public int getLargestArmy()
	{
		return largestArmy;
	}
	
	public String printOut()
	{
		return "" + status + "," + currentTurn + "," + longestRoad + "," + largestArmy;
	}
}