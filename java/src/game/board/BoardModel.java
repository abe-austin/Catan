package game.board;

import java.util.List;

import player.Player;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;
import game.pieces.BoardPiece;
import game.pieces.Robber;

/**
 *
 * @author Kevin MacMaster
 */
public class BoardModel {
	private List<HexTile> tiles;
    private BoardPiece[][] pieces;
    private Robber rob;

    public BoardModel() {
        pieces = new BoardPiece[10][10];
        BuildWorld worldBuilder = new BuildWorld();
        tiles = worldBuilder.getTiles();
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
    		else
    			return false;
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
    
  //Do we need special test-functions for if this is the start of the game (placing settlement shouldn't be based on friendly neighbor)
    /**
    *
    * @param e Edge to build on
    * @param p player to check
    * @return true if Player p can build a road on Edge e
    *          false otherwise
    * @pre assumes that edge is a valid (non-null) edge (i.e. not one between ocean tiles)
    */
    public boolean canBuildRoad(Edge edge, Player player) {
    	if(!player.hasAvailableBoardPiece(PieceType.ROAD))//First check if player has available road pieces
    		return false;
    	if(edge.hasStructure())
    		return false;
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
    * @pre assumes that corner is a valid (non-null) corner (i.e. not one between only ocean tiles)
    */
    public boolean canBuildSettlement(Corner corner, Player player) {
    	if(!player.hasAvailableBoardPiece(PieceType.SETTLEMENT))//First check if player has available settlement pieces
    		return false;
    	if(corner != null) {
    		if(corner.hasStructure())
    			return false;
    		}
    	else {
    		List<VertexLocation> places = corner.getLocations();
    		for(VertexLocation vertexLoc : places) {//Check for any neighbors that prevent building
				HexTile hTile = getHexTileAt(vertexLoc.getHexLoc().getX(), vertexLoc.getHexLoc().getY());
				if(hasNeighborsVertex(hTile, vertexLoc, player))
					return false;					
				}
    		for(VertexLocation vertexLoc : places) {//Check for a road connecting to this corner
    			HexTile hTile = getHexTileAt(vertexLoc.getHexLoc().getX(), vertexLoc.getHexLoc().getY());
    			if(vertexHasEdgeConnecting(hTile, vertexLoc, player))
    				return true;					
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
}
