package shared.communication;

import java.util.ArrayList;

import player.Player;

public class CreateGameRes {
	String title;
	int id;
	ArrayList<Player> players;
	
	public CreateGameRes(String title, int id) {
		super();
		this.title = title;
		this.id = id;
		this.players = null;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	
}
