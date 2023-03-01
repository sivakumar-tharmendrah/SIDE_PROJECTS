package model;


public class Blueprint {
	private Floor[] floors;
	private int nof; /* number of floors */
	private int maxNumOfFloors;

	public Blueprint(int maxNumberOfFloors) {
		this.maxNumOfFloors = maxNumberOfFloors;
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
			results[i] = new Floor(this.floors[i]);
		}
		
		return results;
	}
	
	public String toString() {
		//"0.0 percents of building blueprint completed (0 out of 7 floors)"
		
		double percentage = (this.nof / (double) this.maxNumOfFloors) * 100;
		String percentageS = String.format("%.1f", percentage);
		
		return String.format("%s percents of building blueprint completed (%d out of %d floors)", 
				percentageS, this.nof, this.maxNumOfFloors);
	
		 
	}
}