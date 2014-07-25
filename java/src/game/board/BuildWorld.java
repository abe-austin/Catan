package game.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;


public class BuildWorld {
	
	private List<HexTile> tiles;
	private List<Edge> edges;
	private List<Corner> corners;
	private boolean seenDesert;
	
	public BuildWorld() {//Perhaps a constructor with a list of hexes, tiles, and vertices. Another that is empty and does lists of each
		seenDesert = false;
		defaultSetup();
	}
	
	//public BuildWorld(List<HexTile> tiles, List<Edge> edges, List<Corner> corners) {
	//	this.tiles = tiles;
	//	this.edges = edges;
	//	this.corners = corners;
	//}
	
	public void defaultSetup() {
		tiles = new ArrayList<HexTile>();
		edges = new ArrayList<Edge>();
		corners = new ArrayList<Corner>();
		createTiles();
		addEdges();
		addVertices();
		updateTiles();
	}
	
	public List<HexTile> getTiles() {
		return tiles;
	}
	
	public void createTiles() {
		//Key
		// 0 = Wood
		// 1 = brick
		// 2 = sheep
		// 3 = wheat
		// 4 = ore
		// 5 = desert
		ArrayList<Integer> places = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5));
		
		//Key
		// 0 = threePort
		// 1 = woodPort
		// 2 = brickPort
		// 3 = sheepPort
		// 4 = wheatPort
		// 5 = orePort
		ArrayList<Integer> ports = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 1, 2, 3, 4, 5));
		
		ArrayList<Integer> numTokens = new ArrayList<Integer>(Arrays.asList(2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12));
		
		Collections.shuffle(places);
		Collections.shuffle(ports);
		Collections.shuffle(numTokens);
		
		//Create ocean tiles
		HexTile p0n3 = new OceanTile();
		p0n3.setCoordinates(0, -3);
		HexTile p2n3 = new OceanTile();
		p2n3.setCoordinates(2, -3);
		HexTile p3n2 = new OceanTile();
		p3n2.setCoordinates(3, -2);
		HexTile p3p0 = new OceanTile();
		p3p0.setCoordinates(3, 0);
		HexTile p1p2 = new OceanTile();
		p1p2.setCoordinates(1, 2);
		HexTile n1p3 = new OceanTile();
		n1p3.setCoordinates(-1, 3);
		HexTile n3p3 = new OceanTile();
		n3p3.setCoordinates(-3, 3);
		HexTile n3p1 = new OceanTile();
		n3p1.setCoordinates(-3, 1);
		HexTile n2n1 = new OceanTile();
		n2n1.setCoordinates(-2, -1);
		
		//Create port tiles...Note, we may need some way to denote when a corner is a port corner, as port hexes do not have all of their corners as ports
		HexTile p1n3 = getPortType(ports.get(0));
		p1n3.setCoordinates(1, -3);
		HexTile p3n3 = getPortType(ports.get(1));
		p3n3.setCoordinates(3, -3);
		HexTile p3n1 = getPortType(ports.get(2));
		p3n1.setCoordinates(3, -1);
		HexTile p2p1 = getPortType(ports.get(3));
		p2p1.setCoordinates(2, 1);
		HexTile p0p3 = getPortType(ports.get(4));
		p0p3.setCoordinates(0, 3);
		HexTile n2p3 = getPortType(ports.get(5));
		n2p3.setCoordinates(-2, 3);
		HexTile n3p2 = getPortType(ports.get(6));
		n3p2.setCoordinates(-3, 2);
		HexTile n3p0 = getPortType(ports.get(7));
		n3p0.setCoordinates(-3, 0);
		HexTile n1n2 = getPortType(ports.get(8));
		n1n2.setCoordinates(-1, -2);
		
		//Create land tiles
		HexTile p0n2 = getLandType(places.get(0), numTokens, 0);
		p0n2.setCoordinates(0, -2);
		HexTile p1n2 = getLandType(places.get(1), numTokens, 1);
		p1n2.setCoordinates(1, -2);
		HexTile p2n2 = getLandType(places.get(2), numTokens, 2);
		p2n2.setCoordinates(2, -2);
		HexTile p2n1 = getLandType(places.get(3), numTokens, 3);
		p2n1.setCoordinates(2, -1);
		HexTile p2p0 = getLandType(places.get(4), numTokens, 4);
		p2p0.setCoordinates(2, 0);
		HexTile p1p1 = getLandType(places.get(5), numTokens, 5);
		p1p1.setCoordinates(1, 1);
		HexTile p0p2 = getLandType(places.get(6), numTokens, 6);
		p0p2.setCoordinates(0, 2);
		HexTile n1p2 = getLandType(places.get(7), numTokens, 7);
		n1p2.setCoordinates(-1, 2);
		HexTile n2p2 = getLandType(places.get(8), numTokens, 8);
		n2p2.setCoordinates(-2, 2);
		HexTile n2p1 = getLandType(places.get(9), numTokens, 9);
		n2p1.setCoordinates(-2, 1);
		HexTile n2p0 = getLandType(places.get(10), numTokens, 10);
		n2p0.setCoordinates(-2, 0);
		HexTile n1n1 = getLandType(places.get(11), numTokens, 11);
		n1n1.setCoordinates(-1, -1);
		HexTile p0n1 = getLandType(places.get(12), numTokens, 12);
		p0n1.setCoordinates(0, -1);
		HexTile p1n1 = getLandType(places.get(13), numTokens, 13);
		p1n1.setCoordinates(1, -1);
		HexTile p1p0 = getLandType(places.get(14), numTokens, 14);
		p1p0.setCoordinates(1, 0);
		HexTile p0p1 = getLandType(places.get(15), numTokens, 15);
		p0p1.setCoordinates(0, 1);
		HexTile n1p1 = getLandType(places.get(16), numTokens, 16);
		n1p1.setCoordinates(-1, 1);
		HexTile n1p0 = getLandType(places.get(17), numTokens, 17);
		n1p0.setCoordinates(-1, 0);
		HexTile p0p0 = getLandType(places.get(18), numTokens, 18);
		p0p0.setCoordinates(0, 0);
		
		//Add all tiles to list
		tiles.add(n3p0);
		tiles.add(n3p1);
		tiles.add(n3p2);
		tiles.add(n3p3);
		tiles.add(n2n1);
		tiles.add(n2p0);
		tiles.add(n2p1);
		tiles.add(n2p2);
		tiles.add(n2p3);
		tiles.add(n1n2);
		tiles.add(n1n1);
		tiles.add(n1p0);
		tiles.add(n1p1);
		tiles.add(n1p2);
		tiles.add(n1p3);
		tiles.add(p0n3);
		tiles.add(p0n2);
		tiles.add(p0n1);
		tiles.add(p0p0);
		tiles.add(p0p1);
		tiles.add(p0p2);
		tiles.add(p0p3);
		tiles.add(p1n3);
		tiles.add(p1n2);
		tiles.add(p1n1);
		tiles.add(p1p0);
		tiles.add(p1p1);
		tiles.add(p1p2);
		tiles.add(p2n3);
		tiles.add(p2n2);
		tiles.add(p2n1);
		tiles.add(p2p0);
		tiles.add(p2p1);
		tiles.add(p3n3);
		tiles.add(p3n2);
		tiles.add(p3n1);
		tiles.add(p3p0);
	}
	
	public HexTile getPortType(int number) {
		switch(number) {
			case 0: return new PortTile(PortType.THREE);
			case 1: return new PortTile(PortType.WOOD);
			case 2: return new PortTile(PortType.BRICK);
			case 3: return new PortTile(PortType.SHEEP);
			case 4: return new PortTile(PortType.WHEAT);
			case 5: return new PortTile(PortType.ORE);
			default: return null;
		}
	}
	
	public HexTile getLandType(int number, ArrayList<Integer> numberTokens, int index) {
		int theToken = 0;
		if(seenDesert || index == 18)
			theToken = numberTokens.get(index-1);
		else
			theToken = numberTokens.get(index);
		switch(number) {
			case 0: return new ResourceTile(ResourceType.WOOD, new NumberToken(theToken));
			case 1: return new ResourceTile(ResourceType.BRICK, new NumberToken(theToken));
			case 2: return new ResourceTile(ResourceType.SHEEP, new NumberToken(theToken));
			case 3: return new ResourceTile(ResourceType.WHEAT, new NumberToken(theToken));
			case 4: return new ResourceTile(ResourceType.ORE, new NumberToken(theToken));
			case 5: seenDesert = true; return new DesertTile();//DESERT
			default: return null;
		}
	}
	
	public void addEdges() {
		//0
		EdgeLocation l1 = new EdgeLocation(new HexLocation(0, -1), EdgeDirection.South);
		EdgeLocation l2 = new EdgeLocation(new HexLocation(0, 0), EdgeDirection.North);
		addEdge(l1, l2);
		
		//1
		l1 = new EdgeLocation(new HexLocation(1, -1), EdgeDirection.SouthWest);
		l2 = new EdgeLocation(new HexLocation(0, 0), EdgeDirection.NorthEast);
		addEdge(l1, l2);
		
		//2
		l1 = new EdgeLocation(new HexLocation(1, 0), EdgeDirection.NorthWest);
		l2 = new EdgeLocation(new HexLocation(0, 0), EdgeDirection.SouthEast);
		addEdge(l1, l2);
		
		//3
		l1 = new EdgeLocation(new HexLocation(0, 1), EdgeDirection.North);
		l2 = new EdgeLocation(new HexLocation(0, 0), EdgeDirection.South);
		addEdge(l1, l2);
		
		//4
		l1 = new EdgeLocation(new HexLocation(-1, 1), EdgeDirection.NorthEast);
		l2 = new EdgeLocation(new HexLocation(0, 0), EdgeDirection.SouthWest);
		addEdge(l1, l2);
		
		//5
		l1 = new EdgeLocation(new HexLocation(-1, 0), EdgeDirection.SouthEast);
		l2 = new EdgeLocation(new HexLocation(0, 0), EdgeDirection.NorthWest);
		addEdge(l1, l2);
		
		//6
		l1 = new EdgeLocation(new HexLocation(0, -1), EdgeDirection.SouthWest);
		l2 = new EdgeLocation(new HexLocation(-1, 0), EdgeDirection.NorthEast);
		addEdge(l1, l2);
		
		//7
		l1 = new EdgeLocation(new HexLocation(0, -1), EdgeDirection.SouthEast);
		l2 = new EdgeLocation(new HexLocation(1, -1), EdgeDirection.NorthWest);
		addEdge(l1, l2);
		
		//8
		l1 = new EdgeLocation(new HexLocation(1, -1), EdgeDirection.South);
		l2 = new EdgeLocation(new HexLocation(1, 0), EdgeDirection.North);
		addEdge(l1, l2);
		
		//9
		l1 = new EdgeLocation(new HexLocation(1, 0), EdgeDirection.SouthWest);
		l2 = new EdgeLocation(new HexLocation(0, 1), EdgeDirection.NorthEast);
		addEdge(l1, l2);
		
		//10
		l1 = new EdgeLocation(new HexLocation(0, 1), EdgeDirection.NorthWest);
		l2 = new EdgeLocation(new HexLocation(-1, 1), EdgeDirection.SouthEast);
		addEdge(l1, l2);
		
		//11
		l1 = new EdgeLocation(new HexLocation(-1, 1), EdgeDirection.North);
		l2 = new EdgeLocation(new HexLocation(-1, 0), EdgeDirection.South);
		addEdge(l1, l2);
		
		//12
		l1 = new EdgeLocation(new HexLocation(0, -2), EdgeDirection.South);
		l2 = new EdgeLocation(new HexLocation(0, -1), EdgeDirection.North);
		addEdge(l1, l2);
		
		//13
		l1 = new EdgeLocation(new HexLocation(0, -1), EdgeDirection.NorthEast);
		l2 = new EdgeLocation(new HexLocation(1, -2), EdgeDirection.SouthWest);
		addEdge(l1, l2);
		
		//14
		l1 = new EdgeLocation(new HexLocation(1, -2), EdgeDirection.South);
		l2 = new EdgeLocation(new HexLocation(1, -1), EdgeDirection.North);
		addEdge(l1, l2);
		
		//15
		l1 = new EdgeLocation(new HexLocation(1, -1), EdgeDirection.NorthEast);
		l2 = new EdgeLocation(new HexLocation(2, -2), EdgeDirection.SouthWest);
		addEdge(l1, l2);
		
		//16
		l1 = new EdgeLocation(new HexLocation(2, -1), EdgeDirection.NorthWest);
		l2 = new EdgeLocation(new HexLocation(1, -1), EdgeDirection.SouthEast);
		addEdge(l1, l2);
		
		//17
		l1 = new EdgeLocation(new HexLocation(1, 0), EdgeDirection.NorthEast);
		l2 = new EdgeLocation(new HexLocation(2, -1), EdgeDirection.SouthWest);
		addEdge(l1, l2);
		
		//18
		l1 = new EdgeLocation(new HexLocation(2, 0), EdgeDirection.NorthWest);
		l2 = new EdgeLocation(new HexLocation(1, 0), EdgeDirection.SouthEast);
		addEdge(l1, l2);
		
		//19
		l1 = new EdgeLocation(new HexLocation(1, 1), EdgeDirection.North);
		l2 = new EdgeLocation(new HexLocation(1, 0), EdgeDirection.South);
		addEdge(l1, l2);
		
		//20
		l1 = new EdgeLocation(new HexLocation(1, 1), EdgeDirection.NorthWest);
		l2 = new EdgeLocation(new HexLocation(0, 1), EdgeDirection.SouthEast);
		addEdge(l1, l2);
		
		//21
		l1 = new EdgeLocation(new HexLocation(0, 2), EdgeDirection.North);
		l2 = new EdgeLocation(new HexLocation(0, 1), EdgeDirection.South);
		addEdge(l1, l2);
		
		//22
		l1 = new EdgeLocation(new HexLocation(-1, 2), EdgeDirection.NorthEast);
		l2 = new EdgeLocation(new HexLocation(0, 1), EdgeDirection.SouthWest);
		addEdge(l1, l2);
		
		//23
		l1 = new EdgeLocation(new HexLocation(-1, 2), EdgeDirection.North);
		l2 = new EdgeLocation(new HexLocation(-1, 1), EdgeDirection.South);
		addEdge(l1, l2);
		
		//24
		l1 = new EdgeLocation(new HexLocation(-2, 2), EdgeDirection.NorthEast);
		l2 = new EdgeLocation(new HexLocation(-1, 1), EdgeDirection.SouthWest);
		addEdge(l1, l2);
		
		//25
		l1 = new EdgeLocation(new HexLocation(-1, 1), EdgeDirection.NorthWest);
		l2 = new EdgeLocation(new HexLocation(-2, 1), EdgeDirection.SouthEast);
		addEdge(l1, l2);
		
		//26
		l1 = new EdgeLocation(new HexLocation(-2, 1), EdgeDirection.NorthEast);
		l2 = new EdgeLocation(new HexLocation(-1, 0), EdgeDirection.SouthWest);
		addEdge(l1, l2);
		
		//27
		l1 = new EdgeLocation(new HexLocation(-1, 0), EdgeDirection.NorthWest);
		l2 = new EdgeLocation(new HexLocation(-2, 0), EdgeDirection.SouthEast);
		addEdge(l1, l2);
		
		//28
		l1 = new EdgeLocation(new HexLocation(-1, 0), EdgeDirection.North);
		l2 = new EdgeLocation(new HexLocation(-1, -1), EdgeDirection.South);
		addEdge(l1, l2);
		
		//29
		l1 = new EdgeLocation(new HexLocation(0, -1), EdgeDirection.NorthWest);
		l2 = new EdgeLocation(new HexLocation(-1, -1), EdgeDirection.SouthEast);
		addEdge(l1, l2);
		
		//30
		l1 = new EdgeLocation(new HexLocation(-1, -1), EdgeDirection.NorthEast);
		l2 = new EdgeLocation(new HexLocation(0, -2), EdgeDirection.SouthWest);
		addEdge(l1, l2);
		
		//31
		l1 = new EdgeLocation(new HexLocation(1, -2), EdgeDirection.NorthWest);
		l2 = new EdgeLocation(new HexLocation(0, -2), EdgeDirection.SouthEast);
		addEdge(l1, l2);
		
		//32
		l1 = new EdgeLocation(new HexLocation(2, -2), EdgeDirection.NorthWest);
		l2 = new EdgeLocation(new HexLocation(1, -2), EdgeDirection.SouthEast);
		addEdge(l1, l2);
		
		//33
		l1 = new EdgeLocation(new HexLocation(2, -1), EdgeDirection.North);
		l2 = new EdgeLocation(new HexLocation(2, -2), EdgeDirection.South);
		addEdge(l1, l2);
		
		//34
		l1 = new EdgeLocation(new HexLocation(2, 0), EdgeDirection.North);
		l2 = new EdgeLocation(new HexLocation(2, -1), EdgeDirection.South);
		addEdge(l1, l2);
		
		//35
		l1 = new EdgeLocation(new HexLocation(1, 1), EdgeDirection.NorthEast);
		l2 = new EdgeLocation(new HexLocation(2, 0), EdgeDirection.SouthWest);
		addEdge(l1, l2);
		
		//36
		l1 = new EdgeLocation(new HexLocation(0, 2), EdgeDirection.NorthEast);
		l2 = new EdgeLocation(new HexLocation(1, 1), EdgeDirection.SouthWest);
		addEdge(l1, l2);
		
		//37
		l1 = new EdgeLocation(new HexLocation(0, 2), EdgeDirection.NorthWest);
		l2 = new EdgeLocation(new HexLocation(-1, 2), EdgeDirection.SouthEast);
		addEdge(l1, l2);
		
		//38
		l1 = new EdgeLocation(new HexLocation(-1, 2), EdgeDirection.NorthWest);
		l2 = new EdgeLocation(new HexLocation(-2, 2), EdgeDirection.SouthEast);
		addEdge(l1, l2);
		
		//39
		l1 = new EdgeLocation(new HexLocation(-2, 2), EdgeDirection.North);
		l2 = new EdgeLocation(new HexLocation(-2, 1), EdgeDirection.South);
		addEdge(l1, l2);
		
		//40
		l1 = new EdgeLocation(new HexLocation(-2, 1), EdgeDirection.North);
		l2 = new EdgeLocation(new HexLocation(-2, 0), EdgeDirection.South);
		addEdge(l1, l2);
		
		//41
		l1 = new EdgeLocation(new HexLocation(-2, 0), EdgeDirection.NorthEast);
		l2 = new EdgeLocation(new HexLocation(-1, -1), EdgeDirection.SouthWest);
		addEdge(l1, l2);
		
		//42
		l1 = new EdgeLocation(new HexLocation(0, -2), EdgeDirection.North);
		l2 = new EdgeLocation(new HexLocation(0, -3), EdgeDirection.South);
		addEdge(l1, l2);
		
		//43
		l1 = new EdgeLocation(new HexLocation(0, -2), EdgeDirection.NorthEast);
		l2 = new EdgeLocation(new HexLocation(1, -3), EdgeDirection.SouthWest);
		addEdge(l1, l2);
		
		//44
		l1 = new EdgeLocation(new HexLocation(1, -2), EdgeDirection.North);
		l2 = new EdgeLocation(new HexLocation(1, -3), EdgeDirection.South);
		addEdge(l1, l2);
		
		//45
		l1 = new EdgeLocation(new HexLocation(1, -2), EdgeDirection.NorthEast);
		l2 = new EdgeLocation(new HexLocation(2, -3), EdgeDirection.SouthWest);
		addEdge(l1, l2);
		
		//46
		l1 = new EdgeLocation(new HexLocation(2, -2), EdgeDirection.North);
		l2 = new EdgeLocation(new HexLocation(2, -3), EdgeDirection.South);
		addEdge(l1, l2);
		
		//47
		l1 = new EdgeLocation(new HexLocation(2, -2), EdgeDirection.NorthEast);
		l2 = new EdgeLocation(new HexLocation(3, -3), EdgeDirection.SouthWest);
		addEdge(l1, l2);
		
		//48
		l1 = new EdgeLocation(new HexLocation(3, -2), EdgeDirection.NorthWest);
		l2 = new EdgeLocation(new HexLocation(2, -2), EdgeDirection.SouthEast);
		addEdge(l1, l2);
		
		//49
		l1 = new EdgeLocation(new HexLocation(2, -1), EdgeDirection.NorthEast);
		l2 = new EdgeLocation(new HexLocation(3, -2), EdgeDirection.SouthWest);
		addEdge(l1, l2);
		
		//50
		l1 = new EdgeLocation(new HexLocation(3, -1), EdgeDirection.NorthWest);
		l2 = new EdgeLocation(new HexLocation(2, -1), EdgeDirection.SouthEast);
		addEdge(l1, l2);
		
		//51
		l1 = new EdgeLocation(new HexLocation(2, 0), EdgeDirection.NorthEast);
		l2 = new EdgeLocation(new HexLocation(3, -1), EdgeDirection.SouthWest);
		addEdge(l1, l2);
		
		//52
		l1 = new EdgeLocation(new HexLocation(3, 0), EdgeDirection.NorthWest);
		l2 = new EdgeLocation(new HexLocation(2, 0), EdgeDirection.SouthEast);
		addEdge(l1, l2);
		
		//53
		l1 = new EdgeLocation(new HexLocation(2, 1), EdgeDirection.North);
		l2 = new EdgeLocation(new HexLocation(2, 0), EdgeDirection.South);
		addEdge(l1, l2);
		
		//54
		l1 = new EdgeLocation(new HexLocation(2, 1), EdgeDirection.NorthWest);
		l2 = new EdgeLocation(new HexLocation(1, 1), EdgeDirection.SouthEast);
		addEdge(l1, l2);
		
		//55
		l1 = new EdgeLocation(new HexLocation(1, 2), EdgeDirection.North);
		l2 = new EdgeLocation(new HexLocation(1, 1), EdgeDirection.South);
		addEdge(l1, l2);
		
		//56
		l1 = new EdgeLocation(new HexLocation(1, 2), EdgeDirection.NorthWest);
		l2 = new EdgeLocation(new HexLocation(0, 2), EdgeDirection.SouthEast);
		addEdge(l1, l2);
		
		//57
		l1 = new EdgeLocation(new HexLocation(0, 3), EdgeDirection.North);
		l2 = new EdgeLocation(new HexLocation(0, 2), EdgeDirection.South);
		addEdge(l1, l2);
		
		//58
		l1 = new EdgeLocation(new HexLocation(-1, 3), EdgeDirection.NorthEast);
		l2 = new EdgeLocation(new HexLocation(0, 2), EdgeDirection.SouthWest);
		addEdge(l1, l2);
		
		//59
		l1 = new EdgeLocation(new HexLocation(-1, 3), EdgeDirection.North);
		l2 = new EdgeLocation(new HexLocation(-1, 2), EdgeDirection.South);
		addEdge(l1, l2);
		
		//60
		l1 = new EdgeLocation(new HexLocation(-2, 3), EdgeDirection.NorthEast);
		l2 = new EdgeLocation(new HexLocation(-1, 2), EdgeDirection.SouthWest);
		addEdge(l1, l2);
		
		//61
		l1 = new EdgeLocation(new HexLocation(-2, 3), EdgeDirection.North);
		l2 = new EdgeLocation(new HexLocation(-2, 2), EdgeDirection.South);
		addEdge(l1, l2);
		
		//62
		l1 = new EdgeLocation(new HexLocation(-3, 3), EdgeDirection.NorthEast);
		l2 = new EdgeLocation(new HexLocation(-2, 2), EdgeDirection.SouthWest);
		addEdge(l1, l2);
		
		//63
		l1 = new EdgeLocation(new HexLocation(-2, 2), EdgeDirection.NorthWest);
		l2 = new EdgeLocation(new HexLocation(-3, 2), EdgeDirection.SouthEast);
		addEdge(l1, l2);
		
		//64
		l1 = new EdgeLocation(new HexLocation(-3, 2), EdgeDirection.NorthEast);
		l2 = new EdgeLocation(new HexLocation(-2, 1), EdgeDirection.SouthWest);
		addEdge(l1, l2);
		
		//65
		l1 = new EdgeLocation(new HexLocation(-2, 1), EdgeDirection.NorthWest);
		l2 = new EdgeLocation(new HexLocation(-3, 1), EdgeDirection.SouthEast);
		addEdge(l1, l2);
		
		//66
		l1 = new EdgeLocation(new HexLocation(-3, 1), EdgeDirection.NorthEast);
		l2 = new EdgeLocation(new HexLocation(-2, 0), EdgeDirection.SouthWest);
		addEdge(l1, l2);
		
		//67
		l1 = new EdgeLocation(new HexLocation(-2, 0), EdgeDirection.NorthWest);
		l2 = new EdgeLocation(new HexLocation(-3, 0), EdgeDirection.SouthEast);
		addEdge(l1, l2);
		
		//68
		l1 = new EdgeLocation(new HexLocation(-2, 0), EdgeDirection.North);
		l2 = new EdgeLocation(new HexLocation(-2, -1), EdgeDirection.South);
		addEdge(l1, l2);
		
		//69
		l1 = new EdgeLocation(new HexLocation(-1, -1), EdgeDirection.NorthWest);
		l2 = new EdgeLocation(new HexLocation(-2, -1), EdgeDirection.SouthEast);
		addEdge(l1, l2);
		
		//70
		l1 = new EdgeLocation(new HexLocation(-1, -1), EdgeDirection.North);
		l2 = new EdgeLocation(new HexLocation(-1, -2), EdgeDirection.South);
		addEdge(l1, l2);
		
		//71
		l1 = new EdgeLocation(new HexLocation(0, -2), EdgeDirection.NorthWest);
		l2 = new EdgeLocation(new HexLocation(-1, -2), EdgeDirection.SouthEast);
		addEdge(l1, l2);
	}
	
	public void addEdge(EdgeLocation l1, EdgeLocation l2) {
		List<EdgeLocation> list = new ArrayList<EdgeLocation>();
		list.add(l1);
		list.add(l2);
		edges.add(new Edge(list));
	}
	
	public void addVertices() {
		//0
		VertexLocation l1 = new VertexLocation(new HexLocation(0, 0), VertexDirection.NorthWest);
		VertexLocation l2 = new VertexLocation(new HexLocation(-1, 0), VertexDirection.East);
		VertexLocation l3 = new VertexLocation(new HexLocation(0, -1), VertexDirection.SouthWest);
		addVertex(l1, l2, l3);
				
		//1
		l1 = new VertexLocation(new HexLocation(0, 0), VertexDirection.NorthEast);
		l2 = new VertexLocation(new HexLocation(1, -1), VertexDirection.West);
		l3 = new VertexLocation(new HexLocation(0, -1), VertexDirection.SouthEast);
		addVertex(l1, l2, l3);
		
		//2
		l1 = new VertexLocation(new HexLocation(0, 0), VertexDirection.East);
		l2 = new VertexLocation(new HexLocation(1, -1), VertexDirection.SouthWest);
		l3 = new VertexLocation(new HexLocation(1, 0), VertexDirection.NorthWest);
		addVertex(l1, l2, l3);
		
		//3
		l1 = new VertexLocation(new HexLocation(0, 0), VertexDirection.SouthEast);
		l2 = new VertexLocation(new HexLocation(1, 0), VertexDirection.West);
		l3 = new VertexLocation(new HexLocation(0, 1), VertexDirection.NorthEast);
		addVertex(l1, l2, l3);
		
		//4
		l1 = new VertexLocation(new HexLocation(0, 0), VertexDirection.SouthWest);
		l2 = new VertexLocation(new HexLocation(0, 1), VertexDirection.NorthWest);
		l3 = new VertexLocation(new HexLocation(-1, 1), VertexDirection.East);
		addVertex(l1, l2, l3);
		
		//5
		l1 = new VertexLocation(new HexLocation(0, 0), VertexDirection.West);
		l2 = new VertexLocation(new HexLocation(-1, 0), VertexDirection.SouthEast);
		l3 = new VertexLocation(new HexLocation(-1, 1), VertexDirection.NorthEast);
		addVertex(l1, l2, l3);
		
		//6
		l1 = new VertexLocation(new HexLocation(0, -1), VertexDirection.NorthWest);
		l2 = new VertexLocation(new HexLocation(-1, -1), VertexDirection.East);
		l3 = new VertexLocation(new HexLocation(0, -2), VertexDirection.SouthWest);
		addVertex(l1, l2, l3);
		
		//7
		l1 = new VertexLocation(new HexLocation(0, -1), VertexDirection.NorthEast);
		l2 = new VertexLocation(new HexLocation(1, -2), VertexDirection.West);
		l3 = new VertexLocation(new HexLocation(0, -2), VertexDirection.SouthEast);
		addVertex(l1, l2, l3);
		
		//8
		l1 = new VertexLocation(new HexLocation(0, -1), VertexDirection.East);
		l2 = new VertexLocation(new HexLocation(1, -2), VertexDirection.SouthWest);
		l3 = new VertexLocation(new HexLocation(1, -1), VertexDirection.NorthWest);
		addVertex(l1, l2, l3);
		
		//9
		l1 = new VertexLocation(new HexLocation(1, -1), VertexDirection.NorthEast);
		l2 = new VertexLocation(new HexLocation(2, -2), VertexDirection.West);
		l3 = new VertexLocation(new HexLocation(1, -2), VertexDirection.SouthEast);
		addVertex(l1, l2, l3);
		
		//10
		l1 = new VertexLocation(new HexLocation(2, -1), VertexDirection.NorthWest);
		l2 = new VertexLocation(new HexLocation(1, -1), VertexDirection.East);
		l3 = new VertexLocation(new HexLocation(2, -2), VertexDirection.SouthWest);
		addVertex(l1, l2, l3);
		
		//11
		l1 = new VertexLocation(new HexLocation(1, 0), VertexDirection.NorthEast);
		l2 = new VertexLocation(new HexLocation(2, -1), VertexDirection.West);
		l3 = new VertexLocation(new HexLocation(1, -1), VertexDirection.SouthEast);
		addVertex(l1, l2, l3);
		
		//12
		l1 = new VertexLocation(new HexLocation(2, 0), VertexDirection.NorthWest);
		l2 = new VertexLocation(new HexLocation(1, 0), VertexDirection.East);
		l3 = new VertexLocation(new HexLocation(2, -1), VertexDirection.SouthWest);
		addVertex(l1, l2, l3);
		
		//13
		l1 = new VertexLocation(new HexLocation(1, 1), VertexDirection.NorthEast);
		l2 = new VertexLocation(new HexLocation(2, 0), VertexDirection.West);
		l3 = new VertexLocation(new HexLocation(1, 0), VertexDirection.SouthEast);
		addVertex(l1, l2, l3);
		
		//14
		l1 = new VertexLocation(new HexLocation(1, 1), VertexDirection.NorthWest);
		l2 = new VertexLocation(new HexLocation(0, 1), VertexDirection.East);
		l3 = new VertexLocation(new HexLocation(1, 0), VertexDirection.SouthWest);
		addVertex(l1, l2, l3);
		
		//15
		l1 = new VertexLocation(new HexLocation(0, 2), VertexDirection.NorthEast);
		l2 = new VertexLocation(new HexLocation(1, 1), VertexDirection.West);
		l3 = new VertexLocation(new HexLocation(0, 1), VertexDirection.SouthEast);
		addVertex(l1, l2, l3);
		
		//16
		l1 = new VertexLocation(new HexLocation(0, 2), VertexDirection.NorthWest);
		l2 = new VertexLocation(new HexLocation(-1, 2), VertexDirection.East);
		l3 = new VertexLocation(new HexLocation(0, 1), VertexDirection.SouthWest);
		addVertex(l1, l2, l3);
		
		//17
		l1 = new VertexLocation(new HexLocation(-1, 2), VertexDirection.NorthEast);
		l2 = new VertexLocation(new HexLocation(0, 1), VertexDirection.West);
		l3 = new VertexLocation(new HexLocation(-1, 1), VertexDirection.SouthEast);
		addVertex(l1, l2, l3);
		
		//18
		l1 = new VertexLocation(new HexLocation(-1, 2), VertexDirection.NorthWest);
		l2 = new VertexLocation(new HexLocation(-2, 2), VertexDirection.East);
		l3 = new VertexLocation(new HexLocation(-1, 1), VertexDirection.SouthWest);
		addVertex(l1, l2, l3);
		
		//19
		l1 = new VertexLocation(new HexLocation(-2, 2), VertexDirection.NorthEast);
		l2 = new VertexLocation(new HexLocation(-1, 1), VertexDirection.West);
		l3 = new VertexLocation(new HexLocation(-2, 1), VertexDirection.SouthEast);
		addVertex(l1, l2, l3);
		
		//20
		l1 = new VertexLocation(new HexLocation(-1, 1), VertexDirection.NorthWest);
		l2 = new VertexLocation(new HexLocation(-2, 1), VertexDirection.East);
		l3 = new VertexLocation(new HexLocation(-1, 0), VertexDirection.SouthWest);
		addVertex(l1, l2, l3);
		
		//21
		l1 = new VertexLocation(new HexLocation(-2, 1), VertexDirection.NorthEast);
		l2 = new VertexLocation(new HexLocation(-1, 0), VertexDirection.West);
		l3 = new VertexLocation(new HexLocation(-2, 0), VertexDirection.SouthEast);
		addVertex(l1, l2, l3);
		
		//22
		l1 = new VertexLocation(new HexLocation(-1, 0), VertexDirection.NorthWest);
		l2 = new VertexLocation(new HexLocation(-2, 0), VertexDirection.East);
		l3 = new VertexLocation(new HexLocation(-1, -1), VertexDirection.SouthWest);
		addVertex(l1, l2, l3);
		
		//23
		l1 = new VertexLocation(new HexLocation(-1, 0), VertexDirection.NorthEast);
		l2 = new VertexLocation(new HexLocation(0, -1), VertexDirection.West);
		l3 = new VertexLocation(new HexLocation(-1, -1), VertexDirection.SouthEast);
		addVertex(l1, l2, l3);
		
		//24
		l1 = new VertexLocation(new HexLocation(0, -2), VertexDirection.NorthWest);
		l2 = new VertexLocation(new HexLocation(-1, -2), VertexDirection.East);
		l3 = new VertexLocation(new HexLocation(0, -3), VertexDirection.SouthWest);
		addVertex(l1, l2, l3);
		
		//25
		l1 = new VertexLocation(new HexLocation(0, -2), VertexDirection.NorthEast);
		l2 = new VertexLocation(new HexLocation(1, -3), VertexDirection.West);
		l3 = new VertexLocation(new HexLocation(0, -3), VertexDirection.SouthEast);
		addVertex(l1, l2, l3);
		
		//26
		l1 = new VertexLocation(new HexLocation(1, -2), VertexDirection.NorthWest);
		l2 = new VertexLocation(new HexLocation(0, -2), VertexDirection.East);
		l3 = new VertexLocation(new HexLocation(1, -3), VertexDirection.SouthWest);
		addVertex(l1, l2, l3);
		
		//27
		l1 = new VertexLocation(new HexLocation(1, -2), VertexDirection.NorthEast);
		l2 = new VertexLocation(new HexLocation(2, -3), VertexDirection.West);
		l3 = new VertexLocation(new HexLocation(1, -3), VertexDirection.SouthEast);
		addVertex(l1, l2, l3);
		
		//28
		l1 = new VertexLocation(new HexLocation(2, -2), VertexDirection.NorthWest);
		l2 = new VertexLocation(new HexLocation(1, -2), VertexDirection.East);
		l3 = new VertexLocation(new HexLocation(2, -3), VertexDirection.SouthWest);
		addVertex(l1, l2, l3);
		
		//29
		l1 = new VertexLocation(new HexLocation(2, -2), VertexDirection.NorthEast);
		l2 = new VertexLocation(new HexLocation(3, -3), VertexDirection.West);
		l3 = new VertexLocation(new HexLocation(2, -3), VertexDirection.SouthEast);
		addVertex(l1, l2, l3);
		
		//30
		l1 = new VertexLocation(new HexLocation(3, -2), VertexDirection.NorthWest);
		l2 = new VertexLocation(new HexLocation(2, -2), VertexDirection.East);
		l3 = new VertexLocation(new HexLocation(3, -3), VertexDirection.SouthWest);
		addVertex(l1, l2, l3);
		
		//31
		l1 = new VertexLocation(new HexLocation(2, -1), VertexDirection.NorthEast);
		l2 = new VertexLocation(new HexLocation(3, -2), VertexDirection.West);
		l3 = new VertexLocation(new HexLocation(2, -2), VertexDirection.SouthEast);
		addVertex(l1, l2, l3);
		
		//32
		l1 = new VertexLocation(new HexLocation(3, -1), VertexDirection.NorthWest);
		l2 = new VertexLocation(new HexLocation(2, -1), VertexDirection.East);
		l3 = new VertexLocation(new HexLocation(3, -2), VertexDirection.SouthWest);
		addVertex(l1, l2, l3);
		
		//33
		l1 = new VertexLocation(new HexLocation(2, 0), VertexDirection.NorthEast);
		l2 = new VertexLocation(new HexLocation(3, -1), VertexDirection.West);
		l3 = new VertexLocation(new HexLocation(2, -1), VertexDirection.SouthEast);
		addVertex(l1, l2, l3);
		
		//34
		l1 = new VertexLocation(new HexLocation(3, 0), VertexDirection.NorthWest);
		l2 = new VertexLocation(new HexLocation(2, 0), VertexDirection.East);
		l3 = new VertexLocation(new HexLocation(3, -1), VertexDirection.SouthWest);
		addVertex(l1, l2, l3);
		
		//35
		l1 = new VertexLocation(new HexLocation(2, 1), VertexDirection.NorthEast);
		l2 = new VertexLocation(new HexLocation(3, 0), VertexDirection.West);
		l3 = new VertexLocation(new HexLocation(2, 0), VertexDirection.SouthEast);
		addVertex(l1, l2, l3);
		
		//36
		l1 = new VertexLocation(new HexLocation(2, 1), VertexDirection.NorthWest);
		l2 = new VertexLocation(new HexLocation(1, 1), VertexDirection.East);
		l3 = new VertexLocation(new HexLocation(2, 0), VertexDirection.SouthWest);
		addVertex(l1, l2, l3);
		
		//37
		l1 = new VertexLocation(new HexLocation(1, 2), VertexDirection.NorthEast);
		l2 = new VertexLocation(new HexLocation(2, 1), VertexDirection.West);
		l3 = new VertexLocation(new HexLocation(1, 1), VertexDirection.SouthEast);
		addVertex(l1, l2, l3);
		
		//38
		l1 = new VertexLocation(new HexLocation(1, 2), VertexDirection.NorthWest);
		l2 = new VertexLocation(new HexLocation(0, 2), VertexDirection.East);
		l3 = new VertexLocation(new HexLocation(1, 1), VertexDirection.SouthWest);
		addVertex(l1, l2, l3);
		
		//39
		l1 = new VertexLocation(new HexLocation(0, 3), VertexDirection.NorthEast);
		l2 = new VertexLocation(new HexLocation(1, 2), VertexDirection.West);
		l3 = new VertexLocation(new HexLocation(0, 2), VertexDirection.SouthEast);
		addVertex(l1, l2, l3);
		
		//40
		l1 = new VertexLocation(new HexLocation(0, 3), VertexDirection.NorthWest);
		l2 = new VertexLocation(new HexLocation(-1, 3), VertexDirection.East);
		l3 = new VertexLocation(new HexLocation(0, 2), VertexDirection.SouthWest);
		addVertex(l1, l2, l3);
		
		//41
		l1 = new VertexLocation(new HexLocation(-1, 3), VertexDirection.NorthEast);
		l2 = new VertexLocation(new HexLocation(0, 2), VertexDirection.West);
		l3 = new VertexLocation(new HexLocation(-1, 2), VertexDirection.SouthEast);
		addVertex(l1, l2, l3);
		
		//42
		l1 = new VertexLocation(new HexLocation(-1, 3), VertexDirection.NorthWest);
		l2 = new VertexLocation(new HexLocation(-2, 3), VertexDirection.East);
		l3 = new VertexLocation(new HexLocation(-1, 2), VertexDirection.SouthWest);
		addVertex(l1, l2, l3);
		
		//43
		l1 = new VertexLocation(new HexLocation(-2, 3), VertexDirection.NorthEast);
		l2 = new VertexLocation(new HexLocation(-1, 2), VertexDirection.West);
		l3 = new VertexLocation(new HexLocation(-2, 2), VertexDirection.SouthEast);
		addVertex(l1, l2, l3);
		
		//44
		l1 = new VertexLocation(new HexLocation(-2, 3), VertexDirection.NorthWest);
		l2 = new VertexLocation(new HexLocation(-3, 3), VertexDirection.East);
		l3 = new VertexLocation(new HexLocation(-2, 2), VertexDirection.SouthWest);
		addVertex(l1, l2, l3);
		
		//45
		l1 = new VertexLocation(new HexLocation(-3, 3), VertexDirection.NorthEast);
		l2 = new VertexLocation(new HexLocation(-2, 2), VertexDirection.West);
		l3 = new VertexLocation(new HexLocation(-3, 2), VertexDirection.SouthEast);
		addVertex(l1, l2, l3);
		
		//46
		l1 = new VertexLocation(new HexLocation(-2, 2), VertexDirection.NorthWest);
		l2 = new VertexLocation(new HexLocation(-3, 2), VertexDirection.East);
		l3 = new VertexLocation(new HexLocation(-2, 1), VertexDirection.SouthWest);
		addVertex(l1, l2, l3);
		
		//47
		l1 = new VertexLocation(new HexLocation(-3, 2), VertexDirection.NorthEast);
		l2 = new VertexLocation(new HexLocation(-2, 1), VertexDirection.West);
		l3 = new VertexLocation(new HexLocation(-3, 1), VertexDirection.SouthEast);
		addVertex(l1, l2, l3);
		
		//48
		l1 = new VertexLocation(new HexLocation(-2, 1), VertexDirection.NorthWest);
		l2 = new VertexLocation(new HexLocation(-3, 1), VertexDirection.East);
		l3 = new VertexLocation(new HexLocation(-2, 0), VertexDirection.SouthWest);
		addVertex(l1, l2, l3);
		
		//49
		l1 = new VertexLocation(new HexLocation(-3, 1), VertexDirection.NorthEast);
		l2 = new VertexLocation(new HexLocation(-2, 0), VertexDirection.West);
		l3 = new VertexLocation(new HexLocation(-3, 0), VertexDirection.SouthEast);
		addVertex(l1, l2, l3);
		
		//50
		l1 = new VertexLocation(new HexLocation(-2, 0), VertexDirection.NorthWest);
		l2 = new VertexLocation(new HexLocation(-3, 0), VertexDirection.East);
		l3 = new VertexLocation(new HexLocation(-2, -1), VertexDirection.SouthWest);
		addVertex(l1, l2, l3);
		
		//51
		l1 = new VertexLocation(new HexLocation(-2, 0), VertexDirection.NorthEast);
		l2 = new VertexLocation(new HexLocation(-1, -1), VertexDirection.West);
		l3 = new VertexLocation(new HexLocation(-2, -1), VertexDirection.SouthEast);
		addVertex(l1, l2, l3);
		
		//52
		l1 = new VertexLocation(new HexLocation(-1, -1), VertexDirection.NorthWest);
		l2 = new VertexLocation(new HexLocation(-2, -1), VertexDirection.East);
		l3 = new VertexLocation(new HexLocation(-1, -2), VertexDirection.SouthWest);
		addVertex(l1, l2, l3);
		
		//53
		l1 = new VertexLocation(new HexLocation(-1, -1), VertexDirection.NorthEast);
		l2 = new VertexLocation(new HexLocation(0, -2), VertexDirection.West);
		l3 = new VertexLocation(new HexLocation(-1, -2), VertexDirection.SouthEast);
		addVertex(l1, l2, l3);
	}
	
	public void addVertex(VertexLocation l1, VertexLocation l2, VertexLocation l3) {
		List<VertexLocation> list = new ArrayList<VertexLocation>();
		list.add(l1);
		list.add(l2);
		list.add(l3);
		corners.add(new Corner(list));		
	}
	
	public void updateTiles() {
		//Add edges
		tiles.get(0).addEdge(edges.get(67), EdgeDirection.SouthEast);
		tiles.get(1).addEdge(edges.get(66), EdgeDirection.NorthEast);
		tiles.get(1).addEdge(edges.get(65), EdgeDirection.SouthEast);
		tiles.get(2).addEdge(edges.get(64), EdgeDirection.NorthEast);
		tiles.get(2).addEdge(edges.get(63), EdgeDirection.SouthEast);
		tiles.get(3).addEdge(edges.get(62), EdgeDirection.NorthEast);
		
		tiles.get(4).addEdge(edges.get(69), EdgeDirection.SouthEast);
		tiles.get(4).addEdge(edges.get(68), EdgeDirection.South);
		tiles.get(5).addEdge(edges.get(68), EdgeDirection.North);
		tiles.get(5).addEdge(edges.get(41), EdgeDirection.NorthEast);
		tiles.get(5).addEdge(edges.get(27), EdgeDirection.SouthEast);
		tiles.get(5).addEdge(edges.get(40), EdgeDirection.South);
		tiles.get(5).addEdge(edges.get(66), EdgeDirection.SouthWest);
		tiles.get(5).addEdge(edges.get(67), EdgeDirection.NorthWest);
		tiles.get(6).addEdge(edges.get(40), EdgeDirection.North);
		tiles.get(6).addEdge(edges.get(26), EdgeDirection.NorthEast);
		tiles.get(6).addEdge(edges.get(25), EdgeDirection.SouthEast);
		tiles.get(6).addEdge(edges.get(39), EdgeDirection.South);
		tiles.get(6).addEdge(edges.get(64), EdgeDirection.SouthWest);
		tiles.get(6).addEdge(edges.get(65), EdgeDirection.NorthWest);
		tiles.get(7).addEdge(edges.get(39), EdgeDirection.North);
		tiles.get(7).addEdge(edges.get(24), EdgeDirection.NorthEast);
		tiles.get(7).addEdge(edges.get(38), EdgeDirection.SouthEast);
		tiles.get(7).addEdge(edges.get(61), EdgeDirection.South);
		tiles.get(7).addEdge(edges.get(62), EdgeDirection.SouthWest);
		tiles.get(7).addEdge(edges.get(63), EdgeDirection.NorthWest);
		tiles.get(8).addEdge(edges.get(61), EdgeDirection.North);
		tiles.get(8).addEdge(edges.get(60), EdgeDirection.NorthEast);

		tiles.get(9).addEdge(edges.get(71), EdgeDirection.SouthEast);
		tiles.get(9).addEdge(edges.get(70), EdgeDirection.South);
		tiles.get(10).addEdge(edges.get(70), EdgeDirection.North);
		tiles.get(10).addEdge(edges.get(30), EdgeDirection.NorthEast);
		tiles.get(10).addEdge(edges.get(29), EdgeDirection.SouthEast);
		tiles.get(10).addEdge(edges.get(28), EdgeDirection.South);
		tiles.get(10).addEdge(edges.get(41), EdgeDirection.SouthWest);
		tiles.get(10).addEdge(edges.get(69), EdgeDirection.NorthWest);
		tiles.get(11).addEdge(edges.get(28), EdgeDirection.North);
		tiles.get(11).addEdge(edges.get(6), EdgeDirection.NorthEast);
		tiles.get(11).addEdge(edges.get(5), EdgeDirection.SouthEast);
		tiles.get(11).addEdge(edges.get(11), EdgeDirection.South);
		tiles.get(11).addEdge(edges.get(26), EdgeDirection.SouthWest);
		tiles.get(11).addEdge(edges.get(27), EdgeDirection.NorthWest);
		tiles.get(12).addEdge(edges.get(11), EdgeDirection.North);
		tiles.get(12).addEdge(edges.get(4), EdgeDirection.NorthEast);
		tiles.get(12).addEdge(edges.get(10), EdgeDirection.SouthEast);
		tiles.get(12).addEdge(edges.get(23), EdgeDirection.South);
		tiles.get(12).addEdge(edges.get(24), EdgeDirection.SouthWest);
		tiles.get(12).addEdge(edges.get(25), EdgeDirection.NorthWest);
		tiles.get(13).addEdge(edges.get(23), EdgeDirection.North);
		tiles.get(13).addEdge(edges.get(22), EdgeDirection.NorthEast);
		tiles.get(13).addEdge(edges.get(37), EdgeDirection.SouthEast);
		tiles.get(13).addEdge(edges.get(59), EdgeDirection.South);
		tiles.get(13).addEdge(edges.get(60), EdgeDirection.SouthWest);
		tiles.get(13).addEdge(edges.get(38), EdgeDirection.NorthWest);
		tiles.get(14).addEdge(edges.get(59), EdgeDirection.North);
		tiles.get(14).addEdge(edges.get(58), EdgeDirection.NorthEast);

		tiles.get(15).addEdge(edges.get(42), EdgeDirection.South);
		tiles.get(16).addEdge(edges.get(42), EdgeDirection.North);
		tiles.get(16).addEdge(edges.get(43), EdgeDirection.NorthEast);
		tiles.get(16).addEdge(edges.get(41), EdgeDirection.SouthEast);
		tiles.get(16).addEdge(edges.get(12), EdgeDirection.South);
		tiles.get(16).addEdge(edges.get(30), EdgeDirection.SouthWest);
		tiles.get(16).addEdge(edges.get(71), EdgeDirection.NorthWest);
		tiles.get(17).addEdge(edges.get(12), EdgeDirection.North);
		tiles.get(17).addEdge(edges.get(13), EdgeDirection.NorthEast);
		tiles.get(17).addEdge(edges.get(7), EdgeDirection.SouthEast);
		tiles.get(17).addEdge(edges.get(0), EdgeDirection.South);
		tiles.get(17).addEdge(edges.get(6), EdgeDirection.SouthWest);
		tiles.get(17).addEdge(edges.get(29), EdgeDirection.NorthWest);
		tiles.get(18).addEdge(edges.get(0), EdgeDirection.North);
		tiles.get(18).addEdge(edges.get(1), EdgeDirection.NorthEast);
		tiles.get(18).addEdge(edges.get(2), EdgeDirection.SouthEast);
		tiles.get(18).addEdge(edges.get(3), EdgeDirection.South);
		tiles.get(18).addEdge(edges.get(4), EdgeDirection.SouthWest);
		tiles.get(18).addEdge(edges.get(5), EdgeDirection.NorthWest);
		tiles.get(19).addEdge(edges.get(3), EdgeDirection.North);
		tiles.get(19).addEdge(edges.get(9), EdgeDirection.NorthEast);
		tiles.get(19).addEdge(edges.get(20), EdgeDirection.SouthEast);
		tiles.get(19).addEdge(edges.get(21), EdgeDirection.South);
		tiles.get(19).addEdge(edges.get(22), EdgeDirection.SouthWest);
		tiles.get(19).addEdge(edges.get(10), EdgeDirection.NorthWest);
		tiles.get(20).addEdge(edges.get(21), EdgeDirection.North);
		tiles.get(20).addEdge(edges.get(36), EdgeDirection.NorthEast);
		tiles.get(20).addEdge(edges.get(56), EdgeDirection.SouthEast);
		tiles.get(20).addEdge(edges.get(57), EdgeDirection.South);
		tiles.get(20).addEdge(edges.get(58), EdgeDirection.SouthWest);
		tiles.get(20).addEdge(edges.get(37), EdgeDirection.NorthWest);
		tiles.get(21).addEdge(edges.get(57), EdgeDirection.North);
		
		tiles.get(22).addEdge(edges.get(44), EdgeDirection.South);
		tiles.get(22).addEdge(edges.get(43), EdgeDirection.SouthWest);
		tiles.get(23).addEdge(edges.get(44), EdgeDirection.North);
		tiles.get(23).addEdge(edges.get(45), EdgeDirection.NorthEast);
		tiles.get(23).addEdge(edges.get(32), EdgeDirection.SouthEast);
		tiles.get(23).addEdge(edges.get(14), EdgeDirection.South);
		tiles.get(23).addEdge(edges.get(13), EdgeDirection.SouthWest);
		tiles.get(23).addEdge(edges.get(31), EdgeDirection.NorthWest);
		tiles.get(24).addEdge(edges.get(14), EdgeDirection.North);
		tiles.get(24).addEdge(edges.get(15), EdgeDirection.NorthEast);
		tiles.get(24).addEdge(edges.get(16), EdgeDirection.SouthEast);
		tiles.get(24).addEdge(edges.get(8), EdgeDirection.South);
		tiles.get(24).addEdge(edges.get(1), EdgeDirection.SouthWest);
		tiles.get(24).addEdge(edges.get(7), EdgeDirection.NorthWest);
		tiles.get(25).addEdge(edges.get(8), EdgeDirection.North);
		tiles.get(25).addEdge(edges.get(17), EdgeDirection.NorthEast);
		tiles.get(25).addEdge(edges.get(18), EdgeDirection.SouthEast);
		tiles.get(25).addEdge(edges.get(19), EdgeDirection.South);
		tiles.get(25).addEdge(edges.get(9), EdgeDirection.SouthWest);
		tiles.get(25).addEdge(edges.get(2), EdgeDirection.NorthWest);
		tiles.get(26).addEdge(edges.get(19), EdgeDirection.North);
		tiles.get(26).addEdge(edges.get(35), EdgeDirection.NorthEast);
		tiles.get(26).addEdge(edges.get(54), EdgeDirection.SouthEast);
		tiles.get(26).addEdge(edges.get(55), EdgeDirection.South);
		tiles.get(26).addEdge(edges.get(36), EdgeDirection.SouthWest);
		tiles.get(26).addEdge(edges.get(20), EdgeDirection.NorthWest);
		tiles.get(27).addEdge(edges.get(55), EdgeDirection.North);
		tiles.get(27).addEdge(edges.get(56), EdgeDirection.NorthWest);
		
		tiles.get(28).addEdge(edges.get(46), EdgeDirection.South);
		tiles.get(28).addEdge(edges.get(45), EdgeDirection.SouthWest);
		tiles.get(29).addEdge(edges.get(46), EdgeDirection.North);
		tiles.get(29).addEdge(edges.get(47), EdgeDirection.NorthEast);
		tiles.get(29).addEdge(edges.get(48), EdgeDirection.SouthEast);
		tiles.get(29).addEdge(edges.get(33), EdgeDirection.South);
		tiles.get(29).addEdge(edges.get(15), EdgeDirection.SouthWest);
		tiles.get(29).addEdge(edges.get(32), EdgeDirection.NorthWest);
		tiles.get(30).addEdge(edges.get(33), EdgeDirection.North);
		tiles.get(30).addEdge(edges.get(49), EdgeDirection.NorthEast);
		tiles.get(30).addEdge(edges.get(50), EdgeDirection.SouthEast);
		tiles.get(30).addEdge(edges.get(34), EdgeDirection.South);
		tiles.get(30).addEdge(edges.get(17), EdgeDirection.SouthWest);
		tiles.get(30).addEdge(edges.get(16), EdgeDirection.NorthWest);
		tiles.get(31).addEdge(edges.get(34), EdgeDirection.North);
		tiles.get(31).addEdge(edges.get(51), EdgeDirection.NorthEast);
		tiles.get(31).addEdge(edges.get(52), EdgeDirection.SouthEast);
		tiles.get(31).addEdge(edges.get(53), EdgeDirection.South);
		tiles.get(31).addEdge(edges.get(35), EdgeDirection.SouthWest);
		tiles.get(31).addEdge(edges.get(18), EdgeDirection.NorthWest);
		tiles.get(32).addEdge(edges.get(53), EdgeDirection.North);
		tiles.get(32).addEdge(edges.get(54), EdgeDirection.NorthWest);
		
		tiles.get(33).addEdge(edges.get(47), EdgeDirection.SouthWest);
		tiles.get(34).addEdge(edges.get(48), EdgeDirection.NorthWest);
		tiles.get(34).addEdge(edges.get(49), EdgeDirection.SouthWest);
		tiles.get(35).addEdge(edges.get(50), EdgeDirection.NorthWest);
		tiles.get(35).addEdge(edges.get(51), EdgeDirection.SouthWest);
		tiles.get(36).addEdge(edges.get(52), EdgeDirection.NorthWest);
		
		//Add corners
		tiles.get(0).addCorner(corners.get(50), VertexDirection.East);
		tiles.get(0).addCorner(corners.get(49), VertexDirection.SouthEast);
		tiles.get(1).addCorner(corners.get(49), VertexDirection.NorthEast);
		tiles.get(1).addCorner(corners.get(48), VertexDirection.East);
		tiles.get(1).addCorner(corners.get(47), VertexDirection.SouthEast);
		tiles.get(2).addCorner(corners.get(47), VertexDirection.NorthEast);
		tiles.get(2).addCorner(corners.get(46), VertexDirection.East);
		tiles.get(2).addCorner(corners.get(45), VertexDirection.SouthEast);
		tiles.get(3).addCorner(corners.get(45), VertexDirection.NorthEast);
		tiles.get(3).addCorner(corners.get(44), VertexDirection.East);
		
		tiles.get(4).addCorner(corners.get(52), VertexDirection.East);
		tiles.get(4).addCorner(corners.get(51), VertexDirection.SouthEast);
		tiles.get(4).addCorner(corners.get(50), VertexDirection.SouthWest);
		tiles.get(5).addCorner(corners.get(51), VertexDirection.NorthEast);
		tiles.get(5).addCorner(corners.get(22), VertexDirection.East);
		tiles.get(5).addCorner(corners.get(21), VertexDirection.SouthEast);
		tiles.get(5).addCorner(corners.get(48), VertexDirection.SouthWest);
		tiles.get(5).addCorner(corners.get(49), VertexDirection.West);
		tiles.get(5).addCorner(corners.get(50), VertexDirection.NorthWest);
		tiles.get(6).addCorner(corners.get(21), VertexDirection.NorthEast);
		tiles.get(6).addCorner(corners.get(20), VertexDirection.East);
		tiles.get(6).addCorner(corners.get(19), VertexDirection.SouthEast);
		tiles.get(6).addCorner(corners.get(46), VertexDirection.SouthWest);
		tiles.get(6).addCorner(corners.get(47), VertexDirection.West);
		tiles.get(6).addCorner(corners.get(48), VertexDirection.NorthWest);
		tiles.get(7).addCorner(corners.get(19), VertexDirection.NorthEast);
		tiles.get(7).addCorner(corners.get(18), VertexDirection.East);
		tiles.get(7).addCorner(corners.get(43), VertexDirection.SouthEast);
		tiles.get(7).addCorner(corners.get(44), VertexDirection.SouthWest);
		tiles.get(7).addCorner(corners.get(45), VertexDirection.West);
		tiles.get(7).addCorner(corners.get(46), VertexDirection.NorthWest);
		tiles.get(8).addCorner(corners.get(43), VertexDirection.NorthEast);
		tiles.get(8).addCorner(corners.get(42), VertexDirection.East);
		tiles.get(8).addCorner(corners.get(44), VertexDirection.NorthWest);

		tiles.get(9).addCorner(corners.get(24), VertexDirection.East);
		tiles.get(9).addCorner(corners.get(53), VertexDirection.SouthEast);
		tiles.get(9).addCorner(corners.get(52), VertexDirection.SouthWest);
		tiles.get(10).addCorner(corners.get(53), VertexDirection.NorthEast);
		tiles.get(10).addCorner(corners.get(6), VertexDirection.East);
		tiles.get(10).addCorner(corners.get(23), VertexDirection.SouthEast);
		tiles.get(10).addCorner(corners.get(22), VertexDirection.SouthWest);
		tiles.get(10).addCorner(corners.get(51), VertexDirection.West);
		tiles.get(10).addCorner(corners.get(52), VertexDirection.NorthWest);
		tiles.get(11).addCorner(corners.get(23), VertexDirection.NorthEast);
		tiles.get(11).addCorner(corners.get(0), VertexDirection.East);
		tiles.get(11).addCorner(corners.get(5), VertexDirection.SouthEast);
		tiles.get(11).addCorner(corners.get(20), VertexDirection.SouthWest);
		tiles.get(11).addCorner(corners.get(21), VertexDirection.West);
		tiles.get(11).addCorner(corners.get(22), VertexDirection.NorthWest);
		tiles.get(12).addCorner(corners.get(5), VertexDirection.NorthEast);
		tiles.get(12).addCorner(corners.get(4), VertexDirection.East);
		tiles.get(12).addCorner(corners.get(17), VertexDirection.SouthEast);
		tiles.get(12).addCorner(corners.get(18), VertexDirection.SouthWest);
		tiles.get(12).addCorner(corners.get(19), VertexDirection.West);
		tiles.get(12).addCorner(corners.get(20), VertexDirection.NorthWest);
		tiles.get(13).addCorner(corners.get(17), VertexDirection.NorthEast);
		tiles.get(13).addCorner(corners.get(16), VertexDirection.East);
		tiles.get(13).addCorner(corners.get(41), VertexDirection.SouthEast);
		tiles.get(13).addCorner(corners.get(42), VertexDirection.SouthWest);
		tiles.get(13).addCorner(corners.get(43), VertexDirection.West);
		tiles.get(13).addCorner(corners.get(18), VertexDirection.NorthWest);
		tiles.get(14).addCorner(corners.get(41), VertexDirection.NorthEast);
		tiles.get(14).addCorner(corners.get(40), VertexDirection.East);
		tiles.get(14).addCorner(corners.get(42), VertexDirection.NorthWest);
		
		tiles.get(15).addCorner(corners.get(25), VertexDirection.SouthEast);
		tiles.get(15).addCorner(corners.get(24), VertexDirection.SouthWest);
		tiles.get(16).addCorner(corners.get(25), VertexDirection.NorthEast);
		tiles.get(16).addCorner(corners.get(26), VertexDirection.East);
		tiles.get(16).addCorner(corners.get(7), VertexDirection.SouthEast);
		tiles.get(16).addCorner(corners.get(6), VertexDirection.SouthWest);
		tiles.get(16).addCorner(corners.get(53), VertexDirection.West);
		tiles.get(16).addCorner(corners.get(24), VertexDirection.NorthWest);
		tiles.get(17).addCorner(corners.get(7), VertexDirection.NorthEast);
		tiles.get(17).addCorner(corners.get(8), VertexDirection.East);
		tiles.get(17).addCorner(corners.get(1), VertexDirection.SouthEast);
		tiles.get(17).addCorner(corners.get(0), VertexDirection.SouthWest);
		tiles.get(17).addCorner(corners.get(23), VertexDirection.West);
		tiles.get(17).addCorner(corners.get(6), VertexDirection.NorthWest);
		tiles.get(18).addCorner(corners.get(1), VertexDirection.NorthEast);
		tiles.get(18).addCorner(corners.get(2), VertexDirection.East);
		tiles.get(18).addCorner(corners.get(3), VertexDirection.SouthEast);
		tiles.get(18).addCorner(corners.get(4), VertexDirection.SouthWest);
		tiles.get(18).addCorner(corners.get(5), VertexDirection.West);
		tiles.get(18).addCorner(corners.get(0), VertexDirection.NorthWest);
		tiles.get(19).addCorner(corners.get(3), VertexDirection.NorthEast);
		tiles.get(19).addCorner(corners.get(14), VertexDirection.East);
		tiles.get(19).addCorner(corners.get(15), VertexDirection.SouthEast);
		tiles.get(19).addCorner(corners.get(16), VertexDirection.SouthWest);
		tiles.get(19).addCorner(corners.get(17), VertexDirection.West);
		tiles.get(19).addCorner(corners.get(4), VertexDirection.NorthWest);
		tiles.get(20).addCorner(corners.get(15), VertexDirection.NorthEast);
		tiles.get(20).addCorner(corners.get(38), VertexDirection.East);
		tiles.get(20).addCorner(corners.get(39), VertexDirection.SouthEast);
		tiles.get(20).addCorner(corners.get(40), VertexDirection.SouthWest);
		tiles.get(20).addCorner(corners.get(41), VertexDirection.West);
		tiles.get(20).addCorner(corners.get(16), VertexDirection.NorthWest);
		tiles.get(21).addCorner(corners.get(39), VertexDirection.NorthEast);
		tiles.get(21).addCorner(corners.get(40), VertexDirection.NorthWest);
		
		tiles.get(22).addCorner(corners.get(27), VertexDirection.SouthEast);
		tiles.get(22).addCorner(corners.get(26), VertexDirection.SouthWest);
		tiles.get(22).addCorner(corners.get(25), VertexDirection.West);
		tiles.get(23).addCorner(corners.get(27), VertexDirection.NorthEast);
		tiles.get(23).addCorner(corners.get(28), VertexDirection.East);
		tiles.get(23).addCorner(corners.get(9), VertexDirection.SouthEast);
		tiles.get(23).addCorner(corners.get(8), VertexDirection.SouthWest);
		tiles.get(23).addCorner(corners.get(7), VertexDirection.West);
		tiles.get(23).addCorner(corners.get(26), VertexDirection.NorthWest);
		tiles.get(24).addCorner(corners.get(9), VertexDirection.NorthEast);
		tiles.get(24).addCorner(corners.get(10), VertexDirection.East);
		tiles.get(24).addCorner(corners.get(11), VertexDirection.SouthEast);
		tiles.get(24).addCorner(corners.get(2), VertexDirection.SouthWest);
		tiles.get(24).addCorner(corners.get(1), VertexDirection.West);
		tiles.get(24).addCorner(corners.get(8), VertexDirection.NorthWest);
		tiles.get(25).addCorner(corners.get(11), VertexDirection.NorthEast);
		tiles.get(25).addCorner(corners.get(12), VertexDirection.East);
		tiles.get(25).addCorner(corners.get(13), VertexDirection.SouthEast);
		tiles.get(25).addCorner(corners.get(14), VertexDirection.SouthWest);
		tiles.get(25).addCorner(corners.get(3), VertexDirection.West);
		tiles.get(25).addCorner(corners.get(2), VertexDirection.NorthWest);
		tiles.get(26).addCorner(corners.get(13), VertexDirection.NorthEast);
		tiles.get(26).addCorner(corners.get(36), VertexDirection.East);
		tiles.get(26).addCorner(corners.get(37), VertexDirection.SouthEast);
		tiles.get(26).addCorner(corners.get(38), VertexDirection.SouthWest);
		tiles.get(26).addCorner(corners.get(15), VertexDirection.West);
		tiles.get(26).addCorner(corners.get(14), VertexDirection.NorthWest);
		tiles.get(27).addCorner(corners.get(37), VertexDirection.NorthEast);
		tiles.get(27).addCorner(corners.get(39), VertexDirection.West);
		tiles.get(27).addCorner(corners.get(38), VertexDirection.NorthWest);

		tiles.get(28).addCorner(corners.get(29), VertexDirection.SouthEast);
		tiles.get(28).addCorner(corners.get(28), VertexDirection.SouthWest);
		tiles.get(28).addCorner(corners.get(27), VertexDirection.West);
		tiles.get(29).addCorner(corners.get(29), VertexDirection.NorthEast);
		tiles.get(29).addCorner(corners.get(30), VertexDirection.East);
		tiles.get(29).addCorner(corners.get(31), VertexDirection.SouthEast);
		tiles.get(29).addCorner(corners.get(10), VertexDirection.SouthWest);
		tiles.get(29).addCorner(corners.get(9), VertexDirection.West);
		tiles.get(29).addCorner(corners.get(28), VertexDirection.NorthWest);
		tiles.get(30).addCorner(corners.get(31), VertexDirection.NorthEast);
		tiles.get(30).addCorner(corners.get(32), VertexDirection.East);
		tiles.get(30).addCorner(corners.get(33), VertexDirection.SouthEast);
		tiles.get(30).addCorner(corners.get(12), VertexDirection.SouthWest);
		tiles.get(30).addCorner(corners.get(11), VertexDirection.West);
		tiles.get(30).addCorner(corners.get(10), VertexDirection.NorthWest);
		tiles.get(31).addCorner(corners.get(33), VertexDirection.NorthEast);
		tiles.get(31).addCorner(corners.get(34), VertexDirection.East);
		tiles.get(31).addCorner(corners.get(35), VertexDirection.SouthEast);
		tiles.get(31).addCorner(corners.get(36), VertexDirection.SouthWest);
		tiles.get(31).addCorner(corners.get(13), VertexDirection.West);
		tiles.get(31).addCorner(corners.get(12), VertexDirection.NorthWest);
		tiles.get(32).addCorner(corners.get(35), VertexDirection.NorthEast);
		tiles.get(32).addCorner(corners.get(37), VertexDirection.West);
		tiles.get(32).addCorner(corners.get(36), VertexDirection.NorthWest);
		
		tiles.get(33).addCorner(corners.get(30), VertexDirection.SouthWest);
		tiles.get(33).addCorner(corners.get(29), VertexDirection.West);
		tiles.get(34).addCorner(corners.get(32), VertexDirection.SouthWest);
		tiles.get(34).addCorner(corners.get(31), VertexDirection.West);
		tiles.get(34).addCorner(corners.get(30), VertexDirection.NorthWest);
		tiles.get(35).addCorner(corners.get(34), VertexDirection.SouthWest);
		tiles.get(35).addCorner(corners.get(33), VertexDirection.West);
		tiles.get(35).addCorner(corners.get(32), VertexDirection.NorthWest);
		tiles.get(36).addCorner(corners.get(35), VertexDirection.West);
		tiles.get(36).addCorner(corners.get(34), VertexDirection.NorthWest);
	}
}