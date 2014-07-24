package client.join;

import client.base.*;
import client.data.PlayerInfo;
import controller.ControllerFacade;
import controller.IControllerFacadeListener;
import game.GameModel;
import player.Player;
import shared.definitions.GameState;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController, IControllerFacadeListener {

	public PlayerWaitingController(IPlayerWaitingView view) {
		
		super(view);
                ControllerFacade.getSingleton().addListener(this);
                view.setAIChoices(ControllerFacade.getSingleton().getAIList());
	}
        
	@Override
        public void gameModelChanged(GameModel gameModel){
            if(ControllerFacade.getSingleton().getGameState().equals(GameState.PlayerWaiting) &&
                    gameModel.getPlayers().length == 4){
                start();
                ControllerFacade.getSingleton().setGameState(GameState.Setup);
                getView().closeModal();
            } else {
                if(gameModel.getPlayers().length == 0)
                    return;
                
                PlayerInfo[] info = new PlayerInfo[gameModel.getPlayers().length];
                Player current;
                
                for(int i = 0; i < gameModel.getPlayers().length; i++) {
                    PlayerInfo playerInfo = new PlayerInfo();
                    current = gameModel.getPlayers()[i];
                    playerInfo.setId(current.getUser().getId());
                    
                    playerInfo.setPlayerIndex(current.getIndex());
                    playerInfo.setName(current.getUser().getUsername().getUsername());
                    playerInfo.setCatanColor(current.getColor());
                    info[i] = playerInfo;
                }
                
                getView().setPlayers(info);
            }
        }
         

	@Override
	public IPlayerWaitingView getView() {
		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start() {
	
		getView().showModal();
        ControllerFacade.getSingleton().setGameState(GameState.PlayerWaiting);
		ControllerFacade.getSingleton().startPlayerWaiting();
	}

	@Override
	public void addAI() {
		
		String AIType = getView().getSelectedAI();
		ControllerFacade.getSingleton().addAI(AIType);
	}

}

