package shared.communication;

import game.GameModel;

public class GameModelParam {
	
	GameModel gameModel;

	public GameModelParam() {
		this.gameModel = new GameModel();
	}
	public GameModelParam(GameModel gameModel) {
		super();
		this.gameModel = gameModel;
	}

	public GameModel getGameModel() {
		return gameModel;
	}

	public void setGameModel(GameModel gameModel) {
		this.gameModel = gameModel;
	}
	
	
}
