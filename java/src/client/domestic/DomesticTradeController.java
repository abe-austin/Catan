package client.domestic;

import client.base.*;
import client.data.PlayerInfo;
import client.misc.*;
import controller.ControllerFacade;
import controller.IControllerFacadeListener;
import game.GameModel;
import game.TradeOffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import shared.definitions.*;


/**
 * Domestic trade controller implementation
 */
public class DomesticTradeController extends Controller implements IDomesticTradeController, IControllerFacadeListener {

	private IDomesticTradeOverlay tradeOverlay;
	private IWaitView waitOverlay;
	private IAcceptTradeOverlay acceptOverlay;
	private int getValue;
	private int giveValue;
	private ArrayList<ResourceType> givingResource;
	private ArrayList<ResourceType> gettingResource;
	private ControllerFacade controllerFacade= ControllerFacade.getSingleton();
        private Map<ResourceType,Integer> playerResources;
        private Map<ResourceType,Integer> resourceTradeAmount;
        private int tradePartnerIndex;

	/**
	 * DomesticTradeController constructor
	 * 
	 * @param tradeView Domestic trade view (i.e., view that contains the "Domestic Trade" button)
	 * @param tradeOverlay Domestic trade overlay (i.e., view that lets the user propose a domestic trade)
	 * @param waitOverlay Wait overlay used to notify the user they are waiting for another player to accept a trade
	 * @param acceptOverlay Accept trade overlay which lets the user accept or reject a proposed trade
	 */
	public DomesticTradeController(IDomesticTradeView tradeView, IDomesticTradeOverlay tradeOverlay,
									IWaitView waitOverlay, IAcceptTradeOverlay acceptOverlay) {

		super(tradeView);
		
		setTradeOverlay(tradeOverlay);
		setWaitOverlay(waitOverlay);
		setAcceptOverlay(acceptOverlay);
                controllerFacade.addListener(this);
                
                resourceTradeAmount= new HashMap<ResourceType,Integer>();
                resourceTradeAmount.put(ResourceType.BRICK, 0);
                resourceTradeAmount.put(ResourceType.ORE, 0);
                resourceTradeAmount.put(ResourceType.SHEEP, 0);
                resourceTradeAmount.put(ResourceType.WHEAT, 0);
                resourceTradeAmount.put(ResourceType.WOOD, 0);
	}
	
	@Override
        public void gameModelChanged(GameModel gameModel){
            if (controllerFacade.getGameState()==GameState.GamePlay){
                if(gameModel.getTradeOffer().getReceiverIndex()!= -1){//there is an offer
                    if(gameModel.getTradeOffer().getReceiverIndex()==controllerFacade.getClientPlayer().getIndex()){
                        //the offer is for me
                        TradeOffer offer= gameModel.getTradeOffer();
                        int senderIndex=offer.getSenderIndex();
                        int receiverIndex=offer.getReceiverIndex();
                        int brick=offer.getBrick();
                        int ore=offer.getOre();
                        int sheep=offer.getSheep();
                        int wood= offer.getWood();
                        int wheat=offer.getWheat();
                        getAcceptOverlay().setAcceptEnabled(true);

                        if(brick!=0){
                            if( brick<0){
                                getAcceptOverlay().addGiveResource(ResourceType.BRICK, brick*-1);
                                if(!gameModel.getPlayers()[receiverIndex].hasResource(ResourceType.BRICK, brick)){
                                    //doesn't have enough of this resource to trade
                                    getAcceptOverlay().setAcceptEnabled(false);
                                }
                            }
                            else{
                                getAcceptOverlay().addGetResource(ResourceType.BRICK, brick);
                            }
                        }
                        if(ore!=0){
                            if( ore<0){
                                getAcceptOverlay().addGiveResource(ResourceType.ORE, ore*-1);
                                if(!gameModel.getPlayers()[receiverIndex].hasResource(ResourceType.ORE, ore)){
                                    //doesn't have enough of this resource to trade
                                    getAcceptOverlay().setAcceptEnabled(false);
                                }
                            }
                            else{
                                getAcceptOverlay().addGetResource(ResourceType.ORE, ore);
                            }
                        }
                        if(sheep!=0){
                            if( sheep<0){
                                getAcceptOverlay().addGiveResource(ResourceType.SHEEP, sheep*-1);
                                if(!gameModel.getPlayers()[receiverIndex].hasResource(ResourceType.SHEEP, sheep)){
                                    //doesn't have enough of this resource to trade
                                    getAcceptOverlay().setAcceptEnabled(false);
                                }
                            }
                            else{
                                getAcceptOverlay().addGetResource(ResourceType.SHEEP, sheep);
                            }
                        }
                        if(wood!=0){
                            if(wood<0){
                                getAcceptOverlay().addGiveResource(ResourceType.WOOD, wood*-1);
                                if(!gameModel.getPlayers()[receiverIndex].hasResource(ResourceType.WOOD, wood)){
                                    //doesn't have enough of this resource to trade
                                    getAcceptOverlay().setAcceptEnabled(false);
                                }
                            }
                            else{
                                getAcceptOverlay().addGetResource(ResourceType.WOOD, wood);
                            }
                        }
                        if(wheat!=0){
                            if( wheat<0){
                                getAcceptOverlay().addGiveResource(ResourceType.WHEAT, wheat*-1);
                                if(!gameModel.getPlayers()[receiverIndex].hasResource(ResourceType.WHEAT, wheat)){
                                    //doesn't have enough of this resource to trade
                                    getAcceptOverlay().setAcceptEnabled(false);
                                }
                            }
                            else{
                                getAcceptOverlay().addGetResource(ResourceType.WHEAT, wheat);
                            }
                        }


                        getAcceptOverlay().showModal();
                    }
                }
            }
        }
         
