package client.map;

import client.base.*;
import client.data.*;
import controller.ControllerFacade;
import controller.IControllerFacadeListener;
import game.GameModel;
import game.board.HexTile;
import game.board.PortTile;
import game.board.ResourceTile;

import java.util.*;

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
        
	@Override
        public void gameModelChanged(GameModel gameModel){
        
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
		
		
		
//		System.out.println("Here with " + newMap.size());
//		HexType hexType = HexType.WATER;
//		
//		for (int x = 0; x <= 3; ++x) {	
//			int maxY = 3 - x;			
//			for (int y = -3; y <= maxY; ++y) {
//				HexLocation hexLoc = new HexLocation(x, y);
//				getView().addHex(hexLoc, hexType);
//			}
//		}
//		
//		hexType = HexType.WHEAT;
//		for (int x = 0; x <= 3; ++x) {	
//			int maxY = 3 - x;			
//			for (int y = -3; y <= maxY; ++y) {
//				HexLocation hexLoc = new HexLocation(x, y);
//				getView().addHex(hexLoc, hexType);
//			}
//		}
//		
//		hexType = HexType.SHEEP;
//		HexLocation hexLoc = new HexLocation(0, 0);
//		getView().addHex(hexLoc, hexType);
		
		
		//getView().
	}

	public boolean canPlaceRoad(EdgeLocation edgeLoc) {
		return ControllerFacade.getSingleton().canPlaceRoad(edgeLoc);
	}

	public boolean canPlaceSettlement(VertexLocation vertLoc) {
		return ControllerFacade.getSingleton().canPlaceSettlement(vertLoc);
	}

	public boolean canPlaceCity(VertexLocation vertLoc) {
		return ControllerFacade.getSingleton().canPlaceCity(vertLoc);
	}

	public boolean canPlaceRobber(HexLocation hexLoc) {
		return ControllerFacade.getSingleton().canPlaceRobber(hexLoc);
	}

	public void placeRoad(EdgeLocation edgeLoc) {
		CatanColor playerColor = ControllerFacade.getSingleton().getClientPlayer().getColor(); //Assume this is proper method to determine the color
		getView().placeRoad(edgeLoc, playerColor);
	}

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

	public void placeCity(VertexLocation vertLoc) {
		CatanColor playerColor = ControllerFacade.getSingleton().getClientPlayer().getColor();
		getView().placeCity(vertLoc, playerColor);
	}

	public void placeRobber(HexLocation hexLoc) {
		
		getView().placeRobber(hexLoc);
		
		getRobView().showModal();
	}
	
	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {	
		CatanColor playerColor = ControllerFacade.getSingleton().getClientPlayer().getColor();
		getView().startDrop(pieceType, playerColor, allowDisconnected);
	}
	
	public void cancelMove() {
		
	}
	
	public void playSoldierCard() {	
		
	}
	
	public void playRoadBuildingCard() {	
		
	}
	
	public void robPlayer(RobPlayerInfo victim) {	
		
	}
	
}

