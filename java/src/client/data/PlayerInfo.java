package client.data;

import shared.definitions.*;


/**
 * Used to pass player information into views<br>
 * <br>
 * PROPERTIES:<br>
 * <ul>
 * 	<li>Id: Unique player ID</li>
 * 	<li>PlayerIndex: Player's order in the game [0-3]</li>
 *	<li>Name: Player's name (non-empty string)</li>
 * 	<li>Color: Player's color (cannot be null)</li>
 * </ul>
 * 
 */
public class PlayerInfo {

	private int id;
	private int playerIndex;
	private String name;
	private CatanColor catanColor;
	private String color;
	
	public PlayerInfo() {
		setId(-1);
		setPlayerIndex(-1);
		setName("");
		setColor("white");
		setCatanColor(CatanColor.WHITE);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getPlayerIndex() {
		return playerIndex;
	}
	
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
		setCatanColor(color);
	}
	
	public void setCatanColor(CatanColor color) {
		this.catanColor = color;
	}

	public CatanColor getCatanColor() {
		return this.catanColor;
	}
	
	public void setCatanColor(String color) {
		switch(color) {
		case("red"):
			this.catanColor = CatanColor.RED;
			break;
		case("blue"):
			this.catanColor = CatanColor.BLUE;
			break;
		case("puce"):
			this.catanColor = CatanColor.PUCE;
			break;
		case("orange"):
			this.catanColor = CatanColor.ORANGE;
			break;
		case("green"):
			this.catanColor = CatanColor.GREEN;
			break;
		case("white"):
			this.catanColor = CatanColor.WHITE;
			break;
		case("yellow"):
			this.catanColor = CatanColor.YELLOW;
			break;
		case("purple"):
			this.catanColor = CatanColor.PURPLE;
			break;
		case("brown"):
			this.catanColor = CatanColor.BROWN;
			break;
		}
	}

}

