package model;

public class Log {

private String version;
	
	private String[] fixes;
	private int nof;

	public Log(String version) {
		this.version = version;
	}

	public String getVersion() {
		
		
		return null;
	}

	public int getNumberOfFixes() {
		
		return 0;
	}
	
	public String getFixes() {
		return null;
	}
	
	public String toString() {
		return String.format("Version %s contains %d fixes %s", this.version, this.getNumberOfFixes(), this.getFixes());
	}

}
