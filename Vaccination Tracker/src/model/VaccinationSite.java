package model;

public class VaccinationSite {
	
	private String name;

	public VaccinationSite(String name, int maxSupply) {
		this.name = name;
	}
	
	public int getNumberOfAvailableDoses() {
		return 0;
	}
	
	public int getNumberOfAvailableDoses(String codename) {
		return 0;
	}
	
	public String toString() {
		
		String list = "<";
		
		// loop 
		
		list += ">";
		
		String result = String.format("%s has %d available doses: <>", 
				this.name, 
				this.getNumberOfAvailableDoses(),
				list);
		
		return result;
	}
}
