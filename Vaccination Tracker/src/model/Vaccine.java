package model;

public class Vaccine {
	
	private String status;
	private String codename;
	private String type;
	private String manufacturer;
	
	public Vaccine(String codename, String type, String manufacturer) {
		this.codename = codename;
		this.type = type;
		this.manufacturer = manufacturer;
		this.status = String.format("Recognized vaccine: %s (%s; %s)", this.codename, this.type, this.manufacturer);
		
	}
	
	public String toString() {
		return this.status;
	}
	
	
	
	

}
