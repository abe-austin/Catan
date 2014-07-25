package client.map;

import client.base.*;
import client.data.*;
import client.parse.ParsedStructure;
import controller.ControllerFacade;
import controller.IControllerFacadeListener;
import game.GameModel;
import game.board.HexTile;
import game.board.PortTile;
import game.board.ResourceTile;

import java.util.*;

import player.Player;
import shared.definitions.*;
import shared.locations.*;


/**
 * Implementation for the map controller
 */
public class MapController extends Controller implements IMapController, IControllerFacadeListener {
	
	private IRobView robView;
	
	public MapController(IMapView view, IRobView robView) {
		
		super(view);
		
		setRobView(robView);
		
		initFromModel();
		
		List<HexTile> newMap = ControllerFacade.getSingleton().updateMap(this);
		//updateMap(newMap);
                ControllerFacade.getSingleton().addListener(this);
	}
        private boolean firstSettlement = true;
        private boolean firstRoad =true;
        private boolean secondSettlement= true;
        private boolean afterFirst = true;
        private boolean secondRoad = true;
        private boolean placingPiece=false;
        private HexLocation lastRobLoc;
        
	@Override
        public void gameModelChanged(GameModel gameModel){
            if(ControllerFacade.getSingleton().getGameState()==GameState.Setup){
                //change the gui
                updateMap(gameModel.getBoard().getHexes());
                updateStructures(gameModel.getBoard().getStructures());
                if(ControllerFacade.getSingleton().isCurrentTurn()){
                   
                   if (!placingPiece){
                       placingSetupPiece();
                   }
                }
            }
            else if(ControllerFacade.getSingleton().getGameState()==GameState.GamePlay){
                updateMap(gameModel.getBoard().getHexes());
                updateStructures(gameModel.getBoard().getStructures());
                getView().placeRobber(gameModel.getBoard().getRobber().getLocation());
                if(ControllerFacade.getSingleton().isCurrentTurn()){
                	if(ControllerFacade.getSingleton().getRoll() == 7){
                		ControllerFacade.getSingleton().clearRoll();
                		beginRobber();
                	}
                }
            }
        
        }
        
        private void placingSetupPiece(){
             if(firstSettlement){
                        startMove(PieceType.SETTLEMENT,true,true);
                        firstSettlement=false;
                         placingPiece=true;
                    }
                    else if(firstRoad){
                         startMove(PieceType.ROAD,true,false);
                         firstRoad=false;
                          placingPiece=true;
                    }
                    else if (afterFirst){
                        afterFirst=false;
                         placingPiece=false;
                    }
                    else if(secondSettlement){
                         startMove(PieceType.SETTLEMENT,true,true);
                         secondSettlement=false;
                          placingPiece=true;
                    }
                    else if(secondRoad){
                         startMove(PieceType.ROAD,true,false);
                         secondRoad=false;
                         placingPiece=true;
                    }
        }
         	
	public IMapView getView() {
		
		return (IMapView)super.getView();
	}
	
	private IRobView getRobView() {
		return robView;
	}
	private void setRobView(IRobView robView) {
		this.robView = robView;
	}
	
