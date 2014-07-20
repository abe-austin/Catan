package client.communication;

import client.base.*;
import controller.ControllerFacade;
import controller.IControllerFacadeListener;
import game.GameModel;
import java.util.*;
import java.util.List;

import shared.definitions.*;


/**
 * Game history controller implementation
 */
public class GameHistoryController extends Controller implements IGameHistoryController, IControllerFacadeListener {

	public GameHistoryController(IGameHistoryView view) {
		
		super(view);
		ControllerFacade.getSingleton().addListener(this);
		initFromModel();
	}
        
	@Override
        public void gameModelChanged(GameModel gameModel){
        
        }
         
	@Override
	public IGameHistoryView getView() {
		
		return (IGameHistoryView)super.getView();
	}
	
	private void initFromModel() {
		
	}
	
}

