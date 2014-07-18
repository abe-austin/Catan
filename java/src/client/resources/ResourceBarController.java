package client.resources;

import java.util.*;

import client.base.*;
import controller.ControllerFacade;


/**
 * Implementation for the resource bar controller
 */
public class ResourceBarController extends Controller implements IResourceBarController {

	private Map<ResourceBarElement, IAction> elementActions;
        private ControllerFacade singleton = ControllerFacade.getSingleton();
	
	public ResourceBarController(IResourceBarView view) {

		super(view);
		
		elementActions = new HashMap<ResourceBarElement, IAction>();
	}

	@Override
	public IResourceBarView getView() {
		return (IResourceBarView)super.getView();
	}

	/**
	 * Sets the action to be executed when the specified resource bar element is clicked by the user
	 * 
	 * @param element The resource bar element with which the action is associated
	 * @param action The action to be executed
	 */
	public void setElementAction(ResourceBarElement element, IAction action) {

		elementActions.put(element, action);
	}

	@Override
	public void buildRoad() {
            if(singleton.buildRoad())
                executeElementAction(ResourceBarElement.ROAD);
	}

	@Override
	public void buildSettlement() {
            if(singleton.buildSettlement())
		executeElementAction(ResourceBarElement.SETTLEMENT);
	}

	@Override
	public void buildCity() {
            if(singleton.buildCity())
		executeElementAction(ResourceBarElement.CITY);
	}

	@Override
	public void buyCard() {
            if(singleton.startBuyCard())
		executeElementAction(ResourceBarElement.BUY_CARD);
	}

	@Override
	public void playCard() {
            if(singleton.playCard())
		executeElementAction(ResourceBarElement.PLAY_CARD);
	}
	
	private void executeElementAction(ResourceBarElement element) {
		
		if (elementActions.containsKey(element)) {
			
			IAction action = elementActions.get(element);
			action.execute();
		}
	}

}

