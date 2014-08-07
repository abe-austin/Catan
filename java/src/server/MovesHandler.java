package server;

import client.parse.ParsedChat;
import client.parse.ParsedStructure;
import controller.PlayerReceivingResources;
import game.GameModel;
import game.TradeOffer;
import game.TurnTracker;
import game.bank.Bank;
import game.board.Corner;
import game.board.Edge;
import game.board.HexTile;
import game.board.ResourceTile;
import game.cards.CardOwner;
import game.cards.DevelopmentCard;
import game.cards.ResourceCard;
import game.pieces.BoardPiece;
import game.pieces.City;
import game.pieces.Road;
import game.pieces.Settlement;

import java.util.ArrayList;
import java.util.List;

import player.Player;
import shared.communication.*;
import shared.definitions.Command;
import shared.definitions.DevCardType;
import shared.definitions.HexType;
import shared.definitions.PieceType;
import shared.definitions.ResourceType;
import shared.definitions.ResourceTypeUtils;
import shared.definitions.SpecialCardType;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

/**
 *
 * @author Kevin MacMaster
 */
public class MovesHandler implements IHandler {
    private ServerController controller;
    
    public MovesHandler(ServerController controller) {
        this.controller = controller;
    }

    @Override
    public ServerResponse handle(String command, Object Json) {
        switch(command) {
            case "/moves/sendChat":
                return sendChat((SendChatParam)JsonUtils.convertFromJson(
                        SendChatParam.class, (String)Json));
            case "/moves/rollNumber":
                return rollNumber((RollNumberParam)JsonUtils.convertFromJson(
                        RollNumberParam.class, (String)Json));   
            case "/moves/robPlayer":
                return robPlayer((RobPlayerParam)JsonUtils.convertFromJson(
                        RobPlayerParam.class, (String)Json));      
            case "/moves/finishTurn":
                return finishTurn((FinishTurnParam)JsonUtils.convertFromJson(
                        FinishTurnParam.class, (String)Json));      
            case "/moves/buyDevCard":
                return buyDevCard((BuyDevCardParam)JsonUtils.convertFromJson(
                        BuyDevCardParam.class, (String)Json));   
            case "/moves/Year_Of_Plenty":
                return playYearOfPlenty((PlayYearOfPlentyParam)JsonUtils.convertFromJson(
                        PlayYearOfPlentyParam.class, (String)Json));   
            case "/moves/Road_Building":
                return playRoadBuilding((PlayRoadBuildingParam)JsonUtils.convertFromJson(
                        PlayRoadBuildingParam.class, (String)Json));   
            case "/moves/Soldier":
                return playSoldier((PlaySoldierParam)JsonUtils.convertFromJson(
                        PlaySoldierParam.class, (String)Json));   
            case "/moves/Monopoly":
                return playMonopoly((PlayMonopolyParam)JsonUtils.convertFromJson(
                        PlayMonopolyParam.class, (String)Json));   
            case "/moves/Monument":
                return playMonument((PlayMonumentParam)JsonUtils.convertFromJson(
                        PlayMonumentParam.class, (String)Json));   
            case "/moves/buildRoad":
                return buildRoad((BuildRoadParam)JsonUtils.convertFromJson(
                        BuildRoadParam.class, (String)Json));   
            case "/moves/buildSettlement":
                return buildSettlement((BuildSettlementParam)JsonUtils.convertFromJson(
                        BuildSettlementParam.class, (String)Json));   
            case "/moves/buildCity":
                return buildCity((BuildCityParam)JsonUtils.convertFromJson(
                        BuildCityParam.class, (String)Json));   
            case "/moves/offerTrade":
                return offerTrade((OfferTradeParam)JsonUtils.convertFromJson(
                        OfferTradeParam.class, (String)Json));   
            case "/moves/acceptTrade":
                return acceptTradeOffer((AcceptTradeParam)JsonUtils.convertFromJson(
                        AcceptTradeParam.class, (String)Json));   
            case "/moves/maritimeTrade":
                return maritimeTrade((MaritimeTradeParam)JsonUtils.convertFromJson(
                        MaritimeTradeParam.class, (String)Json));   
            case "/moves/discardCards":
                return discardCards((DiscardCardsParam)JsonUtils.convertFromJson(
                        DiscardCardsParam.class, (String)Json));   
            default:
                return null;
        }
    }
    
