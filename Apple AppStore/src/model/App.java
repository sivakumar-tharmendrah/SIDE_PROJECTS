package model;

public class App {
	private Log[] updates;
	private int nou; /* number of updates */
	private final int MAX_NUM_OF_UPDATES = 20;

	private int[] ratings;
	private int nor; /* number of ratings */

	private String name;

	public App(String name, int maxNumOfRatings) {
		this.name = name;

		this.updates = new Log[MAX_NUM_OF_UPDATES];
		this.nou = 0;

		this.ratings = new int[maxNumOfRatings];
		this.nor = 0;
	}

	public void releaseUpdate(String version) {
		Log nu = new Log(version);
		this.updates[this.nou] = nu;
		this.nou ++;
	}

	public void submitRating(int score) {
		this.ratings[this.nor] = score;
		this.nor ++;
	}

	public String getName() {
		return this.name;
	}

	//return info for latest version (last element in array for log)
	public String getWhatIsNew() {
		String s = "";

		if(this.nou == 0) {
			s = "n/a";
		}
		else {
			s = this.updates[this.nou - 1].toString();
		}

		return s;
	}

	// getEntries()
	public Log[] getUpdateHistory() {
		Log[] uh = new Log[this.nou];

		for(int i = 0; i < this.nou; i++) {
			uh[i] = this.updates[i];
		}

		return uh;
	}


	public Log getVersionInfo(String version) {
		Log result = null;
		boolean foundMatch = false;
		for(int i = 0; i < this.nou && !foundMatch; i ++) {
			Log l = this.updates[i];
			if(l.getVersion().equals(version)) {
				result = l;
				foundMatch = true;
			}
		}

		return result;
	}

	public String getRatingReport() {
		String report = "";

		if(this.nor == 0) {
			report = "No ratings submitted so far!";
		}
		else {
			int total = 0;
			int numberOf5 = 0;
			int numberOf4 = 0;
			int numberOf3 = 0;
			int numberOf2 = 0;
			int numberOf1 = 0;

			for(int i = 0; i < this.nor; i ++) {
				int score = this.ratings[i];
				total += score;
				if(score == 5) {
					numberOf5 ++;
				}
				else if(score == 4) {
					numberOf4 ++;
				}
				else if(score == 3) {
					numberOf3 ++;
				}
				else if(score == 2) {
					numberOf2 ++;
				}
				else {
					numberOf1 ++;
				}
			}
			String avgS = this.getFormattedAve();
			report = String.format("Average of %d ratings: %s (Score 5: %d, Score 4: %d, Score 3: %d, Score 2: %d, Score 1: %d", 
					this.nor, avgS, numberOf5, numberOf4, numberOf3, numberOf2, numberOf1);
		}

		return report;
	}

	private String getFormattedAve() {
		int total = 0;
		for(int i = 0; i < this.nor; i ++) {
			int score = this.ratings[i];
			total += score;
		}
		double avg = ((double) total) / this.nor;
		String avgS = String.format("%.1f", avg);
		return avgS;

	}

	public String toString() {
		String avgS = "";
		if(this.nor == 0) {
			avgS = "n/a";
		}
		else {
			avgS = this.getFormattedAve();
		}
		return String.format("%s (Current Version: %s; Average Rating: %s)", 
				this.name, this.getWhatIsNew().toString(), avgS);
	}

}
