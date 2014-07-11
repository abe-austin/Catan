/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import client.data.RobPlayerInfo;
import game.GameModel;
import game.cards.CardOwner;
import game.pieces.BoardPiece;
import player.Player;
import shared.definitions.DevCardType;
import shared.definitions.PieceType;
import shared.definitions.ResourceType;
import shared.definitions.SpecialCardType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

/**
 * Controls game play (GUI interaction)
 *
 * @author Kevin MacMaster
 */
class GamePlayController {
    private GameModel gameModel;

    public GamePlayController() {
        gameModel = new GameModel();
    }

    /**
     * @return GameModel
     */
    public GameModel getGameModel() {
        return gameModel;
    }

    /**
     * replaces the current gameModel with the new GameModel
     * @param gameModel
     */
    public void switchGameModel(GameModel gameModel){
//        this.gameModel.update(gameModel);
    }

    /**
     * This method displays the "buy dev card" view.
     */
    public void startBuyCard() {}//DevCardController --goes in GamePlay !!Not sure it is needed

    /**
     * This method is called when the user cancels out of buying a development card.
     */
    public void cancelBuyCard() {}//DevCardController --goes in GamePlay !!Not sure it is needed

    /**
     * This method is called when the user buys a development card.
     * @pre player has enough resources to buy dev card, bank still has a development card
     * @post player had a development card, bank has one less
     */
    public void buyCard(Player player) {//DevCardController --goes in GamePlay
        gameModel.getBank().addResourceCard(player.giveResourceCard(ResourceType.WHEAT));
        gameModel.getBank().addResourceCard(player.giveResourceCard(ResourceType.SHEEP));
        gameModel.getBank().addResourceCard(player.giveResourceCard(ResourceType.ORE));

//        player.addDevelopmentCard(gameModel.getBank().giveDevelopmentCard());
    }

    /**
     * This method displays the "play dev card" view.
     */
    public void startPlayCard(){}//DevCardController --goes in GamePlay !!Not sure it is needed

    /**
     * This method is called when the user cancels out of playing a development card.
     */
    public void cancelPlayCard(){}//DevCardController --goes in GamePlay !!Not sure it is needed

    /**
     * This method is called when the user plays a monopoly development card.
     *
     * @pre player has monopoly development card
     * @param resource The resource to take from other players
     * @post player has all resource cards of given type that previously
     *          belonged to other players
     */
    public void playMonopolyCard(Player player, ResourceType resource) {//DevCardController --goes in GamePlay
        player.giveDevelopmentCard(DevCardType.MONOPOLY);
        for(Player person : gameModel.getPlayers()) {
            while(!person.equals(player) && person.hasResource(resource))
                player.addResourceCard(person.giveResourceCard(resource));
        }
    }

    /**
     * This method is called when the user plays a monument development card.
     * @post player has one more point;
     */
    public void playMonumentCard(Player player) {//DevCardController --goes in GamePlay
        player.giveDevelopmentCard(DevCardType.MONUMENT);
        player.addPoint();
    }

    /**
     * This method is called when the user plays a road build development card.
     * @pre player has road builder development card and has at least two more available roads
     * @post player has two more roads on the board
     */
    public void playRoadBuildCard(Player player) {//DevCardController --goes in GamePlay
        player.giveDevelopmentCard(DevCardType.ROAD_BUILD);
        // mapController.playRoadBuildCard(player);
    }

    /**
     * This method is called when the user plays a soldier development card.
     * @pre player has soldier development card
     */
    public void playSoldierCard(Player player) {//DevCardController --goes in GamePlay
        player.giveDevelopmentCard(DevCardType.SOLDIER);
        // mapController.moveRobber(player);
    }

    /**
     * This method is called when the user plays a year of plenty development card.
     *
     * @param resource1 The first resource to gain
     * @param resource2 The second resource to gain
     */
    public void playYearOfPlentyCard(Player player, ResourceType resource1, ResourceType resource2) {//DevCardController --goes in GamePlay
        player.addResourceCard(gameModel.getBank().giveResourceCard(resource1));
        player.addResourceCard(gameModel.getBank().giveResourceCard(resource2));
    }
    /**
     * This method is called when the user increases the amount of the specified resource.
     *
     * @param resource The resource that was increased
     */
    public void increaseAmount(ResourceType resource) {//DiscardController --goes in GamePlay

    }

    /**
     * This method is called when the user decreases the amount of the specified resource.
     *
     * @param resource The resource that was decreased
     */
    public void decreaseAmount(ResourceType resource) {//DiscardController --goes in GamePlay

    }

    /**
     * This exchanges a Resource between two card owners
     *
     * @param receiver
     * @param giver
     * @param resource card type
     * @pre giver has resource card of given type
     * @post receiver has resource card of given type
     */
    public void changeOwnerResource(CardOwner receiver, CardOwner giver, ResourceType resource) {
        receiver.addResourceCard(giver.giveResourceCard(resource));
    }

    /**
     * This exchanges a Special Card between two card owners
     *
     * @param receiver
     * @param giver
     * @param special card type
     * @pre giver has special card of given type
     * @post receiver has special card of given type
     */
    public void changeOwnerSpecial(CardOwner receiver, CardOwner giver, SpecialCardType special) {
        receiver.addSpecialCard(giver.giveSpecialCard(special));
    }    

