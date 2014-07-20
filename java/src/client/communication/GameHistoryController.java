package client.communication;

import client.base.*;
import client.parse.ParsedChat;
import controller.ControllerFacade;
import controller.IControllerFacadeListener;
import game.GameModel;

import java.util.*;

import shared.definitions.*;


/**
 * Game history controller implementation
 */
public class GameHistoryController extends Controller implements IGameHistoryController, IControllerFacadeListener {

	private IGameHistoryView view;

	public GameHistoryController(IGameHistoryView view) {
		
		super(view);
		ControllerFacade.getSingleton().addListener(this);
		this.view = view;
		initFromModel();
	}
        
	@Override
        public void gameModelChanged(GameModel gameModel){
	    	List<LogEntry> entries = new ArrayList<LogEntry>();
	    	for(Command command : gameModel.getGameHistory().getCommands()) {
	    		LogEntry entry = new LogEntry(shared.definitions.CatanColor.BLUE, command.getCommand());
	    		entries.add(entry);
	    	}
	    	view.setEntries(entries);
        }
         
	@Override
	public IGameHistoryView getView() {
		
		return (IGameHistoryView)super.getView();
	}
	
	private void initFromModel() {
		
	}
	
}

