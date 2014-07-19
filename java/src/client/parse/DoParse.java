package client.parse;

import game.ChatLog;
import game.GameModel;
import game.TradeOffer;
import game.TurnTracker;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;



public class DoParse
{
    private GameModel gameModel=new GameModel();
	public DoParse()
	{}
	
	public void process(String input)
	{
		try 
		{
	        Scanner scan = new Scanner(input);
	        while (scan.hasNextLine()) 
	        {
	        	String i = scan.nextLine();
	        	i = parseDeck(i);
	        	i = parseMap(i);
	        	i = parsePlayers(i);
	        	i = parseLog(i);
	        	i = parseChat(i);
	        	i = parseBank(i);
	        	i = parseTurnTracker(i);
                        if (i.contains("tradeOffer")){
                            i = parseTradeOffer(i);
                        }
	        	i = parseWinner(i);
	        	i = parseVersion(i);
	        }
	        scan.close();
	    } 
	    catch (Exception e) {e.printStackTrace();}
	}
	
	public String parseDeck(String input)
	{
		String[] divide = input.split("},");
		String stayHere = divide[0];
		input = input.split("map")[1];
		divide = stayHere.split("\\{");
		stayHere = divide[2];
		divide = stayHere.split(",");
		
		int yearOfPlenty = 0;
		int monopoly = 0;
		int soldier = 0;
		int roadBuilding = 0;
		int monument = 0;

		for(int i = 0; i < divide.length; i++)
		{
			String[] theTwo = divide[i].split(":");
			if(theTwo[0].contains("yearOfPlenty"))
				yearOfPlenty = Integer.parseInt(theTwo[1]);
			if(theTwo[0].contains("monopoly"))
				monopoly = Integer.parseInt(theTwo[1]);
			if(theTwo[0].contains("soldier"))
				soldier = Integer.parseInt(theTwo[1]);
			if(theTwo[0].contains("roadBuilding"))
				roadBuilding = Integer.parseInt(theTwo[1]);
			if(theTwo[0].contains("monument"))
				monument = Integer.parseInt(theTwo[1]);
		}		
		
		//CALL FUNCTIONS TO SET yearOfPlenty, monopoly, soldier, roadBuilding, AND monument TO THE gameModel
		gameModel.getBank().setDevCards(monopoly, roadBuilding, yearOfPlenty, monument, soldier);
		return input;
	}
	
	public String parseMap(String input)
	{
		String theMap = input.split("players\":")[0];
		input = input.split("players\":")[1];
		String theHexes = theMap.split("roads\":")[0];
		String theRest = theMap.split("roads\":")[1];
		String theRoads = theRest.split("cities\":")[0];
		theRest = theRest.split("cities\":")[1];
		String theCities = theRest.split("settlements\":")[0];
		theRest = theRest.split("settlements\":")[1];
		String theSettlements = theRest.split("radius\":")[0];
		theRest = theRest.split("ports\":")[1];
		String thePorts = theRest.split("robber\":")[0];
		theRest = theRest.split("robber\":")[1];
		String theRobber = theRest.split("}")[0];
		
		parseHexes(theHexes);
		parseStructure(theRoads, "ROAD");
		parseStructure(theCities, "CITY");
		parseStructure(theSettlements, "SETTLEMENT");
		parsePorts(thePorts);
		parseRobber(theRobber);
		return input;
	}
	
	
	public void parseHexes(String input)
	{
		ArrayList<ParsedTile> parsedTiles = new ArrayList<ParsedTile>();
		
		String workWith = input.split("hexes\":\\[\\{")[1];
		String[] eachHex = workWith.split("},\\{\"");
		
		for(int i = 0; i < eachHex.length; i++)
		{			
			String xStr = eachHex[i].split("x\":")[1];
			int x = Integer.parseInt(xStr.split(",")[0]);
			
			String yStr = eachHex[i].split("y\":")[1];
			int y = Integer.parseInt(yStr.split("}")[0]);
			
			if(eachHex[i].startsWith("resource"))
			{
				String gettingResource = eachHex[i].split("\":\"")[1];
				String resource = gettingResource.split("\",\"")[0];
				String gettingNumber = eachHex[i].split("number\":")[1];
				int number = Integer.parseInt(gettingNumber.split("}")[0]);
				ParsedTile pT = new ParsedTile(x, y, resource, number);
				parsedTiles.add(pT);
			}
			else//desert
			{				
				ParsedTile pT = new ParsedTile(x, y, "DESERT", -1);
				parsedTiles.add(pT);
			}
		}
		//CALL FUNCTIONS TO SET parsedTiles TO THE gameModel
	}
	
