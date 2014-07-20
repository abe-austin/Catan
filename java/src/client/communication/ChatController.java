package client.communication;

import java.util.ArrayList;
import java.util.List;

import client.base.*;
import client.parse.ParsedChat;
import controller.ControllerFacade;
import controller.IControllerFacadeListener;
import game.GameModel;


/**
 * Chat controller implementation
 */
public class ChatController extends Controller implements IChatController, IControllerFacadeListener {

	private IChatView view;
	
	public ChatController(IChatView view) {
		
		super(view);
        ControllerFacade.getSingleton().addListener(this);
        this.view = view;        
	}

	@Override
	public IChatView getView() {
		return (IChatView)super.getView();
	}

	@Override
	public void sendMessage(String message) {
		ControllerFacade.getSingleton().sendMessage(message);
	}
	
    @Override
    public void gameModelChanged(GameModel gameModel){
    	List<LogEntry> entries = new ArrayList<LogEntry>();
    	for(ParsedChat mes : gameModel.getGameHistory().getChatlog().getChatLines()) {
    		LogEntry entry = new LogEntry(shared.definitions.CatanColor.BLUE, mes.getMessage());
    		entries.add(entry);
    	}
    	view.setEntries(entries);
    }

}

