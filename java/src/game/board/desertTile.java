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
         * @param hasRobber the hasRobber to set
         */
        public void setHasRobber(boolean hasRobber) {
            this.hasRobber = hasRobber;
        }
}