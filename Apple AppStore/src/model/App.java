package model;

public class App {
	private String name;
	
	public App(String name, int maxNumOfRatings) {
		
	}
	
	public String getName() {
		return null;
	}
	
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

}
