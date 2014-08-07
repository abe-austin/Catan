package client.communication;

import client.base.*;
import controller.ControllerFacade;
import controller.IControllerFacadeListener;
import game.GameModel;
import java.util.*;
import player.Player;
import shared.definitions.*;

/**
 * Game history controller implementation
 */
public class GameHistoryController extends Controller implements
		IGameHistoryController, IControllerFacadeListener {

	private IGameHistoryView view;

	public GameHistoryController(IGameHistoryView view) {

		super(view);
		ControllerFacade.getSingleton().addListener(this);
		this.view = view;
		initFromModel();
	}

	@Override
	public void gameModelChanged(GameModel gameModel) {

		if (ControllerFacade.getSingleton().getGameState() == GameState.Setup
				|| ControllerFacade.getSingleton().getGameState() == GameState.GamePlay) {
			List<LogEntry> entries = new ArrayList<LogEntry>();
			for (Command command : gameModel.getGameHistory().getGameCommands()) {
				Player player = ControllerFacade.getSingleton().getPlayerByUsername(command.getSource());
				if (player != null) {
					LogEntry entry = new LogEntry(player.getColor(), command.getSource() + " " + command.getCommand());
					entries.add(entry);
				}
			}
			view.setEntries(entries);

		} else if (ControllerFacade.getSingleton().getGameState() == GameState.JoinGame) {
			view.setEntries(new ArrayList<LogEntry>());
		}
	}

	@Override
	public IGameHistoryView getView() {

		return (IGameHistoryView) super.getView();
	}

	private void initFromModel() {

	}
}

