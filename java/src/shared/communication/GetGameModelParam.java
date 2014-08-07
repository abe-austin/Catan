package shared.communication;

public class GetGameModelParam {
	int version;
        private String name;

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

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }
	
}
