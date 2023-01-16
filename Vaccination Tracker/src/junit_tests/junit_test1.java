package junit_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.HealthRecord;
import model.TooMuchDistributionException;
import model.UnrecognizedVaccineCodeNameException;
import model.VaccinationSite;
import model.Vaccine;
import model.VaccineDistribution;

public class junit_test1 {
	
	@Test
	public void test_vaccine_01() {
		/* 
		 * Initialize records of vaccines recognized in Canada. 
		 * Input values of the constructor consist of the codename, type, and manufacturer. 
		 */ 
		Vaccine v1 = new Vaccine("mRNA-1273", "RNA", "Moderna");
		Vaccine v2 = new Vaccine("BNT162b2", "RNA", "Pfizer/BioNTech");
		Vaccine v3 = new Vaccine("Ad26.COV2.S", "Non Replicating Viral Vector", "Janssen");
		Vaccine v4 = new Vaccine("AZD1222", "Non Replicating Viral Vector", "Oxford/AstraZeneca");

		assertEquals("Recognized vaccine: mRNA-1273 (RNA; Moderna)", v1.toString());
		assertEquals("Recognized vaccine: BNT162b2 (RNA; Pfizer/BioNTech)", v2.toString());
		assertEquals("Recognized vaccine: Ad26.COV2.S (Non Replicating Viral Vector; Janssen)", v3.toString());
		assertEquals("Recognized vaccine: AZD1222 (Non Replicating Viral Vector; Oxford/AstraZeneca)", v4.toString());
	}
	
	@Test
	public void test_vaccine_02() {
		/* 
		 * Initialize records of vaccines not recognized in Canada. 
		 * Input values of the constructor consist of the codename, type, and manufacturer. 
		 */ 
		Vaccine v5 = new Vaccine("Covishield", "Non Replicating Viral Vector", "Serum Institute of India");
		Vaccine v6 = new Vaccine("BBIBP-CorV", "Inactivated", "Sinopharm");
		Vaccine v7 = new Vaccine("CoronaVac", "Inactivated", "Sinovac"); 

		assertEquals("Unrecognized vaccine: Covishield (Non Replicating Viral Vector; Serum Institute of India)", v5.toString());
		assertEquals("Unrecognized vaccine: BBIBP-CorV (Inactivated; Sinopharm)", v6.toString());
		assertEquals("Unrecognized vaccine: CoronaVac (Inactivated; Sinovac)", v7.toString()); 
	}
	
	/*
	 * Tests related to the VaccineDistribution class.
	 */
	@Test 
	public void test_vaccine_distribution_01() {
		/* Create a distribution of some recognized vaccine. */
		Vaccine v1 = new Vaccine("mRNA-1273", "RNA", "Moderna");
		VaccineDistribution dist1 = new VaccineDistribution(v1, 10000);
		assertEquals("10000 doses of mRNA-1273 by Moderna", dist1.toString());

		/* Create a distribution of some unrecognized vaccine. */
		Vaccine v6 = new Vaccine("BBIBP-CorV", "Inactivated", "Sinopharm");
		VaccineDistribution dist2 = new VaccineDistribution(v6, 25000);
		assertEquals("25000 doses of BBIBP-CorV by Sinopharm", dist2.toString());
	}
	
	/*
	 * Tests related to the HealthRecord class.
	 */
	@Test
	public void test_health_record_01() {
		/* 
		 * Create a health record with the patient name and 
		 * 	the limit on the number of doses appearing on their vaccination history.
		 */
		HealthRecord rec = new HealthRecord("Alan", 5);

		/* No vaccination history yet for the patient. */
		String s1 = rec.getVaccinationReceipt();
		assertEquals("Alan has not yet received any doses.", s1);

		/* No appointments booked yet for the patient */
		String s2 = rec.getAppointmentStatus();
		assertEquals("No vaccination appointment for Alan yet", s2);
	}
	