    /**
     * Sends Chat Message to Other users
     * 
     * @param param chat message
     * @return success or failure
     */
    public ServerResponse sendChat(SendChatParam param) {
        ServerResponse response = null;
        
        GameModel game = controller.getGameModel();
        Player player = game.getPlayers()[param.getPlayerIndex()];
        game.getGameHistory().getChatlog().
                addChatLine(new ParsedChat(player.getUsername(), param.getContent()));
      
        game.getGameHistory().getGameCommands().add(new Command(
        		controller.getCurrentCookie().getUsername(),
        		"sent a chat message."));
   
        return response;
    }
    
    /**
     * Applies Roll to GameModel
     * 
     * @param param roll info
     * @return success or failure
     */
    public ServerResponse rollNumber(RollNumberParam param) {
        GameModel game = controller.getGameModel();
        List<HexTile> hexes = game.getBoard().getHexes();
    	ArrayList<PlayerReceivingResources> resourceChanges = new ArrayList<>();
    	if( param.getNumber()!= 7){
            for(HexTile tile : hexes) {
                if(tile.getType() != HexType.DESERT && tile.getType() != HexType.WATER && !tile.getHasRobber()) {
                    ResourceTile resourceTile = (ResourceTile)tile;

                    if(!resourceTile.getToken().hitTest(param.getNumber()))
                        continue;

                    for(Corner corner : resourceTile.getCorners()) {
                        if(corner.hasStructure()) {
                            BoardPiece boardPiece = corner.getStructure();
                            PlayerReceivingResources receiving = getPlayerResources(boardPiece, resourceTile);
                            resourceChanges.add(receiving);
                        }
                    }                
                }
            }

            for(PlayerReceivingResources changes : resourceChanges) {
                    Player player = changes.getPlayer();
                    ResourceType type = changes.getResourceType();
                    int amount = changes.getAmount();

                    CardOwner.changeOwnerResource(player, game.getBank(), type, amount);
            }     
            game.getTurnTracker().setStatus("playing");
        }
        else{
            boolean needToDiscard=false;
            for(Player player:game.getPlayers()){
                if( player.getHandSize()>7){
                    needToDiscard=true;
                    player.setNeedsToDiscard(true);
                }
                else{
                    player.setNeedsToDiscard(false);
                }
            }
            if(needToDiscard){
                game.getTurnTracker().setStatus("discarding");
            }
            else{
                game.getTurnTracker().setStatus("robbing");
            }
        }
        
    	controller.getGameModel().incrementVersion();
    	
        game.getGameHistory().getGameCommands().add(new Command(
        		controller.getCurrentCookie().getUsername(), 
        		"Rolled a " + param.getNumber() + "."));

        return new ServerResponse(200, game);
    }
    
    /**
     * Sets up resource distribution for a given hex
     * 
     * @param boardPiece
     * @param resourceTile
     * @return 
     */
    public PlayerReceivingResources getPlayerResources(BoardPiece boardPiece, ResourceTile resourceTile) {    
        int amount = (boardPiece.getPieceType() == PieceType.CITY) ? 2 : 1;  
        String name = boardPiece.getOwnerName();
        Player player = null;
        for (Player p:controller.getGameModel().getPlayers()){
            if( p.getUsername().equals(name))
                player=p;
        }
        return new PlayerReceivingResources(player, resourceTile.getResourceType(), amount);

    }
    
    /**
     * Applies End of Turn to GameModel
     * 
     * @param param end of turn info
     * @return success or failure
     */
    public ServerResponse finishTurn(FinishTurnParam param) {
        GameModel game = controller.getGameModel();
        
        boolean isFirst = false;
        boolean isSecond = false;
        
        for(Player player : game.getPlayers()) {            
            if(player.getPoints() < 2)
                isSecond = true;
            if(player.getPoints() < 1)
                isFirst = true;
        }
        
        if(param.getPlayerIndex() == 3 && isSecond && game.getPlayers()[3].getPoints() == 1)
            isFirst = true;
        
        if(isFirst || isSecond) {
            game.getTurnTracker().setSetup(true);
        } else if(game.getTurnTracker().isSetup() && param.getPlayerIndex() == 0) {
          if(!game.getTurnTracker().isFirstTurn())  
              game.getTurnTracker().setFirstTurn(true);
          else {
              game.getTurnTracker().setSetup(false);
          }
        } else {
            game.getTurnTracker().setSetup(false);
        }
            
        if(isFirst)
            firstTurn(param, game);
        else if(isSecond)
            secondTurn(param, game);
        else
            regularTurn(param, game);
        
        controller.getGameModel().incrementVersion();
        
        game.getGameHistory().getGameCommands().add(new Command(
        		controller.getCurrentCookie().getUsername(), 
        		"finished their turn."));
        
        return new ServerResponse(200, controller.getGameModel());
    }
    
