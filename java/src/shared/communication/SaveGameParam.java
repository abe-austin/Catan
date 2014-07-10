package shared.communication;

public class SaveGameParam {

	int id;
	String name;
	
	public SaveGameParam(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getID() {
		return id;
	}
	
	public void setID(int gameID) {
		this.id = gameID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setGameName(String name) {
		this.name = name;
	}
	
	
}
