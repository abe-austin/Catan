package game.board;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import client.parse.ParsedPort;
import client.parse.ParsedStructure;
import client.parse.ParsedTile;
import controller.ControllerFacade;
import player.Player;
import shared.definitions.PieceType;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import game.GameModel;
import game.pieces.BoardPiece;
import game.pieces.City;
import game.pieces.Road;
import game.pieces.Robber;
import shared.definitions.GameState;

/**
 *
 * @author Kevin MacMaster
 */
public class BoardModel {
	private List<HexTile> tiles;
    private BoardPiece[][] pieces;
    private Robber rob;
    private GameModel gameModel;
    private List<ParsedStructure> theStructures;

    public BoardModel(GameModel gameModel) {
    	this.gameModel = gameModel;
        pieces = new BoardPiece[10][10];
        BuildWorld worldBuilder = new BuildWorld();
        tiles = worldBuilder.getTiles();
        rob = new Robber(new HexLocation(0,0));
        theStructures = null;
    }
    
    public void updateBoardResources(ArrayList<ParsedTile> newTiles) {
    	BuildWorld worldBuilder = new BuildWorld();//Should now be empty of any pieces
    	tiles = worldBuilder.getTiles();
    	for(int i = 0; i < newTiles.size(); i++)
    	{
    		int x = newTiles.get(i).getX();
    		int y = newTiles.get(i).getY();    		
    		int number = newTiles.get(i).getNumberTile();
    	
    		for(int j = 0; j < tiles.size(); j++)
            {
            	if(tiles.get(j).getX() == x && tiles.get(j).getY() == y)
            	{
            		Corner nw = tiles.get(j).northWestCorner;
            		Corner ne = tiles.get(j).northEastCorner;
            		Corner e = tiles.get(j).eastCorner;
            		Corner se = tiles.get(j).southEastCorner;
            		Corner sw = tiles.get(j).southWestCorner;
            		Corner w = tiles.get(j).westCorner;
            		Edge en = tiles.get(j).northEdge;
            		Edge ene = tiles.get(j).northEastEdge;
            		Edge ese = tiles.get(j).southEastEdge;
            		Edge es = tiles.get(j).southEdge;
            		Edge esw = tiles.get(j).southWestEdge;
            		Edge enw = tiles.get(j).northWestEdge;
            		HexTile t = null;           		
            		
            		String type = newTiles.get(i).getType();
            		if(type.equals("DESERT"))
            			t = new DesertTile();
            		if(type.equals("brick"))
            			t = new ResourceTile(ResourceType.BRICK, new NumberToken(number));
            		if(type.equals("wood"))
            			t = new ResourceTile(ResourceType.WOOD, new NumberToken(number));
            		if(type.equals("ore"))
            			t = new ResourceTile(ResourceType.ORE, new NumberToken(number));
            		if(type.equals("sheep"))
            			t = new ResourceTile(ResourceType.SHEEP, new NumberToken(number));
            		if(type.equals("wheat"))
            			t = new ResourceTile(ResourceType.WHEAT, new NumberToken(number));
            		
            		t.northWestCorner = nw;
            		t.northEastCorner = ne;
            		t.eastCorner = e;
            		t.southEastCorner = se;
            		t.southWestCorner = sw;
            		t.westCorner = w;
            		t.northEdge = en;
            		t.northEastEdge = ene;
            		t.southEastEdge = ese;
            		t.southEdge = es;
            		t.southWestEdge = esw;
            		t.northWestEdge = enw;
            		t.setCoordinates(x, y);
            		tiles.set(j, t);
            		j = tiles.size();
            	}
            }	
    	}    	
    }
    
