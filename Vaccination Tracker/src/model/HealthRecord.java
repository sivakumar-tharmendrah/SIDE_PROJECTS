package model;

public class HealthRecord {

	private String name;

	private Vaccine[] vaccines;
	private String[] sites;
	private String[] dates;
	private int noi; //number of data items
	private final int MAX_SIZE_OF_DATA_COLLECTION;

	public HealthRecord(String name, int maxNumOfDoses) {
		this.name = name;
		this.MAX_SIZE_OF_DATA_COLLECTION = maxNumOfDoses;
		this.vaccines = new Vaccine[MAX_SIZE_OF_DATA_COLLECTION];
		this.sites = new String[MAX_SIZE_OF_DATA_COLLECTION];
		this.dates = new String[MAX_SIZE_OF_DATA_COLLECTION];
		this.noi = 0;
	}

	//receive the vaccine v1 in the site NYGH on this particular date
	public void addRecord(Vaccine v1, String site, String date) {
		this.vaccines[this.noi] = v1;
		this.sites[this.noi] = site;
		this.dates[this.noi] = date;
		this.noi++;
	}

	public String getVaccinationReceipt() { 
		String result = null;
		
		if(this.noi == 0) {
			result = String.format("%s has not yet received any doses.", this.name);
		}
		else {
			String list = "[";
			for(int i = 0; i < this.noi; i ++) {
				// Recognized vaccine: mRNA-1273 (RNA; Moderna) in North York General Hospital on April-20-2021
				list += String.format("%s in %s on %s", 
							this.vaccines[i].toString(), this.sites[i], this.dates[i]);
				if(i < this.noi - 1) {//going thru last item that's stored in the array
					list += "; ";
				}	
			}
			list += "]";
			//  "Number of doses Alan has received: 1 [Recognized vaccine: mRNA-1273 (RNA; Moderna) in North York General Hospital on April-20-2021]"
			result = String.format("Number of doses %s has received: %d %s",
						this.name, this.noi, list);
		
		}

		return result;
	}

	public String getAppointmentStatus() {
		String result = String.format("No vaccination appointment for %s yet", this.name);
		return result;
	}

}
