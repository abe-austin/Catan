package game.board;

import org.junit.Before;
import org.junit.Test;

import player.Player;
import shared.definitions.CatanColor;
import system.User;
import static org.junit.Assert.*;

public class BuildWorldTest{
    
	BoardModel bModel;
	Player p1;
	Player p2;
	
	@Before
	public void setUp() {
		bModel = new BoardModel();
		p1 = new Player(CatanColor.BLUE, new User());
		p2 = new Player(CatanColor.RED, new User());
	}
	
	/**
     * test 
     */
    @Test
    public void testWorldCreation(){
    	//Ensure that the edges are properly null or not so
        assertEquals(bModel.getHexTileAt(-3, 0).northEdge, null);
        assertEquals(bModel.getHexTileAt(-3, 0).northEastEdge, null);
        assertEquals(bModel.getHexTileAt(-3, 0).southEastEdge.equals(null), false);
        assertEquals(bModel.getHexTileAt(-3, 0).southEdge, null);
        assertEquals(bModel.getHexTileAt(-3, 0).southWestEdge, null);
        assertEquals(bModel.getHexTileAt(-3, 0).northWestEdge, null);
        
        assertEquals(bModel.getHexTileAt(0, 0).northEdge.equals(null), false);
        assertEquals(bModel.getHexTileAt(0, 0).northEastEdge.equals(null), false);
        assertEquals(bModel.getHexTileAt(0, 0).southEastEdge.equals(null), false);
        assertEquals(bModel.getHexTileAt(0, 0).southEdge.equals(null), false);
        assertEquals(bModel.getHexTileAt(0, 0).southWestEdge.equals(null), false);
        assertEquals(bModel.getHexTileAt(0, 0).northWestEdge.equals(null), false);
        
        assertEquals(bModel.getHexTileAt(3, -1).northEdge, null);
        assertEquals(bModel.getHexTileAt(3, -1).northEastEdge, null);
        assertEquals(bModel.getHexTileAt(3, -1).southEastEdge, null);
        assertEquals(bModel.getHexTileAt(3, -1).southEdge, null);
        assertEquals(bModel.getHexTileAt(3, -1).southWestEdge.equals(null), false);
        assertEquals(bModel.getHexTileAt(3, -1).northWestEdge.equals(null), false);
        
        
      //Ensure that the corners are properly null or not so
        assertEquals(bModel.getHexTileAt(-3, 0).northWestCorner, null);
        assertEquals(bModel.getHexTileAt(-3, 0).northEastCorner, null);
        assertEquals(bModel.getHexTileAt(-3, 0).eastCorner.equals(null), false);
        assertEquals(bModel.getHexTileAt(-3, 0).southEastCorner.equals(null), false);
        assertEquals(bModel.getHexTileAt(-3, 0).southWestCorner, null);
        assertEquals(bModel.getHexTileAt(-3, 0).westCorner, null);
        
        assertEquals(bModel.getHexTileAt(0, 0).northWestCorner.equals(null), false);
        assertEquals(bModel.getHexTileAt(0, 0).northEastCorner.equals(null), false);
        assertEquals(bModel.getHexTileAt(0, 0).eastCorner.equals(null), false);
        assertEquals(bModel.getHexTileAt(0, 0).southEastCorner.equals(null), false);
        assertEquals(bModel.getHexTileAt(0, 0).southWestCorner.equals(null), false);
        assertEquals(bModel.getHexTileAt(0, 0).westCorner.equals(null), false);
        
        assertEquals(bModel.getHexTileAt(3, -1).northWestCorner.equals(null), false);
        assertEquals(bModel.getHexTileAt(3, -1).northEastCorner, null);
        assertEquals(bModel.getHexTileAt(3, -1).eastCorner, null);
        assertEquals(bModel.getHexTileAt(3, -1).southEastCorner, null);
        assertEquals(bModel.getHexTileAt(3, -1).southWestCorner.equals(null), false);
        assertEquals(bModel.getHexTileAt(3, -1).westCorner.equals(null), false);
    }
    
    /**
     * test 
     */
    @Test
    public void testUnitPlacement(){
        assertEquals(true, true);
    }
}