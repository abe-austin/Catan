package game.board;

import shared.definitions.HexType;
import shared.definitions.PortType;

public class PortTile extends HexTile {
        private PortType type;
	
	public PortTile(PortType type) {
            this.type = type;
            myType = HexType.PORT;
	}

        /**
         * @return the type of port
         */
        public PortType getType() {
            return type;
        }
}