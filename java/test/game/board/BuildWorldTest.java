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
		bModel.getHexTileAt(0, 0).buildSettlement(bModel.getHexTileAt(0, 0).northWestCorner, p1);
		bModel.getHexTileAt(0, 0).buildSettlement(bModel.getHexTileAt(0, 0).eastCorner, p2);
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
     * test, ensure that roads, settlements, and cities will be verified for being built in valid locations and
     * not in invalid ones
     */
    @Test
    public void testUnitPlacement(){
    	
    	//Ensure cannot place a settlement where one already exists
    	assertEquals(bModel.canBuildSettlement(bModel.getHexTileAt(0, 0).northWestCorner, p1, false), false);
    	
    	//Ensure cannot place a settlement where there is no connection to rest of player pieces
    	assertEquals(bModel.canBuildSettlement(bModel.getHexTileAt(-1, 0).northWestCorner, p1, false), false);
    	
    	//Ensure cannot place a road where there is no connection to rest of player pieces
    	assertEquals(bModel.canBuildRoad(bModel.getHexTileAt(-1, 0).northEdge, p1, false), false);
    	
    	//Ensure cannot place a city where there is not a settlement
    	assertEquals(bModel.canBuildCity(bModel.getHexTileAt(-1, 0).northWestCorner, p1), false);
    	
    	//Ensure cannot place city where the settlement is another player's
    	assertEquals(bModel.canBuildCity(bModel.getHexTileAt(0, 0).eastCorner, p1), false);
    	
    	//Ensure can place a city where a friendly settlement is
    	assertEquals(bModel.canBuildCity(bModel.getHexTileAt(0, 0).northWestCorner, p1), true);
    	
    	//build some roads to enable further tests
    	bModel.getHexTileAt(0, 0).buildRoad(bModel.getHexTileAt(0, 0).northEastEdge, p2);
    	bModel.getHexTileAt(0, 0).buildRoad(bModel.getHexTileAt(0, 0).northEdge, p2);
    	bModel.getHexTileAt(0, -1).buildRoad(bModel.getHexTileAt(0, -1).southWestEdge, p1);
    	bModel.getHexTileAt(0, -1).buildRoad(bModel.getHexTileAt(0, -1).northWestEdge, p1);
    	bModel.getHexTileAt(0, -2).buildRoad(bModel.getHexTileAt(0, -2).southWestEdge, p1);
    	bModel.getHexTileAt(0, -2).buildRoad(bModel.getHexTileAt(0, -2).northWestEdge, p1);
    	
    	//Ensure cannot place a settlement adjacent to another one
    	assertEquals(bModel.canBuildSettlement(bModel.getHexTileAt(0, 0).northEastCorner, p2, false), false);
    	
    	//Ensure cannot place a road that runs through another player's settlement
    	assertEquals(bModel.canBuildRoad(bModel.getHexTileAt(0, 0).northWestEdge, p2, false), false);
    	
    	//Ensure can build a settlement in a valid location, connected by a road and apart from other settlements
    	assertEquals(bModel.canBuildSettlement(bModel.getHexTileAt(0, -1).northWestCorner, p1, false), true);
    	
    	//Ensure cannot build a road in the ocean
    	assertEquals(bModel.canBuildRoad(bModel.getHexTileAt(0, -3).southWestEdge, p1, false), false);
    	
    	//Ensure cannot build a settlement in the ocean
    	assertEquals(bModel.canBuildSettlement(bModel.getHexTileAt(0, -3).northWestCorner, p1, false), false);
    }
}