    public void updateBoardPorts(ArrayList<ParsedPort> newPorts) {
    	
    	for(int i = 0; i < newPorts.size(); i++)
    	{
    		int x = newPorts.get(i).getX();
    		int y = newPorts.get(i).getY();
    		int ratio = newPorts.get(i).getRatio();
    		
    		for(int j = 0; j < tiles.size(); j++)
            {
            	if(tiles.get(j).getX() == x && tiles.get(j).getY() == y)
            	{
            		Corner nw = tiles.get(j).northWestCorner;
            		Corner ne = tiles.get(j).northEastCorner;
            		Corner e = tiles.get(j).eastCorner;
            		Corner se = tiles.get(j).southEastCorner;
            		Corner sw = tiles.get(j).southWestCorner;
            		Corner w = tiles.get(j).westCorner;
            		Edge en = tiles.get(j).northEdge;
            		Edge ene = tiles.get(j).northEastEdge;
            		Edge ese = tiles.get(j).southEastEdge;
            		Edge es = tiles.get(j).southEdge;
            		Edge esw = tiles.get(j).southWestEdge;
            		Edge enw = tiles.get(j).northWestEdge;
            		HexTile t = null; 
            		
            		String type = newPorts.get(i).getType();
            		if(type.equals("three"))
            			t = new PortTile(PortType.THREE);
            		if(type.equals("brick"))
            			t =new PortTile(PortType.BRICK);
            		if(type.equals("wheat"))
            			t = new PortTile(PortType.WHEAT);
            		if(type.equals("sheep"))
            			t = new PortTile(PortType.SHEEP);
            		if(type.equals("ore"))
            			t = new PortTile(PortType.ORE);
            		if(type.equals("wood"))
            			t = new PortTile(PortType.WOOD);
            		
            		t.northWestCorner = nw;
            		t.northEastCorner = ne;
            		t.eastCorner = e;
            		t.southEastCorner = se;
            		t.southWestCorner = sw;
            		t.westCorner = w;
            		t.northEdge = en;
            		t.northEastEdge = ene;
            		t.southEastEdge = ese;
            		t.southEdge = es;
            		t.southWestEdge = esw;
            		t.northWestEdge = enw;
            		t.setCoordinates(x, y);
            		tiles.set(j, t);
            		j = tiles.size();
            	}
            }	
    	}
    }
    
    public void updateStructures(ArrayList<ParsedStructure> newStructures) {
    	for(int i = 0; i < newStructures.size(); i++)
    	{
    		int x = newStructures.get(i).getX();
    		int y = newStructures.get(i).getY();
    		int owner = newStructures.get(i).getOwner();
    		String direction = newStructures.get(i).getDirection();
    		String type = newStructures.get(i).getType();
    		Player p = null;
    		
    		Player[] players = gameModel.getPlayers();
    		for(int j = 0; j < players.length; j++)
    		{
    			if(owner == players[j].getIndex())//Presumably owner's value is the same as index
    				p = players[j];
    		}
    		 		
    		if(type.equals("ROAD"))
    		{
    			Edge edge = null;
    			if(direction.equals("N"))
    				edge = getHexTileAt(x,y).northEdge;
        		if(direction.equals("NE"))
        			edge = getHexTileAt(x,y).northEastEdge;
        		if(direction.equals("SE"))
        			edge = getHexTileAt(x,y).southEastEdge;
        		if(direction.equals("S"))
        			edge = getHexTileAt(x,y).southEdge;
        		if(direction.equals("SW"))
        			edge = getHexTileAt(x,y).southWestEdge;
        		if(direction.equals("NW"))
        			edge = getHexTileAt(x,y).northWestEdge;
    			getHexTileAt(x, y).buildRoad(edge, p);
    		}
    		
    		//Evaluate if these are on a port
    		if(type.equals("CITY"))
    		{
    			Corner corner = null;
    			if(direction.equals("NW"))
    				corner = getHexTileAt(x,y).northWestCorner;
        		if(direction.equals("NE"))
        			corner = getHexTileAt(x,y).northEastCorner;
        		if(direction.equals("E"))
        			corner = getHexTileAt(x,y).eastCorner;
        		if(direction.equals("SE"))
        			corner = getHexTileAt(x,y).southEastCorner;
        		if(direction.equals("SW"))
        			corner = getHexTileAt(x,y).southWestCorner;
        		if(direction.equals("W"))
        			corner = getHexTileAt(x,y).westCorner;
    			getHexTileAt(x, y).buildCity(corner, p);
    			
    			PortType port = checkPort(x, y, direction);
    			if(port != null)
    				p.addPort(port);
    		}
    		
    		if(type.equals("SETTLEMENT"))
    		{
    			Corner corner = null;
    			if(direction.equals("NW"))
    				corner = getHexTileAt(x,y).northWestCorner;
        		if(direction.equals("NE"))
        			corner = getHexTileAt(x,y).northEastCorner;
        		if(direction.equals("E"))
        			corner = getHexTileAt(x,y).eastCorner;
        		if(direction.equals("SE"))
        			corner = getHexTileAt(x,y).southEastCorner;
        		if(direction.equals("SW"))
        			corner = getHexTileAt(x,y).southWestCorner;
        		if(direction.equals("W"))
        			corner = getHexTileAt(x,y).westCorner;
    			getHexTileAt(x, y).buildSettlement(corner, p);
    			
    			PortType port = checkPort(x, y, direction);
    			if(port != null)
    				p.addPort(port);
    		}	
    	}
    	theStructures = newStructures;
    }
    