    /**
     * First Turn end turn
     * 
     * @param param
     * @param game 
     */
    public void firstTurn(FinishTurnParam param, GameModel game) {
        TurnTracker tracker = game.getTurnTracker();    
        tracker.setStatus("first Turn");
        
            if(param.getPlayerIndex() < 3)
                tracker.setCurrentTurn(param.getPlayerIndex()+1);
            else
                tracker.setCurrentTurn(param.getPlayerIndex());
    }
    
    /**
     * Second turn handler
     * 
     * @param param
     * @param game 
     */
    public void secondTurn(FinishTurnParam param, GameModel game) {
        TurnTracker tracker = game.getTurnTracker();                         
        tracker.setStatus("second Turn");        
        tracker.setCurrentTurn(param.getPlayerIndex()-1);
   
        boolean isSecond = false;
        for(Player player : game.getPlayers()) {
            if(player.getPoints() < 2)
                isSecond = true;
        }
        
        if(isSecond) {            
            tracker.setStatus("second Turn");
            if(param.getPlayerIndex() > 0)
                tracker.setCurrentTurn(param.getPlayerIndex()-1);
           
        } 
        else if(tracker.getStatus().equals("second Turn")){
            tracker.setStatus("rolling");
            tracker.setCurrentTurn(param.getPlayerIndex());
        }
        else {
            regularTurn(param, game);
        }

        //game.incrementVersion();
    }
    
    /**
     * Regular end turn
     * 
     * @param param
     * @param game 
     */
    public void regularTurn(FinishTurnParam param, GameModel game) {
        TurnTracker tracker = game.getTurnTracker();                
        
        if(!tracker.isSetup())
            tracker.setCurrentTurn(param.getPlayerIndex()+1);   
        
        if(tracker.getCurrentTurn() > 3)
            tracker.setCurrentTurn(0);
        
        setDevCardsToOld(param.getPlayerIndex());
        for(Player player:game.getPlayers()){
            player.setDiscarded(false);
            player.setNeedsToDiscard(false);
        }
        
        tracker.setStatus("rolling");
        
        //game.incrementVersion();
    }
    
    private void setDevCardsToOld(int playerIndex){
        Player player= controller.getGameModel().getPlayers()[playerIndex];
        for(DevelopmentCard card:player.getDevelopmentCards()){
            card.setOld(true);
        }
    }
    
    /**
     * Applies Rob to GameModel
     * 
     * @param param rob info
     * @return success or failure
     * @post resource change from one player to another
     */
    public ServerResponse robPlayer(RobPlayerParam param) {
        GameModel game = controller.getGameModel();
        game.getBoard().getRobber().updateLocation(param.getLocation());
        Player robber = game.getPlayers()[param.getPlayerIndex()];
        String victimName;
        if (param.getVictimIndex()!=-1){
            Player victim = game.getPlayers()[param.getVictimIndex()];

            if(victim.getHandSize() > 0) {
                int index = (int)(Math.random() * victim.getHandSize());
                ResourceCard card = (ResourceCard)victim.getResourceCards().toArray()[index];
                robber.addResourceCard(victim.giveResourceCard(card.getResourceType()));
            }
            victimName= victim.getUsername();
        }
        else{
            victimName = "no one";
        }
        game.getTurnTracker().setStatus("playing");
        game.incrementVersion();

        game.getGameHistory().getGameCommands().add(new Command(
                        controller.getCurrentCookie().getUsername(), 
                        "moved the robber and robbed " + victimName + "."));

        return new ServerResponse(200, controller.getGameModel());
    }
    
