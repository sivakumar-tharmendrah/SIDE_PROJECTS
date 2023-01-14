package model;


public class VaccineDistribution {
	
	private String status;
	private int numberOfDoses;
	
	private Vaccine vaccine;
	
	public VaccineDistribution(Vaccine vaccine, int numberOfDoses) {
		this.vaccine = vaccine;
		this.numberOfDoses = numberOfDoses;
		
		this.status = 
				String.format("%d doses of %s by %s", 
						this.numberOfDoses, 
						this.vaccine.getCodename(),
						this.vaccine.getManufacturer());
	}
	
	public String toString() {
		return this.status;
	}
	

	
	
	

}