package client.resources;


import client.base.*;
import controller.ControllerFacade;
import controller.IControllerFacadeListener;
import game.GameModel;
import java.util.*;
import shared.definitions.GameState;
import shared.definitions.ResourceType;


/**
 * Implementation for the resource bar controller
 */
public class ResourceBarController extends Controller implements IResourceBarController, IControllerFacadeListener {

	private Map<ResourceBarElement, IAction> elementActions;
        private final ControllerFacade facade = ControllerFacade.getSingleton();
	
	public ResourceBarController(IResourceBarView view) {

		super(view);
		
		elementActions = new HashMap<ResourceBarElement, IAction>();
                facade.addListener(this);
	}
        
	@Override
        public void gameModelChanged(GameModel gameModel){
            if (ControllerFacade.getSingleton().getGameState()==GameState.GamePlay){
                getView().setElementEnabled(ResourceBarElement.BUY_CARD, facade.startBuyCard());
                getView().setElementEnabled(ResourceBarElement.CITY, facade.buildCity());
                getView().setElementEnabled(ResourceBarElement.ROAD, facade.buildRoad());
                getView().setElementEnabled(ResourceBarElement.SETTLEMENT, facade.buildSettlement());
                getView().setElementEnabled(ResourceBarElement.PLAY_CARD, facade.startPlayCard().size() > 0);

                getView().setElementAmount(ResourceBarElement.WOOD, facade.getNumOfResourceCards(ResourceType.WOOD));
                getView().setElementAmount(ResourceBarElement.WHEAT, facade.getNumOfResourceCards(ResourceType.WHEAT));
                getView().setElementAmount(ResourceBarElement.SHEEP, facade.getNumOfResourceCards(ResourceType.SHEEP));
                getView().setElementAmount(ResourceBarElement.BRICK, facade.getNumOfResourceCards(ResourceType.BRICK));
                getView().setElementAmount(ResourceBarElement.ORE, facade.getNumOfResourceCards(ResourceType.ORE));
            }
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
            if(facade.buildRoad())
                executeElementAction(ResourceBarElement.ROAD);
	}

	@Override
	public void buildSettlement() {
            if(facade.buildSettlement())
		executeElementAction(ResourceBarElement.SETTLEMENT);
	}

	@Override
	public void buildCity() {
            if(facade.buildCity())
		executeElementAction(ResourceBarElement.CITY);
	}

	@Override
	public void buyCard() {
            if(facade.startBuyCard())
		executeElementAction(ResourceBarElement.BUY_CARD);
	}

	@Override
	public void playCard() {
            if(facade.playCard())
		executeElementAction(ResourceBarElement.PLAY_CARD);
	}
	
	private void executeElementAction(ResourceBarElement element) {
		
		if (elementActions.containsKey(element)) {
			
			IAction action = elementActions.get(element);
			action.execute();
		}
	}

}

