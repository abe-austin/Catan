package client.devcards;

import client.base.*;
import controller.ControllerFacade;
import controller.IControllerFacadeListener;
import game.GameModel;
import java.util.ArrayList;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;


/**
 * "Dev card" controller implementation
 */
public class DevCardController extends Controller implements IDevCardController, IControllerFacadeListener {

	private IBuyDevCardView buyCardView;
	private IAction soldierAction;
	private IAction roadAction;
        private ControllerFacade facade = ControllerFacade.getSingleton();
	
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
                facade.setRobberAction(soldierAction);
                facade.addListener(this);
	}

	@Override
        public void gameModelChanged(GameModel gameModel){
            // NO CHANGE
        }
         
	public IPlayDevCardView getPlayCardView() {
		return (IPlayDevCardView)super.getView();
	}

	public IBuyDevCardView getBuyCardView() {
		return buyCardView;
	}

	@Override
	public void startBuyCard() {
		if(facade.startBuyCard())
                    getBuyCardView().showModal();
	}

	@Override
	public void cancelBuyCard() {
		
		getBuyCardView().closeModal();
	}

	@Override
	public void buyCard() {
		facade.buyCard();
		getBuyCardView().closeModal();
	}

	@Override
	public void startPlayCard() {
		ArrayList<DevCardType> cards = facade.startPlayCard();
                
                if(cards == null)
                    return;
                
                if(cards.isEmpty())
                    return;
                
                // Set which cards are useable and amounts
                
                for(DevCardType type : DevCardType.values()) {
                    if(cards.contains(type)) {
                        getPlayCardView().setCardEnabled(type, true); 
                        getPlayCardView().setCardAmount(type, facade.getNumOfDevCards(type));
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
		facade.playMonopolyCard(resource);
	}

	@Override
	public void playMonumentCard() {
		facade.playMonumentCard();
	}

	@Override
	public void playRoadBuildCard() {
		facade.playRoadBuildCard();
		roadAction.execute();
	}

	@Override
	public void playSoldierCard() {
		facade.playSoldierCard();
		soldierAction.execute();
	}

	@Override
	public void playYearOfPlentyCard(ResourceType resource1, ResourceType resource2) {
		facade.playYearOfPlentyCard(resource1, resource2);
	}

}

