package shared.communication;

public class ServerResponse {

	private int code;
	private Object body;
	
	public ServerResponse(int code, Object body) {
		super();
		this.code = code;
		this.body = body;
	}

	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public Object getBody() {
		return body;
	}
	
	public void setBody(Object body) {
		this.body = body;
	}
	
	
}
