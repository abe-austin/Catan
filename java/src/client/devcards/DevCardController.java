package client.devcards;

import shared.definitions.ResourceType;
import client.base.*;
import controller.ControllerFacade;


/**
 * "Dev card" controller implementation
 */
public class DevCardController extends Controller implements IDevCardController {

	private IBuyDevCardView buyCardView;
	private IAction soldierAction;
	private IAction roadAction;
	
	/**
	 * DevCardController constructor
	 * 
	 * @param view "Play dev card" view
	 * @param buyCardView "Buy dev card" view
	 * @param soldierAction Action to be executed when the user plays a soldier card.  It calls "mapController.playSoldierCard()".
	 * @param roadAction Action to be executed when the user plays a road building card.  It calls "mapController.playRoadBuildingCard()".
	 */
	public DevCardController(IPlayDevCardView view, IBuyDevCardView buyCardView, 
								IAction soldierAction, IAction roadAction) {

		super(view);
		
		this.buyCardView = buyCardView;
		this.soldierAction = soldierAction;
		this.roadAction = roadAction;
	}

	public IPlayDevCardView getPlayCardView() {
		return (IPlayDevCardView)super.getView();
	}

	public IBuyDevCardView getBuyCardView() {
		return buyCardView;
	}

	@Override
	public void startBuyCard() {
		ControllerFacade.getSingleton().startBuyCard(); // Use boolean from this to stop/allow user to buy dev card
		getBuyCardView().showModal();
	}

	@Override
	public void cancelBuyCard() {
		
		getBuyCardView().closeModal();
	}

	@Override
	public void buyCard() {
		ControllerFacade.getSingleton().buyCard();
		getBuyCardView().closeModal();
	}

	@Override
	public void startPlayCard() {
		// GET dev cards here?
		getPlayCardView().showModal();
	}

	@Override
	public void cancelPlayCard() {

		getPlayCardView().closeModal();
	}

	@Override
	public void playMonopolyCard(ResourceType resource) {
		ControllerFacade.getSingleton().playMonopolyCard(resource);
	}

	@Override
	public void playMonumentCard() {
		ControllerFacade.getSingleton().playMonumentCard();
	}

	@Override
	public void playRoadBuildCard() {
		ControllerFacade.getSingleton().playRoadBuildCard();
		roadAction.execute();
	}

	@Override
	public void playSoldierCard() {
		ControllerFacade.getSingleton().playSoldierCard();
		soldierAction.execute();
	}

	@Override
	public void playYearOfPlentyCard(ResourceType resource1, ResourceType resource2) {
		ControllerFacade.getSingleton().playYearOfPlentyCard(resource1, resource2);
	}

}

