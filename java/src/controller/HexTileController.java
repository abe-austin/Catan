package controller;

import java.util.ArrayList;
import java.util.List;

import client.data.RobPlayerInfo;
import game.GameModel;
import game.board.BoardModel;
import game.board.Corner;
import game.board.Edge;
import game.board.HexTile;
import game.board.ResourceTile;
import game.pieces.BoardPiece;
import game.pieces.Robber;
import player.Player;
import shared.definitions.HexType;
import shared.definitions.PieceType;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

/**
 *
 * @author Kevin MacMaster
 */
public class HexTileController {
    private GameModel gameModel;
    private Boolean allowDisconnect;

    public void updateGameModel(GameModel model) {
        gameModel = model;
        allowDisconnect = false;
    }

    /**
     * This method is called whenever the user is trying to place a road on the map.
     * It is called by the view for each "mouse move" event. The returned value tells
     * the view whether or not to allow the road to be placed at the specified location.
     *
     * @param edgeLoc The proposed road location
     * @return true if the road can be placed at edgeLoc, false otherwise
     */
    public boolean canPlaceRoad(Player player, EdgeLocation edgeLoc) {//MapController --goes in GamePlay
    	HexTile theHex = gameModel.getBoard().getHexTileAt(edgeLoc.getHexLoc().getX(), edgeLoc.getHexLoc().getY());
    	Edge e = null;
    	
    	switch(edgeLoc.getDir()) {
    		case North: e = theHex.northEdge; break;
    		case NorthEast: e = theHex.northEastEdge; break;
    		case SouthEast: e = theHex.southEastEdge; break;
    		case South: e = theHex.southEdge; break;
    		case SouthWest: e = theHex.southWestEdge; break;
    		case NorthWest: e = theHex.northWestEdge; break;
    	}
    	
        return gameModel.getBoard().canBuildRoad(e, player, allowDisconnect);
    }

    /**
     * This method is called whenever the user is trying to place a settlement on the map.
     * It is called by the view for each "mouse move" event. The returned value tells
     * the view whether or not to allow the settlement to be placed at the specified location.
     *
     * @param vertLoc The proposed settlement location
     * @return true if the settlement can be placed at vertLoc, false otherwise
     */
    public boolean canPlaceSettlement(Player player, VertexLocation vertLoc) {//MapController --goes in GamePlay
    	HexTile theHex = gameModel.getBoard().getHexTileAt(vertLoc.getHexLoc().getX(), vertLoc.getHexLoc().getY());
    	Corner c = null;
    	
    	switch(vertLoc.getDir()) {
    		case East: c = theHex.eastCorner; break;
    		case NorthEast: c = theHex.northEastCorner; break;
    		case SouthEast: c = theHex.southEastCorner; break;
    		case West: c = theHex.westCorner; break;
    		case SouthWest: c = theHex.southWestCorner; break;
    		case NorthWest: c = theHex.northWestCorner; break;
    	}
    	
        return gameModel.getBoard().canBuildSettlement(c, player, allowDisconnect);
    }

    /**
     * This method is called whenever the user is trying to place a city on the map.
     * It is called by the view for each "mouse move" event. The returned value tells
     * the view whether or not to allow the city to be placed at the specified location.
     *
     * @param vertLoc The proposed city location
     * @return true if the city can be placed at vertLoc, false otherwise
     */
    public boolean canPlaceCity(Player player, VertexLocation vertLoc) {//MapController --goes in GamePlay
    	HexTile theHex = gameModel.getBoard().getHexTileAt(vertLoc.getHexLoc().getX(), vertLoc.getHexLoc().getY());
    	Corner c = null;
    	
    	switch(vertLoc.getDir()) {
    		case East: c = theHex.eastCorner; break;
    		case NorthEast: c = theHex.northEastCorner; break;
    		case SouthEast: c = theHex.southEastCorner; break;
    		case West: c = theHex.westCorner; break;
    		case SouthWest: c = theHex.southWestCorner; break;
    		case NorthWest: c = theHex.northWestCorner; break;
    	}
    	
        return gameModel.getBoard().canBuildCity(c, player);
    }

    /**
     * This method is called whenever the user is trying to place the robber on the map.
     * It is called by the view for each "mouse move" event. The returned value tells
     * the view whether or not to allow the robber to be placed at the specified location.
     *
     * @param hexLoc The proposed robber location
     * @return true if the robber can be placed at hexLoc, false otherwise
     */
    public boolean canPlaceRobber(HexLocation hexLoc) {//MapController --goes in GamePlay
        Robber rob = gameModel.getBoard().getRobber();
        if(hexLoc.getX() == -3 || hexLoc.getX() == 3 || hexLoc.getY() == 3 || hexLoc.getY() == -3 || 
        	(hexLoc.getX() + hexLoc.getY()) == 3 || (hexLoc.getX() + hexLoc.getY()) == -3)//Checks for if ocean
        	return false;
        if(rob.getLocation().equals(hexLoc))//Checks for if same location
        	return false;
        return true;
    }

    /**
     * This method is called when the user clicks the mouse to place a road.
     *
     * @param edgeLoc The road location
     */
    public void placeRoad(Player player, BoardPiece piece, EdgeLocation edgeLoc) {//MapController --goes in GamePlay
    	allowDisconnect = false;
    }

