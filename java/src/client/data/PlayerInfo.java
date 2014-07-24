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
	private CatanColor color;
	
	public PlayerInfo() {
		setId(-1);
		setPlayerIndex(-1);
		setName("");
		setColor(CatanColor.WHITE);
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

	public CatanColor getColor() {
		return color;
	}
        public void setColor(String color){
            if(color.contains("red"))
			this.color = CatanColor.RED;
		if(color.contains("orange"))
			this.color = CatanColor.ORANGE;
		if(color.contains("yellow"))
			this.color = CatanColor.YELLOW;
		if(color.contains("blue"))
			this.color = CatanColor.BLUE;
		if(color.contains("green"))
			this.color = CatanColor.GREEN;
		if(color.contains("purple"))
			this.color = CatanColor.PURPLE;
		if(color.contains("puce"))
			this.color = CatanColor.PUCE;
		if(color.contains("white"))
			this.color = CatanColor.WHITE;
		if(color.contains("brown"))
			this.color = CatanColor.BROWN;
        }
	public void setColor(CatanColor color) {
		this.color = color;
	}

}

