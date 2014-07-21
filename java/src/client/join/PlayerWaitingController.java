package client.join;

import client.base.*;
import controller.ControllerFacade;
import controller.IControllerFacadeListener;
import game.GameModel;
import shared.definitions.GameState;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController, IControllerFacadeListener {

	public PlayerWaitingController(IPlayerWaitingView view) {
		
		super(view);
                ControllerFacade.getSingleton().addListener(this);
	}
        
	@Override
        public void gameModelChanged(GameModel gameModel){
            if(ControllerFacade.getSingleton().getGameState().equals(GameState.PlayerWaiting) &&
                    gameModel.getPlayers().length == 4)
                getView().closeModal();
        }
         

	@Override
	public IPlayerWaitingView getView() {
		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start() {
	
		getView().showModal();
                ControllerFacade.getSingleton().setGameState(GameState.PlayerWaiting);
		boolean full = ControllerFacade.getSingleton().startPlayerWaiting();
		if(full == true) {
			getView().closeModal();
		}
	}

	@Override
	public void addAI() {
		
		// TEMPORARY
		String AIType = getView().getSelectedAI();
		ControllerFacade.getSingleton().addAI(AIType);
		getView().closeModal();
	}

}

