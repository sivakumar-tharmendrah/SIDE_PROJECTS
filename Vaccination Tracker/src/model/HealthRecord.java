package model;

public class HealthRecord {
	
	private String name;

	public HealthRecord(String name, int maxNumOfDoses) {
		this.name = name;
	}

	public String getVaccinationReceipt() {
		String result = String.format("%s has not yet received any doses.", this.name);
		return result;
	}

	public String getAppointmentStatus() {
		String result = String.format("No vaccination appointment for %s yet", this.name);
		return result;
	}
	
}
  