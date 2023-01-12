package junit_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Vaccine;

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

}
