package client.roll;

import client.base.*;
import controller.ControllerFacade;
import controller.IControllerFacadeListener;
import game.GameModel;
import java.util.Timer;
import java.util.TimerTask;
import shared.definitions.GameState;

/**
 * Implementation for the roll controller
 */
public class RollController extends Controller implements IRollController,
		IControllerFacadeListener {

	private Timer timer;
	private boolean waitingOnTimer = false;
	private ControllerFacade facade;

	private IRollResultView resultView;

	/**
	 * RollController constructor
	 * @param view
	 *            Roll view
	 * @param resultView
	 *            Roll result view
	 */
	public RollController(IRollView view, IRollResultView resultView) {

		super(view);
		view.setController(this);
		resultView.setController(this);
		setResultView(resultView);
		facade = ControllerFacade.getSingleton();
		facade.addListener(this);
	}

	@Override
	public void gameModelChanged(GameModel gameModel) {
		if (facade.getGameState().equals(GameState.GamePlay)){
                    //System.out.println("waitingOnTimer "+waitingOnTimer);
                    //System.out.println("isStartTurn "+facade.isStartTurn());
			if (!waitingOnTimer && facade.isStartTurn()) {
                            //System.out.println("entered the if");
				getRollView().showModal();
				start();
			}
                }
	}

	public IRollResultView getResultView() {
		return resultView;
	}

	public void setResultView(IRollResultView resultView) {
		this.resultView = resultView;
	}

	public IRollView getRollView() {
		return (IRollView) getView();
	}

	@Override
	public void rollDice() {
		timer.cancel();
		if (getRollView().isModalShowing())
			getRollView().closeModal();
		getResultView().setRollValue(facade.rollDice());
		getResultView().showModal();
		waitingOnTimer = false;

	}

	private void start() {
		if (!waitingOnTimer) {
			this.timer = new Timer();
			TimerTask task = new Countdown();
			timer.schedule(task, 0, 1000);
			waitingOnTimer = true;
		}
	}

	/**
	 * 
	 * @param seconds
	 * @return
	 */
	public int pollRoll(int seconds) {
		if (seconds > 0) {
			getRollView().setMessage(seconds + " seconds left to roll");
			seconds--;
		} else {
			rollDice();
		}

		return seconds;
	}

	class Countdown extends TimerTask {
		int seconds;

		public Countdown() {
			seconds = 3;
		}

		@Override
		public void run() {
			seconds = pollRoll(seconds);
		}
	}
}