	@Test
	public void test_health_record_02() {
		/* 
		 * Creating a health record with the patient name and 
		 * 	the limit on the number of doses appearing on their vaccination history.
		 */
		HealthRecord rec = new HealthRecord("Alan", 5);

		Vaccine v1 = new Vaccine("mRNA-1273", "RNA", "Moderna");
		Vaccine v2 = new Vaccine("BNT162b2", "RNA", "Pfizer/BioNTech");

		/* 
		 * Add a record for the patient's 1st received dose.
		 * Each record contains:
		 * 	- the vaccine reference
		 * 	- the name of vaccination site
		 * 	- the date of receiving the dose  
		 */
		rec.addRecord(v1, "North York General Hospital", "April-20-2021");
		/* 
		 * A vaccination receipt contains:
		 * 	- The number of doses the patient has received
		 * 	- A semi-colon-separated list, where each item gives information about: 
		 * 		+ the vaccine info
		 * 		+ the name of vaccination site
		 * 		+ the date of vaccination
		 */
		assertEquals("Number of doses Alan has received: 1 [Recognized vaccine: mRNA-1273 (RNA; Moderna) in North York General Hospital on April-20-2021]", rec.getVaccinationReceipt());

		/* Add a record for the patient's 2nd received dose. */
		rec.addRecord(v2, "Humber River Hospital", "June-30-2021");
		assertEquals("Number of doses Alan has received: 2 [Recognized vaccine: mRNA-1273 (RNA; Moderna) in North York General Hospital on April-20-2021; Recognized vaccine: BNT162b2 (RNA; Pfizer/BioNTech) in Humber River Hospital on June-30-2021]", rec.getVaccinationReceipt());

		/* 
		 * Patient's appointment status does not get changed by adding records.
		 * It's only changed when the `bookAppointment` method is invoked on a VaccinationSite object.
		 */
		assertEquals("No vaccination appointment for Alan yet", rec.getAppointmentStatus());
	}
	
	/*
	 * Tests related to the VaccinationSite class.
	 */

	@Test
	public void test_vaccination_site_01() {
		/* 
		 * Create a vaccination site with its name and
		 * 	the limit on the number of doses accumulated from the added distributions.
		 */
		VaccinationSite vs = new VaccinationSite("North York General Hospital", 10);

		assertEquals("North York General Hospital has 0 available doses: <>", vs.toString());

		/* Initially, there is no supply on the site. */
		int totalSupply = vs.getNumberOfAvailableDoses();
		assertEquals(0, totalSupply);

		/* 
		 * When inquiring about the supply of a kind of vaccines,
		 * 	the codename is used. 
		 */

		/* Supplies of recognized vaccines (but remember that the site is empty) */
		int supplyOfModerna = vs.getNumberOfAvailableDoses("mRNA-1273");
		int supplyOfPfizer = vs.getNumberOfAvailableDoses("BNT162b2");
		assertEquals(0, supplyOfModerna);
		assertEquals(0, supplyOfPfizer);

		/* 
		 * Supplies of unrecognized vaccines should always be zero,
		 * 	as their distributions can never be added to the site. 
		 */
		int supplyOfSinovac = vs.getNumberOfAvailableDoses("CoronaVac");
		assertEquals(0, supplyOfSinovac);
	}
	
	@Test
	public void test_vaccination_site_02a() {
		/* 
		 * Create a vaccination site with its name and
		 * 	the limit on the number of doses accumulated from the added distributions.
		 */
		VaccinationSite vs = new VaccinationSite("North York General Hospital", 10);

		/* Add distributions of three recognized vaccines (identified by their codenames) */
		Vaccine v1 = new Vaccine("mRNA-1273", "RNA", "Moderna");
		Vaccine v2 = new Vaccine("BNT162b2", "RNA", "Pfizer/BioNTech");
		Vaccine v3 = new Vaccine("AZD1222", "Non Replicating Viral Vector", "Oxford/AstraZeneca");

		try {
			/* 
			 * The string description of a vaccination site includes:
			 * 	1) its name
			 * 	2) total supply
			 * 	3) supplies of available kinds of vaccines (each displayed with their manufacturer; see below)
			 * 	
			 * 	Note. For 3), the order in which these supplies are reported corresponds to 
			 * 			the chronological order of their first-added distributions.
			 */

			vs.addDistribution(v1, 3); /* 1st distribution of Moderna */
			assertEquals("North York General Hospital has 3 available doses: <3 doses of Moderna>", vs.toString());

			vs.addDistribution(v2, 2); /* 1st distribution of Pfizer */
			assertEquals("North York General Hospital has 5 available doses: <3 doses of Moderna, 2 doses of Pfizer/BioNTech>", vs.toString());

			vs.addDistribution(v1, 1); 
			assertEquals("North York General Hospital has 6 available doses: <4 doses of Moderna, 2 doses of Pfizer/BioNTech>", vs.toString());

			vs.addDistribution(v3, 1); /* 1st distribution of AZ */
			assertEquals("North York General Hospital has 7 available doses: <4 doses of Moderna, 2 doses of Pfizer/BioNTech, 1 doses of Oxford/AstraZeneca>", vs.toString());

			vs.addDistribution(v2, 3);
			assertEquals("North York General Hospital has 10 available doses: <4 doses of Moderna, 5 doses of Pfizer/BioNTech, 1 doses of Oxford/AstraZeneca>", vs.toString());
		}
		catch(UnrecognizedVaccineCodeNameException e) {
			fail("Unexpected exception thrown");
		}
		catch(TooMuchDistributionException e) {
			fail("Unexpected exception thrown");
		}
	}
	

