package model;

public class AppStore {
	
	private String branch;
	
	private App[] apps;
	
	private int noa; /* number of apps */
	
	public AppStore(String branch, int maxAvailableApps) {
		this.branch = branch;
		this.apps = new App[maxAvailableApps];
		this.noa = 0;
	}
	
	public String getBranch() {
		return this.branch;
	}
	
	public App getApp(String nameOfApp) {
		App result = null;
		boolean foundMatch = false;
		for(int i = 0; i < this.noa && !foundMatch; i ++) {
			App app = this.apps[i];
			if(app.getName().equals(nameOfApp)) {
				result = app;
				foundMatch = true;
			}
		}

		return result;
	}
	
	public String[] getStableApps(int numberOfUpdates) {
		String[] stableApps = new String[this.noa];//actual number of stable apps is stricly smaller than this.noa, contain null spots
		
		int count = 0;
		
		for(int i = 0; i < this.noa; i ++) {
			App app = this.apps[i];
			if(app.getUpdateHistory().length >= numberOfUpdates) {
				stableApps[count] =
					String.format("%d versions; Current Versions: %s", 
							app.getName(), app.getUpdateHistory().length, app.getUpdateHistory().length, app.getWhatIsNew());
				count ++;
			}
		}
		String[] stableAppsPrecise = new String[count];
		
		for(int i = 0; i < count; i ++) {
			stableAppsPrecise[i] = stableApps[i];
		}
		return stableAppsPrecise;
	}
	
	public void addApp(App app) {
		this.apps[this.noa] = app;
		this.noa ++;
	}
}
