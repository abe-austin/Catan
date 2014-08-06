package server.data.sql;

import java.util.List;

import shared.definitions.Command;

public class SQLCommand {
	
	public Command addCommand(Command command) {
		// TODO add command to the database
		return null;
	}
	
	public Command getCommand(int commandID) {
		// TODO get the specified command
		return null;
	}
	
	public List<Command> getAllCommands() {
		// TODO get a list of all the commands
		return null;
	}
	
	public void deleteCommand(int commandID) {
		// TODO delete command from database
	}
	
	public int getNextCommandID() {
		// TODO get the next available command id
		return -1;
	}
}
