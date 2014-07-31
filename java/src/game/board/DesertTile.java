package game.board;

import shared.definitions.HexType;

public class DesertTile extends HexTile {
        @Override
        public HexType getType() {
            return HexType.DESERT;
        }
}