    /**
     * This method is called when the user clicks the discard button.
     */
    public void discard(Player player) {//DiscardController --goes in GamePlay
        
    }
    
    /**
     * This method is called whenever the user is trying to place a road on the map.
     * It is called by the view for each "mouse move" event. The returned value tells
     * the view whether or not to allow the road to be placed at the specified location.
     *
     * @param edgeLoc The proposed road location
     * @return true if the road can be placed at edgeLoc, false otherwise
     */
    public boolean canPlaceRoad(Player player, EdgeLocation edgeLoc) {//MapController --goes in GamePlay
        return false;
    }

    /**
     * This method is called whenever the user is trying to place a settlement on the map.
     * It is called by the view for each "mouse move" event. The returned value tells
     * the view whether or not to allow the settlement to be placed at the specified location.
     *
     * @param vertLoc The proposed settlement location
     * @return true if the settlement can be placed at vertLoc, false otherwise
     */
    public boolean canPlaceSettlement(Player player, VertexLocation vertLoc) {//MapController --goes in GamePlay
        return false;
    }

    /**
     * This method is called whenever the user is trying to place a city on the map.
     * It is called by the view for each "mouse move" event. The returned value tells
     * the view whether or not to allow the city to be placed at the specified location.
     *
     * @param vertLoc The proposed city location
     * @return true if the city can be placed at vertLoc, false otherwise
     */
    public boolean canPlaceCity(Player player, VertexLocation vertLoc) {//MapController --goes in GamePlay
        return false;
    }

    /**
     * This method is called whenever the user is trying to place the robber on the map.
     * It is called by the view for each "mouse move" event. The returned value tells
     * the view whether or not to allow the robber to be placed at the specified location.
     *
     * @param hexLoc The proposed robber location
     * @return true if the robber can be placed at hexLoc, false otherwise
     */
    public boolean canPlaceRobber(HexLocation hexLoc) {//MapController --goes in GamePlay
        return false;
    }

    /**
     * This method is called when the user clicks the mouse to place a road.
     *
     * @param edgeLoc The road location
     */
    public void placeRoad(Player player, EdgeLocation edgeLoc) {//MapController --goes in GamePlay
        BoardPiece piece = player.getAvaliableBoardPiece(PieceType.ROAD);
        piece.setActive(true);
        
    }

    /**
     * This method is called when the user clicks the mouse to place a settlement.
     *
     * @param vertLoc The settlement location
     */
    public void placeSettlement(Player player, VertexLocation vertLoc) {//MapController --goes in GamePlay
        BoardPiece piece = player.getAvaliableBoardPiece(PieceType.SETTLEMENT);
        piece.setActive(true);
    }

    /**
     * This method is called when the user clicks the mouse to place a city.
     *
     * @param vertLoc The city location
     */
    public void placeCity(Player player, VertexLocation vertLoc) {//MapController --goes in GamePlay
        BoardPiece piece = player.getAvaliableBoardPiece(PieceType.CITY);
        piece.setActive(true);
    }

    /**
     * This method is called when the user clicks the mouse to place the robber.
     *
     * @param hexLoc The robber location
     */
    public void placeRobber(HexLocation hexLoc) {//MapController --goes in GamePlay
        
    }

    /**
     * This method is called when the user requests to place a piece on the map (road, city, or settlement)
     *
     * @param pieceType The type of piece to be placed
     * @param isFree true if the piece should not cost the player resources, false otherwise.
     * 				Set to true during initial setup and when a road building card is played.
     * @param allowDisconnected true if the piece can be disconnected, false otherwise.
     * 				Set to true only during initial setup.
     */
    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {//MapController --goes in GamePlay
        
    }

    /**
     * This method is called from the modal map overlay when the cancel button is pressed.
     */
    public void cancelMove() {//MapController --goes in GamePlay
        
    }

    /**
     * This method is called when the user plays a "road building" progress development card.
     * It should initiate the process of allowing the player to place two roads.
     */
    public void playRoadBuildingCard() {//MapController --goes in GamePlay
        
    }

    /**
     * This method is called by the Rob View when a player to rob is selected via a button click.
     *
     * @param victim The player to be robbed
     */
    public void robPlayer(RobPlayerInfo victim) {//MapController --goes in GamePlay
        
    }

    /**
     * Called by the view then the user requests to build a road
     */
    public void buildRoad() {//ResourceBarController --goes in GamePlay

    }

    /**
     * Called by the view then the user requests to build a settlement
     */
    public void buildSettlement() {//ResourceBarController --goes in GamePlay

    }

    /**
     * Called by the view then the user requests to build a city
     */
    void buildCity() {//ResourceBarController --goes in GamePlay

    }

    /**
     * Called by the view then the user requests to play a card
     */
    public void playCard() {//ResourceBarController --goes in GamePlay
        
    }

    /**
     * Called when the user clicks the "Roll!" button in the roll view
     */
    public void rollDice() {//RollController --goes in GamePlay or GameInfo not sure where I want it
        
    }
}