	@Test
	public void test_vaccination_site_02b() {
		/* 
		 * Create a vaccination site with its name and
		 * 	the limit on the number of doses accumulated from the added distributions.
		 */
		VaccinationSite vs = new VaccinationSite("North York General Hospital", 10);

		/* Add distributions of three recognized vaccines (identified by their codenames) */
		Vaccine v1 = new Vaccine("mRNA-1273", "RNA", "Moderna");
		Vaccine v2 = new Vaccine("BNT162b2", "RNA", "Pfizer/BioNTech");
		Vaccine v3 = new Vaccine("AZD1222", "Non Replicating Viral Vector", "Oxford/AstraZeneca");

		try {
			/* 
			 * The string description of a vaccination site includes:
			 * 	1) its name
			 * 	2) total supply
			 * 	3) supplies of available kinds of vaccines (each displayed with their manufacturer; see below)
			 * 	
			 * 	Note. For 3), the order in which these supplies are reported corresponds to 
			 * 			the chronological order of their first-added distributions.
			 */

			vs.addDistribution(v3, 3); /* 1st distribution of AZ */
			assertEquals("North York General Hospital has 3 available doses: <3 doses of Oxford/AstraZeneca>", vs.toString());
			assertEquals(3, vs.getNumberOfAvailableDoses());
			assertEquals(3, vs.getNumberOfAvailableDoses("AZD1222"));
			assertEquals(0, vs.getNumberOfAvailableDoses("mRNA-1273"));
			assertEquals(0, vs.getNumberOfAvailableDoses("BNT162b2"));

			vs.addDistribution(v1, 2); /* 1st distribution of Moderna */
			assertEquals("North York General Hospital has 5 available doses: <3 doses of Oxford/AstraZeneca, 2 doses of Moderna>", vs.toString());
			assertEquals(5, vs.getNumberOfAvailableDoses());
			assertEquals(3, vs.getNumberOfAvailableDoses("AZD1222"));
			assertEquals(2, vs.getNumberOfAvailableDoses("mRNA-1273"));
			assertEquals(0, vs.getNumberOfAvailableDoses("BNT162b2"));

			vs.addDistribution(v3, 1); 
			assertEquals("North York General Hospital has 6 available doses: <4 doses of Oxford/AstraZeneca, 2 doses of Moderna>", vs.toString());
			assertEquals(6, vs.getNumberOfAvailableDoses());
			assertEquals(4, vs.getNumberOfAvailableDoses("AZD1222"));
			assertEquals(2, vs.getNumberOfAvailableDoses("mRNA-1273"));
			assertEquals(0, vs.getNumberOfAvailableDoses("BNT162b2"));

			vs.addDistribution(v2, 1); /* 1st distribution of Pfizer */
			assertEquals("North York General Hospital has 7 available doses: <4 doses of Oxford/AstraZeneca, 2 doses of Moderna, 1 doses of Pfizer/BioNTech>", vs.toString());
			assertEquals(7, vs.getNumberOfAvailableDoses());
			assertEquals(4, vs.getNumberOfAvailableDoses("AZD1222"));
			assertEquals(2, vs.getNumberOfAvailableDoses("mRNA-1273"));
			assertEquals(1, vs.getNumberOfAvailableDoses("BNT162b2"));

			vs.addDistribution(v1, 3);
			assertEquals("North York General Hospital has 10 available doses: <4 doses of Oxford/AstraZeneca, 5 doses of Moderna, 1 doses of Pfizer/BioNTech>", vs.toString());
			assertEquals(10, vs.getNumberOfAvailableDoses());
			assertEquals(4, vs.getNumberOfAvailableDoses("AZD1222"));
			assertEquals(5, vs.getNumberOfAvailableDoses("mRNA-1273"));
			assertEquals(1, vs.getNumberOfAvailableDoses("BNT162b2"));
		}
		catch(UnrecognizedVaccineCodeNameException e) {
			fail("Unexpected exception thrown");
		}
		catch(TooMuchDistributionException e) {
			fail("Unexpected exception thrown");
		}
	}
	
