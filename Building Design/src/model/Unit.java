package model;

public class Unit {

	private String function;
	private int width;
	private int length;
	private String mode; /* feet or meters */
	private final String FEET_MODE = "feet";
	private final String METERS_MODE = "meters"; 
	private final double FOOT_IN_METERS = 0.3048;

	public Unit(String function, int width, int length) {
		this.function = function;
		this.width = width;
		this.length = length;
		this.mode = FEET_MODE;
	}

	public void toogleMeasurement() {
		if(this.mode.equals(FEET_MODE)) {
			this.mode = METERS_MODE;
		}
		else { /* mode is "meters" */
			this.mode = FEET_MODE;
		}
	}
	
	public boolean equals(Object obj){
		if(this == obj) {
			return true;
		}
		else if(obj == null || this.getClass() != obj.getClass()) {
			
		}
		//Reaching this line means both 'this' and 'obj' are pointing to objects of the same dynamic type
		Unit other = (Unit) obj;
		return 
				this.function.equals(other.function) 
			&&  this.getAreaInSquareMeters() == other.getAreaInSquareMeters();
	}

	public String toString() {
		String result = null;
		if(mode.equals(FEET_MODE)) {
			result = 
					String.format("A unit of %d square feet (%d' wide and %d' long) functioning as %s",  
							this.getAreaInSquareFeet(), 
							this.width, 
							this.length, 
							this.function);
		}
		else {
			result = String.format("A unit of %.2f square meters (%.2f m wide and %.2f m long) functioning as %s", 
					this.getAreaInSquareMeters(), 
					this.width * FOOT_IN_METERS, 
					this.length * FOOT_IN_METERS, 
					this.function);
		}
		return result;
	}

	public int getAreaInSquareFeet() {
		return this.width * this.length;
	}

	public double getAreaInSquareMeters() {
		return ((this.width * FOOT_IN_METERS) * (this.length * FOOT_IN_METERS));
	}


}
