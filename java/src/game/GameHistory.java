package game;

import java.util.ArrayList;
import java.util.List;

import shared.definitions.Command;

public class GameHistory{	
	private List<Command> gameCommands;
        private ChatLog chatlog;
	
	public GameHistory(){
		gameCommands = new ArrayList<Command>();
                chatlog = new ChatLog();
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

    /**
     * @return the chatlog
     */
    public ChatLog getChatlog() {
        return chatlog;
    }
}