package controller;

import shared.definitions.ResourceType;
import player.Player;


public class PlayerReceivingResources {
	
	Player player;
	ResourceType resourceType;
	int amount;
	
	
	public PlayerReceivingResources(Player player, ResourceType resourceType, int amount) {
		this.player = player;
		this.resourceType = resourceType;
		this.amount = amount;
	}

	public Player getPlayer() {
		return player;
	}
	
	public ResourceType getResourceType() {
		return resourceType;
	}
	
	public int getAmount() {
		return amount;
	}

}