	public IDomesticTradeView getTradeView() {
		
		return (IDomesticTradeView)super.getView();
	}

	public IDomesticTradeOverlay getTradeOverlay() {
		return tradeOverlay;
	}

	public void setTradeOverlay(IDomesticTradeOverlay tradeOverlay) {
		this.tradeOverlay = tradeOverlay;
	}

	public IWaitView getWaitOverlay() {
		return waitOverlay;
	}

	public void setWaitOverlay(IWaitView waitView) {
		this.waitOverlay = waitView;
	}

	public IAcceptTradeOverlay getAcceptOverlay() {
		return acceptOverlay;
	}

	public void setAcceptOverlay(IAcceptTradeOverlay acceptOverlay) {
		this.acceptOverlay = acceptOverlay;
	}

	@Override
	public void startTrade() {
            if (controllerFacade.isCurrentTurn()){//if( currentPlayers turn){
                 //set the enabled resources for the player
                    ArrayList<ResourceType> playerResourceType = controllerFacade.domesticStartTrade();
                    //if (cards==null)System.out.println("cards is empty");
                ResourceType[] playerResourceTypes= new ResourceType[playerResourceType.size()];
                for (int i=0; i < playerResourceType.size();i++){
                    playerResourceTypes[i]=playerResourceType.get(i);
                }
                getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.BRICK, true, false);
                getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.ORE, true, false);
                getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.SHEEP, true, false);
                getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WHEAT, true, false);
                getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WOOD, true, false);
                
                tradePartnerIndex=-1;
                giveValue=0;
                getValue=0;
                gettingResource=new ArrayList<ResourceType>();
                givingResource= new ArrayList<ResourceType>();
                playerResources= controllerFacade.getPlayerResources();
                PlayerInfo[] players= controllerFacade.getPlayerInfo();
                getTradeOverlay().setPlayers(players);
                getTradeOverlay().setPlayerSelectionEnabled(true);
                getTradeOverlay().setStateMessage("can't trade yet");
            }
            else{
                    getTradeOverlay().setStateMessage("not your turn");
                    getTradeOverlay().setPlayerSelectionEnabled(false);
                    getTradeOverlay().setResourceSelectionEnabled(false);
            }
            getTradeOverlay().setTradeEnabled(false);
            getTradeOverlay().setCancelEnabled(true);
	    getTradeOverlay().showModal();
	}

	@Override//So I assume each player does this only for their own resource, not the other player's
	public void decreaseResourceAmount(ResourceType resource) {
            int amount=resourceTradeAmount.get(resource);
            if (amount>1){
                resourceTradeAmount.put(resource,amount-1);
		getTradeOverlay().setResourceAmountChangeEnabled(resource, true, true);
                if(givingResource.contains(resource)){
                    giveValue--;
                }
                else{
                    getValue--;
                }
            }
            else if (amount==1){
                resourceTradeAmount.put(resource,amount-1);
                getTradeOverlay().setResourceAmountChangeEnabled(resource, true, false);
                if(givingResource.contains(resource)){
                    giveValue--;
                }
                else{
                    getValue--;
                }
            }
            
            if(giveValue>0 && getValue>0 && tradePartnerIndex!=-1){
                getTradeOverlay().setTradeEnabled(true);
                getTradeOverlay().setStateMessage("make trade offer");
            }
            else{
                getTradeOverlay().setTradeEnabled(false);
                getTradeOverlay().setStateMessage("can't trade yet");
            }
	}

	@Override
	public void increaseResourceAmount(ResourceType resource) {
            int amount=resourceTradeAmount.get(resource);
            if (amount<playerResources.get(resource)-1 || gettingResource.contains(resource)){
                resourceTradeAmount.put(resource,amount+1);
		getTradeOverlay().setResourceAmountChangeEnabled(resource, true, true);
                if(gettingResource.contains(resource)){
                    getValue++;
                }
                else{
                    giveValue++;
                }
            }
            else if (amount==playerResources.get(resource)-1){
                resourceTradeAmount.put(resource,amount+1);
                getTradeOverlay().setResourceAmountChangeEnabled(resource, false, true);
                if(gettingResource.contains(resource)){
                    getValue++;
                }
                else{
                    giveValue++;
                }
            }
            
            if(giveValue>0 && getValue>0 && tradePartnerIndex!=-1){
                getTradeOverlay().setTradeEnabled(true);
                getTradeOverlay().setStateMessage("make trade offer");
            }
            else{
                getTradeOverlay().setTradeEnabled(false);
                getTradeOverlay().setStateMessage("can't trade yet");
            }
	}

	@Override
	public void sendTradeOffer() {
            for(ResourceType resource:givingResource){
                resourceTradeAmount.put(resource,(resourceTradeAmount.get(resource)*-1));
            }
            controllerFacade.sendTradeOffer(resourceTradeAmount, tradePartnerIndex);

		getTradeOverlay().closeModal();
		getWaitOverlay().showModal();
	}

	@Override
	public void setPlayerToTradeWith(int playerIndex) {
            tradePartnerIndex=playerIndex;
            if(giveValue>0 && getValue>0 && tradePartnerIndex!=-1){
                getTradeOverlay().setTradeEnabled(true);
                getTradeOverlay().setStateMessage("make trade offer");
            }
            else{
                getTradeOverlay().setTradeEnabled(false);
                getTradeOverlay().setStateMessage("can't trade yet");
            }
	}

	@Override
	public void setResourceToReceive(ResourceType resource) {
		gettingResource.add(resource);
		boolean wasGive = givingResource.remove(resource);
            if(wasGive){
                giveValue = giveValue -resourceTradeAmount.get(resource);
                resourceTradeAmount.put(resource,0);
                getTradeOverlay().setResourceAmount(resource,"0");
                getTradeOverlay().setResourceAmountChangeEnabled(resource, true, false);
                if(giveValue==0 || getValue==0 || tradePartnerIndex==-1){
                    getTradeOverlay().setTradeEnabled(false);
                    getTradeOverlay().setStateMessage("can't trade yet");
                }
            }
        }

	@Override
	public void setResourceToSend(ResourceType resource) {
		givingResource.add(resource);
		boolean wasGet = gettingResource.remove(resource);
            if(wasGet){
                getValue = getValue -resourceTradeAmount.get(resource);
                resourceTradeAmount.put(resource,0);
                getTradeOverlay().setResourceAmount(resource,"0");
                getTradeOverlay().setResourceAmountChangeEnabled(resource, true, false);
                if(giveValue==0 || getValue==0 || tradePartnerIndex==-1){
                    getTradeOverlay().setTradeEnabled(false);
                    getTradeOverlay().setStateMessage("can't trade yet");
                }
            }
	}

	@Override
	public void unsetResource(ResourceType resource) {
            boolean wasGive = givingResource.remove(resource);
            gettingResource.remove(resource);
            if(wasGive){
                giveValue = giveValue -resourceTradeAmount.get(resource);
            }
            else{
                getValue = getValue - resourceTradeAmount.get(resource);
            }
            resourceTradeAmount.put(resource,0);
            getTradeOverlay().setResourceAmountChangeEnabled(resource, true, false);
            
            if(giveValue==0 || getValue==0 || tradePartnerIndex==-1){
                getTradeOverlay().setTradeEnabled(false);
                getTradeOverlay().setStateMessage("can't trade yet");
            }
	}

	@Override
	public void cancelTrade() {

		getTradeOverlay().closeModal();
	}

	@Override
	public void acceptTrade(boolean willAccept) {
            controllerFacade.acceptTrade(willAccept);
		getAcceptOverlay().closeModal();
	}

}

