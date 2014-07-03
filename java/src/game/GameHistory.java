package game;

import java.util.ArrayList;
import java.util.List;

import shared.definitions.Command;

public class GameHistory{
	
	private List<Command> gameCommands;
	
	public GameHistory(){
		gameCommands = new ArrayList<Command>();
	}
	
	/**
	 * @param command the next command to be executed by the game model
	 */
	public void addCommand(Command command) {
		gameCommands.add(command);
	}
	
	/**
	 * @return the list of commands made to the game model thus far
	 */
	public List<Command> getCommands() {
		return gameCommands;
	}
}