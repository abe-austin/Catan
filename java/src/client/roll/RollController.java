package client.roll;

import client.base.*;
import controller.ControllerFacade;
import controller.IControllerFacadeListener;
import game.GameModel;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Implementation for the roll controller
 */
public class RollController extends Controller implements IRollController, IControllerFacadeListener {

	private Timer timer;
	private boolean waitingOnTimer = false;
    
	private IRollResultView resultView;

	/**
	 * RollController constructor
	 * 
	 * @param view Roll view
	 * @param resultView Roll result view
	 */
	public RollController(IRollView view, IRollResultView resultView) {

		super(view);
		
		setResultView(resultView);
                ControllerFacade.getSingleton().addListener(this);
	}
        
	@Override
        public void gameModelChanged(GameModel gameModel){
            if(resultView.isModalShowing() && !waitingOnTimer)
               startRolling();
        }
         
	
	public IRollResultView getResultView() {
		return resultView;
	}
	public void setResultView(IRollResultView resultView) {
		this.resultView = resultView;
	}

	public IRollView getRollView() {
		return (IRollView)getView();
	}
	
	@Override
	public void rollDice() {
                getResultView().setRollValue(ControllerFacade.getSingleton().rollDice());
		getResultView().showModal();
                waitingOnTimer = false;
                timer.cancel();
	}
        
        private void startRolling() {
            if (!waitingOnTimer) {
                this.timer = new Timer();
                TimerTask task = new DisplayCountdown();
                timer.schedule(task, 0, 1000);
                waitingOnTimer = true;
            }
	}

	public int pollRoll(int seconds) {
            if (seconds > 0) {
                getRollView().setMessage(seconds + " seconds left to roll");
                seconds--;
            } else {
                getRollView().closeModal();
                rollDice();
            }
            
            return seconds;
	}

	class DisplayCountdown extends TimerTask {
            int seconds;

            public DisplayCountdown() {
                seconds = 10;
            }

            @Override
            public void run() {
                seconds = pollRoll(seconds);
            }
	}
}