    public List<ParsedStructure> getStructures() {
    	return theStructures;
    }
    
    public PortType checkPort(int x, int y, String direction) {
    	PortType toReturn = null;
    	
    	if( (x == 1 && y == -3 && (direction.equals("SE") || direction.equals("SW")))
    		|| (x == 1 && y == -2 && (direction.equals("NE") || direction.equals("NW")))
    		|| (x == 2 && y == -3 && direction.equals("W")) || (x == 2 && y == -3 &&direction.equals("E")) )
    		toReturn = ((PortTile)getHexTileAt(1, -3)).getPortType();
    	
    	if( (x == -1 && y == -2 && (direction.equals("SE") || direction.equals("SW")))
        	|| (x == -1 && y == -1 && (direction.equals("NE") || direction.equals("NW")))
        	|| (x == 0 && y == -2 && direction.equals("W")) || (x == -2 && y == -1 &&direction.equals("E")) )
    		toReturn = ((PortTile)getHexTileAt(-1, -2)).getPortType();
    	
    	if( (x == 3 && y == -3 && (direction.equals("W") || direction.equals("SW")))
        	|| (x == 2 && y == -2 && (direction.equals("E") || direction.equals("NE")))
        	|| (x == 2 && y == -3 && direction.equals("SE")) || (x == 3 && y == -2 &&direction.equals("NW")) )
    		toReturn = ((PortTile)getHexTileAt(3, -3)).getPortType();
    	
    	if( (x == -3 && y == 0 && (direction.equals("SE") || direction.equals("E")))
        	|| (x == -2 && y == 0 && (direction.equals("NW") || direction.equals("W")))
        	|| (x == -2 && y == -1 && direction.equals("SW")) || (x == -3 && y == 1 &&direction.equals("NE")) )
    		toReturn = ((PortTile)getHexTileAt(-3, 0)).getPortType();
    	
    	if( (x == 3 && y == -1 && (direction.equals("W") || direction.equals("NW")))
        	|| (x == 2 && y == -1 && (direction.equals("E") || direction.equals("SE")))
        	|| (x == 3 && y == -2 && direction.equals("SW")) || (x == 2 && y == 0 &&direction.equals("NE")) )
    		toReturn = ((PortTile)getHexTileAt(3, -1)).getPortType();
    	
    	if( (x == 2 && y == 1 && (direction.equals("W") || direction.equals("NW")))
           	|| (x == 1 && y == 1 && (direction.equals("E") || direction.equals("SE")))
           	|| (x == 2 && y == 0 && direction.equals("SW")) || (x == 1 && y == 2 &&direction.equals("NE")) )
       		toReturn = ((PortTile)getHexTileAt(2, 1)).getPortType();
    	
    	if( (x == 0 && y == 3 && (direction.equals("NE") || direction.equals("NW")))
        	|| (x == 0 && y == 2 && (direction.equals("SE") || direction.equals("SW")))
        	|| (x == 1 && y == 2 && direction.equals("W")) || (x == -1 && y == 3 &&direction.equals("E")) )
    		toReturn = ((PortTile)getHexTileAt(0, 3)).getPortType();
    	
    	if( (x == -2 && y == 3 && (direction.equals("NE") || direction.equals("E")))
        	|| (x == -1 && y == 2 && (direction.equals("W") || direction.equals("SW")))
        	|| (x == -1 && y == 3 && direction.equals("NW")) || (x == -2 && y == 2 &&direction.equals("SE")) )
    		toReturn = ((PortTile)getHexTileAt(-2, 3)).getPortType();
    	
    	if( (x == -3 && y == 2 && (direction.equals("NE") || direction.equals("E")))
           	|| (x == -2 && y == 1 && (direction.equals("W") || direction.equals("SW")))
           	|| (x == -2 && y == 2 && direction.equals("NW")) || (x == -3 && y == 1 &&direction.equals("SE")) )
       		toReturn = ((PortTile)getHexTileAt(-3, 2)).getPortType();
    	
    	return toReturn;
    }
    
