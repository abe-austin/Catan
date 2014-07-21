package client.turntracker;

import client.base.*;
import controller.ControllerFacade;
import controller.IControllerFacadeListener;
import game.GameModel;
import player.Player;
import shared.definitions.CatanColor;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController, IControllerFacadeListener {
        private boolean firstTurn = true;

	public TurnTrackerController(ITurnTrackerView view) {
		
		super(view);
		
		initFromModel();
                ControllerFacade.getSingleton().addListener(this);
	}
        
	@Override
        public void gameModelChanged(GameModel gameModel){
            Player player = ControllerFacade.getSingleton().getClientPlayer();
            if(firstTurn) {
                getView().initializePlayer(player.getIndex(), "NAME", player.getColor());
                firstTurn = false;
            } else
                getView().updatePlayer(player.getIndex(), player.getPoints(), false, 
                    player.hasLargestArmy(), player.hasLongestRoad());
        }
         
	
	@Override
	public ITurnTrackerView getView() {
		
		return (ITurnTrackerView)super.getView();
	}

	@Override
	public void endTurn() {
            ControllerFacade.getSingleton().endTurn();
            getView().updateGameState("Waiting for Other Players", false);
	}
	
	private void initFromModel() {
            Player player = new Player(CatanColor.BLUE, null, 1);
            getView().initializePlayer(player.getIndex(), "NAME", player.getColor());
	}

}

