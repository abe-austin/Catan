package game.board;

import game.pieces.BoardPiece;
import game.pieces.robber;

/**
 *
 * @author Kevin MacMaster
 */
public class BoardModel {
    private hexTile[][] tiles;
    private BoardPiece[][] pieces;
    private robber rob;

    public BoardModel() {
        pieces = new BoardPiece[10][10];
        tiles = new hexTile[10][10];

        // Create Hex Tiles here
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
    public hexTile getHexTileAt(int x, int y) {
        return tiles[x][y];
    }

    /**
     * 
     * @return Robber
     */
    public robber getRobber() {
        return rob;
    }
}
