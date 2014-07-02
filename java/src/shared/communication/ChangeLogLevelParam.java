package shared.communication;

import shared.definitions.LogLevel;

public class ChangeLogLevelParam {
	
	private LogLevel logLevel;
	
	public ChangeLogLevelParam(){}

	public LogLevel getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
	}

}
