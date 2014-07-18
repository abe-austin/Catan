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
        private IPlayDevCardView playView;
	
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
		
                this.playView = view;
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
                
                if(cards.contains(DevCardType.MONOPOLY))
                    playView.setCardEnabled(DevCardType.MONOPOLY, true);
                else
                    playView.setCardEnabled(DevCardType.MONOPOLY, false);
                
                if(cards.contains(DevCardType.YEAR_OF_PLENTY))
                    playView.setCardEnabled(DevCardType.YEAR_OF_PLENTY, true);
                else
                    playView.setCardEnabled(DevCardType.YEAR_OF_PLENTY, false);
                
                if(cards.contains(DevCardType.ROAD_BUILD))
                    playView.setCardEnabled(DevCardType.ROAD_BUILD, true);
                else
                    playView.setCardEnabled(DevCardType.ROAD_BUILD, false);
                
                if(cards.contains(DevCardType.SOLDIER))
                    playView.setCardEnabled(DevCardType.SOLDIER, true);
                else
                    playView.setCardEnabled(DevCardType.SOLDIER, false);
                
                if(cards.contains(DevCardType.MONUMENT))
                    playView.setCardEnabled(DevCardType.MONUMENT, true);
                else
                    playView.setCardEnabled(DevCardType.MONUMENT, false);
                
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

