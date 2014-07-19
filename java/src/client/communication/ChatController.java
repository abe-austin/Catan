package client.communication;

import client.base.*;
import controller.ControllerFacade;
import controller.IControllerFacadeListener;
import game.GameModel;


/**
 * Chat controller implementation
 */
public class ChatController extends Controller implements IChatController, IControllerFacadeListener {

	public ChatController(IChatView view) {
		
		super(view);
                ControllerFacade.getSingleton().addListener(this);
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
        
        }

}

