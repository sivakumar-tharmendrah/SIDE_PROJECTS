package model;

public class Account {
	
	private String status;
	private String name;
	private AppStore store; //each owner can only be registered with one store
	
	private String[] namesOfDownloadedApps;
	private int noa; /* number of downloads*/
	
	public Account(String name, AppStore appStore) {
		this.name = name;
		this.store = appStore;
		
		this.namesOfDownloadedApps = new String[50];
		this.noa = 0;
		
		this.status = 
				String.format("An account linked to the %s store is created for %s.", 
						appStore.getBranch(), name);
	}
	
	public void download(String nameOfApp) {
		this.namesOfDownloadedApps[this.noa] = nameOfApp;
		this.noa ++;
		
		this.status = String.format("%s is successfully downloaded for %s.", 
									nameOfApp, this.name);
	}
	
	public void submitRating(String nameOfApp, int score) {
		this.status =  String.format("Error: %s is not a downloaded app for %s.", 
									nameOfApp, this.name);
	}
	
	public void switchStore(AppStore store) {
		this.store = store;
		this.status = String.format("Account for %s is now linked to the %s store.", 
										this.name, this.store.getBranch());
	}
	
	public void uninstall(String nameOfApp) {
		// check to see if 'nameOfApp' is the name of a downloaded app.
		// if non-existing, do nothing.
		// if existing, remove the app.
		
		// Initially, when an account is first created, no apps have been downloaded,
		// so therefore nothing can be uninstalled
		
		this.status = String.format("Error: %s has not been downloaded for %s.",
									nameOfApp, this.name);
	}
	
	public String[] getNamesOfDownloadedApps() {
		String[] names = new String[this.noa];

		for(int i = 0; i < this.noa; i++) {
			names[i] = this.namesOfDownloadedApps[i];
		}

		return names;
	}
	
	public App[] getObjectsOfDownloadedApps() {
		return new App[0];
	}
	
	//Given the name of app, find its corresponding app object in the linked store
	private App getApp(String nameOfApp) {
		App result = null;
		boolean foundMatch = false;
		for(int i = 0; i < this.noa && !foundMatch; i ++) {
			String l = this.updates[i];
			if(l.getVersion().equals(version)) {
				result = l;
				foundMatch = true;
			}
		}

		return result;
	}
	
	public String toString() {
		return this.status;
	}

}
