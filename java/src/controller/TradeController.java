/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import client.data.PlayerInfo;
import game.GameModel;
import game.cards.ResourceCard;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import player.Player;
import shared.definitions.CatanColor;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import system.*;

/**
 *
 * @author Cory
 */
class TradeController {
    private GameModel gameModel;
    private Player player;
    
    public TradeController(Player player){
        this.player=player;
    }
    
     public void switchGameModel(GameModel gameModel){
        this.gameModel = gameModel;
    }
    /**
	 * Called by the domestic trade view when the user clicks the domestic trade button.
	 */
	public ArrayList<ResourceType> domesticStartTrade(){//DomesticTradeController --goes in Trade
             player.addResourceCard(new ResourceCard(ResourceType.BRICK));
           player.addResourceCard(new ResourceCard(ResourceType.BRICK));
           player.addResourceCard(new ResourceCard(ResourceType.BRICK));           
           player.addResourceCard(new ResourceCard(ResourceType.BRICK));
           player.addResourceCard(new ResourceCard(ResourceType.WOOD));
           player.addResourceCard(new ResourceCard(ResourceType.WOOD));
           player.addResourceCard(new ResourceCard(ResourceType.WOOD));
           player.addResourceCard(new ResourceCard(ResourceType.WOOD));
           player.addResourceCard(new ResourceCard(ResourceType.SHEEP));
           player.addResourceCard(new ResourceCard(ResourceType.SHEEP));
           player.addResourceCard(new ResourceCard(ResourceType.SHEEP));
           player.addResourceCard(new ResourceCard(ResourceType.ORE));
           player.addResourceCard(new ResourceCard(ResourceType.ORE));
           player.addResourceCard(new ResourceCard(ResourceType.WHEAT));
           gameModel.addPlayers(player, 0);
           gameModel.addPlayers(new Player(CatanColor.BLUE,new User(new Username("Ralphie"),new Password("password"),2),2), 1);
gameModel.addPlayers(new Player(CatanColor.GREEN,new User(new Username("josh"),new Password("passwor"),3),3), 2);
gameModel.addPlayers(new Player(CatanColor.PURPLE,new User(new Username("randall"),new Password("passwo"),4),4), 3);            

//System.out.println("added players");
           return getPlayerResourceTypes();
        }
	
        public PlayerInfo[] getPlayerInfo(){
            Player[] players = gameModel.getPlayers();
            PlayerInfo[] playersInfo= new PlayerInfo[3];
            int i = 0;
            for(Player player:players){
                if(player==null)System.out.println("player null");
                if(this.player==null)System.out.println("this.player null");
                if (player!=this.player){
                    PlayerInfo playerInfo= new PlayerInfo();
                    playerInfo.setColor(player.getColor());
                    playerInfo.setName(player.getUser().getUsername().getUsername());
                    playerInfo.setPlayerIndex(player.getIndex());
                    playersInfo[i] = playerInfo;
                    i++;
                }
            }
            return playersInfo;
        }
      
        /**
	 * Called by the maritime trade view when the user clicks the maritime trade button.
	 */
	public ArrayList<ArrayList<ResourceType>> maritimeStartTrade(){//MaritimeTradeController --goes in Trade
           player.addResourceCard(new ResourceCard(ResourceType.BRICK));
           player.addResourceCard(new ResourceCard(ResourceType.BRICK));
           player.addResourceCard(new ResourceCard(ResourceType.BRICK));           
           player.addResourceCard(new ResourceCard(ResourceType.BRICK));
           player.addResourceCard(new ResourceCard(ResourceType.WOOD));
           player.addResourceCard(new ResourceCard(ResourceType.WOOD));
           player.addResourceCard(new ResourceCard(ResourceType.WOOD));
           player.addResourceCard(new ResourceCard(ResourceType.WOOD));
           player.addResourceCard(new ResourceCard(ResourceType.SHEEP));
           player.addResourceCard(new ResourceCard(ResourceType.SHEEP));
           player.addResourceCard(new ResourceCard(ResourceType.SHEEP));
           player.addResourceCard(new ResourceCard(ResourceType.ORE));
           player.addResourceCard(new ResourceCard(ResourceType.ORE));
           player.addResourceCard(new ResourceCard(ResourceType.WHEAT));
           player.addPort(PortType.ORE);
           //player.addPort(PortType.THREE);
           
            ArrayList<ResourceType> playerResourceTypes =maritimeGetPlayerResourceTypes();
            ArrayList<ResourceType> bankResourceTypes=getBankResourceTypes();
            ArrayList< ArrayList<ResourceType>> resources=new ArrayList<>();
            resources.add(playerResourceTypes);
            resources.add(bankResourceTypes);
            return resources;      
        }
	
        public int setGetResource(ResourceType resource){
            int number= 4;
            ArrayList<PortType> ports=player.getPorts();
            if(ports.contains(PortType.THREE)){
                number=3;
            }

            if( resource == ResourceType.BRICK){
                if(ports.contains(PortType.BRICK))
                    number=2;
            }
            else if( resource == ResourceType.ORE){
                if(ports.contains(PortType.ORE))
                    number=2;
            }
            else if( resource == ResourceType.SHEEP){
                if(ports.contains(PortType.SHEEP))
                    number=2;
            }
            else if( resource == ResourceType.WHEAT){
                if(ports.contains(PortType.WHEAT))
                    number=2;
            }
            else if( resource == ResourceType.WOOD){
                if(ports.contains(PortType.WOOD))
                    number=2;
            }
            return number;
        }
        
