package model;

public class Blueprint {
	private Floor[] floors;
	private int nof; /* number of floors */

	public Blueprint(int maxNumberOfFloors) {
		this.floors = new Floor[maxNumberOfFloors];
		this.nof = 0;
	}    
	
	public void addFloorPlan(Floor f) {
		this.floors[this.nof] = f;
		this.nof ++;
	}
	
	public Floor[] getFloors() {
		Floor[] results = new Floor[this.nof];
		
		for(int i = 0; i < this.nof; i ++) {
			results[i] = this.floors[i];
		}
		
		return results;
	}
	
	public String toString() {
		//"0.0 percents of building blueprint completed (0 out of 7 floors)"
	}
}