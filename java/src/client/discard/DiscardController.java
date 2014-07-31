package client.discard;

import client.base.*;
import client.misc.*;
import controller.ControllerFacade;
import controller.IControllerFacadeListener;
import game.GameModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import player.Player;
import shared.definitions.*;


/**
 * Discard controller implementation
 */
public class DiscardController extends Controller implements IDiscardController, IControllerFacadeListener {

	private IWaitView waitView;
        private IDiscardView view;
        private ArrayList<ResourceType> toDiscard = new ArrayList<>();
        private Map<ResourceType, Integer> numToDiscard;
        private ControllerFacade facade = ControllerFacade.getSingleton();
	
	/**
	 * DiscardController constructor
	 * 
	 * @param view View displayed to let the user select cards to discard
	 * @param waitView View displayed to notify the user that they are waiting for other players to discard
	 */
	public DiscardController(IDiscardView view, IWaitView waitView) {
		super(view);
                this.view = view;
		this.waitView = waitView;
                view.setController(this);
                
                view.setDiscardButtonEnabled(true);
                ControllerFacade.getSingleton().addListener(this);
                
                numToDiscard = new HashMap<>();
                for(ResourceType type : ResourceType.values())
                    numToDiscard.put(type, 0);
	}
        //private boolean discarding=false;
	@Override
        public void gameModelChanged(GameModel gameModel){
            if(facade.getGameState()==GameState.GamePlay){
                if(gameModel.getTurnTracker().getStatus().contains("Discard")){  
                    //System.out.println("in discarding");
                   //System.out.println(facade.getClientPlayer().getUsername()+" "+facade.getClientPlayer().hasDiscarded());
                    if(!facade.getClientPlayer().hasDiscarded()){//haven't discarded
                        //System.out.println("has not discarded");
                        if( facade.getClientPlayer().getHandSize() > 7 ){//need to discard
                            //System.out.println("hand size more than 7");
                            if(!getDiscardView().isModalShowing()) {
                                //System.out.println("modal not showing");
                                updateValues();
                                facade.getClientPlayer().setDiscarded(true);
                                getDiscardView().showModal();
                            }
                        }
                        else{//hand <=7 doesn't actually need to discard
                            //System.out.println("hand size less than 8");
                            if(!getWaitView().isModalShowing()){
                               getWaitView().setMessage("Waiting for others to discard");
                               getWaitView().showModal();
                            }
                        }
                        
                    }
                    else{//have discarded
                        //System.out.println("waiting");
                        if(!getWaitView().isModalShowing()){
                               getWaitView().setMessage("Waiting for others to discard");
                               getWaitView().showModal();
                           }
                        }
                        
                             
                    }
                else{//aren't waiting, close the waiting view if open
                   if(getWaitView().isModalShowing()){
                       getWaitView().closeModal();
                   }
                }
            }
            
        }
         
	public IDiscardView getDiscardView() {
		return (IDiscardView)super.getView();
	}
	
	public IWaitView getWaitView() {
		return waitView;
	}

	@Override
	public void increaseAmount(ResourceType resource) {
            for(ResourceType type : ResourceType.values()) {
                if(facade.increaseAmount(resource, numToDiscard.get(type)) && resource.equals(type))
                    numToDiscard.put(type, numToDiscard.get(type)+1);
            }
            
            updateValues();
	}

	@Override
	public void decreaseAmount(ResourceType resource) {
		
            for(ResourceType type : ResourceType.values()) {
                if(facade.decreaseAmount(resource, numToDiscard.get(type)) && resource.equals(type))
                    numToDiscard.put(type, numToDiscard.get(type)-1);
            }
            
            updateValues();
	}

	@Override
	public void discard() {
            //toDiscard.clear();
            
            // adds all cards to discard list
            for(ResourceType type : ResourceType.values()) {
                for(int i = 0; i < numToDiscard.get(type); i++)
                    toDiscard.add(type);
            }
            
            
            
            if(toDiscard.size() == facade.getClientPlayer().getHandSize()/2)//Just make sure it is successfully sending command to be received later
            {
            	ControllerFacade.getSingleton().discard(toDiscard);
            	toDiscard.clear();
                for(ResourceType type : ResourceType.values())
                    numToDiscard.put(type, 0);
                getDiscardView().closeModal();
                ControllerFacade.getSingleton().getClientPlayer().setDiscarded(false);
            }
            else
            	toDiscard.clear();
	}
        
        /**
         * Updates the GUI information to reflect changes by player
         */
        public void updateValues() {
            for(ResourceType type : ResourceType.values()) {
                // Update Number to Discard on view
                view.setResourceDiscardAmount(type, numToDiscard.get(type));
                
                // Update UP/DOWN button availability
                if(numToDiscard.get(type) > 0 && facade.getClientPlayer().hasResource(type, numToDiscard.get(type)+1))
                    getDiscardView().setResourceAmountChangeEnabled(type, true, true);
                else if(facade.getClientPlayer().hasResource(type, numToDiscard.get(type)+1))
                            getDiscardView().setResourceAmountChangeEnabled(type, true, false);
                else if(numToDiscard.get(type) > 0)
                    getDiscardView().setResourceAmountChangeEnabled(type, false, true);
                else
                    getDiscardView().setResourceAmountChangeEnabled(type, false, false);
            }
            
            int total = 0;
            for(ResourceType type : ResourceType.values())
                total += numToDiscard.get(type);
            
            // Shows number of cards prepared to discard
            view.setStateMessage(String.valueOf(total) + " / " + String.valueOf(
                    facade.getClientPlayer().getHandSize()/2));
        }       
}

