package client.points;

import client.base.*;
import controller.ControllerFacade;
import controller.IControllerFacadeListener;
import game.GameModel;


/**
 * Implementation for the points controller
 */
public class PointsController extends Controller implements IPointsController, IControllerFacadeListener {

	private IGameFinishedView finishedView;
	
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
            getPointsView().setPoints(ControllerFacade.getSingleton().getPoints());
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

