/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client.parse;

/**
 *
 * @author Cory
 */
public class ParsedTradeOffer {
     private int senderIndex;
    private int receiverIndex;
    private int brick;
    private int ore;
    private int sheep;
    private int wood;
    private int wheat;
    //negative numbers are being asked for
    //positive numbers are being offered
    
    public ParsedTradeOffer(){}
    
    public ParsedTradeOffer(int senderIndex, int receiverIndex, int brick, int ore, int sheep, int wood, int wheat) {
        this.senderIndex = senderIndex;
        this.receiverIndex = receiverIndex;
        this.brick = brick;
        this.ore = ore;
        this.sheep = sheep;
        this.wood = wood;
        this.wheat = wheat;
    }

    public int getSenderIndex() {
        return senderIndex;
    }

    public void setSenderIndex(int senderIndex) {
        this.senderIndex = senderIndex;
    }

    public int getReceiverIndex() {
        return receiverIndex;
    }

    public void setReceiverIndex(int receiverIndex) {
        this.receiverIndex = receiverIndex;
    }

    public int getBrick() {
        return brick;
    }

    public void setBrick(int brick) {
        this.brick = brick;
    }

    public int getOre() {
        return ore;
    }

    public void setOre(int ore) {
        this.ore = ore;
    }

    public int getSheep() {
        return sheep;
    }

    public void setSheep(int sheep) {
        this.sheep = sheep;
    }

    public int getWood() {
        return wood;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public int getWheat() {
        return wheat;
    }

    public void setWheat(int wheat) {
        this.wheat = wheat;
    }
    
}