    /**
     * This method is called when the user clicks the mouse to place a settlement.
     *
     * @param vertLoc The settlement location
     */
    public void placeSettlement(Player player, BoardPiece piece, VertexLocation vertLoc) {//MapController --goes in GamePlay
    	allowDisconnect = false;
    }

    /**
     * This method is called when the user clicks the mouse to place a city.
     *
     * @param vertLoc The city location
     */
    public void placeCity(Player player, BoardPiece piece, VertexLocation vertLoc) {//MapController --goes in GamePlay
    	allowDisconnect = false;
    }

    /**
     * This method is called when the user clicks the mouse to place the robber.
     *
     * @param hexLoc The robber location
     */
    public void placeRobber(HexLocation hexLoc) {//MapController --goes in GamePlay
        
    }

    /**
     * This method is called when the user requests to place a piece on the map (road, city, or settlement)
     *
     * @param pieceType The type of piece to be placed
     * @param isFree true if the piece should not cost the player resources, false otherwise.
     * 				Set to true during initial setup and when a road building card is played.
     * @param allowDisconnected true if the piece can be disconnected, false otherwise.
     * 				Set to true only during initial setup.
     */
    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {//MapController --goes in GamePlay
    	allowDisconnect = allowDisconnected;//Does this need to be set to false after every placement?
    }

    /**
     * This method is called from the modal map overlay when the cancel button is pressed.
     */
    public void cancelMove() {//MapController --goes in GamePlay
    	allowDisconnect = false;
    }

    /**
     * This method is called when the user plays a "road building" progress development card.
     * It should initiate the process of allowing the player to place two roads.
     */
    public void playRoadBuildingCard() {//MapController --goes in GamePlay

    }

    /**
     * This method is called by the Rob View when a player to rob is selected via a button click.
     *
     * @param victim The player to be robbed
     */
    public void robPlayer(RobPlayerInfo victim) {//MapController --goes in GamePlay

    }
    
    
    /**
     * Distributes to players resources for a given roll
     * 
     * @param roll 
     * @post players may have more resource cards
     */
    public void rollResourceDistribution(int roll) {
    	List<HexTile> hexes = gameModel.getBoard().getHexes();
    	ArrayList<PlayerReceivingResources> resourceChanges = new ArrayList<PlayerReceivingResources>();
    	for(int i = 0; i < hexes.size(); i++) {
    		if(hexes.get(i).getType() != HexType.DESERT && hexes.get(i).getType() != HexType.WATER && !hexes.get(i).getHasRobber()) {
    			ResourceTile resourceTile = (ResourceTile)hexes.get(i);
    			
    			if(resourceTile.northEastCorner.hasStructure()) {
    				BoardPiece boardPiece = resourceTile.northEastCorner.getStructure();
    				PlayerReceivingResources pRR = getPlayerResources(boardPiece, resourceTile);
    				resourceChanges.add(pRR);
    			}
    			if(resourceTile.eastCorner.hasStructure()){
    				BoardPiece boardPiece = resourceTile.northEastCorner.getStructure();
    				PlayerReceivingResources pRR = getPlayerResources(boardPiece, resourceTile);
    				resourceChanges.add(pRR);
    			}	
    			if(resourceTile.southEastCorner.hasStructure()) {
    				BoardPiece boardPiece = resourceTile.northEastCorner.getStructure();
    				PlayerReceivingResources pRR = getPlayerResources(boardPiece, resourceTile);
    				resourceChanges.add(pRR);
    			}
    			if(resourceTile.southWestCorner.hasStructure()) {
    				BoardPiece boardPiece = resourceTile.northEastCorner.getStructure();
    				PlayerReceivingResources pRR = getPlayerResources(boardPiece, resourceTile);
    				resourceChanges.add(pRR);
    			}
    			if(resourceTile.westCorner.hasStructure()) {
    				BoardPiece boardPiece = resourceTile.northEastCorner.getStructure();
    				PlayerReceivingResources pRR = getPlayerResources(boardPiece, resourceTile);
    				resourceChanges.add(pRR);
    			}
    			if(resourceTile.northWestCorner.hasStructure()) {
    				BoardPiece boardPiece = resourceTile.northEastCorner.getStructure();
    				PlayerReceivingResources pRR = getPlayerResources(boardPiece, resourceTile);
    				resourceChanges.add(pRR);
    			}
    		}
    	}

    	for(int j = 0; j < resourceChanges.size(); j++) {
    		Player p = resourceChanges.get(j).getPlayer();
    		ResourceType r = resourceChanges.get(j).getResourceType();
    		int a = resourceChanges.get(j).getAmount();
    		ControllerFacade.getSingleton().changeOwnerResource(p, gameModel.getBank(), r, a);
    	}
    }
    
    
    public PlayerReceivingResources getPlayerResources(BoardPiece boardPiece, ResourceTile resourceTile) {
    	int amount = 0;
		if(boardPiece.getPieceType() == PieceType.CITY)
			amount = 2;
		else
			amount = 1;
		return new PlayerReceivingResources(boardPiece.getOwner(), resourceTile.getResourceType(), amount);
    }
}
