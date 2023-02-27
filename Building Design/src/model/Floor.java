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

	public void addUnit(String function, int width, int length) throws InsufficientFloorSpaceException {
		
		if(this.maxCapacity - this.getUtilizedSpace() - width * length < 0) {
			throw new InsufficientFloorSpaceException("Error: ...");
		}
		else {
			Unit u = new Unit(function, width, length);
			this.units[this.nou] = u;
			this.nou ++;
		}
		
	}
	
	
	 
	public String toString() {
		
		String list = "[";
		for(int i = 0; i < this.nou; i ++) {
			Unit u = this.units[i];
			list += String.format("%s: %d sq ft (%d' by %d')", 
					u.getFunction(),
					u.getAreaInSquareFeet(),
					u.getWidth(),
					u.getLength());
			if(i < this.nou - 1) {
				list += ", ";
			}
		}
		list = "]";
		
		
		int utilizedSpace = this.getUtilizedSpace();
		
		String result = String.format("Floor's utilized space is %d sq ft (%d sq ft remaining): [%s", 
								utilizedSpace,
								this.maxCapacity - utilizedSpace,
								list);
		
		return result;
	}
	
	public int getUtilizedSpace() {
		int total = 0;
		
		for(int i = 0; i < this.nou; i ++) {
			total += this.units[i].getAreaInSquareFeet();
		}
		
		return total;
	}


}