	@Test
	public void test_vaccination_site_02c() {

		VaccinationSite vs = new VaccinationSite("North York General Hospital", 10);
		Vaccine v1 = new Vaccine("mRNA-1273", "RNA", "Moderna");
		Vaccine v2 = new Vaccine("Covishield", "Non Replicating Viral Vector", "Serum Institute of India");

		try {
			/* Add the distribution of a recognized vaccine (identified by its codename) */
			vs.addDistribution(v1, 6); // 4 doses left before reaching the maximum    
		}
		catch(UnrecognizedVaccineCodeNameException e) {
			fail("Unexpected exception thrown");
		}
		catch(TooMuchDistributionException e) {
			fail("Unexpected exception thrown");
		}

		/* up to this point, the site is 4 doses away from being "full". */

		try {
			/* 
			 * Add the distribution of an unrecognized vaccine (identified by its codename).
			 * Even though the quantity to add (5) will cause the resulting supply 
			 * 	to exceed the limit 10 (set above), the error of unrecognized vaccine takes priority.	
			 */
			vs.addDistribution(v2, 5);   
			fail("Expected exception not thrown");
		}
		catch(UnrecognizedVaccineCodeNameException e) {
			// Expected
		}
		catch(TooMuchDistributionException e) {
			fail("Unexpected exception thrown");
		}
	}
	
	@Test
	public void test_vaccination_site_02d() {

		VaccinationSite vs = new VaccinationSite("North York General Hospital", 10);
		Vaccine v1 = new Vaccine("mRNA-1273", "RNA", "Moderna");
		Vaccine v2 = new Vaccine("BNT162b2", "RNA", "Pfizer/BioNTech");

		try {
			/* Add the distribution of a recognized vaccine (identified by its codename) */
			vs.addDistribution(v1, 6); // 4 doses left before reaching the maximum    
		}
		catch(UnrecognizedVaccineCodeNameException e) {
			fail("Unexpected exception thrown");
		}
		catch(TooMuchDistributionException e) {
			fail("Unexpected exception thrown");
		}

		/* up to this point, the site is 4 doses away from being "full". */

		assertEquals(6, vs.getNumberOfAvailableDoses());
		try {
			/* 
			 * Add the distribution of another recognized vaccine (identified by its codename).
			 * Given that the vaccine's codename is recognized, 
			 * 	because the quantity to add (5) will cause the resulting supply 
			 * 	to exceed the limit 10 (set above), an error related to too much distribution will occur.	
			 */
			vs.addDistribution(v2, 5);   
			fail("Expected exception not thrown");
		}
		catch(UnrecognizedVaccineCodeNameException e) {
			fail("Unexpected exception thrown");
		}
		catch(TooMuchDistributionException e) {
			// Expected
		}

	}
	
	@Test
	public void test_vaccination_site_03a() {
		/* 
		 * Create a vaccination site with its name and
		 * 	the limit on the number of doses accumulated from the added distributions.
		 */
		VaccinationSite vs = new VaccinationSite("North York General Hospital", 10);

		Vaccine v1 = new Vaccine("mRNA-1273", "RNA", "Moderna");
		Vaccine v2 = new Vaccine("BNT162b2", "RNA", "Pfizer/BioNTech");
		try {
			/* Add distributions of two recognized vaccines. */
			vs.addDistribution(v1, 1);   
			vs.addDistribution(v2, 2); 
		}
		catch(UnrecognizedVaccineCodeNameException e) {
			fail("Unexpected exception thrown");
		}
		catch(TooMuchDistributionException e) {
			fail("Unexpected exception thrown");
		}

		/* 3 doses are available: 3 appointments  possible */
		assertEquals(3, vs.getNumberOfAvailableDoses());

		HealthRecord alan = new HealthRecord("Alan", 5);
		HealthRecord mark = new HealthRecord("Mark", 5);
		HealthRecord tom = new HealthRecord("Tom", 5);

		try {

			vs.bookAppointment(alan);
			/* success of appointment is reflected on the patient's appointment status */
			assertEquals("Last vaccination appointment for Alan with North York General Hospital succeeded", alan.getAppointmentStatus());
			vs.bookAppointment(mark);
			assertEquals("Last vaccination appointment for Mark with North York General Hospital succeeded", mark.getAppointmentStatus());
			vs.bookAppointment(tom);
			assertEquals("Last vaccination appointment for Tom with North York General Hospital succeeded", tom.getAppointmentStatus());
		}
		catch(InsufficientVaccineDosesException e) {
			fail("Unexpected exception thrown");
		}

		/* 
		 * 3 appointments reserved: once they are administered, no doses will be left for further appointments.
		 * 
		 * However, since these appointments have not yet been administered, 
		 * 	  the counting of available doses still includes ones that will be consumed by these appointments.  
		 */
		assertEquals(3, vs.getNumberOfAvailableDoses());

		HealthRecord jim = new HealthRecord("Jim", 5);
		try {
			vs.bookAppointment(jim);
			fail("Expeted exception not thrown");
		}
		catch(InsufficientVaccineDosesException e) {
			/* failure of appointment is reflected on the patient's appointment status */
			assertEquals("Last vaccination appointment for Jim with North York General Hospital failed", jim.getAppointmentStatus());
		}
	}
	

}
