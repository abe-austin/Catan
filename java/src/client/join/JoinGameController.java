package client.join;

import client.base.*;
import client.data.*;
import client.misc.*;
import controller.ControllerFacade;
import controller.IControllerFacadeListener;
import game.GameModel;

import java.util.ArrayList;

import shared.communication.ServerResponse;
import shared.definitions.CatanColor;


/**
 * Implementation for the join game controller
 */
public class JoinGameController extends Controller implements IJoinGameController, IControllerFacadeListener {

	private INewGameView newGameView;
	private ISelectColorView selectColorView;
	private IMessageView messageView;
	private IAction joinAction;
	
	/**
	 * JoinGameController constructor
	 * @param view Join game view
	 * @param newGameView New game view
	 * @param selectColorView Select color view
	 * @param messageView Message view (used to display error messages that occur while the user is joining a game)
	 */
	public JoinGameController(IJoinGameView view, INewGameView newGameView, 
								ISelectColorView selectColorView, IMessageView messageView) {

		super(view);

		setNewGameView(newGameView);
		setSelectColorView(selectColorView);
		setMessageView(messageView);
                ControllerFacade.getSingleton().addListener(this);
	}
	
	@Override
        public void gameModelChanged(GameModel gameModel){
            
        }
         
	public IJoinGameView getJoinGameView() {
		
		return (IJoinGameView)super.getView();
	}
	
	/**
	 * Returns the action to be executed when the user joins a game
	 * @return The action to be executed when the user joins a game
	 */
	public IAction getJoinAction() {
		
		return joinAction;
	}

	/**
	 * Sets the action to be executed when the user joins a game
	 * @param value The action to be executed when the user joins a game
	 */
	public void setJoinAction(IAction value) {	
		
		joinAction = value;
	}
	
	public INewGameView getNewGameView() {
		
		return newGameView;
	}

	public void setNewGameView(INewGameView newGameView) {
		
		this.newGameView = newGameView;
	}
	
	public ISelectColorView getSelectColorView() {
		
		return selectColorView;
	}
	
	public void setSelectColorView(ISelectColorView selectColorView) {
		
		this.selectColorView = selectColorView;
	}
	
	public IMessageView getMessageView() {
		
		return messageView;
	}
	
	public void setMessageView(IMessageView messageView) {
		
		this.messageView = messageView;
	}

	@Override
	public void start() {

		getJoinGameView().showModal();
		PlayerInfo playerInfo = new PlayerInfo();
		playerInfo.setName(ControllerFacade.getSingleton().getClientPlayer().toString());
		ArrayList<GameInfo> games = ControllerFacade.getSingleton().getAllGames();
		getJoinGameView().setGames(games, playerInfo);
	}

	@Override
	public void startCreateNewGame() {
		
		getNewGameView().showModal();
	}

	@Override
	public void cancelCreateNewGame() {
		
		getNewGameView().closeModal();
	}

	@Override
	public void createNewGame() {

		ArrayList<GameInfo> gamesTemp = ControllerFacade.getSingleton().createNewGame(
				newGameView.getTitle(),
				newGameView.getRandomlyPlaceHexes(),
				newGameView.getRandomlyPlaceNumbers(),
				newGameView.getUseRandomPorts());
		PlayerInfo playerInfo = new PlayerInfo();
		playerInfo.setName(ControllerFacade.getSingleton().getClientPlayer().toString());
		ControllerFacade.getSingleton().setCurrentPlayerInfo(playerInfo);
		getJoinGameView().setGames(gamesTemp, playerInfo);
		getNewGameView().closeModal();
                startJoinGame(gamesTemp.get(gamesTemp.size()-1));
	}

	@Override
	public void startJoinGame(GameInfo game) {            
            
		ControllerFacade.getSingleton().startJoinGame(game);
                
                for (CatanColor color : CatanColor.values())
			getSelectColorView().setColorEnabled(color, true);
                

		for(PlayerInfo player : game.getPlayers()) {
                    player.setCatanColor(player.getColor());
			if (player.getName().equals(ControllerFacade.getSingleton().
                            getClientPlayer().toString())) {
				joinGame(player.getCatanColor()); // TEMP FIX
				return;
			}
			getSelectColorView().setColorEnabled(player.getCatanColor(), false);
		}

//		for(PlayerInfo player : game.getPlayers()) {
//			if (player.getName().equals(ControllerFacade.getSingleton().
//                            getClientPlayer().toString())) {
//				joinGame(CatanColor.RED); // TEMP FIX
//				return;
//			}
//			getSelectColorView().setColorEnabled(player.getColor(), false);
//		}

                
		getSelectColorView().showModal();
	}

	@Override
	public void cancelJoinGame() {
	
		getJoinGameView().closeModal();
	}

	@Override
	public void joinGame(CatanColor color) {
		
		// If join succeeded
		ServerResponse response = ControllerFacade.getSingleton().joinGame(color);
		if(response.getCode() == 200) {
                    if(getSelectColorView().isModalShowing())
                        getSelectColorView().closeModal();
                    
                    if(getJoinGameView().isModalShowing())
			getJoinGameView().closeModal();
                    
                    joinAction.execute();
		}
		else {
			getJoinGameView().showModal();
		}
	}

}

