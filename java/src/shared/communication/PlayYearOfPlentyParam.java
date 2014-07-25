package shared.communication;

import shared.definitions.ResourceType;

public class PlayYearOfPlentyParam {

	private String type;
	private int playerIndex;
	private String resource1;
	private String resource2;
	
	public PlayYearOfPlentyParam(String type, int playerIndex, ResourceType resource1,
			ResourceType resource2) {
		super();
		this.type = type;
		this.playerIndex = playerIndex;
		
                if(resource1==ResourceType.BRICK){
                     this.resource1="brick";
                }
                else if(resource1==ResourceType.ORE){
                    this.resource1="ore";
                }
                else if(resource1==ResourceType.SHEEP){
                    this.resource1="sheep";
                }
                else if(resource1==ResourceType.WHEAT){
                    this.resource1="wheat";
                }
                else if(resource1==ResourceType.WOOD){
                    this.resource1="wood";
                }
		
                if(resource2==ResourceType.BRICK){
                     this.resource2="brick";
                }
                else if(resource2==ResourceType.ORE){
                    this.resource2="ore";
                }
                else if(resource2==ResourceType.SHEEP){
                    this.resource2="sheep";
                }
                else if(resource2==ResourceType.WHEAT){
                    this.resource2="wheat";
                }
                else if(resource2==ResourceType.WOOD){
                    this.resource2="wood";
                }
        }	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public String getResource1() {
		return resource1;
	}

	public void setResource1(String resource1) {
		this.resource1 = resource1;
	}

	public String getResource2() {
		return resource2;
	}

	public void setResource2(String resource2) {
		this.resource2 = resource2;
	}
	
	
}