	protected void initFromModel() {
		
		//<temp>
		
		//This undoes everything I've done with the random world generation...those posers.
		
		Random rand = new Random();

		for (int x = 0; x <= 3; ++x) {
			
			int maxY = 3 - x;			
			for (int y = -3; y <= maxY; ++y) {				
				int r = rand.nextInt(HexType.values().length);
				HexType hexType = HexType.values()[r];
				HexLocation hexLoc = new HexLocation(x, y);
				getView().addHex(hexLoc, hexType);
//				getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
//						CatanColor.RED);
//				getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
//						CatanColor.BLUE);
//				getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
//						CatanColor.ORANGE);
//				getView().placeSettlement(new VertexLocation(hexLoc,  VertexDirection.NorthWest), CatanColor.GREEN);
//				getView().placeCity(new VertexLocation(hexLoc,  VertexDirection.NorthEast), CatanColor.PURPLE);
			}
			
			if (x != 0) {
				int minY = x - 3;
				for (int y = minY; y <= 3; ++y) {
					int r = rand.nextInt(HexType.values().length);
					HexType hexType = HexType.values()[r];
					HexLocation hexLoc = new HexLocation(-x, y);
					getView().addHex(hexLoc, hexType);
//					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
//							CatanColor.RED);
//					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
//							CatanColor.BLUE);
//					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
//							CatanColor.ORANGE);
//					getView().placeSettlement(new VertexLocation(hexLoc,  VertexDirection.NorthWest), CatanColor.GREEN);
//					getView().placeCity(new VertexLocation(hexLoc,  VertexDirection.NorthEast), CatanColor.PURPLE);
				}
			}
		}
		
		PortType portType = PortType.BRICK;
		//getView().addPort(new EdgeLocation(new HexLocation(0, 3), EdgeDirection.North), portType);
		//getView().addPort(new EdgeLocation(new HexLocation(0, -3), EdgeDirection.South), portType);
		//getView().addPort(new EdgeLocation(new HexLocation(-3, 3), EdgeDirection.NorthEast), portType);
		//getView().addPort(new EdgeLocation(new HexLocation(-3, 0), EdgeDirection.SouthEast), portType);
		//getView().addPort(new EdgeLocation(new HexLocation(3, -3), EdgeDirection.SouthWest), portType);
		//getView().addPort(new EdgeLocation(new HexLocation(3, 0), EdgeDirection.NorthWest), portType);
		
		getView().placeRobber(new HexLocation(0, 0));
		
//		getView().addNumber(new HexLocation(-2, 0), 2);
//		getView().addNumber(new HexLocation(-2, 1), 3);
//		getView().addNumber(new HexLocation(-2, 2), 4);
//		getView().addNumber(new HexLocation(-1, 0), 5);
//		getView().addNumber(new HexLocation(-1, 1), 6);
//		getView().addNumber(new HexLocation(1, -1), 8);
//		getView().addNumber(new HexLocation(1, 0), 9);
//		getView().addNumber(new HexLocation(2, -2), 10);
//		getView().addNumber(new HexLocation(2, -1), 11);
//		getView().addNumber(new HexLocation(2, 0), 12);
		
		//</temp>
	}
	
	private void updateMap(List<HexTile> newMap) {
		
		for(int i = 0; i < newMap.size(); i++) {
			int x = newMap.get(i).getX();
			int y = newMap.get(i).getY();
			if( (x==1 && y==-3) || (x==3 && y==-3) || (x==3 && y==-1) || (x==2 && y==1) || (x==0 && y==3) || (x==-2 && y==3)
				|| (x==-3 && y==2) || (x==-3 && y==0) || (x==-1 && y==-2)) {//See if is a port
				HexLocation hexLoc = new HexLocation(newMap.get(i).getX(), newMap.get(i).getY());
				PortType portType = ((PortTile)newMap.get(i)).getPortType();
				
				EdgeLocation edgeLocation = null;
				if((x == 1 && y == -3) || (x == -1 && y == -2))
					edgeLocation = new EdgeLocation(new HexLocation(x, y), EdgeDirection.South);
				if(x == 3 && y == -3)
					edgeLocation = new EdgeLocation(new HexLocation(x, y), EdgeDirection.SouthWest);
				if((x == 3 && y == -1) || (x == 2 && y == 1))
					edgeLocation = new EdgeLocation(new HexLocation(x, y), EdgeDirection.NorthWest);
				if(x == 0 && y == 3)
					edgeLocation = new EdgeLocation(new HexLocation(x, y), EdgeDirection.North);
				if((x == -2 && y == 3) || (x == -3 && y == 2))
					edgeLocation = new EdgeLocation(new HexLocation(x, y), EdgeDirection.NorthEast);
				if(x == -3 && y == 0)
					edgeLocation = new EdgeLocation(new HexLocation(x, y), EdgeDirection.SouthEast);
				
				
				getView().addPort(edgeLocation, portType);		
			}
			
				HexLocation hexLoc = new HexLocation(newMap.get(i).getX(), newMap.get(i).getY());
				HexType hexType = newMap.get(i).getType();
				getView().addHex(hexLoc, hexType);
				if(hexType == HexType.DESERT)
					getView().placeRobber(new HexLocation(newMap.get(i).getX(), newMap.get(i).getY()));
				
				else if(hexType != HexType.WATER) {
					ResourceTile resourceTile = (ResourceTile)newMap.get(i);
					getView().addNumber(new HexLocation(x, y), resourceTile.getToken().getValue());
				}
			
		}
	}
	