    /**
     * Applies Purchase of DevCard to GameModel
     * 
     * @pre player can buy DevCard
     * @param param purchase info
     * @return success or failure
     * @post player has another DevCard
     */
    public ServerResponse buyDevCard(BuyDevCardParam param) {
        GameModel game = controller.getGameModel();
        Player player = game.getPlayers()[param.getPlayerIndex()];
        
        game.getBank().addResourceCard(player.giveResourceCard(ResourceType.WHEAT));
        game.getBank().addResourceCard(player.giveResourceCard(ResourceType.SHEEP));
        game.getBank().addResourceCard(player.giveResourceCard(ResourceType.ORE));
        
        DevelopmentCard devCard = game.getBank().giveDevelopmentCard(null);
        if( devCard.getDevelopmentType()== DevCardType.MONUMENT){
            devCard.setOld(true);
        }
        else{
            devCard.setOld(false);
        }
        player.addDevelopmentCard(devCard);
        
        game.incrementVersion();
        
        game.getGameHistory().getGameCommands().add(new Command(
        		controller.getCurrentCookie().getUsername(), 
        		"bought a development card."));
        
        return new ServerResponse(200, controller.getGameModel());
    }
    
    /**
     * Applies Play of Monopoly DevCard to GameModel
     * 
     * @pre player has monopoly card
     * @param param play card info
     * @return success or failure
     * @post player has one less monopoly card
     */
    public ServerResponse playMonopoly(PlayMonopolyParam param) {
        GameModel game = controller.getGameModel();
        Player player = game.getPlayers()[param.getPlayerIndex()];        
        player.giveDevelopmentCard(DevCardType.MONOPOLY);
        ResourceType resource = ResourceTypeUtils.getResourceType(param.getResource());
        
        for(Player person : game.getPlayers()) {
            while(!person.equals(player) && person.hasResource(resource))
                player.addResourceCard(person.giveResourceCard(resource));
        }
        
        game.incrementVersion();
        
        game.getGameHistory().getGameCommands().add(new Command(
        		controller.getCurrentCookie().getUsername(), 
        		"played a " + param.getResource() + " monopoly card."));
        
        return new ServerResponse(200, controller.getGameModel());
    }
    
    /**
     * Applies Play of RoadBuilding DevCard to GameModel
     * 
     * @pre player has two roads available
     * @param param play card info
     * @return success or failure
     * @post player has two more roads built
     */
    public ServerResponse playRoadBuilding(PlayRoadBuildingParam param) {       
        Player player = controller.getGameModel().getPlayers()[param.getPlayerIndex()];
        player.giveDevelopmentCard(DevCardType.ROAD_BUILD);
        
        MapLocationParam map1 = param.getSpot1();
        MapLocationParam map2 = param.getSpot2();
        GameModel game = controller.getGameModel();        
        
        HexTile tile = game.getBoard().getHexTileAt(map1.getX(), map1.getY());
        Edge edge = tile.getEdge(map1.getDirection());
        Road road = (Road)player.getAvailableBoardPiece(PieceType.ROAD);
        edge.buildStructure(road);
        road.setActive(true);
        ParsedStructure parsedStruct = new ParsedStructure(param.getPlayerIndex(), map1.getX(), map1.getY(), map1.getDirection(), "ROAD");
        controller.getGameModel().getBoard().addStructure(parsedStruct);
        
        HexTile tile2 = game.getBoard().getHexTileAt(map2.getX(), map2.getY());
        Edge edge2 = tile2.getEdge(map2.getDirection());
        Road road2 = (Road)player.getAvailableBoardPiece(PieceType.ROAD);
        edge2.buildStructure(road2);
        road2.setActive(true);
        ParsedStructure parsedStruct2 = new ParsedStructure(param.getPlayerIndex(), map2.getX(), map2.getY(), map2.getDirection(), "ROAD");
        controller.getGameModel().getBoard().addStructure(parsedStruct2);
        
        checkMostRoads(game.getPlayers()[param.getPlayerIndex()]);
        game.incrementVersion();
        
        game.getGameHistory().getGameCommands().add(new Command(
        		controller.getCurrentCookie().getUsername(), 
        		"played a road building card."));
        
        return new ServerResponse(200, controller.getGameModel());
    }
    