    public void updateRobber(int x, int y)
    {
    	HexLocation robLoc = rob.getLocation();
    	int robX = robLoc.getX();
    	int robY = robLoc.getY();
    	
    	for(int j = 0; j < tiles.size(); j++)
        {
        	if(tiles.get(j).getX() == x && tiles.get(j).getY() == y)
        		tiles.get(j).setHasRobber(true);
        	
        	if(tiles.get(j).getX() == robX && tiles.get(j).getY() == robY)
        		tiles.get(j).setHasRobber(false);
        }
    	robLoc = new HexLocation(x, y);
    	rob.updateLocation(robLoc);
    }
    
    /**
     * @return the list of HexTiles on the board
     */
    public List<HexTile> getHexes(){
    	return tiles;
    }

    /**
     * 
     * @param x position on board
     * @param y position on board
     * @return BoardPiece at given position or null
     */
    public BoardPiece getBoardPieceAt(int x, int y) {
        return pieces[x][y];
    }

    /**
     *
     * @param x position on board
     * @param y position on board
     * @return HexTile at given position
     */
    public HexTile getHexTileAt(int x, int y) {
        for(HexTile theTile : tiles)
        {
        	if(theTile.getX() == x && theTile.getY() == y)
        		return theTile;
        }
        return null;
    }

    /**
     * 
     * @return Robber
     */
    public Robber getRobber() {
        return rob;
    }
    
    
    /**
     * @param edge, which edge we need to check
     * @param player, which player the edge is being checked if belonged to
     * @return boolean result
     */
    public Boolean checkEdgeOwned(Edge edge, Corner inBetween, Player player) {
    	if(inBetween != null)
    		if(inBetween.hasStructure()) {
    			if(!inBetween.getStructure().getOwner().equals(player))
    				return false;
    		}
    	if(edge != null) {
    		if(edge.hasStructure()) {
    			if(edge.getStructure().getOwner().equals(player))
    				return true;
    		}
    	}
    	return false;
    }
    
    /**
     * @param edge, which edge we need to check
     * @param player, which player the edge is being checked if belonged to
     * @return boolean result
     */
    public Boolean checkEdgeOwned2(Edge edge, Player player) {
    	if(edge != null) {
    		if(edge.hasStructure()) {
    			if(edge.getStructure().getOwner().equals(player))
    				return true;
    		}
    	}
    	return false;
    }
    
    /**
     * @param corner, which corner we need to check
     * @param player, which player the corner is being checked if belonged to
     * @return boolean result
     */
    public Boolean checkCornerOwned(Corner corner, Player player) {
    	if(corner != null) {
    		if(corner.hasStructure()) {
    			if(corner.getStructure().getOwner().equals(player))
    				return true;
    		}
    	}
    	return false;
    }