	public void parseStructure(String input, String type)
	{
		ArrayList<ParsedStructure> structures = new ArrayList<ParsedStructure>();
		
		String[] theStructures = input.split("}},\\{");
		
		if(input.contains("}},{"))
			for(int i = 0; i < theStructures.length; i++)
			{
				String gettingOwner = theStructures[i].split("owner\":")[1];
				int owner = Integer.parseInt(gettingOwner.split(",")[0]);
				String gettingDirection = theStructures[i].split("direction\":\"")[1];
				String direction = gettingDirection.split("\"")[0];
				String xStr = theStructures[i].split("x\":")[1];
				int x = Integer.parseInt(xStr.split(",")[0]);
				String yStr = theStructures[i].split("y\":")[1];
				int y = Integer.parseInt(yStr.split("}")[0]);
			
				ParsedStructure pS = new ParsedStructure(owner, x, y, direction, type);
				structures.add(pS);
			}
		//CALL FUNCTIONS TO SET structures TO THE gameModel
	}
	
	public void parsePorts(String input)
	{
		String thePorts[] = input.split("}},\\{");
		ArrayList<ParsedPort> ports = new ArrayList<ParsedPort>();
		
		for(int i = 0; i < thePorts.length; i++)
		{
			String gettingRatio = thePorts[i].split("ratio\":")[1];
			int ratio = Integer.parseInt(gettingRatio.split(",")[0]);
			String type = "three";
			if(ratio == 2)
			{
				String gettingType = thePorts[i].split("resource\":\"")[1];
				type = gettingType.split("\"")[0];
			}
			String gettingDirection = thePorts[i].split("direction\":\"")[1];
			String direction = gettingDirection.split("\"")[0];
			String xStr = thePorts[i].split("x\":")[1];
			int x = Integer.parseInt(xStr.split(",")[0]);
			String yStr = thePorts[i].split("y\":")[1];
			int y = Integer.parseInt(yStr.split("}")[0]);
			
			ParsedPort pP = new ParsedPort(type, ratio, x, y, direction);
			ports.add(pP);
		}
		//CALL FUNCTIONS TO SET ports TO THE gameModel
	}
	
	public void parseRobber(String input)
	{
		String xStr = input.split("x\":")[1];
		int x = Integer.parseInt(xStr.split(",")[0]);
		String yStr = input.split("y\":")[1];
		int y = Integer.parseInt(yStr.split("}")[0]);
		
		ParsedRobber robber = new ParsedRobber(x, y);
	}
	//CALL FUNCTIONS TO SET robber TO THE gameModel
	
	public String parsePlayers(String input)
	{
		String[] divide = input.split("log\":");
		input = divide[1];
		String allPlayers = divide[0];
		String[] thePlayers = allPlayers.split("resources\":");
		
		ArrayList<ParsedPlayer> players = new ArrayList<ParsedPlayer>();
		
		for(int i = 1; i < thePlayers.length; i++)
		{
			ParsedPlayer pP = parsePlayer(thePlayers[i]);
			players.add(pP);
		}
		//CALL FUNCTIONS TO SET players TO THE gameModel
		return input;
	}
	
