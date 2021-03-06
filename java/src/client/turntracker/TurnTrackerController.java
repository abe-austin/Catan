package client.turntracker;

import client.base.*;
import controller.ControllerFacade;
import controller.IControllerFacadeListener;
import game.GameModel;
import player.Player;
import shared.definitions.CatanColor;
import shared.definitions.GameState;

/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements
        ITurnTrackerController, IControllerFacadeListener {

    private boolean firstTurn = true;
    private int playerNumber = 0;

    public TurnTrackerController(ITurnTrackerView view) {

        super(view);

        initFromModel();
        ControllerFacade.getSingleton().addListener(this);
    }

    @Override
    public void gameModelChanged(GameModel gameModel) {
        if (ControllerFacade.getSingleton().getGameState() == GameState.GamePlay) {

            if ((ControllerFacade.getSingleton().isCurrentTurn())) {
                if (gameModel.getTurnTracker().getStatus().contains("Playing")) {
                    getView().updateGameState("End Turn", true);
                } else if (gameModel.getTurnTracker().getStatus().contains("Robbing")
                        || gameModel.getTurnTracker().getStatus().contains("Discard")) {
                    getView().updateGameState("Robbing", true);
                } else {
                    getView().updateGameState("End Turn", true);
                }
            } else {
                getView().updateGameState(
                        gameModel.getTurnTracker().getStatus(), false);
            }
        }
        // else if(ControllerFacade.getSingleton().getGameState()==GameState.Setup || ControllerFacade.getSingleton().getGameState()==GameState.PlayerWaiting){
        if (firstTurn || gameModel.getPlayers().length > playerNumber) {
            int i = 0;
            for (Player player : gameModel.getPlayers()) {
                if (i >= playerNumber) {
                    getView().initializePlayer(player.getIndex(),
                            player.toString(), player.getColor());
                }
                i++;
                if (player.getIndex() == ControllerFacade.getSingleton().getClientPlayer().getIndex()) {
                    getView().setLocalPlayerColor(player.getColor());
                }
            }
            firstTurn = false;
            playerNumber = gameModel.getPlayers().length;
        } else {
            for (Player player : gameModel.getPlayers()) {
                getView().updatePlayer(player.getIndex(),player.getPoints(),
                        gameModel.getTurnTracker().getCurrentTurn() == player.getIndex(),
                        player.getIndex() == gameModel.getTurnTracker().getLargestArmy(),
                        player.getIndex() == gameModel.getTurnTracker().getLongestRoad());
            }
        }
        if(ControllerFacade.getSingleton().getGameState()==GameState.JoinGame){
            for (Player player : gameModel.getPlayers()) {
                getView().updatePlayer(player.getIndex(),0,false,false,false);
                getView().initializePlayer(player.getIndex(), "", CatanColor.WHITE);
                firstTurn=true;
            }
        }

        //}
    }

    @Override
    public ITurnTrackerView getView() {

        return (ITurnTrackerView) super.getView();
    }

    @Override
    public void endTurn() {
        ControllerFacade.getSingleton().endTurn();
        getView().updateGameState("Waiting for Other Players", false);
    }

    private void initFromModel() {

    }
}