    /**
     * 
     * @param hTile the tile we are checking for neighboring edges and corners of
     * @param edgeLoc the current edge on the hex we are checking for neighbors of
     * @param player the player that we want to find a neighboring edge of
     * @return whether this given edge has a neighboring edge or corner owned by the given player
     */
    public boolean hasNeighborsEdge(HexTile hTile, EdgeLocation edgeLoc, Player player) {
    	switch(edgeLoc.getDir()){
		case NorthWest: 
			if(checkEdgeOwned(hTile.northEdge, hTile.northWestCorner, player) || checkEdgeOwned(hTile.southWestEdge, hTile.westCorner, player) ||
					checkCornerOwned(hTile.westCorner, player) || checkCornerOwned(hTile.northWestCorner, player))
				return true;
			break;
		case North:
			if(checkEdgeOwned(hTile.northEastEdge, hTile.northEastCorner, player) || checkEdgeOwned(hTile.northWestEdge, hTile.northEastCorner, player) ||
					checkCornerOwned(hTile.northEastCorner, player) || checkCornerOwned(hTile.northWestCorner, player))
				return true;
			break;
		case NorthEast:
			if(checkEdgeOwned(hTile.northEdge, hTile.northEastCorner, player) || checkEdgeOwned(hTile.southEastEdge, hTile.eastCorner, player) ||
					checkCornerOwned(hTile.northEastCorner, player) || checkCornerOwned(hTile.eastCorner, player))
				return true;
			break;
		case SouthEast:
			if(checkEdgeOwned(hTile.northEastEdge, hTile.eastCorner, player) || checkEdgeOwned(hTile.southEdge, hTile.southEastCorner, player) ||
					checkCornerOwned(hTile.eastCorner, player) || checkCornerOwned(hTile.southEastCorner, player))
				return true;
			break;
		case South:
			if(checkEdgeOwned(hTile.southEastEdge, hTile.southEastCorner, player) || checkEdgeOwned(hTile.southWestEdge, hTile.southWestCorner, player) ||
					checkCornerOwned(hTile.southEastCorner, player) || checkCornerOwned(hTile.southWestCorner, player))
				return true;
			break;
		case SouthWest:
			if(checkEdgeOwned(hTile.southEdge, hTile.southWestCorner, player) || checkEdgeOwned(hTile.northWestEdge, hTile.westCorner, player) ||
					checkCornerOwned(hTile.southWestCorner, player) || checkCornerOwned(hTile.westCorner, player))
				return true;
			break;
    	}
    	return false;
    }
    
    
    /**
     * 
     * @param hTile the tile we are checking for neighboring corners along
     * @param vertexLoc the current corner on the hex we are checking for neighbors of
     * @param player the player that we want to find a neighboring corner of
     * @return whether this given corner has a neighboring corner owned by the given player
     */
    public boolean hasNeighborsVertex(HexTile hTile, VertexLocation vertexLoc, Player player) {
    	switch(vertexLoc.getDir()){
    		case West: 
    			if(hTile.southWestCorner != null)
    				if(hTile.southWestCorner.hasStructure())
    					return true;
    			if(hTile.northWestCorner != null)
    				if(hTile.northWestCorner.hasStructure())
    					return true;
    			break;
    		case NorthWest:
    			if(hTile.westCorner != null)
    				if(hTile.westCorner.hasStructure())
    					return true;
    			if(hTile.northEastCorner != null)
    				if(hTile.northEastCorner.hasStructure())
    					return true;
    			break;
    		case NorthEast: 
    			if(hTile.northWestCorner != null)
    				if(hTile.northWestCorner.hasStructure())
    					return true;
    			if(hTile.eastCorner != null)
    				if(hTile.eastCorner.hasStructure())
    					return true;
    			break;
    		case East: 
    			if(hTile.northEastCorner != null)
    				if(hTile.northEastCorner.hasStructure())
    					return true;
    			if(hTile.southEastCorner != null)
    				if(hTile.southEastCorner.hasStructure())
    					return true;
    			break;
    		case SouthEast: 
    			if(hTile.eastCorner != null)
    				if(hTile.eastCorner.hasStructure())
    					return true;
    			if(hTile.southWestCorner != null)
    				if(hTile.southWestCorner.hasStructure())
    					return true;
    			break;
    		case SouthWest: 
    			if(hTile.southEastCorner != null)
    				if(hTile.southEastCorner.hasStructure())
    					return true;
    			if(hTile.westCorner != null)
    				if(hTile.westCorner.hasStructure())
    					return true;
    			break;
    	}
    	return false;	
    }
    
    
    /**
     * 
     * @param hTile the tile we are checking for neighboring edges along
     * @param vertexLoc vertexLoc the current corner on the hex we are checking for neighbors of
     * @param player the player that we want to find a neighboring edge of
     * @return whether this given corner has a neighboring edge owned by the given player
     */
    public boolean vertexHasEdgeConnecting(HexTile hTile, VertexLocation vertexLoc, Player player) {
    	switch(vertexLoc.getDir()){
    	case West:
			if(hTile.northWestEdge!= null)
				if(checkEdgeOwned2(hTile.northWestEdge, player))
					return true;
			if(hTile.southWestEdge!= null)
				if(checkEdgeOwned2(hTile.southWestEdge, player))
					return true;
			break;
		case NorthWest:
			if(hTile.northWestEdge!= null)
				if(checkEdgeOwned2(hTile.northWestEdge, player))
					return true;
			if(hTile.northEdge!= null)
				if(checkEdgeOwned2(hTile.northEdge, player))
					return true;
			break;
		case NorthEast:
			if(hTile.northEdge!= null)
				if(checkEdgeOwned2(hTile.northEdge, player))
					return true;
			if(hTile.northEastEdge!= null)
				if(checkEdgeOwned2(hTile.northEastEdge, player))
					return true;
			break;
		case East:
			if(hTile.northEastEdge!= null)
				if(checkEdgeOwned2(hTile.northEastEdge, player))
					return true;
			if(hTile.southEastEdge!= null)
				if(checkEdgeOwned2(hTile.southEastEdge, player))
					return true;
			break;
		case SouthEast:
			if(hTile.southEastEdge!= null)
				if(checkEdgeOwned2(hTile.southEastEdge, player))
					return true;
			if(hTile.southEdge!= null)
				if(checkEdgeOwned2(hTile.southEdge, player))
					return true;
			break;
		case SouthWest:
			if(hTile.southEdge!= null)
				if(checkEdgeOwned2(hTile.southEdge, player))
					return true;
			if(hTile.southWestEdge!= null)
				if(checkEdgeOwned2(hTile.southWestEdge, player))
					return true;
			break;    	
    	}
    	return false;
    }
    