	public ParsedPlayer parsePlayer(String input)
	{
		String gettingResources = input.split("oldDevCards\":")[0];
		String theRest = input.split("oldDevCards\":")[1];
		String gettingOldDevs = theRest.split("newDevCards\":")[0];
		theRest = theRest.split("newDevCards\":")[1];
		String gettingNewDevs = theRest.split("roads\":")[0];
		theRest = theRest.split("},")[1];

		//Resources
		String gettingBrick = gettingResources.split("brick\":")[1];
		int brick = Integer.parseInt(gettingBrick.split(",")[0]);
		String gettingWood = gettingResources.split("wood\":")[1];
		int wood = Integer.parseInt(gettingWood.split(",")[0]);
		String gettingSheep = gettingResources.split("sheep\":")[1];
		int sheep = Integer.parseInt(gettingSheep.split(",")[0]);
		String gettingWheat = gettingResources.split("wheat\":")[1];
		int wheat = Integer.parseInt(gettingWheat.split(",")[0]);
		String gettingOre = gettingResources.split("ore\":")[1];
		int ore = Integer.parseInt(gettingOre.split("}")[0]);
		
		//OldDevs
		String gettingYearOfPlenty = gettingOldDevs.split("yearOfPlenty\":")[1];
		int yearOfPlenty = Integer.parseInt(gettingYearOfPlenty.split(",")[0]);
		String gettingMonopoly = gettingOldDevs.split("monopoly\":")[1];
		int monopoly = Integer.parseInt(gettingMonopoly.split(",")[0]);
		String gettingSoldier = gettingOldDevs.split("soldier\":")[1];
		int soldier = Integer.parseInt(gettingSoldier.split(",")[0]);
		String gettingRoadBuilding = gettingOldDevs.split("roadBuilding\":")[1];
		int roadBuilding = Integer.parseInt(gettingRoadBuilding.split(",")[0]);
		String gettingMonument = gettingOldDevs.split("monument\":")[1];
		int monument = Integer.parseInt(gettingMonument.split("}")[0]);
		
		//NewDevs
		String gettingYearOfPlenty2 = gettingNewDevs.split("yearOfPlenty\":")[1];
		int yearOfPlenty2 = Integer.parseInt(gettingYearOfPlenty2.split(",")[0]);
		String gettingMonopoly2 = gettingNewDevs.split("monopoly\":")[1];
		int monopoly2 = Integer.parseInt(gettingMonopoly2.split(",")[0]);
		String gettingSoldier2 = gettingNewDevs.split("soldier\":")[1];
		int soldier2 = Integer.parseInt(gettingSoldier2.split(",")[0]);
		String gettingRoadBuilding2 = gettingNewDevs.split("roadBuilding\":")[1];
		int roadBuilding2 = Integer.parseInt(gettingRoadBuilding2.split(",")[0]);
		String gettingMonument2 = gettingNewDevs.split("monument\":")[1];
		int monument2 = Integer.parseInt(gettingMonument2.split("}")[0]);
		
		//theRest
		String gettingRoads = theRest.split("roads\":")[1];
		int roads = Integer.parseInt(gettingRoads.split(",")[0]);
		String gettingCities = theRest.split("cities\":")[1];
		int cities = Integer.parseInt(gettingCities.split(",")[0]);
		String gettingSettlements = theRest.split("settlements\":")[1];
		int settlements = Integer.parseInt(gettingSettlements.split(",")[0]);
		String gettingSoldiers = theRest.split("soldiers\":")[1];
		int soldiers = Integer.parseInt(gettingSoldiers.split(",")[0]);
		String gettingVictoryPoints = theRest.split("victoryPoints\":")[1];
		int victoryPoints = Integer.parseInt(gettingVictoryPoints.split(",")[0]);
		String gettingMonuments = theRest.split("monuments\":")[1];
		int monuments = Integer.parseInt(gettingMonuments.split(",")[0]);
		String gettingPlayedDevCard = theRest.split("playedDevCard\":")[1];
		boolean playedDevCard = Boolean.parseBoolean(gettingPlayedDevCard.split(",")[0]);
		String gettingDiscarded = theRest.split("discarded\":")[1];
		boolean discarded = Boolean.parseBoolean(gettingDiscarded.split(",")[0]);
		String gettingPlayerID = theRest.split("playerID\":")[1];
		int playerID = Integer.parseInt(gettingPlayerID.split(",")[0]);
		String gettingPlayerIndex = theRest.split("playerIndex\":")[1];
		int playerIndex = Integer.parseInt(gettingPlayerIndex.split(",")[0]);
		String gettingName = theRest.split("name\":")[1];
		String name = gettingName.split(",")[0];
		String gettingColor = theRest.split("color\":")[1];
		String color = gettingColor.split(",")[0];
		
		ParsedPlayerResources pPR = new ParsedPlayerResources(brick, wood, sheep, wheat, ore);
		ParsedPlayerDevCards oldCards = new ParsedPlayerDevCards("OLD", yearOfPlenty, monopoly, soldier, roadBuilding, monument);
		ParsedPlayerDevCards newCards = new ParsedPlayerDevCards("NEW", yearOfPlenty2, monopoly2, soldier2, roadBuilding2, monument2);
		
		return new ParsedPlayer(pPR, oldCards, newCards, roads, cities, settlements, soldiers, victoryPoints, monuments, playedDevCard, 
				discarded, playerID, playerIndex, name, color);
		
	}
	
