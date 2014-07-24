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
        private int playerNumber= 0;
	public TurnTrackerController(ITurnTrackerView view) {
		
		super(view);
		
		initFromModel();
                ControllerFacade.getSingleton().addListener(this);
	}
        
	@Override
        public void gameModelChanged(GameModel gameModel){
            if(firstTurn|| gameModel.getPlayers().length>playerNumber) {
                for(Player player : gameModel.getPlayers())
                    getView().initializePlayer(player.getIndex(), player.toString(), player.getColor());
                firstTurn = false;
                playerNumber = gameModel.getPlayers().length;
            } else {
                for(Player player : gameModel.getPlayers()) {
                    getView().updatePlayer(player.getIndex(), player.getPoints(), 
                            gameModel.getTurnTracker().getCurrentTurn() == player.getIndex(),
                            player.hasLargestArmy(), player.hasLongestRoad());
                }
            }
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

	}

}

