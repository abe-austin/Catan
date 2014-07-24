package client.discard;

import client.base.*;
import client.misc.*;
import controller.ControllerFacade;
import controller.IControllerFacadeListener;
import game.GameModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
                
                view.setDiscardButtonEnabled(true);
                ControllerFacade.getSingleton().addListener(this);
                
                numToDiscard = new HashMap<>();
                for(ResourceType type : ResourceType.values())
                    numToDiscard.put(type, 0);
	}

	@Override
        public void gameModelChanged(GameModel gameModel){
            if(gameModel.isCheckDiscard() && !ControllerFacade.getSingleton().getClientPlayer().hasDiscarded() 
                    && ControllerFacade.getSingleton().getClientPlayer().getHandSize() > 7) {
                updateValues();
                getDiscardView().showModal();
                ControllerFacade.getSingleton().getClientPlayer().setDiscarded(true);
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
                if(facade.increaseAmount(resource, numToDiscard.get(type)))
                    numToDiscard.put(type, numToDiscard.get(type)+1);
            }
            
            updateValues();
	}

	@Override
	public void decreaseAmount(ResourceType resource) {
		
            for(ResourceType type : ResourceType.values()) {
                if(facade.decreaseAmount(resource, numToDiscard.get(type)))
                    numToDiscard.put(type, numToDiscard.get(type)-1);
            }
            
            updateValues();
	}

	@Override
	public void discard() {
            toDiscard.clear();
            
            // adds all cards to discard list
            for(ResourceType type : ResourceType.values()) {
                for(int i = 0; i < numToDiscard.get(type); i++)
                    toDiscard.add(type);
            }
            
            if(facade.discard(toDiscard)) {      // only closes if discard was successful
                for(ResourceType type : ResourceType.values())
                    numToDiscard.put(type, 0);
                getDiscardView().closeModal();
                ControllerFacade.getSingleton().getClientPlayer().setDiscarded(false);
            }
	}
        
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

