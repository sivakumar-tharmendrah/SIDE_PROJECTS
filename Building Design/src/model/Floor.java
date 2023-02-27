package model;

public class Floor {
	
	private Unit[] units;
	private int nou; /* number of units */
	private final int MAX_NUM_OF_UNITS = 20;
	
	private int maxCapacity; /* in square feet, everything is int not floating point number */
	
	public Floor(int maxCapacity) {
		this.maxCapacity = maxCapacity;
		this.units = new Unit[MAX_NUM_OF_UNITS];
		this.nou = 0;
	}

	public void addUnit(String function, int width, int length) {
		Unit u = new Unit(function, width, length);
		this.units[this.nou] = u;
		this.nou ++;
	}
	 
	public String toString() {
		String result = String.format("Floor's utilized space is 0 sq ft (500 sq ft remaining): []", null);
		
		return result;
	}


}
