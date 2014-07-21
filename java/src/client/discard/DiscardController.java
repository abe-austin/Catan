package client.discard;

import client.base.*;
import client.misc.*;
import controller.ControllerFacade;
import controller.IControllerFacadeListener;
import game.GameModel;
import java.util.ArrayList;
import shared.definitions.*;


/**
 * Discard controller implementation
 */
public class DiscardController extends Controller implements IDiscardController, IControllerFacadeListener {

	private IWaitView waitView;
        private IDiscardView view;
        private int numWheat = 0, numWood = 0, numOre = 0, numSheep = 0, numBrick = 0;
        private ArrayList<ResourceType> toDiscard = new ArrayList<>();
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
	}

	@Override
        public void gameModelChanged(GameModel gameModel){
            // NO CHANGE
        }
         
	public IDiscardView getDiscardView() {
		return (IDiscardView)super.getView();
	}
	
	public IWaitView getWaitView() {
		return waitView;
	}

	@Override
	public void increaseAmount(ResourceType resource) {
            	
            switch(resource) {
                case BRICK:
                    if(facade.increaseAmount(resource, numBrick))
                        numBrick++;
                    break;
                case ORE:
                    if(facade.increaseAmount(resource, numOre))
                        numOre++;
                    break;
                case WHEAT:
                    if(facade.increaseAmount(resource, numWheat))
                        numWheat++;
                    break;
                case WOOD:
                    if(facade.increaseAmount(resource, numWood))
                        numWood++;
                    break;
                case SHEEP:
                    if(facade.increaseAmount(resource, numSheep))
                        numSheep++;
                    break;
            }
            
            updateValues();
	}

	@Override
	public void decreaseAmount(ResourceType resource) {
		
            switch(resource) {
                case BRICK:
                    if(facade.decreaseAmount(resource, numBrick))
                        numBrick--;
                    break;
                case ORE:
                    if(facade.decreaseAmount(resource, numOre))
                        numOre--;
                    break;
                case WHEAT:
                    if(facade.decreaseAmount(resource, numWheat))
                        numWheat--;
                    break;
                case WOOD:
                    if(facade.decreaseAmount(resource, numWood))
                        numWood--;
                    break;
                case SHEEP:
                    if(facade.decreaseAmount(resource, numSheep))
                        numSheep--;
                    break;
            }
            
            updateValues();
	}

	@Override
	public void discard() {
            toDiscard.clear();
            
            // adds all cards to discard list
            for(int i = 0; i < numBrick; i++)
                toDiscard.add(ResourceType.BRICK);
            for(int i = 0; i < numWheat; i++)
                toDiscard.add(ResourceType.WHEAT);
            for(int i = 0; i < numOre; i++)
                toDiscard.add(ResourceType.ORE);
            for(int i = 0; i < numWood; i++)
                toDiscard.add(ResourceType.WOOD);
            for(int i = 0; i < numSheep; i++)
                toDiscard.add(ResourceType.SHEEP);
            
            if(facade.discard(toDiscard)) {// only closes if discard was successful
                numSheep = 0;
                numWood = 0;
                numOre = 0;
                numBrick = 0;
                numWheat = 0;
                getDiscardView().closeModal();
            }
	}
        
        public void updateValues() {
            view.setResourceDiscardAmount(ResourceType.WOOD, numWood);
            view.setResourceDiscardAmount(ResourceType.WHEAT, numWheat);
            view.setResourceDiscardAmount(ResourceType.ORE, numOre);
            view.setResourceDiscardAmount(ResourceType.BRICK, numBrick);
            view.setResourceDiscardAmount(ResourceType.SHEEP, numSheep);
            
            int total = numWood + numWheat + numOre + numBrick + numSheep;
            
            view.setStateMessage(String.valueOf(total) + " / " + String.valueOf(
                    facade.getClientPlayer().getHandSize()/2));
        }

}

