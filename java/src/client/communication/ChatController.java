package client.communication;

import java.util.ArrayList;
import java.util.List;

import player.Player;
import client.base.*;
import client.parse.ParsedChat;
import controller.ControllerFacade;
import controller.IControllerFacadeListener;
import game.GameModel;
import shared.definitions.GameState;

/**
 * Chat controller implementation
 */
public class ChatController extends Controller implements IChatController,
		IControllerFacadeListener {

	private IChatView view;

	public ChatController(IChatView view) {

		super(view);
		ControllerFacade.getSingleton().addListener(this);
		this.view = view;
	}

	@Override
	public IChatView getView() {
		return (IChatView) super.getView();
	}

	@Override
	public void sendMessage(String message) {
		ControllerFacade.getSingleton().sendMessage(message);
	}

	@Override
	public void gameModelChanged(GameModel gameModel) {

		if (ControllerFacade.getSingleton().getGameState() == GameState.Setup
				|| ControllerFacade.getSingleton().getGameState() == GameState.GamePlay) {
			List<LogEntry> entries = new ArrayList<LogEntry>();
			for (ParsedChat chat : gameModel.getGameHistory().getChatlog()
					.getChatLines()) {
				Player player = ControllerFacade.getSingleton()
						.getPlayerByUsername(chat.getSource());
				if (player != null) {
					String message = chat.getSource() + ": "
							+ chat.getMessage();
					LogEntry entry = new LogEntry(player.getColor(), message);
					entries.add(entry);
				}
			}
			view.setEntries(entries);
		}
	}
}

