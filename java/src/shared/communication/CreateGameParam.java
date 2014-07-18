package shared.communication;

public class CreateGameParam {
	
	private String name;

	public CreateGameParam(String name, boolean randomHexes,
			boolean randomNumbers, boolean randomPorts) {
		super();
		
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
