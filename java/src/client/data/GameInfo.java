package client.data;

import java.util.*;


/**
 * Used to pass game information into views<br>
 * <br>
 * PROPERTIES:<br>
 * <ul>
 * 	<li>Id: Unique game ID</li>
 * 	<li>Title: Game title (non-empty string)</li>
 * 	<li>Players: List of players who have joined the game (can be empty)</li>
 * </ul>
 * 
 */
public class GameInfo {

	private int id;
	private String title;
	private List<PlayerInfo> players;

        public GameInfo(){
            id=-1;
            players = new ArrayList<>();
        }
        
	public GameInfo(int id, String title) {
		this.id = id;
		this.title = title;
		players = new ArrayList<PlayerInfo>();
	}
	
	public GameInfo(int id, String title, List<PlayerInfo> players) {
		this.id = id;
		this.title = title;
		this.players = players;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void addPlayer(PlayerInfo newPlayer) {
		
		players.add(newPlayer);
	}
	
	public void setPlayerInfo(ArrayList<PlayerInfo> playerInfo) {
		this.players = playerInfo;
	}
	
	public List<PlayerInfo> getPlayers() {
		
		return Collections.unmodifiableList(players);
	}
	
	public void resetPlayer() {
		ArrayList<PlayerInfo> newPlayers = new ArrayList<PlayerInfo>();
		for(PlayerInfo player : players) {
			if(player.getId() != -1) {
				newPlayers.add(player);
			}
		}
		players = newPlayers;
	}
}

