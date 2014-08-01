package server;

import client.parse.ParsedChat;
import controller.PlayerReceivingResources;
import game.GameModel;
import game.TradeOffer;
import game.board.Corner;
import game.board.HexTile;
import game.board.ResourceTile;
import game.cards.CardOwner;
import game.cards.ResourceCard;
import game.pieces.BoardPiece;
import java.util.ArrayList;
import java.util.List;
import player.Player;
import shared.communication.*;
import shared.definitions.DevCardType;
import shared.definitions.HexType;
import shared.definitions.PieceType;
import shared.definitions.ResourceType;
import shared.definitions.ResourceTypeUtils;

/**
 *
 * @author Kevin MacMaster
 */
public class MovesHandler implements IHandler {
    private ServerController controller;
    
    public MovesHandler(ServerController controller) {
        this.controller = controller;
    }

    @Override
    public ServerResponse handle(String command, Object Json) {
        switch(command) {
            case "/moves/sendChat":
                return sendChat((SendChatParam)JsonUtils.convertFromJson(
                        SendChatParam.class, (String)Json));
            case "/moves/rollNumber":
                return rollNumber((RollNumberParam)JsonUtils.convertFromJson(
                        RollNumberParam.class, (String)Json));   
            case "/moves/robPlayer":
                return robPlayer((RobPlayerParam)JsonUtils.convertFromJson(
                        RobPlayerParam.class, (String)Json));      
            case "/moves/finishTurn":
                return finishTurn((FinishTurnParam)JsonUtils.convertFromJson(
                        FinishTurnParam.class, (String)Json));      
            case "/moves/buyDevCard":
                return buyDevCard((BuyDevCardParam)JsonUtils.convertFromJson(
                        BuyDevCardParam.class, (String)Json));   
            case "/moves/Year_Of_Plenty":
                return playYearOfPlenty((PlayYearOfPlentyParam)JsonUtils.convertFromJson(
                        PlayYearOfPlentyParam.class, (String)Json));   
            case "/moves/Road_Building":
                return playRoadBuilding((PlayRoadBuildingParam)JsonUtils.convertFromJson(
                        RollNumberParam.class, (String)Json));   
            case "/moves/Soldier":
                return playSoldier((PlaySoldierParam)JsonUtils.convertFromJson(
                        PlaySoldierParam.class, (String)Json));   
            case "/moves/Monopoly":
                return playMonopoly((PlayMonopolyParam)JsonUtils.convertFromJson(
                        PlayMonopolyParam.class, (String)Json));   
            case "/moves/Monument":
                return playMonument((PlayMonumentParam)JsonUtils.convertFromJson(
                        PlayMonumentParam.class, (String)Json));   
            case "/moves/buildRoad":
                return buildRoad((BuildRoadParam)JsonUtils.convertFromJson(
                        BuildRoadParam.class, (String)Json));   
            case "/moves/buildSettlement":
                return buildSettlement((BuildSettlementParam)JsonUtils.convertFromJson(
                        BuildSettlementParam.class, (String)Json));   
            case "/moves/buildCity":
                return buildCity((BuildCityParam)JsonUtils.convertFromJson(
                        BuildCityParam.class, (String)Json));   
            case "/moves/offerTrade":
                return offerTrade((OfferTradeParam)JsonUtils.convertFromJson(
                        OfferTradeParam.class, (String)Json));   
            case "/moves/acceptTrade":
                return acceptTradeOffer((AcceptTradeParam)JsonUtils.convertFromJson(
                        AcceptTradeParam.class, (String)Json));   
            case "/moves/maritimeTrade":
                return maritimeTrade((MaritimeTradeParam)JsonUtils.convertFromJson(
                        MaritimeTradeParam.class, (String)Json));   
            case "/moves/discardCards":
                return discardCards((DiscardCardsParam)JsonUtils.convertFromJson(
                        DiscardCardsParam.class, (String)Json));   
            default:
                return null;
        }
    }
    
    /**
     * Sends Chat Message to Other users
     * 
     * @param param chat message
     * @return success or failure
     */
    public ServerResponse sendChat(SendChatParam param) {
        ServerResponse response = null;
        
        GameModel game = controller.getGameModel();
        Player player = game.getPlayers()[param.getPlayerIndex()];
        game.getGameHistory().getChatlog().
                addChatLine(new ParsedChat(player.getUsername(), param.getContent()));
        
        return response;
    }
    
