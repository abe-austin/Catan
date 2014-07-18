package client.maritime;

import client.base.*;
import controller.ControllerFacade;
import game.cards.ResourceCard;
import java.util.ArrayList;
import shared.definitions.*;


/**
 * Implementation for the maritime trade controller
 */
public class MaritimeTradeController extends Controller implements IMaritimeTradeController {
        private ControllerFacade controllerFacade= ControllerFacade.getSingleton();
	private IMaritimeTradeOverlay tradeOverlay;
        private int getValue;
        private int giveValue;
	private ResourceType[] bankResources;
        private ResourceType[] playerResources;
        private ResourceType selectedGetResource;
        private ResourceType selectedGiveResource;
        
	public MaritimeTradeController(IMaritimeTradeView tradeView, IMaritimeTradeOverlay tradeOverlay) {
		
		super(tradeView);

		setTradeOverlay(tradeOverlay);
	}
	
	public IMaritimeTradeView getTradeView() {
		
		return (IMaritimeTradeView)super.getView();
	}
	
	public IMaritimeTradeOverlay getTradeOverlay() {
		return tradeOverlay;
	}

	public void setTradeOverlay(IMaritimeTradeOverlay tradeOverlay) {
		this.tradeOverlay = tradeOverlay;
	}

	@Override
	public void startTrade() {
            
            //set the enabled resources for trade of bank and player
		ArrayList<ArrayList<ResourceType>> cards = controllerFacade.maritimeStartTrade();
		//if (cards==null)System.out.println("cards is empty");
                ArrayList<ResourceType> playerResources = cards.get(0);
                ArrayList<ResourceType> bankResources= cards.get(1);
                ResourceType[] playerResourceTypes= new ResourceType[playerResources.size()];
                for (int i=0; i < playerResources.size();i++){
                    playerResourceTypes[i]=playerResources.get(i);
                }
                ResourceType[] bankResourceTypes = new ResourceType[bankResources.size()];
                for(int i=0; i < bankResources.size();i++){
                    bankResourceTypes[i]=bankResources.get(i);
                }
                getTradeOverlay().showGiveOptions(playerResourceTypes);
                getTradeOverlay().showGetOptions(bankResourceTypes);
                
                getValue=1;
                giveValue=4;//default trade with bank
                
                getTradeOverlay().setTradeEnabled(false);
                getTradeOverlay().setCancelEnabled(true);
                getTradeOverlay().setStateMessage("can't trade yet");
                getTradeOverlay().showModal();
	}

	@Override
	public void makeTrade() {
            //get the selectedGetOption resource (quantitiy must be 1)
            //get the selectedGiveOption resource and quantity (2 or 3 depending on port type) or 4
            
		getTradeOverlay().closeModal();
	}

	@Override
	public void cancelTrade() {

		getTradeOverlay().closeModal();
	}

	@Override
	public void setGetResource(ResourceType resource) {
           selectedGetResource = resource;
           //set getValue based on whether the player has a port
	}

	@Override
	public void setGiveResource(ResourceType resource) {
            selectedGiveResource = resource;
	}

	@Override
	public void unsetGetValue() {
            selectedGetResource=null;
            //getTradeOverlay().reset(); only resets the get
	}

	@Override
	public void unsetGiveValue() {
            selectedGiveResource=null;
            selectedGetResource=null;
            getTradeOverlay().reset();
	}

}