        public ArrayList<ResourceType> getBankResourceTypes(){
            ArrayList<ResourceType> resources = new ArrayList<>();
            if(gameModel==null)System.out.println("gameModel null");
            else if ( gameModel.getBank()== null)System.out.println("bank null");
            if ( gameModel.getBank().hasResource(ResourceType.WHEAT)){
                resources.add(ResourceType.WHEAT);
            }
            if ( gameModel.getBank().hasResource(ResourceType.BRICK)){
                resources.add(ResourceType.BRICK);
            }
            if ( gameModel.getBank().hasResource(ResourceType.ORE)){
                resources.add(ResourceType.ORE);
            }
            if ( gameModel.getBank().hasResource(ResourceType.SHEEP)){
                resources.add(ResourceType.SHEEP);
            }
            if ( gameModel.getBank().hasResource(ResourceType.WOOD)){
                resources.add(ResourceType.WOOD);
            }
            return resources;
        }
        
        public ArrayList<ResourceType> getPlayerResourceTypes(){
            
            //need to figure out if player has ports and what type first
            
            ArrayList<ResourceType> resources = new ArrayList<>();
            if ( player.hasResource(ResourceType.WHEAT)){
                resources.add(ResourceType.WHEAT);
            }
            if ( player.hasResource(ResourceType.BRICK)){
                resources.add(ResourceType.BRICK);
            }
            if ( player.hasResource(ResourceType.ORE)){
                resources.add(ResourceType.ORE);
            }
            if ( player.hasResource(ResourceType.SHEEP)){
                resources.add(ResourceType.SHEEP);
            }
            if ( player.hasResource(ResourceType.WOOD)){
                resources.add(ResourceType.WOOD);
            }
            return resources;
        }
        
        public Map<ResourceType,Integer> getPlayerResources(){
            Map<ResourceType,Integer> resources = new HashMap<ResourceType,Integer>();
            int brickCount=player.hasResourceNumber(ResourceType.BRICK);
            resources.put(ResourceType.BRICK, brickCount);
            int oreCount=player.hasResourceNumber(ResourceType.ORE);
            resources.put(ResourceType.ORE, oreCount);
            int sheepCount=player.hasResourceNumber(ResourceType.SHEEP);
            resources.put(ResourceType.SHEEP, sheepCount);
            int woodCount=player.hasResourceNumber(ResourceType.WOOD);
            resources.put(ResourceType.WOOD, woodCount);
            int wheatCount=player.hasResourceNumber(ResourceType.WHEAT);
            resources.put(ResourceType.WHEAT, wheatCount);

            return resources;
        }
                
        public ArrayList<ResourceType> maritimeGetPlayerResourceTypes(){
            
            ArrayList<PortType> ports=player.getPorts();
            int brickCount=player.hasResourceNumber(ResourceType.BRICK);
            int oreCount=player.hasResourceNumber(ResourceType.ORE);
            int sheepCount=player.hasResourceNumber(ResourceType.SHEEP);
            int woodCount=player.hasResourceNumber(ResourceType.WOOD);
            int wheatCount=player.hasResourceNumber(ResourceType.WHEAT);
            ArrayList<ResourceType> resources = new ArrayList<>();

            for( PortType portType: ports){
                switch(portType){
                    case WHEAT:
                        if ( wheatCount>=2){
                             addResourceToList(resources,ResourceType.WHEAT);
                        }
                        break;
                    case BRICK:
                        if ( brickCount>=2){
                             addResourceToList(resources,ResourceType.BRICK);
                        }
                        break;
                    case ORE:
                        if ( oreCount>=2){
                             addResourceToList(resources,ResourceType.ORE);
                        }
                        break;
                    case SHEEP:
                        if ( sheepCount>=2){
                             addResourceToList(resources,ResourceType.SHEEP);
                        }
                        break;
                    case WOOD:
                        if ( woodCount>=2){
                             addResourceToList(resources,ResourceType.WOOD);
                        }
                        break;
                    case THREE:
                        if ( wheatCount>=3){
                             addResourceToList(resources,ResourceType.WHEAT);
                        }
                        if ( brickCount>=3){
                             addResourceToList(resources,ResourceType.BRICK);
                        }
                        if ( oreCount>=3){
                             addResourceToList(resources,ResourceType.ORE);
                        }
                        if ( sheepCount>=3){
                             addResourceToList(resources,ResourceType.SHEEP);
                        }
                        if ( woodCount>=3){
                             addResourceToList(resources,ResourceType.WOOD);
                        }
                        break;
                }
            }
            if ( wheatCount>=4){
                 addResourceToList(resources,ResourceType.WHEAT);
            }
            if ( brickCount>=4){
                 addResourceToList(resources,ResourceType.BRICK);
            }
            if ( oreCount>=4){
                 addResourceToList(resources,ResourceType.ORE);
            }
            if ( sheepCount>=4){
                 addResourceToList(resources,ResourceType.SHEEP);
            }
            if ( woodCount>=4){
                 addResourceToList(resources,ResourceType.WOOD);
            }
            return resources;
        }
        
        private void addResourceToList(ArrayList<ResourceType> resources, ResourceType type){
            if(!resources.contains(type)){
                resources.add(type);
            }
        }
}
