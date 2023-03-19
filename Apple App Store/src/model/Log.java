package model;

public class Log {
	
	private String version;

	public Log(String version) {
		this.version = version;
	}

	public String getVersion() {
		
		
		return this.version;
	}

	public int getNumberOfFixes() {
		
		return 0;
	}
	
	public String getFixes() {
		return null;
	}
	
	public String toString() {
		"Version 5.7.31 contains 0 fixes []"
	}
	
}
