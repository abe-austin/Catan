package controller;

import client.base.IAction;
import client.data.RobPlayerInfo;
import game.GameModel;
import game.cards.CardOwner;
import game.cards.DevelopmentCard;
import game.cards.ResourceCard;
import game.pieces.BoardPiece;

import java.util.ArrayList;
import java.util.Random;

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
    private Player player;
    private final HexTileController hexTileController;
    private IAction robberAction;

    public GamePlayController(Player player) {
        hexTileController = new HexTileController();
        this.player = player;
    }

    public void setPlayer(Player player){
        this.player=player;
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
     * @post new GameModel pointer has been set
     */
    public void switchGameModel(GameModel gameModel){
        this.gameModel = gameModel;
        hexTileController.updateGameModel(gameModel);
    }

    /**
     * This method checks to see if player has enough
     *   resources to buy a dev card.
     */
    public boolean startBuyCard() {
        return (player.hasResource(ResourceType.WHEAT) && 
           player.hasResource(ResourceType.SHEEP) && 
           player.hasResource(ResourceType.ORE) &&
           gameModel.getTurnTracker().getCurrentTurn() == player.getIndex());
    }

    /**
     * This method is called when the user buys a development card.
     * @pre player has enough resources to buy dev card, bank still has a development card
     * @post player had a development card, bank has one less
     */
    public void buyCard() {                                                     // DevCardController
        gameModel.getBank().addResourceCard(player.giveResourceCard(ResourceType.WHEAT));
        gameModel.getBank().addResourceCard(player.giveResourceCard(ResourceType.SHEEP));
        gameModel.getBank().addResourceCard(player.giveResourceCard(ResourceType.ORE));

        player.addDevelopmentCard(gameModel.getBank().giveDevelopmentCard(null));
    }

    /**
     * This method displays the "play dev card" view.
     */
    public ArrayList<DevCardType> startPlayCard() {
        ArrayList<DevCardType> cards = new ArrayList<>();
        
        for(DevelopmentCard card : player.getDevelopmentCards())
            cards.add(card.getDevelopmentType());
        
        return cards;
    }
    /**
     * This method is called when the user plays a monopoly development card.
     *
     * @pre player has monopoly development card
     * @param resource The resource to take from other players
     * @post player has all resource cards of given type that previously
     *          belonged to other players
     */
    public void playMonopolyCard(ResourceType resource) {                       // DevCardController
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
    public void playMonumentCard() {                                            // DevCardController
        player.giveDevelopmentCard(DevCardType.MONUMENT);
        player.addPoint();
    }

    /**
     * This method is called when the user plays a road build development card.
     * @pre player has road builder development card and has at least two more available roads
     * @post player has two more roads on the board
     */
    public void playRoadBuildCard() {                                           // DevCardController
        player.giveDevelopmentCard(DevCardType.ROAD_BUILD);
        hexTileController.playRoadBuildingCard();  // May not be necessary 
    }

    /**
     * This method is called when the user plays a soldier development card.
     * @pre player has soldier development card
     * @post player has one less soldier card, robber has been moved
     */
    public void playSoldierCard() {                                             // DevCardController
        player.giveDevelopmentCard(DevCardType.SOLDIER);
    }

    /**
     * This method is called when the user plays a year of plenty development card.
     *
     * @pre bank has at least one of each given resource type
     * @param resource1 The first resource to gain
     * @param resource2 The second resource to gain
     * @post player has one more resource card of each given resource type
     */                                                                         // DevCardController
    public void playYearOfPlentyCard(ResourceType resource1, ResourceType resource2) {  
        player.addResourceCard(gameModel.getBank().giveResourceCard(resource1));
        player.addResourceCard(gameModel.getBank().giveResourceCard(resource2));
    }
    /**
     * This method is called when the user increases the amount of the specified resource.
     *
     * @param resource The resource that was increased
     */
    public boolean increaseAmount(ResourceType resource, int number) {          // DiscardController
        return (player.hasResource(resource, number+1));
    }
    
    /**
	 * This method is called when the user decreases the amount of the specified resource.
	 * 
	 * @param resource The resource that was decreased
	 */
	public boolean decreaseAmount(ResourceType resource, int number){       // DiscardController
            return (number > 0);
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
    
    public void changeOwnerResource(CardOwner receiver, CardOwner giver, ResourceType resource, int amount) {
    	for(int i = 0; i < amount; i++)
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
    public boolean discard(ArrayList<ResourceType> cardsToDiscard) {               // DiscardController
        if(player.getHandSize()/2 > cardsToDiscard.size())
                return false;
        else {
            for(ResourceType resource : cardsToDiscard)
                gameModel.getBank().addResourceCard(player.giveResourceCard(resource));//Don't need this, should be done on Server side
            
            return true;
        }
    }
    
    public Player getPlayer() {
    	return player;
    }
    
    /**
     * This method is called whenever the user is trying to place a road on the map.
     * It is called by the view for each "mouse move" event. The returned value tells
     * the view whether or not to allow the road to be placed at the specified location.
     *
     * @param edgeLoc The proposed road location
     * @return true if the road can be placed at edgeLoc, false otherwise
     */
    public boolean canPlaceRoad(EdgeLocation edgeLoc) {                         // MapController
        return hexTileController.canPlaceRoad(player, edgeLoc);
    }

    /**
     * This method is called whenever the user is trying to place a settlement on the map.
     * It is called by the view for each "mouse move" event. The returned value tells
     * the view whether or not to allow the settlement to be placed at the specified location.
     *
     * @param vertLoc The proposed settlement location
     * @return true if the settlement can be placed at vertLoc, false otherwise
     */
    public boolean canPlaceSettlement(VertexLocation vertLoc) {                 // MapController
        return hexTileController.canPlaceSettlement(player, vertLoc);
    }

    /**
     * This method is called whenever the user is trying to place a city on the map.
     * It is called by the view for each "mouse move" event. The returned value tells
     * the view whether or not to allow the city to be placed at the specified location.
     *
     * @param vertLoc The proposed city location
     * @return true if the city can be placed at vertLoc, false otherwise
     */
    public boolean canPlaceCity(VertexLocation vertLoc) {                       // MapController
        return hexTileController.canPlaceCity(player, vertLoc);
    }

    /**
     * This method is called whenever the user is trying to place the robber on the map.
     * It is called by the view for each "mouse move" event. The returned value tells
     * the view whether or not to allow the robber to be placed at the specified location.
     *
     * @param hexLoc The proposed robber location
     * @return true if the robber can be placed at hexLoc, false otherwise
     */
    public boolean canPlaceRobber(HexLocation hexLoc) {                         // MapController 
        return hexTileController.canPlaceRobber(hexLoc);
    }

    /**
     * This method is called when the user clicks the mouse to place a road.
     *
     * @pre player has at least one inactive road and has met cost
     * @param edgeLoc The road location
     * @post player has one more road that is active
     */
    public void placeRoad(EdgeLocation edgeLoc) {                               // MapController
        BoardPiece piece = player.getAvailableBoardPiece(PieceType.ROAD);
//        piece.setActive(true);
        hexTileController.placeRoad(player, piece, edgeLoc);
    }

    /**
     * This method is called when the user clicks the mouse to place a settlement.
     *
     * @pre player has at least one inactive settlement and has met cost
     * @param vertLoc The settlement location
     * @post player has one more victory point and has one more active settlement
     */
    public void placeSettlement(VertexLocation vertLoc) {                       // MapController
        BoardPiece piece = player.getAvailableBoardPiece(PieceType.SETTLEMENT);
//        piece.setActive(true);
        player.addPoint();
        hexTileController.placeSettlement(player, piece, vertLoc);
    }

    /**
     * This method is called when the user clicks the mouse to place a city.
     *
     * @pre settlement exists in given VertexLocation, player has met cost and
     *      has at least one inactive city
     * @param vertLoc The city location
     * @post player has one more victory point, one more active city, and one less
     *       active settlement
     */
    public void placeCity(VertexLocation vertLoc) {                             // MapController
        BoardPiece piece = player.getAvailableBoardPiece(PieceType.CITY);
//        piece.setActive(true);
        player.addPoint();
        hexTileController.placeCity(player, piece, vertLoc);
    }

    /**
     * This method is called when the user clicks the mouse to place the robber.
     *
     * @param hexLoc The robber location
     * @post robber is not in original location
     */
    public void placeRobber(HexLocation hexLoc) {                               // MapController
        hexTileController.placeRobber(hexLoc);
    }

    /**
     * This method is called when the user requests to place a piece on the map (road, city, or settlement)
     *
     * @param pieceType The type of piece to be placed
     * @param isFree true if the piece should not cost the player resources, false otherwise.
     * 				Set to true during initial setup and when a road building card is played.
     * @param allowDisconnected true if the piece can be disconnected, false otherwise.
     * 				Set to true only during initial setup.
     */                                                                         // MapController 
    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) { 
        if(gameModel.getTurnTracker().getCurrentTurn() == player.getIndex())
            hexTileController.startMove(pieceType, isFree, allowDisconnected);
    }
    
    public void cancelMove() {
    	hexTileController.cancelMove();
    }

    /**
     * This method is called when the user plays a "road building" progress development card.
     * It should initiate the process of allowing the player to place two roads.
     */
    public void playRoadBuildingCard() {                                        // MapController
        hexTileController.playRoadBuildingCard();
    }

    /**
     * This method is called by the Rob View when a player to rob is selected via a button click.
     *
     * @param victim The player to be robbed
     */
    public void robPlayer(RobPlayerInfo victim) {                               // MapController
        Player stolen = gameModel.getPlayers()[victim.getPlayerIndex()];

        if(stolen.getHandSize() == 0)
            return;

        int index = (int)(Math.random()*stolen.getHandSize());

        ResourceCard card = (ResourceCard)stolen.getResourceCards().toArray()[index];
        
        this.changeOwnerResource(player, stolen, card.getResourceType());
    }

    /**
     * Called by the view then the user requests to build a road
     */
    public boolean buildRoad() {                                                // ResourceBarController
        return (player.hasAvailableBoardPiece(PieceType.ROAD) &&
                player.hasResource(ResourceType.BRICK) &&
                player.hasResource(ResourceType.WOOD) &&
           gameModel.getTurnTracker().getCurrentTurn() == player.getIndex());
    }

    /**
     * Called by the view then the user requests to build a settlement
     */
    public boolean buildSettlement() {                                          // ResourceBarController
        return (player.hasAvailableBoardPiece(PieceType.SETTLEMENT) &&
                player.hasResource(ResourceType.WHEAT) &&
                player.hasResource(ResourceType.SHEEP) &&
                player.hasResource(ResourceType.BRICK) &&
                player.hasResource(ResourceType.WOOD)  &&
           gameModel.getTurnTracker().getCurrentTurn() == player.getIndex());
    }

    /**
     * Called by the view then the user requests to build a city
     */
    public boolean buildCity() {                                                // ResourceBarController
        return (player.hasAvailableBoardPiece(PieceType.CITY) &&
                player.hasResource(ResourceType.WHEAT, 2) &&
                player.hasResource(ResourceType.ORE, 3) &&
           gameModel.getTurnTracker().getCurrentTurn() == player.getIndex());
    }

    /**
     * Called by the view then the user requests to play a card
     */
    public boolean playCard() {                                                    // ResourceBarController
        return !player.getDevelopmentCards().isEmpty();
    }

    /**
     * Called when the user clicks the "Roll!" button in the roll view
     */
    public int rollDice() {                                                    // RollController
        Random rand = new Random();
        return (rand.nextInt(Integer.MAX_VALUE) % 6 + 1 ) + (rand.nextInt(Integer.MAX_VALUE) % 6 + 1); 
    }
    
        /**
         * Gives the number of a given DevCardType the client player has
         * 
         * @param type of DevCard
         * @return number of cards
         */
        public int getNumOfDevCards(DevCardType type) {
            int number = 0;
            
            for(DevelopmentCard card : player.getDevelopmentCards())
                if(card.getDevelopmentType().equals(type))
                    number++;
            
            return number;
        }
        
        /**
         * Gives the number of a given ResourceType the client player has
         * 
         * @param type of Resource
         * @return number of cards
         */
        public int getNumOfResourceCards(ResourceType type) {
            int number = 0;
            
            for(ResourceCard card : player.getResourceCards())
                if(card.getResourceType().equals(type))
                    number++;
            
            return number;
           
        }
        
        /**
         * Distributes to players resources for a given roll
         * 
         * @param roll 
         * @post players may have more resource cards
         */
        public void rollResourceDistribution(int roll) {
          hexTileController.rollResourceDistribution(roll);
        }
        
        /**
         * Allows access to robber/soldier action
         * 
         * @param action 
         */
        public void setRobberAction(IAction action) {
            robberAction = action;
        }
}
