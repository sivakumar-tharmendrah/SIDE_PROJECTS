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
		//mRNA-1273, BNT162b2, Ad26.COV2.S, and AZD1222
		if(		this.codename.equals("mRNA-1273")
			||  this.codename.equals("BNT162b2")
			||  this.codename.equals("Ad26.COV2.S")
			||  this.codename.equals("AZD1222")
		) {
			this.status = String.format("Recognized vaccine: %s (%s; %s)", this.codename, this.type, this.manufacturer);
		}
		else {
			this.status = String.format("Unrecognized vaccine: %s (%s; %s)", this.codename, this.type, this.manufacturer);

		}
		
	}
	
	public String toString() {
		return this.status;
	}

}
