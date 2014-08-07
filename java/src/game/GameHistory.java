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
	public void addGameCommand(Command command) {
		gameCommands.add(command);
	}
	
	public void setGameCommands(List<Command> gameCommands) {
		this.gameCommands = gameCommands;
	}
	
	/**
	 * @return the list of commands made to the game model thus far
	 */
	public List<Command> getGameCommands() {
		return gameCommands;
	}
	
	public void setChatlog(ChatLog chatlog) {
		this.chatlog = chatlog;
	}

    /**
     * @return the chatlog
     */
    public ChatLog getChatlog() {
        return chatlog;
    }
}