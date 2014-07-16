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
                        if(!ControllerFacade.getSingleton().increaseAmount(resource, numBrick))
                            break;
                        numBrick++;
                    case ORE:
                        if(!ControllerFacade.getSingleton().increaseAmount(resource, numBrick))
                            break;
                        numBrick++;
                    case WHEAT:
                        if(!ControllerFacade.getSingleton().increaseAmount(resource, numBrick))
                            break;
                        numBrick++;
                    case WOOD:
                        break;
                    case SHEEP:
                        break;
                }
	}

	@Override
	public void decreaseAmount(ResourceType resource) {
		
	}

	@Override
	public void discard() {
		
		getDiscardView().closeModal();
	}

}

