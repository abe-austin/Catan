package game.board;

import shared.definitions.HexType;

public class OceanTile extends HexTile {
	
	@Override
        public HexType getType() {
            return HexType.WATER;
        }
}