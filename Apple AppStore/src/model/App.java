package model;

public class App {
	private Log[] updates;
	private int nou; /* number of updates */
	private final int MAX_NUM_OF_UPDATES = 20;
	
	private String name;
	
	public App(String name, int maxNumOfRatings) {
		this.name = name;
		this.updates = new Log[MAX_NUM_OF_UPDATES];
		this.nou = 0;
	}
	
	public void releaseUpdate(String version) {
		
	}
	
	public String getName() {
		return this.name;
	}
	
	//return info for latest version (last element in array for log)
	public String getWhatIsNew() {
		return "n/a";
	}
	
	public Log[] getUpdateHistory() {
		return new Log[0];
	}
	
	
	public Log getVersionInfo(String version) {
		return null;
	}
	
	public String getRatingReport() {
		return "No rating submitted so far";
	}
	
	public String toString() {
		return String.format("%s (Current Version: %s; Average Rating: n/a)", 
					this.name, this.getWhatIsNew());
	}

	
}
