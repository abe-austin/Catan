package client.domestic;

import java.util.ArrayList;

import controller.ControllerFacade;
import shared.definitions.*;
import client.base.*;
import client.misc.*;


/**
 * Domestic trade controller implementation
 */
public class DomesticTradeController extends Controller implements IDomesticTradeController {

	private IDomesticTradeOverlay tradeOverlay;
	private IWaitView waitOverlay;
	private IAcceptTradeOverlay acceptOverlay;
	private int getValue;
	private int giveValue;
	private ResourceType givingResource;
	private ResourceType gettingResource;
	private ControllerFacade controllerFacade= ControllerFacade.getSingleton();

	/**
	 * DomesticTradeController constructor
	 * 
	 * @param tradeView Domestic trade view (i.e., view that contains the "Domestic Trade" button)
	 * @param tradeOverlay Domestic trade overlay (i.e., view that lets the user propose a domestic trade)
	 * @param waitOverlay Wait overlay used to notify the user they are waiting for another player to accept a trade
	 * @param acceptOverlay Accept trade overlay which lets the user accept or reject a proposed trade
	 */
	public DomesticTradeController(IDomesticTradeView tradeView, IDomesticTradeOverlay tradeOverlay,
									IWaitView waitOverlay, IAcceptTradeOverlay acceptOverlay) {

		super(tradeView);
		
		setTradeOverlay(tradeOverlay);
		setWaitOverlay(waitOverlay);
		setAcceptOverlay(acceptOverlay);
	}
	
	public IDomesticTradeView getTradeView() {
		
		return (IDomesticTradeView)super.getView();
	}

	public IDomesticTradeOverlay getTradeOverlay() {
		return tradeOverlay;
	}

	public void setTradeOverlay(IDomesticTradeOverlay tradeOverlay) {
		this.tradeOverlay = tradeOverlay;
	}

	public IWaitView getWaitOverlay() {
		return waitOverlay;
	}

	public void setWaitOverlay(IWaitView waitView) {
		this.waitOverlay = waitView;
	}

	public IAcceptTradeOverlay getAcceptOverlay() {
		return acceptOverlay;
	}

	public void setAcceptOverlay(IAcceptTradeOverlay acceptOverlay) {
		this.acceptOverlay = acceptOverlay;
	}

	@Override
	public void startTrade() {

	     //set the enabled resources for the player
		ArrayList<ResourceType> playerResources = controllerFacade.domesticStartTrade();
		//if (cards==null)System.out.println("cards is empty");
	    ResourceType[] playerResourceTypes= new ResourceType[playerResources.size()];
	    for (int i=0; i < playerResources.size();i++){
	    	playerResourceTypes[i]=playerResources.get(i);
	    }
	    //getTradeOverlay().showGiveOptions(playerResourceTypes);//Not allowed for Domestic Trade
	                
	    getValue=1;
	    giveValue=1;
	                
	    getTradeOverlay().setTradeEnabled(false);
	    getTradeOverlay().setCancelEnabled(true);
	    getTradeOverlay().setStateMessage("can't trade yet");
	    getTradeOverlay().showModal();
	}

	@Override//So I assume each player does this only for their own resource, not the other player's
	public void decreaseResourceAmount(ResourceType resource) {
		giveValue--;
	}

	@Override
	public void increaseResourceAmount(ResourceType resource) {
		giveValue++;
	}

	@Override
	public void sendTradeOffer() {

		getTradeOverlay().closeModal();
//		getWaitOverlay().showModal();
	}

	@Override
	public void setPlayerToTradeWith(int playerIndex) {
		controllerFacade.setPlayerToTradeWith(playerIndex);
	}

	@Override
	public void setResourceToReceive(ResourceType resource) {
		gettingResource = resource;
		//Set some active/inactive on the modal maybe?
	}

	@Override
	public void setResourceToSend(ResourceType resource) {
		givingResource = resource;
		//Do I need to run some test that they have this resource, or will they not have been able to accept it unless they have it
	}

	@Override
	public void unsetResource(ResourceType resource) {
		givingResource = null;
		gettingResource = null;
		//Undo any changes made to view from setResource

	}

	@Override
	public void cancelTrade() {

		getTradeOverlay().closeModal();
	}

	@Override
	public void acceptTrade(boolean willAccept) {
		getAcceptOverlay().closeModal();
	}

}