    /**
     * Applies Roll to GameModel
     * 
     * @param param roll info
     * @return success or failure
     */
    public ServerResponse rollNumber(RollNumberParam param) {        
        List<HexTile> hexes = controller.getGameModel().getBoard().getHexes();
    	ArrayList<PlayerReceivingResources> resourceChanges = new ArrayList<>();
    	
        for(HexTile tile : hexes) {
            if(tile.getType() != HexType.DESERT && tile.getType() != HexType.WATER && !tile.getHasRobber()) {
                ResourceTile resourceTile = (ResourceTile)tile;
                
                if(!resourceTile.getToken().hitTest(param.getNumber()))
                    continue;
                
                for(Corner corner : resourceTile.getCorners()) {
                    if(corner.hasStructure()) {
                        BoardPiece boardPiece = corner.getStructure();
                        PlayerReceivingResources receiving = getPlayerResources(boardPiece, resourceTile);
                        resourceChanges.add(receiving);
                    }
                }                
            }
    	}

    	for(PlayerReceivingResources changes : resourceChanges) {
    		Player player = changes.getPlayer();
    		ResourceType type = changes.getResourceType();
    		int amount = changes.getAmount();
    		
                CardOwner.changeOwnerResource(player, controller.getGameModel().getBank(), type, amount);
    	}
        
        
        return new ServerResponse(200, "Success");
    }
    
    public PlayerReceivingResources getPlayerResources(BoardPiece boardPiece, ResourceTile resourceTile) {
    	int amount;
        
        if(boardPiece.getPieceType() == PieceType.CITY) {
                amount = 2;
        } else {
                amount = 1;
        }
        
        return new PlayerReceivingResources(boardPiece.getOwner(), resourceTile.getResourceType(), amount);
    }
    
    /**
     * Applies End of Turn to GameModel
     * 
     * @param parm end of turn info
     * @return success or failure
     */
    public ServerResponse finishTurn(FinishTurnParam parm) {
        ServerResponse response = null;
        
        
        
        return response;
    }
    
    /**
     * Applies Rob to GameModel
     * 
     * @param param rob info
     * @return success or failure
     * @post resource change from one player to another
     */
    public ServerResponse robPlayer(RobPlayerParam param) {
        GameModel game = controller.getGameModel();
        game.getBoard().getRobber().updateLocation(param.getLocation());
        Player robber = game.getPlayers()[param.getPlayerIndex()];
        Player victim = game.getPlayers()[param.getVictimIndex()];
        
        if(victim.getHandSize() > 0) {
            int index = (int)(Math.random() * victim.getHandSize());
            ResourceCard card = (ResourceCard)victim.getResourceCards().toArray()[index];
            robber.addResourceCard(victim.giveResourceCard(card.getResourceType()));
        }
        
        return new ServerResponse(200, "Success");
    }
    
    /**
     * Applies Purchase of DevCard to GameModel
     * 
     * @pre player can buy DevCard
     * @param param purchase info
     * @return success or failure
     * @post player has another DevCard
     */
    public ServerResponse buyDevCard(BuyDevCardParam param) {
        GameModel game = controller.getGameModel();
        Player player = game.getPlayers()[param.getPlayerIndex()];
        
        game.getBank().addResourceCard(player.giveResourceCard(ResourceType.WHEAT));
        game.getBank().addResourceCard(player.giveResourceCard(ResourceType.SHEEP));
        game.getBank().addResourceCard(player.giveResourceCard(ResourceType.ORE));
        
        player.addDevelopmentCard(game.getBank().giveDevelopmentCard(null));
        
        return new ServerResponse(200, "Success");
    }
    
    /**
     * Applies Play of Monopoly DevCard to GameModel
     * 
     * @pre player has monopoly card
     * @param param play card info
     * @return success or failure
     * @post player has one less monopoly card
     */
    public ServerResponse playMonopoly(PlayMonopolyParam param) {
        GameModel game = controller.getGameModel();
        Player player = game.getPlayers()[param.getPlayerIndex()];        
        player.giveDevelopmentCard(DevCardType.MONOPOLY);
        ResourceType resource = ResourceTypeUtils.getResourceType(param.getResource());
        
        for(Player person : game.getPlayers()) {
            while(!person.equals(player) && person.hasResource(resource))
                player.addResourceCard(person.giveResourceCard(resource));
        }
        
        return new ServerResponse(200, "Success");
    }
    
