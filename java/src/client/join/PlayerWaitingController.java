package client.join;

import controller.ControllerFacade;
import client.base.*;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController {

	public PlayerWaitingController(IPlayerWaitingView view) {
		
		super(view);
	}

	@Override
	public IPlayerWaitingView getView() {
		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start() {
	
		getView().showModal();
	}

	@Override
	public void addAI() {
		
		// TEMPORARY
		String AIType = getView().getSelectedAI();
		ControllerFacade.getSingleton().addAI(AIType);
		getView().closeModal();
	}

}