  //NEED TO BE DIFFERENT IN SETUP, SECOND ROAD HAS TO CONNECT TO SECOND SETTLEMENT...I THINK
    /**
    *
    * @param e Edge to build on
    * @param p player to check
    * @return true if Player p can build a road on Edge e
    *          false otherwise
    */
    public boolean canBuildRoad(Edge edge, Player player, Boolean allowDisconnected) {
    	if(!player.hasAvailableBoardPiece(PieceType.ROAD))//First check if player has available road pieces
    		return false;
    	if(edge == null)
    		return false;
    	if(edge.hasStructure())
    		return false;
    	if(allowDisconnected)
    		return true;
    	else {
			List<EdgeLocation> places = edge.getLocations();
			for(EdgeLocation edgeLoc : places) {
				HexTile hTile = getHexTileAt(edgeLoc.getHexLoc().getX(), edgeLoc.getHexLoc().getY());
				if(hasNeighborsEdge(hTile, edgeLoc, player))
					return true;					
				}
			}
			return false;		
    }//Edge,Player

   /**
    *
    * @param c Corner to build on
    * @param p player to check
    * @return
    */
    public boolean canBuildSettlement(Corner corner, Player player, Boolean allowDisconnected) {
    	if(!player.hasAvailableBoardPiece(PieceType.SETTLEMENT))//First check if player has available settlement pieces
    		return false;
    	if(corner == null)
    		return false;
    	if(corner.hasStructure())
    		return false;
    	else {
    		List<VertexLocation> places = corner.getLocations();
    		for(VertexLocation vertexLoc : places) {//Check for any neighbors that prevent building
				HexTile hTile = getHexTileAt(vertexLoc.getHexLoc().getX(), vertexLoc.getHexLoc().getY());
				if(hasNeighborsVertex(hTile, vertexLoc, player))
					return false;					
				}
    		if(allowDisconnected || ControllerFacade.getSingleton().getGameState()==GameState.Setup)
    			return true;
    		else {
    			for(VertexLocation vertexLoc : places) {//Check for a road connecting to this corner
    				HexTile hTile = getHexTileAt(vertexLoc.getHexLoc().getX(), vertexLoc.getHexLoc().getY());
    				if(vertexHasEdgeConnecting(hTile, vertexLoc, player))
    					return true;					
    			}
    		}
    	}
		return false;
    }//vertex,Player (building type?)
    

