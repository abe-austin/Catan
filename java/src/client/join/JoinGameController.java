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
		playerInfo.setName(ControllerFacade.getSingleton().getUser().getUsername().getUsername());
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
		playerInfo.setName(ControllerFacade.getSingleton().getUser().getUsername().getUsername());
		
		getJoinGameView().setGames(gamesTemp, playerInfo);
		getNewGameView().closeModal();
	}

	@Override
	public void startJoinGame(GameInfo game) {
		ControllerFacade.getSingleton().startJoinGame(game);
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
			getSelectColorView().closeModal();
			getJoinGameView().closeModal();
			joinAction.execute();
		}
		else {
			getJoinGameView().showModal();
		}
	}

}

