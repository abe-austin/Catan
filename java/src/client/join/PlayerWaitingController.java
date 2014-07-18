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
		ControllerFacade.getSingleton().playerWaitingStart();
		getView().showModal();
	}

	@Override
	public void addAI() {
		ControllerFacade.getSingleton().addAI();
		// TEMPORARY
		getView().closeModal();
	}

}

