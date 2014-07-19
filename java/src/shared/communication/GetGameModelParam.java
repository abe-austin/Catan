package shared.communication;

public class GetGameModelParam {
	int version;

	public GetGameModelParam(int version) {
		super();
		this.version = version;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