   /**
    *
    * @param c Corner to build on
    * @param p player to check
    * @return
    * @pre assumes that corner is a valid (non-null) corner (i.e. not one between only ocean tiles)
    */
    public boolean canBuildCity(Corner corner, Player player) {
    	if(!player.hasAvailableBoardPiece(PieceType.CITY))//First check if player has available city pieces
    		return false;
    	if(!corner.hasStructure())
    		return false;
    	else {
    		if(corner.getStructure().getPieceType() != PieceType.SETTLEMENT)
    			return false;
    		else {
    			if(corner.getStructure().getOwner() != player)
    				return false;
    			else
    				return true;
    		}
    	}
    }//vertex,Player (building type?)
    
    
    
    public Set<Player> getPlayersOn(HexLocation hexLoc) {
    	HexTile hTile = getHexTileAt(hexLoc.getX(), hexLoc.getY());
    	Set<Player> playersOn = new HashSet<Player>();
    	
    	if(hTile.northWestCorner.hasStructure()) {
    		if(hTile.northWestCorner.getStructure().getOwner() != ControllerFacade.getSingleton().getClientPlayer())
    			playersOn.add(hTile.northWestCorner.getStructure().getOwner());
    	}
    	if(hTile.northEastCorner.hasStructure()) {
    		if(hTile.northEastCorner.getStructure().getOwner() != ControllerFacade.getSingleton().getClientPlayer())
    		playersOn.add(hTile.northEastCorner.getStructure().getOwner());
    	}
    	if(hTile.eastCorner.hasStructure()) {
    		if(hTile.eastCorner.getStructure().getOwner() != ControllerFacade.getSingleton().getClientPlayer())
    			playersOn.add(hTile.eastCorner.getStructure().getOwner());
    	}
    	if(hTile.southEastCorner.hasStructure()) {
    		if(hTile.southEastCorner.getStructure().getOwner() != ControllerFacade.getSingleton().getClientPlayer())
    			playersOn.add(hTile.southEastCorner.getStructure().getOwner());
    	}
    	if(hTile.southWestCorner.hasStructure()) {
    		if(hTile.southWestCorner.getStructure().getOwner() != ControllerFacade.getSingleton().getClientPlayer())
    			playersOn.add(hTile.southWestCorner.getStructure().getOwner());
    	}
    	if(hTile.westCorner.hasStructure()) {
    		if(hTile.westCorner.getStructure().getOwner() != ControllerFacade.getSingleton().getClientPlayer())
    			playersOn.add(hTile.westCorner.getStructure().getOwner());
    	}
    	
    	return playersOn;
    }
}
