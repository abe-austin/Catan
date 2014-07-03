package shared.communication;

import java.util.List;
import shared.definitions.Command;

public class DoGameCommandsParam {
	
	private List<Command> commands;
		
	public DoGameCommandsParam(List<Command> commands) {
		super();
		this.commands = commands;
	}

	public List<Command> getCommands() {
		return commands;
	}

	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}

}
