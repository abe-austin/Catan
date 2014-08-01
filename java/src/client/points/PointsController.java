package client.points;

import client.base.*;
import controller.ControllerFacade;
import controller.IControllerFacadeListener;
import game.GameModel;
import player.Player;
import shared.definitions.GameState;


/**
 * Implementation for the points controller
 */
public class PointsController extends Controller implements IPointsController, IControllerFacadeListener {

	private IGameFinishedView finishedView;
        private int winLimit = 10;
        private boolean backToBeginning=false;
	
	/**
	 * PointsController constructor
	 * 
	 * @param view Points view
	 * @param finishedView Game finished view, which is displayed when the game is over
	 */
	public PointsController(IPointsView view, IGameFinishedView finishedView) {
		
		super(view);
		
		setFinishedView(finishedView);
		
		initFromModel();
                ControllerFacade.getSingleton().addListener(this);
	}
        
	@Override
        public void gameModelChanged(GameModel gameModel){
            if(ControllerFacade.getSingleton().getGameState()==GameState.Setup || ControllerFacade.getSingleton().getGameState()==GameState.GamePlay){
                getPointsView().setPoints(ControllerFacade.getSingleton().getPoints());
                if (gameModel.getWinner()!=-1){
                    if( !getFinishedView().isModalShowing()){
                        if(backToBeginning){
                            //already showed the finishedView, so send back to game hub
                            ControllerFacade.getSingleton().setGameState(GameState.JoinGame);
                        }
                        //System.out.println("winner "+gameModel.getWinner());
                        else if (gameModel.getWinner()==ControllerFacade.getSingleton().getClientPlayer().getUser().getId()){
                            //System.out.println("winner "+ControllerFacade.getSingleton().getClientPlayer().getUsername());
                            getFinishedView().setWinner(ControllerFacade.getSingleton().getClientPlayer().getUsername(), true);
                            getFinishedView().showModal();
                            backToBeginning=true;
                        }
                        else{
                            String name="Someone other than you";
                            //System.out.println("loser "+ControllerFacade.getSingleton().getClientPlayer().getUsername());
                            for(Player player:gameModel.getPlayers()){
                                if(player.getUser().getId()==gameModel.getWinner()){
                                    name=player.getUsername();
                                }
                            }
                            getFinishedView().setWinner(name, false);     
                            getFinishedView().showModal();
                            backToBeginning=true;
                        }
                    }
                }
            }
            if (ControllerFacade.getSingleton().getGameState()==GameState.JoinGame){
                initFromModel();
            }
        }
         
	
	public IPointsView getPointsView() {
		
		return (IPointsView)super.getView();
	}
	
	public IGameFinishedView getFinishedView() {
		return finishedView;
	}
	public void setFinishedView(IGameFinishedView finishedView) {
		this.finishedView = finishedView;
	}

	private void initFromModel() {
		getPointsView().setPoints(0);
	}
	
}

