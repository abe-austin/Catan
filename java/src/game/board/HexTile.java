package game.board;

import shared.definitions.HexType;
import player.Player;
import shared.locations.HexLocation;

public abstract class HexTile {
	
	protected HexType myType;
	protected HexLocation location;

        /**
         *
         * @param e Edge to build on
         * @param p player to check
         * @return true if Player p can build a road on Edge e
         *          false otherwise
         */
	public boolean canBuildRoad(Edge edge, Player player) {
		return false;
	}//Edge,Player

        /**
         *
         * @param c Corner to build on
         * @param p player to check
         * @return
         */
	public boolean canBuildSettlement(Corner corner, Player player) {
		return false;
	}//vertex,Player (building type?)

        /**
         *
         * @param c Corner to build on
         * @param p player to check
         * @return
         */
	public boolean canBuildCity(Corner corner, Player player) {
		return false;
	}//vertex,Player (building type?)

        /**
         *
         * @param e Edge to build on
         * @param p players road to build
         */
	public void buildRoad(Edge edge, Player player) {
		
	}//Build the requested object

        /**
         *
         * @param c Corner to build on
         * @param p players settlement/city to build
         */
	public void buildSettlement(Corner corner,  Player player) {
		
	}

        /**
         *
         * @param c Corner to build on
         * @param p players settlement/city to build
         */
	public void buildCity(Corner corner,  Player player) {

	}

        abstract HexType getType();
}