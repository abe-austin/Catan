package client.maritime;

import client.base.*;
import controller.ControllerFacade;
import controller.IControllerFacadeListener;
import game.GameModel;
import game.cards.ResourceCard;
import java.util.ArrayList;
import shared.definitions.*;


/**
 * Implementation for the maritime trade controller
 */
public class MaritimeTradeController extends Controller implements IMaritimeTradeController, IControllerFacadeListener {
        private ControllerFacade controllerFacade= ControllerFacade.getSingleton();
        private IMaritimeTradeOverlay tradeOverlay;
        private int getValue;
        private int giveValue;
	private ResourceType[] bankResourceTypes;
        private ResourceType[] playerResourceTypes;
        private ResourceType selectedGetResource;
        private ResourceType selectedGiveResource;
        
	public MaritimeTradeController(IMaritimeTradeView tradeView, IMaritimeTradeOverlay tradeOverlay) {
		
		super(tradeView);

		setTradeOverlay(tradeOverlay);
                controllerFacade.addListener(this);
	}
        
	@Override
        public void gameModelChanged(GameModel gameModel){
        
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
            if ( controllerFacade.getGameState()==GameState.GamePlay){
            //set the enabled resources for trade of bank and player
		ArrayList<ArrayList<ResourceType>> cards = controllerFacade.maritimeStartTrade();
		//if (cards==null)System.out.println("cards is empty");
                ArrayList<ResourceType> playerResources = cards.get(0);
                ArrayList<ResourceType> bankResources= cards.get(1);
                playerResourceTypes= new ResourceType[playerResources.size()];
                for (int i=0; i < playerResources.size();i++){
                    playerResourceTypes[i]=playerResources.get(i);
                }
                bankResourceTypes = new ResourceType[bankResources.size()];
                for(int i=0; i < bankResources.size();i++){
                    bankResourceTypes[i]=bankResources.get(i);
                }
                
                getTradeOverlay().showGetOptions(bankResourceTypes);
                getTradeOverlay().showGiveOptions(playerResourceTypes);
                
                selectedGiveResource=null;
                selectedGetResource=null;
                getValue=1;
                giveValue=4;//default trade with bank
                
                getTradeOverlay().setTradeEnabled(false);
                getTradeOverlay().setCancelEnabled(true);
                getTradeOverlay().setStateMessage("can't trade yet");
                if(!controllerFacade.isCurrentTurn()){
                    getTradeOverlay().setStateMessage("Not your turn");
                    getTradeOverlay().hideGetOptions();
                    getTradeOverlay().hideGiveOptions();
                }
                getTradeOverlay().showModal();
            }
        }

	@Override
	public void makeTrade() {
            //get the selectedGetOption resource (quantitiy must be 1)
            //get the selectedGiveOption resource and quantity (2 or 3 depending on port type) or 4
//            System.out.println("selectedGive "+selectedGiveResource);
//            System.out.println("give Value "+giveValue);
//            System.out.println("selectedGet "+selectedGetResource);
            controllerFacade.makeTrade(selectedGiveResource, giveValue, selectedGetResource);
            getTradeOverlay().closeModal();
	}

	@Override
	public void cancelTrade() {

		getTradeOverlay().closeModal();
	}

	@Override
	public void setGetResource(ResourceType resource) {
           selectedGetResource = resource;
           getTradeOverlay().selectGetOption(selectedGetResource, getValue);
           if(selectedGiveResource!=null){
               getTradeOverlay().setTradeEnabled(true);
               getTradeOverlay().setStateMessage("Trade Away!");
           }
	}

	@Override
	public void setGiveResource(ResourceType resource) {
            int number = controllerFacade.setGiveResource(resource);
            if (number !=-1){
                selectedGiveResource = resource;
                giveValue=number;
                getTradeOverlay().selectGiveOption(selectedGetResource, number);
                if(selectedGetResource!=null){
                     getTradeOverlay().setTradeEnabled(true);
                     getTradeOverlay().setStateMessage("Trade Away!");
                }
            }
	}

	@Override
	public void unsetGetValue() {
            selectedGetResource=null;
            getTradeOverlay().showGetOptions(bankResourceTypes);
            getTradeOverlay().setTradeEnabled(false);
            getTradeOverlay().setStateMessage("Choose wisely");
            //getTradeOverlay().reset(); only resets the get
	}

	@Override
	public void unsetGiveValue() {
            selectedGiveResource=null;
            selectedGetResource=null;
            //getTradeOverlay().reset();
            
            getTradeOverlay().showGetOptions(bankResourceTypes);
            getTradeOverlay().showGiveOptions(playerResourceTypes);
            getTradeOverlay().setTradeEnabled(false);
            getTradeOverlay().setStateMessage("Choose wisely");
	}

}