    /**
     * Applies Play of Soldier DevCard to GameModel
     * 
     * @param param play card info
     * @return success or failure
     * @post robber is moved
     */
    public ServerResponse playSoldier(PlaySoldierParam param) {
        GameModel game = controller.getGameModel();        
        Player player = game.getPlayers()[param.getPlayerIndex()];
        player.giveDevelopmentCard(DevCardType.SOLDIER);
        //player.setSoldiersPlayed(player.getSoldiersPlayed()+1);
        
        RobPlayerParam rob = new RobPlayerParam(param.getType(),param.getPlayerIndex(),
                                            param.getVictimIndex(), param.getLocation());   
        
        checkLargestArmy(player);
        game.incrementVersion();
        
        game.getGameHistory().getGameCommands().add(new Command(
        		controller.getCurrentCookie().getUsername(), 
        		"played a soldier card."));
        
        return robPlayer(rob);
    }
    
    /**
     * Checks largest army and changes owner of card if needed
     * 
     * @param challenger 
     */
    public void checkLargestArmy(Player challenger) {
        Bank bank = controller.getGameModel().getBank();
        
        if(bank.hasLargestArmy()&& challenger.getSoldiersPlayed() > 2) {
            challenger.addSpecialCard(bank.giveSpecialCard(SpecialCardType.LARGEST_ARMY));
            controller.getGameModel().getTurnTracker().setLargestArmy(challenger.getIndex());
            //controller.getGameModel().incrementVersion();
            if(challenger.isVictory()){
                controller.getGameModel().setWinner(challenger.getIndex());
            }
            return;
        }    
        
        for(Player player : controller.getGameModel().getPlayers()) {
            if(player.hasLargestArmy() && !player.equals(challenger)) {
                if(player.getSoldiersPlayed() < challenger.getSoldiersPlayed()) {
                    challenger.addSpecialCard(bank.giveSpecialCard(SpecialCardType.LARGEST_ARMY));
                    controller.getGameModel().getTurnTracker().setLargestArmy(challenger.getIndex());
                    //controller.getGameModel().incrementVersion();
                    if(challenger.isVictory()){
                        controller.getGameModel().setWinner(challenger.getIndex());
                    }
                }
            }
        }
    }
    
    /**
     * Applies Play of YearOfPlenty DevCard to GameModel
     * 
     * @param param play card info
     * @return success or failure
     * @post player has two more resource cards
     */
    public ServerResponse playYearOfPlenty(PlayYearOfPlentyParam param) {
        GameModel game = controller.getGameModel();        
        Player player = game.getPlayers()[param.getPlayerIndex()];
        player.giveDevelopmentCard(DevCardType.YEAR_OF_PLENTY);
        
        player.addResourceCard(game.getBank().giveResourceCard(
                ResourceTypeUtils.getResourceType(param.getResource1())));
        player.addResourceCard(game.getBank().giveResourceCard(
                ResourceTypeUtils.getResourceType(param.getResource2())));
        
        game.incrementVersion();
        
        game.getGameHistory().getGameCommands().add(new Command(
        		controller.getCurrentCookie().getUsername(), 
        		"played a year of plenty card."));
        
        return new ServerResponse(200, controller.getGameModel());
    }
    
    /**
     * Applies Play of Monument DevCard to GameModel
     * 
     * @param param play card info
     * @return success or failure
     * @post player has one more point
     */
    public ServerResponse playMonument(PlayMonumentParam param) {
        GameModel game = controller.getGameModel();        
        Player player = game.getPlayers()[param.getPlayerIndex()];
        player.giveDevelopmentCard(DevCardType.MONUMENT);
        addPoint(player);
        
        game.incrementVersion();
        
        game.getGameHistory().getGameCommands().add(new Command(
        		controller.getCurrentCookie().getUsername(), 
        		"played a monument card."));
        
        return new ServerResponse(200, controller.getGameModel());
    }
    
