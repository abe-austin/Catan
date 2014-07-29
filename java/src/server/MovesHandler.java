package server;

import shared.communication.*;

/**
 *
 * @author Kevin MacMaster
 */
public class MovesHandler implements IHandler {

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
     * @param parm chat message
     * @return success or failure
     */
    public ServerResponse sendChat(SendChatParam parm) {
        ServerResponse response = null;
        
        
        
        return response;
    }
    
    /**
     * Applies Roll to GameModel
     * 
     * @param parm roll info
     * @return success or failure
     */
    public ServerResponse rollNumber(RollNumberParam parm) {
        ServerResponse response = null;
        
        
        
        return response;
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
     * @param parm rob info
     * @return success or failure
     */
    public ServerResponse robPlayer(RobPlayerParam parm) {
        ServerResponse response = null;
        
        
        
        return response;
    }
    
    /**
     * Applies Purchase of DevCard to GameModel
     * 
     * @param parm purchase info
     * @return success or failure
     */
    public ServerResponse buyDevCard(BuyDevCardParam parm) {
        ServerResponse response = null;
        
        
        
        return response;
    }
    
    /**
     * Applies Play of Monopoly DevCard to GameModel
     * 
     * @param parm play card info
     * @return success or failure
     */
    public ServerResponse playMonopoly(PlayMonopolyParam parm) {
        ServerResponse response = null;
        
        
        
        return response;
    }
    
    /**
     * Applies Play of RoadBuilding DevCard to GameModel
     * 
     * @param parm play card info
     * @return success or failure
     */
    public ServerResponse playRoadBuilding(PlayRoadBuildingParam parm) {
        ServerResponse response = null;
        
        
        
        return response;
    }
    
    /**
     * Applies Play of Soldier DevCard to GameModel
     * 
     * @param parm play card info
     * @return success or failure
     */
    public ServerResponse playSoldier(PlaySoldierParam parm) {
        ServerResponse response = null;
        
        
        
        return response;
    }
    
    /**
     * Applies Play of YearOfPlenty DevCard to GameModel
     * 
     * @param parm play card info
     * @return success or failure
     */
    public ServerResponse playYearOfPlenty(PlayYearOfPlentyParam parm) {
        ServerResponse response = null;
        
        
        
        return response;
    }
    
    /**
     * Applies Play of Monument DevCard to GameModel
     * 
     * @param parm play card info
     * @return success or failure
     */
    public ServerResponse playMonument(PlayMonumentParam parm) {
        ServerResponse response = null;
        
        
        
        return response;
    }
    
    /**
     * Applies Road built to GameModel
     * 
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
     * @param parm board piece info
     * @return success or failure
     */
    public ServerResponse buildSettlement(BuildSettlementParam parm) {
        ServerResponse response = null;
        
        
        
        return response;
    }
    
    /**
     * Applies City built to GameModel
     * 
     * @param parm board piece info
     * @return success or failure
     */
    public ServerResponse buildCity(BuildCityParam parm) {
        ServerResponse response = null;
        
        
        
        return response;
    }
    
    /**
     * Sends Trade Offer
     * 
     * @param parm trade info
     * @return success or failure
     */
    public ServerResponse offerTrade(OfferTradeParam parm) {
        ServerResponse response = null;
        
        
        
        return response;
    }
    
    /**
     * Accepts Trade Offer
     * 
     * @param parm trade info
     * @return success or failure
     */
    public ServerResponse acceptTradeOffer(AcceptTradeParam parm) {
        ServerResponse response = null;
        
        
        
        return response;
    }
    
    /**
     * Maritime Trade applied to Player/Bank
     * 
     * @param parm trade info
     * @return success or failure
     */
    public ServerResponse maritimeTrade(MaritimeTradeParam parm) {
        ServerResponse response = null;
        
        
        
        return response;
    }
    
    /**
     * Discard Card applied to GameModel
     * 
     * @param parm discard info
     * @return success or failure
     */
    public ServerResponse discardCards(DiscardCardsParam parm) {
        ServerResponse response = null;
        
        
        
        return response;
    }
}
