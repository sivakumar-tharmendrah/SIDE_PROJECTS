package model;

public class VaccinationSite {
	
	private String name;
	private VaccineDistribution[] distributions;
	private int nod;
	private final int MAX_NUM_OF_DIST = 4;
	
	public VaccinationSite(String name, int maxSupply) {
		this.name = name;
		this.distributions = new VaccineDistribution[MAX_NUM_OF_DIST];
		this.nod = 0;
	}
	
	public void addDistribution(Vaccine v, int numberOfDoses) throws 
	UnrecognizedVaccineCodeNameException, TooMuchDistributionException {
		boolean b1 = false;
		boolean b2 = false;
		
		if(b1) {
			throw new UnrecognizedVaccineCodeNameException("Error: ...");
		}
		else if(b2){
			throw new TooMuchDistributionException("Error: ...");
		}
		else {
			int index = -1; //index of distribution
			boolean found = false; //found is initually false, go over 
			for (int i = 0; !found && i < this.nod; i ++) {
				if(this.distributions[i].getVaccine().getCodename().equals(v.getCodename())) {
					found = true;
					index = i;
				}
			}
			if(index < 0) {
				// case 1: The first distribution of 'v' has not been added yet.
				this.distributions[this.nod] = new VaccineDistribution(v, numberOfDoses);
				this.nod ++;  
			}
			else { // index >= 0
				// case 2: the first distribution of 'v' has been added.
				VaccineDistribution existing = this.distributions[index];
				existing.setNumberOfDoses(existing.getNumberOfDoses() + numberOfDoses); 
				
			}
			
			
			// case 2: The first distribution of 'v' has been added already
		}
		
	}
	
	public int getNumberOfAvailableDoses() {
		int total = 0;
		for(int i = 0; i < this.nod; i ++) {
			total += this.distributions[i].getNumberOfDoses();
		}
		return total;
	}
	
	public int getNumberOfAvailableDoses(String codename) {
		int index = -1; //index of distribution
		boolean found = false; //found is initually false, go over 
		for (int i = 0; !found && i < this.nod; i ++) {
			if(this.distributions[i].getVaccine().getCodename().equals(codename)) {
				found = true;
				index = i;  
			}
		}
		
		int result = 0;
		if(index >= 0) { //vaccine does not exist in distribution 
			result = this.distributions[index].getNumberOfDoses();
		}
		return result;
	}
	

	
	public String toString() {
		
		String list = "<";
		
		// loop 
		for(int i = 0; i < this.nod; i ++) {
			VaccineDistribution d = this.distributions[i];
			list += String.format("%d doses of %s",
					d.getNumberOfDoses(), 
					d.getVaccine().getManufacturer());
			if(i < this.nod - 1) {
				list += ", ";
			}
		}
		
		
		list += ">";
		
		String result = String.format("%s has %d available doses: %s", 
				this.name, 
				this.getNumberOfAvailableDoses(),
				list);
		
		return result;
	}
	
}