    /**
     * Applies Road built to GameModel
     * 
     * @pre player has available road and is connected (if not setup)
     * @param param board piece info
     * @return success or failure
     */
    public ServerResponse buildRoad(BuildRoadParam param) {
        try {
	        MapLocationParam map = param.getRoadLocation();
	        GameModel game = controller.getGameModel();        
	        Player player = game.getPlayers()[param.getPlayerIndex()];
	        
	        HexTile tile = game.getBoard().getHexTileAt(map.getX(), map.getY());
	        Edge edge = tile.getEdge(map.getDirection());
	        Road road = (Road)player.getAvailableBoardPiece(PieceType.ROAD);
	        edge.buildStructure(road);
	        road.setActive(true);
	        
	        if(!param.isFree()) {
	            CardOwner.changeOwnerResource(game.getBank(), player, ResourceType.WOOD);
	            CardOwner.changeOwnerResource(game.getBank(), player, ResourceType.BRICK);
	        }
	        
	        checkMostRoads(game.getPlayers()[param.getPlayerIndex()]);
	        ParsedStructure parsedStruct = new ParsedStructure(param.getPlayerIndex(), map.getX(), map.getY(), map.getDirection(), "ROAD");
	        controller.getGameModel().getBoard().addStructure(parsedStruct);//Send a parsedStructure
	        
	        controller.getGameModel().incrementVersion();
	        
	        game.getGameHistory().getGameCommands().add(new Command(
	        		controller.getCurrentCookie().getUsername(), 
	        		"Built a Road."));
	        
		    return new ServerResponse(200, controller.getGameModel());
        }
    	catch(Exception e) {
    		e.printStackTrace();
			return new ServerResponse(400, "Failed");
    	}	
    }
    
    /**
     * Checks for most roads, changes owner of longest road if needed
     * 
     * @param challenger 
     */
    public void checkMostRoads(Player challenger) {
        Bank bank = controller.getGameModel().getBank();
        
        if(bank.hasLongestRoad() && challenger.getNumOfRoadsPlayed() > 4) {
            challenger.addSpecialCard(bank.giveSpecialCard(SpecialCardType.LONGEST_ROAD));
            controller.getGameModel().getTurnTracker().setLongestRoad(challenger.getIndex());
            //controller.getGameModel().incrementVersion();
            if(challenger.isVictory()){
                controller.getGameModel().setWinner(challenger.getIndex());
            }
            return;
        }            
        
        for(Player player : controller.getGameModel().getPlayers()) {
            if(player.hasLongestRoad() && !player.equals(challenger)) {
                if(player.getNumOfRoadsPlayed() < challenger.getNumOfRoadsPlayed()) {
                    challenger.addSpecialCard(player.giveSpecialCard(SpecialCardType.LONGEST_ROAD));
                    controller.getGameModel().getTurnTracker().setLongestRoad(challenger.getIndex());
                    if(challenger.isVictory()){
                        controller.getGameModel().setWinner(challenger.getIndex());
                    }
                    //controller.getGameModel().incrementVersion();
                }                    
            }
        }
    }
    
    /**
     * Applies Settlement built to GameModel
     * 
     * @pre player has available settlement and is being build in correct place
     * @param param board piece info
     * @return success or failure
     */
    public ServerResponse buildSettlement(BuildSettlementParam param) {
    	try{
	        MapLocationParam map = param.getVertexLocation();
	        GameModel game = controller.getGameModel();
	        //So from here we could probably update the BoardModel with the structure info and extract it over on the client side
	        //So it should include probably just the ParsedStructure
	        Player player = game.getPlayers()[param.getPlayerIndex()];
	        addPoint(player);
	        
	        HexTile tile = game.getBoard().getHexTileAt(map.getX(), map.getY());
	        Corner corner = tile.getCorner(map.getDirection());
	        Settlement settlement = (Settlement)player.getAvailableBoardPiece(PieceType.SETTLEMENT);
	        System.out.println("Settlement: "+settlement);
	        System.out.println("Corner: "+corner);
	        corner.buildStructure(settlement);
	        settlement.setActive(true);
	        
	        if(!param.isFree()) {
	            CardOwner.changeOwnerResource(game.getBank(), player, ResourceType.WHEAT);
	            CardOwner.changeOwnerResource(game.getBank(), player, ResourceType.SHEEP);
	            CardOwner.changeOwnerResource(game.getBank(), player, ResourceType.BRICK);
	            CardOwner.changeOwnerResource(game.getBank(), player, ResourceType.WOOD);
	        } else {
	            // Get resource hexes adjacent and give player resources
	            for(VertexLocation locs : corner.getLocations()) {
	                HexTile nextTo = game.getBoard().getHexTileAt(locs.getX(), locs.getY());
	                if(!nextTo.getType().equals(HexType.DESERT) && !nextTo.getType().equals(HexType.WATER)) {
	                    CardOwner.changeOwnerResource(player, game.getBank(), ((ResourceTile)nextTo).getResourceType());
	                }
	            }
	        }
	
	        ParsedStructure parsedStruct = new ParsedStructure(param.getPlayerIndex(), map.getX(), map.getY(), map.getDirection(), "SETTLEMENT");
	        controller.getGameModel().getBoard().addStructure(parsedStruct);//Send a parsedStructure
	        controller.getGameModel().incrementVersion();
	        
	        game.getGameHistory().getGameCommands().add(new Command(
	        		controller.getCurrentCookie().getUsername(), 
	        		"built a settlement."));
	        
	        return new ServerResponse(200, controller.getGameModel());
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return new ServerResponse(400, "Failed");
    	}
    }
    