	public void updateStructures(List<ParsedStructure> newStructures) {
		for(int i = 0; i < newStructures.size(); i++) {
			
			int playerIndex = newStructures.get(i).getOwner();
			Player[] players = ControllerFacade.getSingleton().getPlayers();
			CatanColor color = null;
			
			for(int j = 0; j < players.length; j++) {
				if(players[j].getIndex() == playerIndex)
					color = players[j].getColor();
			}
			
			if(newStructures.get(i).getType().equals("ROAD"))
			{
				EdgeDirection eD = null;
				if(newStructures.get(i).getDirection().equals("N"))
					eD = EdgeDirection.North;
				if(newStructures.get(i).getDirection().equals("NW"))
					eD = EdgeDirection.NorthWest;
				if(newStructures.get(i).getDirection().equals("SW"))
					eD = EdgeDirection.SouthWest;
				if(newStructures.get(i).getDirection().equals("S"))
					eD = EdgeDirection.South;
				if(newStructures.get(i).getDirection().equals("SE"))
					eD = EdgeDirection.SouthEast;
				if(newStructures.get(i).getDirection().equals("NE"))
					eD = EdgeDirection.NorthEast;
				
				HexLocation hexLoc = new HexLocation(newStructures.get(i).getX(), newStructures.get(i).getY());
				EdgeLocation e = new EdgeLocation(hexLoc, eD);
				placeRoad(e, color);
			}
			
			else
			{
				VertexDirection vD = null;
				if(newStructures.get(i).getDirection().equals("NW"))
					vD = VertexDirection.NorthWest;
				if(newStructures.get(i).getDirection().equals("NE"))
					vD = VertexDirection.NorthEast;
				if(newStructures.get(i).getDirection().equals("E"))
					vD = VertexDirection.East;
				if(newStructures.get(i).getDirection().equals("SE"))
					vD = VertexDirection.SouthEast;
				if(newStructures.get(i).getDirection().equals("SW"))
					vD = VertexDirection.SouthWest;
				if(newStructures.get(i).getDirection().equals("W"))
					vD = VertexDirection.West;
				
				HexLocation hexLoc = new HexLocation(newStructures.get(i).getX(), newStructures.get(i).getY());
				VertexLocation e = new VertexLocation(hexLoc, vD);
				
				if(newStructures.get(i).getType().equals("SETTLEMENT"))
					placeSettlement(e, color);
				else if(newStructures.get(i).getType().equals("CITY"))
						placeCity(e, color);
			}
				
		}
	}
	
        public void pieceBuilt(){
        	
            if(ControllerFacade.getSingleton().getGameState()==GameState.Setup){
                if(firstRoad){
                    placingPiece=false;
                }
                else if(secondSettlement){
                    placingPiece=false;
                    ControllerFacade.getSingleton().endTurn();
                }
                else if(secondRoad){
                    placingPiece=false;
                }
                else{
                    ControllerFacade.getSingleton().endTurn();
                    ControllerFacade.getSingleton().setGameState(GameState.GamePlay);
                }
            }
        }
        
	public void placeRoad(EdgeLocation edgeLoc, CatanColor color) {
		getView().placeRoad(edgeLoc, color);
                //pieceBuilt();
	}
	
	public void placeSettlement(VertexLocation vertLoc, CatanColor color) {
		getView().placeSettlement(vertLoc, color);
                
		int portType = isPort(vertLoc);
		if(portType != 0)
			switch(portType) {
				case 1: ControllerFacade.getSingleton().getClientPlayer().addPort(PortType.BRICK); break;
				case 2: ControllerFacade.getSingleton().getClientPlayer().addPort(PortType.WHEAT); break;
				case 3: ControllerFacade.getSingleton().getClientPlayer().addPort(PortType.ORE); break;
				case 4: ControllerFacade.getSingleton().getClientPlayer().addPort(PortType.SHEEP); break;
				case 5: ControllerFacade.getSingleton().getClientPlayer().addPort(PortType.WOOD); break;
				case 6: ControllerFacade.getSingleton().getClientPlayer().addPort(PortType.THREE); break;
			}	
                //pieceBuilt();
	}
	
	public void placeCity(VertexLocation vertLoc, CatanColor color) {
		getView().placeCity(vertLoc, color);
	}
	
        @Override
	public boolean canPlaceRoad(EdgeLocation edgeLoc) {
		return ControllerFacade.getSingleton().canPlaceRoad(edgeLoc);
	}

        @Override
	public boolean canPlaceSettlement(VertexLocation vertLoc) {
		return ControllerFacade.getSingleton().canPlaceSettlement(vertLoc);
	}

        @Override
	public boolean canPlaceCity(VertexLocation vertLoc) {
		return ControllerFacade.getSingleton().canPlaceCity(vertLoc);
	}

        @Override
	public boolean canPlaceRobber(HexLocation hexLoc) {
		return ControllerFacade.getSingleton().canPlaceRobber(hexLoc);
	}

        @Override
	public void placeRoad(EdgeLocation edgeLoc) {
		CatanColor playerColor = ControllerFacade.getSingleton().getClientPlayer().getColor(); //Assume this is proper method to determine the color
		getView().placeRoad(edgeLoc, playerColor);
                ControllerFacade.getSingleton().placeRoad(edgeLoc);
                pieceBuilt();
	}