    /**
     * Applies Play of RoadBuilding DevCard to GameModel
     * 
     * @pre player has two roads available
     * @param param play card info
     * @return success or failure
     * @post player has two more roads built
     */
    public ServerResponse playRoadBuilding(PlayRoadBuildingParam param) {       
        Player player = controller.getGameModel().getPlayers()[param.getPlayerIndex()];
        player.giveDevelopmentCard(DevCardType.ROAD_BUILD);
        
        // BUILD!!
        
        return new ServerResponse(200, "Success");
    }
    
    /**
     * Applies Play of Soldier DevCard to GameModel
     * 
     * @param param play card info
     * @return success or failure
     * @post robber is moved
     */
    public ServerResponse playSoldier(PlaySoldierParam param) {
        Player player = controller.getGameModel().getPlayers()[param.getPlayerIndex()];
        player.giveDevelopmentCard(DevCardType.SOLDIER);
        player.setSoldiersPlayed(player.getSoldiersPlayed()+1);
        
        RobPlayerParam rob = new RobPlayerParam(param.getType(),param.getPlayerIndex(),
                                            param.getVictimIndex(), param.getLocation());        
        
        return robPlayer(rob);
    }
    
    /**
     * Applies Play of YearOfPlenty DevCard to GameModel
     * 
     * @param param play card info
     * @return success or failure
     * @post player has two more resource cards
     */
    public ServerResponse playYearOfPlenty(PlayYearOfPlentyParam param) {
        Player player = controller.getGameModel().getPlayers()[param.getPlayerIndex()];
        player.giveDevelopmentCard(DevCardType.YEAR_OF_PLENTY);
        
        player.addResourceCard(controller.getGameModel().getBank().giveResourceCard(
                ResourceTypeUtils.getResourceType(param.getResource1())));
        player.addResourceCard(controller.getGameModel().getBank().giveResourceCard(
                ResourceTypeUtils.getResourceType(param.getResource2())));
        
        return new ServerResponse(200, "Success");
    }
    
    /**
     * Applies Play of Monument DevCard to GameModel
     * 
     * @param param play card info
     * @return success or failure
     * @post player has one more point
     */
    public ServerResponse playMonument(PlayMonumentParam param) {
        Player player = controller.getGameModel().getPlayers()[param.getPlayerIndex()];
        player.giveDevelopmentCard(DevCardType.MONUMENT);
        player.addPoint();
        
        return new ServerResponse(200, "Success");
    }
    
    /**
     * Applies Road built to GameModel
     * 
     * @pre player has available road and is connected (if not setup)
     * @param parm board piece info
     * @return success or failure
     */
    public ServerResponse buildRoad(BuildRoadParam parm) {
        ServerResponse response = null;
        
        
        
        return response;
    }
    
    /**
     * Applies Settlement built to GameModel
     * 
     * @pre player has available settlement and is being build in correct place
     * @param parm board piece info
     * @return success or failure
     */
    public ServerResponse buildSettlement(BuildSettlementParam parm) {
        ServerResponse response = null;
        
        parm.getVertexLocation().getDirection();
        
        return response;
    }
    
    /**
     * Applies City built to GameModel
     * 
     * @pre player has available city and city is to be built on settlement
     * @param parm board piece info
     * @return success or failure
     * @post player has one less available city and one more settlement
     */
    public ServerResponse buildCity(BuildCityParam parm) {
        ServerResponse response = null;
        
        
        
        return response;
    }
    
    /**
     * Sends Trade Offer
     * 
     * @pre player has resources to trade
     * @param param trade info
     * @return success or failure
     */
    public ServerResponse offerTrade(OfferTradeParam param) {
        GameModel game = controller.getGameModel();
        
        OfferParam offer = param.getOffer();
        TradeOffer trade = new TradeOffer();
        
        trade.setBrick(offer.getBrick());
        trade.setOre(offer.getOre());
        trade.setSheep(offer.getSheep());
        trade.setWheat(offer.getWheat());
        trade.setWood(offer.getWood());
        trade.setReceiverIndex(param.getReceiver());
        trade.setSenderIndex(param.getPlayerIndex());
        
        game.setTradeOffer(null);
        
        return new ServerResponse(200, "Success");
    }
    