    /**
     * Applies City built to GameModel
     * 
     * @pre player has available city and city is to be built on settlement
     * @param param board piece info
     * @return success or failure
     * @post player has one less available city and one more settlement
     */
    public ServerResponse buildCity(BuildCityParam param) {
        MapLocationParam map = param.getVertexLocation();
        GameModel game = controller.getGameModel();        
        Player player = game.getPlayers()[param.getPlayerIndex()];
       addPoint(player);
        
        CardOwner.changeOwnerResource(game.getBank(), player, ResourceType.ORE, 3);
        CardOwner.changeOwnerResource(game.getBank(), player, ResourceType.WHEAT, 2);
        
        HexTile tile = game.getBoard().getHexTileAt(map.getX(), map.getY());
        Corner corner = tile.getCorner(map.getDirection());
        City city = (City)player.getAvailableBoardPiece(PieceType.CITY);        
        corner.buildStructure(city);
        city.setActive(true);
        
        ParsedStructure parsedStruct = new ParsedStructure(param.getPlayerIndex(), map.getX(), map.getY(), map.getDirection(), "CITY");
        controller.getGameModel().getBoard().addStructure(parsedStruct);//Send a parsedStructure
        controller.getGameModel().incrementVersion();
        
        game.getGameHistory().getGameCommands().add(new Command(
        		controller.getCurrentCookie().getUsername(), 
        		"built a city."));
        
        return new ServerResponse(200, controller.getGameModel());

    }
    
    /**
     * Sends Trade Offer
     * 
     * @pre player has resources to trade
     * @param param trade info
     * @return success or failure
     */
    public ServerResponse offerTrade(OfferTradeParam param) {
        GameModel game = controller.getGameModel();
        
        OfferParam offer = param.getOffer();
        TradeOffer trade = new TradeOffer();
        
        trade.setBrick(offer.getBrick());
        trade.setOre(offer.getOre());
        trade.setSheep(offer.getSheep());
        trade.setWheat(offer.getWheat());
        trade.setWood(offer.getWood());
        trade.setReceiverIndex(param.getReceiver());
        trade.setSenderIndex(param.getPlayerIndex());
        
        game.setTradeOffer(trade);
        controller.getGameModel().incrementVersion();
        
        return new ServerResponse(200, controller.getGameModel());
    }
    
    /**
     * Accepts Trade Offer
     * 
     * @pre players have resources to trade
     * @param param trade info
     * @return success or failure
     * @post resources are traded
     */
    public ServerResponse acceptTradeOffer(AcceptTradeParam param) {
        if(param.isWillAccept()) {            
            GameModel game = controller.getGameModel();
            TradeOffer offer = game.getTradeOffer();
            Player sender = game.getPlayers()[offer.getSenderIndex()];
            Player receiver = game.getPlayers()[offer.getReceiverIndex()];            
            
            if(offer.getBrick() > 0) {
                for(int i = 0; i < offer.getBrick(); i++)
                    receiver.addResourceCard(sender.giveResourceCard(ResourceType.BRICK));
            } else {
                for(int i = 0; i > offer.getBrick(); i--)
                    receiver.addResourceCard(sender.giveResourceCard(ResourceType.BRICK));
            }
            
            if(offer.getSheep()> 0) {
                for(int i = 0; i < offer.getSheep(); i++)
                    receiver.addResourceCard(sender.giveResourceCard(ResourceType.SHEEP));
            } else {
                for(int i = 0; i > offer.getSheep(); i--)
                    receiver.addResourceCard(sender.giveResourceCard(ResourceType.SHEEP));
            }
            
            if(offer.getWheat()> 0) {
                for(int i = 0; i < offer.getWheat(); i++)
                    receiver.addResourceCard(sender.giveResourceCard(ResourceType.WHEAT));
            } else {
                for(int i = 0; i > offer.getWheat(); i--)
                    receiver.addResourceCard(sender.giveResourceCard(ResourceType.WHEAT));
            }
            
            if(offer.getWood() > 0) {
                for(int i = 0; i < offer.getWood(); i++)
                    receiver.addResourceCard(sender.giveResourceCard(ResourceType.WOOD));
            } else {
                for(int i = 0; i > offer.getWood(); i--)
                    receiver.addResourceCard(sender.giveResourceCard(ResourceType.WOOD));
            }
            
            if(offer.getOre()> 0) {
                for(int i = 0; i < offer.getOre(); i++)
                    receiver.addResourceCard(sender.giveResourceCard(ResourceType.ORE));
            } else {
                for(int i = 0; i > offer.getOre(); i--)
                    receiver.addResourceCard(sender.giveResourceCard(ResourceType.ORE));
            }
            controller.getGameModel().setTradeOffer(new TradeOffer());
            controller.getGameModel().incrementVersion();
            return new ServerResponse(200, controller.getGameModel());
        } else {
            
            controller.getGameModel().setTradeOffer(new TradeOffer());
            controller.getGameModel().incrementVersion();
            return new ServerResponse(200, controller.getGameModel());
        }
    }
    