        @Override
	public void placeSettlement(VertexLocation vertLoc) {
		CatanColor playerColor = ControllerFacade.getSingleton().getClientPlayer().getColor();
		getView().placeSettlement(vertLoc, playerColor);
               
		int portType = isPort(vertLoc);
		if(portType != 0)
			switch(portType) {
				case 1: ControllerFacade.getSingleton().getClientPlayer().addPort(PortType.BRICK); break;
				case 2: ControllerFacade.getSingleton().getClientPlayer().addPort(PortType.WHEAT); break;
				case 3: ControllerFacade.getSingleton().getClientPlayer().addPort(PortType.ORE); break;
				case 4: ControllerFacade.getSingleton().getClientPlayer().addPort(PortType.SHEEP); break;
				case 5: ControllerFacade.getSingleton().getClientPlayer().addPort(PortType.WOOD); break;
				case 6: ControllerFacade.getSingleton().getClientPlayer().addPort(PortType.THREE); break;
			}
                ControllerFacade.getSingleton().placeSettlement(vertLoc);
                 pieceBuilt();
	}
	
	public int isPort(VertexLocation vertLoc) {
		HexLocation hexL = vertLoc.getHexLoc();
		VertexDirection corner = vertLoc.getDir();
		
		if(hexL.getX() == 1 && hexL.getY() == -3 && corner == VertexDirection.SouthEast ||
			hexL.getX() == 1 && hexL.getY() == -3 && corner == VertexDirection.SouthWest ||	
			hexL.getX() == 1 && hexL.getY() == -2 && corner == VertexDirection.NorthEast ||
			hexL.getX() == 1 && hexL.getY() == -2 && corner == VertexDirection.NorthWest ||
			hexL.getX() == 2 && hexL.getY() == -3 && corner == VertexDirection.West ||
			hexL.getX() == 0 && hexL.getY() == -2 && corner == VertexDirection.East)
			return getPortType(1, -3);
		
		if(hexL.getX() == 3 && hexL.getY() == -3 && corner == VertexDirection.West ||
			hexL.getX() == 3 && hexL.getY() == -3 && corner == VertexDirection.SouthWest ||	
			hexL.getX() == 2 && hexL.getY() == -2 && corner == VertexDirection.NorthEast ||
			hexL.getX() == 2 && hexL.getY() == -2 && corner == VertexDirection.East ||
			hexL.getX() == 3 && hexL.getY() == -2 && corner == VertexDirection.NorthWest ||
			hexL.getX() == 2 && hexL.getY() == -3 && corner == VertexDirection.SouthEast)
			return getPortType(3, -3);
		
		if(hexL.getX() == 2 && hexL.getY() == -2 && corner == VertexDirection.SouthWest ||
			hexL.getX() == 3 && hexL.getY() == -1 && corner == VertexDirection.West ||	
			hexL.getX() == 3 && hexL.getY() == -1 && corner == VertexDirection.NorthWest ||
			hexL.getX() == 2 && hexL.getY() == -1 && corner == VertexDirection.SouthEast ||
			hexL.getX() == 2 && hexL.getY() == -1 && corner == VertexDirection.East ||
			hexL.getX() == 2 && hexL.getY() == 0 && corner == VertexDirection.NorthEast)
			return getPortType(3, -1);
		
		if(hexL.getX() == 2 && hexL.getY() == 1 && corner == VertexDirection.West ||
			hexL.getX() == 2 && hexL.getY() == 1 && corner == VertexDirection.NorthWest ||	
			hexL.getX() == 1 && hexL.getY() == 1 && corner == VertexDirection.SouthEast ||
			hexL.getX() == 1 && hexL.getY() == 1 && corner == VertexDirection.East ||
			hexL.getX() == 1 && hexL.getY() == 2 && corner == VertexDirection.NorthEast ||
			hexL.getX() == 2 && hexL.getY() == 0 && corner == VertexDirection.SouthWest)
			return getPortType(2, 1);
		
		if(hexL.getX() == 0 && hexL.getY() == 3 && corner == VertexDirection.NorthWest ||
			hexL.getX() == 0 && hexL.getY() == 3 && corner == VertexDirection.NorthEast ||	
			hexL.getX() == 0 && hexL.getY() == 2 && corner == VertexDirection.SouthEast ||
			hexL.getX() == 0 && hexL.getY() == 2 && corner == VertexDirection.SouthWest ||
			hexL.getX() == 1 && hexL.getY() == 2 && corner == VertexDirection.West ||
			hexL.getX() == -1 && hexL.getY() == 3 && corner == VertexDirection.East)
			return getPortType(0, 3);
		
		if(hexL.getX() == -2 && hexL.getY() == 3 && corner == VertexDirection.East ||
			hexL.getX() == -2 && hexL.getY() == 3 && corner == VertexDirection.NorthEast ||	
			hexL.getX() == -1 && hexL.getY() == 2 && corner == VertexDirection.SouthWest ||
			hexL.getX() == -1 && hexL.getY() == 2 && corner == VertexDirection.West ||
			hexL.getX() == -1 && hexL.getY() == 3 && corner == VertexDirection.NorthWest ||
			hexL.getX() == -2 && hexL.getY() == 2 && corner == VertexDirection.SouthEast)
			return getPortType(-2, 3);
		
		if(hexL.getX() == -3 && hexL.getY() == 2 && corner == VertexDirection.East ||
			hexL.getX() == -3 && hexL.getY() == 2 && corner == VertexDirection.NorthEast ||	
			hexL.getX() == -2 && hexL.getY() == 1 && corner == VertexDirection.SouthWest ||
			hexL.getX() == -2 && hexL.getY() == 1 && corner == VertexDirection.West ||
			hexL.getX() == -2 && hexL.getY() == 2 && corner == VertexDirection.NorthWest ||
			hexL.getX() == -3 && hexL.getY() == 1 && corner == VertexDirection.SouthEast)
			return getPortType(-3, 2);
		
		if(hexL.getX() == -3 && hexL.getY() == 0 && corner == VertexDirection.East ||
			hexL.getX() == -3 && hexL.getY() == 0 && corner == VertexDirection.SouthEast||	
			hexL.getX() == -2 && hexL.getY() == 0 && corner == VertexDirection.NorthWest ||
			hexL.getX() == -2 && hexL.getY() == 0 && corner == VertexDirection.West ||
			hexL.getX() == -2 && hexL.getY() == 1 && corner == VertexDirection.SouthWest ||
			hexL.getX() == -3 && hexL.getY() == 1 && corner == VertexDirection.NorthEast)
			return getPortType(-3, 0);
		
		if(hexL.getX() == -1 && hexL.getY() == -2 && corner == VertexDirection.SouthEast ||
			hexL.getX() == -1 && hexL.getY() == -2 && corner == VertexDirection.SouthWest ||	
			hexL.getX() == -1 && hexL.getY() == -1 && corner == VertexDirection.NorthEast ||
			hexL.getX() == -1 && hexL.getY() == -1 && corner == VertexDirection.NorthWest ||
			hexL.getX() == 0 && hexL.getY() == -2 && corner == VertexDirection.West ||
			hexL.getX() == -2 && hexL.getY() == -1 && corner == VertexDirection.East)
			return getPortType(3, -3);
			
		return 0;
	}
	
