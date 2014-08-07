package shared.communication;

public class CreateGameParam {
	
	private String name;
    private boolean randomHexes;
    private boolean randomNumbers;
    private boolean randomPorts;

	public CreateGameParam(String name, boolean randomHexes,
			boolean randomNumbers, boolean randomPorts) {
		super();
		
		this.name = name;
        this.randomHexes = randomHexes;
        this.randomNumbers = randomNumbers;
        this.randomPorts = randomPorts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    /**
     * @return the randomHexes
     */
    public boolean isRandomHexes() {
        return randomHexes;
    }

    /**
     * @param randomHexes the randomHexes to set
     */
    public void setRandomHexes(boolean randomHexes) {
        this.randomHexes = randomHexes;
    }

    /**
     * @return the randomNumbers
     */
    public boolean isRandomNumbers() {
        return randomNumbers;
    }

    /**
     * @param randomNumbers the randomNumbers to set
     */
    public void setRandomNumbers(boolean randomNumbers) {
        this.randomNumbers = randomNumbers;
    }

    /**
     * @return the randomPorts
     */
    public boolean isRandomPorts() {
        return randomPorts;
    }

    /**
     * @param randomPorts the randomPorts to set
     */
    public void setRandomPorts(boolean randomPorts) {
        this.randomPorts = randomPorts;
    }

}
