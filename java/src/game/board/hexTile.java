package game.board;

import game.pieces.*;
import shared.definitions.HexType;
import player.Player;

public abstract class hexTile {
	
	protected HexType myType;
	protected boardCoord location;
	
	boolean canBuild(edge e, Player p) {
		return false;
	}//edge,Player
	
	boolean canBuild(corner c, boolean isSettlement, Player p) {
		return false;
	}//vertex,Player (building type?)
	
	void build(edge e, Player p) {
		
	}//Build the requested object
	
	void build(corner c, boolean isSettlement, Player p) {
		
	}
	
}