    /**
     * Accepts Trade Offer
     * 
     * @pre players have resources to trade
     * @param param trade info
     * @return success or failure
     * @post resources are traded
     */
    public ServerResponse acceptTradeOffer(AcceptTradeParam param) {
        if(param.isWillAccept()) {            
            GameModel game = controller.getGameModel();
            TradeOffer offer = game.getTradeOffer();
            Player sender = game.getPlayers()[offer.getSenderIndex()];
            Player receiver = game.getPlayers()[offer.getReceiverIndex()];
            
            // TONS of IF statements because Cory won't let me use a MAP object!
            
            if(offer.getBrick() > 0) {
                for(int i = 0; i < offer.getBrick(); i++)
                    receiver.addResourceCard(sender.giveResourceCard(ResourceType.BRICK));
            } else {
                for(int i = 0; i > offer.getBrick(); i--)
                    receiver.addResourceCard(sender.giveResourceCard(ResourceType.BRICK));
            }
            
            if(offer.getSheep()> 0) {
                for(int i = 0; i < offer.getSheep(); i++)
                    receiver.addResourceCard(sender.giveResourceCard(ResourceType.SHEEP));
            } else {
                for(int i = 0; i > offer.getSheep(); i--)
                    receiver.addResourceCard(sender.giveResourceCard(ResourceType.SHEEP));
            }
            
            if(offer.getWheat()> 0) {
                for(int i = 0; i < offer.getWheat(); i++)
                    receiver.addResourceCard(sender.giveResourceCard(ResourceType.WHEAT));
            } else {
                for(int i = 0; i > offer.getWheat(); i--)
                    receiver.addResourceCard(sender.giveResourceCard(ResourceType.WHEAT));
            }
            
            if(offer.getWood() > 0) {
                for(int i = 0; i < offer.getWood(); i++)
                    receiver.addResourceCard(sender.giveResourceCard(ResourceType.WOOD));
            } else {
                for(int i = 0; i > offer.getWood(); i--)
                    receiver.addResourceCard(sender.giveResourceCard(ResourceType.WOOD));
            }
            
            if(offer.getOre()> 0) {
                for(int i = 0; i < offer.getOre(); i++)
                    receiver.addResourceCard(sender.giveResourceCard(ResourceType.ORE));
            } else {
                for(int i = 0; i > offer.getOre(); i--)
                    receiver.addResourceCard(sender.giveResourceCard(ResourceType.ORE));
            }
            
            return new ServerResponse(200, "Accepted");
        } else {
            return new ServerResponse(200, "Not Accepted");
        }
    }
    
    /**
     * Maritime Trade applied to Player/Bank
     * 
     * @pre player and bank have enough resources of each
     * @param param trade info
     * @return success or failure
     * @post player and bank have new resources
     */
    public ServerResponse maritimeTrade(MaritimeTradeParam param) {
        GameModel game = controller.getGameModel();
        
        Player player = game.getPlayers()[param.getPlayerIndex()];
        ResourceType give = ResourceTypeUtils.getResourceType(param.getInputResource());
        ResourceType receive = ResourceTypeUtils.getResourceType(param.getOutputResource());
        
        player.addResourceCard(game.getBank().giveResourceCard(receive));
        
        for(int i = 0; i < param.getRatio(); i++)
            game.getBank().addResourceCard(player.giveResourceCard(give));
        
        return new ServerResponse(200, "Success");
    }
    
    /**
     * Discard Card applied to GameModel
     * 
     * @pre player has cards that will be discarded
     * @param param discard info
     * @return success or failure
     * @post player has less cards
     */
    public ServerResponse discardCards(DiscardCardsParam param) {
        ServerResponse response = null;
        
        GameModel game = controller.getGameModel();
        if(game != null) {
            Player player = game.getPlayers()[param.getPlayerIndex()];
            
            for(int i=0;i<param.getDiscardedCards().getBrick();i++)
                game.getBank().addResourceCard(player.giveResourceCard(ResourceType.BRICK));
            for(int i=0;i<param.getDiscardedCards().getOre();i++)
                game.getBank().addResourceCard(player.giveResourceCard(ResourceType.ORE));
            for(int i=0;i<param.getDiscardedCards().getSheep();i++)
                game.getBank().addResourceCard(player.giveResourceCard(ResourceType.SHEEP));
            for(int i=0;i<param.getDiscardedCards().getWheat();i++)
                game.getBank().addResourceCard(player.giveResourceCard(ResourceType.WHEAT));
            for(int i=0;i<param.getDiscardedCards().getWood();i++)
                game.getBank().addResourceCard(player.giveResourceCard(ResourceType.WOOD));
                                                
            response = new ServerResponse(200, "Success");
            
        } else {
            response = new ServerResponse(400, "No game of that type");
        }
        
        return response;
    }
}