    /**
     * Maritime Trade applied to Player/Bank
     * 
     * @pre player and bank have enough resources of each
     * @param param trade info
     * @return success or failure
     * @post player and bank have new resources
     */
    public ServerResponse maritimeTrade(MaritimeTradeParam param) {
        GameModel game = controller.getGameModel();
        
        Player player = game.getPlayers()[param.getPlayerIndex()];
        ResourceType give = ResourceTypeUtils.getResourceType(param.getInputResource());
        ResourceType receive = ResourceTypeUtils.getResourceType(param.getOutputResource());
        
        player.addResourceCard(game.getBank().giveResourceCard(receive));
        
        for(int i = 0; i < param.getRatio(); i++)
            game.getBank().addResourceCard(player.giveResourceCard(give));
        //controller.getGameModel().incrementVersion();
        
        return new ServerResponse(200, controller.getGameModel());
    }
    
    /**
     * Discard Card applied to GameModel
     * 
     * @pre player has cards that will be discarded
     * @param param discard info
     * @return success or failure
     * @post player has less cards
     */
    public ServerResponse discardCards(DiscardCardsParam param) {
        ServerResponse response = null;
        
        GameModel game = controller.getGameModel();
        if(game != null) {
            Player player = game.getPlayers()[param.getPlayerIndex()];
            
            for(int i=0;i<param.getDiscardedCards().getBrick();i++)
                game.getBank().addResourceCard(player.giveResourceCard(ResourceType.BRICK));
            for(int i=0;i<param.getDiscardedCards().getOre();i++)
                game.getBank().addResourceCard(player.giveResourceCard(ResourceType.ORE));
            for(int i=0;i<param.getDiscardedCards().getSheep();i++)
                game.getBank().addResourceCard(player.giveResourceCard(ResourceType.SHEEP));
            for(int i=0;i<param.getDiscardedCards().getWheat();i++)
                game.getBank().addResourceCard(player.giveResourceCard(ResourceType.WHEAT));
            for(int i=0;i<param.getDiscardedCards().getWood();i++)
                game.getBank().addResourceCard(player.giveResourceCard(ResourceType.WOOD));
            
            player.setDiscarded(true);
            //controller.getGameModel().incrementVersion();
            boolean discardingDone = true;
            for(Player p: game.getPlayers()){
                if(p.getNeedsToDiscard() && !p.hasDiscarded())
                    discardingDone = false;
            }
            if(discardingDone){
                game.getTurnTracker().setStatus("robbing");
                for(Player p: game.getPlayers()){
                    p.setNeedsToDiscard(false);
                }
            }
            else{
                game.getTurnTracker().setStatus("discarding");
            }
            
            response = new ServerResponse(200, controller.getGameModel());
            
        } else {
            response = new ServerResponse(400, "No game of that type");
        }
        
        return response;
    }
    private void addPoint(Player player){
        player.addPoint();
        if (player.isVictory()){
            controller.getGameModel().setWinner(player.getIndex());
        }
    }
}