	public int getPortType(int x, int y) {
		return ControllerFacade.getSingleton().getPortType(x, y);
	}

        @Override
	public void placeCity(VertexLocation vertLoc) {
		CatanColor playerColor = ControllerFacade.getSingleton().getClientPlayer().getColor();
		getView().placeCity(vertLoc, playerColor);
                ControllerFacade.getSingleton().placeCity(vertLoc);
	}

        @Override
        public void placeRobber(HexLocation hexLoc) {
    		
    		getView().placeRobber(hexLoc);
    		
    		ArrayList<RobPlayerInfo> robInfos = ControllerFacade.getSingleton().placeRobber(hexLoc);
    		RobPlayerInfo[] candidateVictims = robInfos.toArray(new RobPlayerInfo[robInfos.size()]);		
    		getRobView().setPlayers(candidateVictims);
    		getRobView().showModal();
    		lastRobLoc = hexLoc;
    	}
    	
    	public void beginRobber() {
    		getView().startDrop(PieceType.ROBBER, ControllerFacade.getSingleton().getClientPlayer().getColor(), true);
    		ControllerFacade.getSingleton().discardExtras();
    	}
	
    @Override
    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {	
    		CatanColor playerColor = ControllerFacade.getSingleton().getClientPlayer().getColor();
    		getView().startDrop(pieceType, playerColor, allowDisconnected);
    }
	
        @Override
	public void cancelMove() {
		
	}
	
        @Override
	public void playSoldierCard() {	
		
	}
	
        @Override
	public void playRoadBuildingCard() {	
		
	}
	
        @Override
        public void robPlayer(RobPlayerInfo victim) {		
    		ControllerFacade.getSingleton().robPlayer(victim, lastRobLoc);
    	}
	
}