	public String parseLog(String input)
	{
		String theLog = input.split("chat\":")[0];
		input = input.split("chat\":")[1];
		String[] logs = theLog.split("},\\{");
		ArrayList<ParsedLog> parsedLogs = new ArrayList<ParsedLog>();
		
		if(input.contains("}},{"))
			for(int i = 0; i < logs.length; i++)
			{
				String gettingSource = logs[i].split("source\":\"")[1];
				String source = gettingSource.split("\"")[0];
				String gettingMessage = logs[i].split("message\":\"")[1];
				String message = gettingMessage.split("\"")[0];
				ParsedLog pL = new ParsedLog(source, message);
				parsedLogs.add(pL);
			}
		//CALL FUNCTIONS TO SET parsedLogs TO THE gameModel
		return input;
	}
	
	public String parseChat(String input)//NOTE: I assume my method here is correct, but our test file had no chat entries, so I can't be sure
	{
		String theChat = input.split("bank\":")[0];
		input = input.split("bank\":")[1];
		String[] chats = theChat.split("},\\{");
		ArrayList<ParsedChat> parsedChats = new ArrayList<ParsedChat>();
		
		if(input.contains("}},{"))
			for(int i = 0; i < chats.length; i++)
			{
				String gettingSource = chats[i].split("source\":\"")[1];
				String source = gettingSource.split("\"")[0];
				String gettingMessage = chats[i].split("message\":\"")[1];
				String message = gettingMessage.split("\"")[0];
				ParsedChat pC = new ParsedChat(source, message);
				parsedChats.add(pC);
			}
		//CALL FUNCTIONS TO SET parsedChats TO THE gameModel
                
                for(ParsedChat chatLine:parsedChats){                
                    gameModel.getGameHistory().getChatlog().addChatLine(chatLine);
                }
		return input;
	}
	
	public String parseBank(String input)
	{
		String theBank = input.split("turnTracker\":")[0];
		input = input.split("turnTracker\":")[1];
		
		String gettingBrick = theBank.split("brick\":")[1];
		int brick = Integer.parseInt(gettingBrick.split(",")[0]);
		String gettingWood = theBank.split("wood\":")[1];
		int wood = Integer.parseInt(gettingWood.split(",")[0]);
		String gettingSheep = theBank.split("sheep\":")[1];
		int sheep = Integer.parseInt(gettingSheep.split(",")[0]);
		String gettingWheat = theBank.split("wheat\":")[1];
		int wheat = Integer.parseInt(gettingWheat.split(",")[0]);
		String gettingOre = theBank.split("ore\":")[1];
		int ore = Integer.parseInt(gettingOre.split("}")[0]);
		
		ParsedBank bank = new ParsedBank(brick, wood, sheep, wheat, ore);
		//CALL FUNCTIONS TO SET bank TO THE gameModel
                gameModel.getBank().setResources(brick, ore, sheep, wheat, wood);
		return input;
	}
	
