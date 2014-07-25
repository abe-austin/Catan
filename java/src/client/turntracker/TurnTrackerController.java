package client.turntracker;

import client.base.*;
import controller.ControllerFacade;
import controller.IControllerFacadeListener;
import game.GameModel;
import player.Player;
import shared.definitions.CatanColor;
import shared.definitions.GameState;


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
            if(ControllerFacade.getSingleton().getGameState()==GameState.GamePlay){
//                if(ControllerFacade.getSingleton().isCurrentTurn()){
//                    if(gameModel.getTurnTracker().getStatus().equals("Robbing")){
//                        getView().updateGameState(gameModel.getTurnTracker().getStatus(), false);
//                    }
//                    else if(!ControllerFacade.getSingleton().isStartTurn()){
//                        getView().updateGameState("End Turn", true);
//                    }
//                }
//                else{
//                    getView().updateGameState("Hold your horses! It's not your turn",false );
//                }
                if ((ControllerFacade.getSingleton().isCurrentTurn())){
                    if(gameModel.getTurnTracker().getStatus().contains("Playing")){
                        getView().updateGameState("End Turn", true);
                    }
                    else if(gameModel.getTurnTracker().getStatus().contains("Robbing")){
                        getView().updateGameState("Robbing", true);
                    }
                }
                else{
                    getView().updateGameState(gameModel.getTurnTracker().getStatus(), false);
                }
            }
            if(firstTurn|| gameModel.getPlayers().length>playerNumber) {
                int i = 0;
                for(Player player : gameModel.getPlayers()){
                    if( i>=playerNumber)
                    getView().initializePlayer(player.getIndex(), player.toString(), player.getColor());
                    i++;
                }
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

