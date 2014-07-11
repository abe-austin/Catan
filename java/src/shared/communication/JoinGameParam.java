package shared.communication;


public class JoinGameParam {
	
	private int id;
	private String color;
	
	public JoinGameParam(int id, String color) {
		super();
		this.id = id;
		this.color = color;
	}
	
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