	public String parseTurnTracker(String input)
	{
		String theTurnTracker = input.split("winner\":")[0];
                if (input.contains("tradeOffer")){
                    input= input.split("tradeOffer\":")[1];
                }
                else{
                    input = input.split("winner\":")[1];
                }
		String gettingStatus = theTurnTracker.split("status\":\"")[1];
		String status = gettingStatus.split("\"")[0];
		String gettingCurrentTurn = theTurnTracker.split("currentTurn\":")[1];
		int currentTurn = Integer.parseInt(gettingCurrentTurn.split(",")[0]);
		String gettingLongestRoad = theTurnTracker.split("longestRoad\":")[1];
		int longestRoad = Integer.parseInt(gettingLongestRoad.split(",")[0]);
		String gettingLargestArmy= theTurnTracker.split("largestArmy\":")[1];
		int largestArmy = Integer.parseInt(gettingLargestArmy.split("}")[0]);
		
		ParsedTurnTracker turnTracker = new ParsedTurnTracker(status, currentTurn, longestRoad, largestArmy);
		//CALL FUNCTIONS TO SET turnTracker TO THE gameModel
                boolean road=false;
                boolean army=false;
                if(longestRoad==-1){
                    road=true;
                }
                if(largestArmy==-1){
                    army=true;
                }
                gameModel.getBank().setSpecialCards(army, road);
                gameModel.setTurnTracker(new TurnTracker(status,currentTurn,longestRoad,largestArmy));
		return input;
	}
	public String parseTradeOffer(String input){
            String theTradeOffer = input.split("winner\":")[0];
            input = input.split("winner\":")[1];
            
            String gettingSender = theTradeOffer.split("sender\":")[1]; 
            int sender = Integer.parseInt(gettingSender.split(",")[0]);
            String gettingReceiver = theTradeOffer.split("receiver\":")[1];
            int receiver = Integer.parseInt(gettingReceiver.split(",")[0]);
		
            String gettingBrick = theTradeOffer.split("brick\":")[1];
            int brick = Integer.parseInt(gettingBrick.split(",")[0]);
            String gettingWood = theTradeOffer.split("wood\":")[1];
            int wood = Integer.parseInt(gettingWood.split(",")[0]);
            String gettingSheep = theTradeOffer.split("sheep\":")[1];
            int sheep = Integer.parseInt(gettingSheep.split(",")[0]);
            String gettingWheat = theTradeOffer.split("wheat\":")[1];
            int wheat = Integer.parseInt(gettingWheat.split(",")[0]);
            String gettingOre = theTradeOffer.split("ore\":")[1];
            int ore = Integer.parseInt(gettingOre.split("}")[0]);
            
            ParsedTradeOffer tradeOffer = new ParsedTradeOffer(sender, receiver, brick, ore, sheep, wood, wheat);
            //CALL Functions to set trade offer to gameModel
            gameModel.setTradeOffer(new TradeOffer(sender, receiver, brick, ore, sheep, wood, wheat));
            return input;
        }
	public String parseWinner(String input)
	{
		int winner = Integer.parseInt(input.split(",\"version\":")[0]);
		input = input.split(",\"version\":")[1];
		//CALL FUNCTIONS TO SET winner TO THE gameModel
                gameModel.setWinner(winner);
		return input;
	}
	
	public String parseVersion(String input)
	{
		int version = Integer.parseInt(input.split("}")[0]);
		//CALL FUNCTIONS TO SET version TO THE gameModel
                gameModel.setVersion(version);
		input = "";
		return input;
	}
	
	
}