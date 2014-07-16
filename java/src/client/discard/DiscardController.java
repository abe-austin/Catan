package client.discard;

import shared.definitions.*;
import client.base.*;
import client.misc.*;
import controller.ControllerFacade;
import java.util.ArrayList;


/**
 * Discard controller implementation
 */
public class DiscardController extends Controller implements IDiscardController {

	private IWaitView waitView;
        private int numWheat = 0, numWood = 0, numOre = 0, numSheep = 0, numBrick = 0;
        private ArrayList<ResourceType> toDiscard = new ArrayList<>();
        private ControllerFacade singleton = ControllerFacade.getSingleton();
	
	/**
	 * DiscardController constructor
	 * 
	 * @param view View displayed to let the user select cards to discard
	 * @param waitView View displayed to notify the user that they are waiting for other players to discard
	 */
	public DiscardController(IDiscardView view, IWaitView waitView) {
		
		super(view);
                
		this.waitView = waitView;
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
                    if(!singleton.increaseAmount(resource, numBrick))
                        break;
                    numBrick++;
                case ORE:
                    if(!singleton.increaseAmount(resource, numOre))
                        break;
                    numOre++;
                case WHEAT:
                    if(!singleton.increaseAmount(resource, numWheat))
                        break;
                    numWheat++;
                case WOOD:
                    if(!singleton.increaseAmount(resource, numWood))
                        break;
                    numWood++;
                    break;
                case SHEEP:
                    if(!singleton.increaseAmount(resource, numSheep))
                        break;
                    numSheep++;
                    break;
            }
            
            
	}

	@Override
	public void decreaseAmount(ResourceType resource) {
		
            switch(resource) {
                case BRICK:
                    if(!singleton.decreaseAmount(resource, numBrick))
                        break;
                    numBrick--;
                case ORE:
                    if(!singleton.decreaseAmount(resource, numOre))
                        break;
                    numOre--;
                case WHEAT:
                    if(!singleton.decreaseAmount(resource, numWheat))
                        break;
                    numWheat--;
                case WOOD:
                    if(!singleton.decreaseAmount(resource, numWood))
                        break;
                    numWood--;
                    break;
                case SHEEP:
                    if(!singleton.decreaseAmount(resource, numSheep))
                        break;
                    numSheep--;
                    break;
            }
	}

	@Override
	public void discard() {
            toDiscard.clear();
            
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
            
            if(singleton.discard(toDiscard))
                getDiscardView().closeModal();
	}

}

