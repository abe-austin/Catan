package client.devcards;

import shared.definitions.ResourceType;
import client.base.*;
import controller.ControllerFacade;
import java.util.ArrayList;
import shared.definitions.DevCardType;


/**
 * "Dev card" controller implementation
 */
public class DevCardController extends Controller implements IDevCardController {

	private IBuyDevCardView buyCardView;
	private IAction soldierAction;
	private IAction roadAction;
        private ControllerFacade singleton = ControllerFacade.getSingleton();
	
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
		if(singleton.startBuyCard())
                    getBuyCardView().showModal();
	}

	@Override
	public void cancelBuyCard() {
		
		getBuyCardView().closeModal();
	}

	@Override
	public void buyCard() {
		singleton.buyCard();
		getBuyCardView().closeModal();
	}

	@Override
	public void startPlayCard() {
		ArrayList<DevCardType> cards = singleton.startPlayCard();
                
                if(cards == null)
                    return;
                
                if(cards.isEmpty())
                    return;
                
                // Set which cards are useable and amounts
                
                for(DevCardType type : DevCardType.values()) {
                    if(cards.contains(type)) {
                        getPlayCardView().setCardEnabled(type, true); 
                        getPlayCardView().setCardAmount(type, singleton.getNumOfDevCards(type));
                    } else {
                        getPlayCardView().setCardEnabled(type, false);
                        getPlayCardView().setCardAmount(type, 0);
                    }
                }
                
		getPlayCardView().showModal();
	}

	@Override
	public void cancelPlayCard() {

		getPlayCardView().closeModal();
	}

	@Override
	public void playMonopolyCard(ResourceType resource) {
		singleton.playMonopolyCard(resource);
	}

	@Override
	public void playMonumentCard() {
		singleton.playMonumentCard();
	}

	@Override
	public void playRoadBuildCard() {
		singleton.playRoadBuildCard();
		roadAction.execute();
	}

	@Override
	public void playSoldierCard() {
		singleton.playSoldierCard();
		soldierAction.execute();
	}

	@Override
	public void playYearOfPlentyCard(ResourceType resource1, ResourceType resource2) {
		singleton.playYearOfPlentyCard(resource1, resource2);
	}

}

