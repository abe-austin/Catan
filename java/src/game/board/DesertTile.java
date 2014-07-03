package game.board;

import shared.definitions.HexType;

public class DesertTile extends HexTile {
        private boolean hasRobber;

	public DesertTile(){
            myType = HexType.DESERT;
	}

        /**
         * @return the hasRobber
         */
        public boolean isHasRobber() {
            return hasRobber;
        }

        /**
         * @param hasRobber sets whether the tile has robber
         */
        public void setHasRobber(boolean hasRobber) {
            this.hasRobber = hasRobber;
        }
}