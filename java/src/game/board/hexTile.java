package game.board;

import game.pieces.*;
import shared.definitions.HexType;
import player.player;

public abstract class hexTile {
	
	protected HexType myType;
	protected boardCoord location;
	
	boolean canBuild(edge e, player p) {
		return false;
	}//edge,player
	
	boolean canBuild(corner c, boolean isSettlement, player p) {
		return false;
	}//vertex,player (building type?)
	
	void build(edge e, player p) {
		
	}//Build the requested object
	
	void build(corner c, boolean isSettlement, player p) { 
		
	}
	
}