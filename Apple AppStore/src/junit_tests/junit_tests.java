package junit_tests;

import static org.junit.Assert.*;

import org.junit.Test;
import model.Log;

public class junit_tests {

	@Test
	public void test_log_01() {
		// Create a new app update log with a version number
		Log appUpdate = new Log("5.7.31");

		// Retrieve the set version value
		String v = appUpdate.getVersion();
		assertEquals("5.7.31", v);

		//Initially, no fixes have been added to the update log
		int n = appUpdate.getNumberOfFixes();
		assertEquals(0, n);

		// The list of fixes (appearing within a pair of square brackets) is empty
		String s1 = appUpdate.getFixes();
		assertEquals("[]", s1);

		/*The string rep of update log object includes:
		 * -its set version
		 * -the number of fixes so far
		 * -a comma-separated list of fices, enclosed within a pair of square brackets
		 */
		String s2 = appUpdate.toString();
		assertEquals("Version 5.7.31 contains 0 fixes []", s2);
	}

	@Test
	public void test_log_02() {
		Log appUpdate = new Log("5.7.31");
		
		//add 2 fixes to update log
		//no error handling is needed when the max limit is exceeded
		
		appUpdate.addFix("Addressed writing lag issues");
		appUpdate.addFix("Fixed a bug about dismissing menus");
		
		assertEquals("5.7.31", appUpdate.getVersion());
		//2 fixes have been added to the app update log so far
		assertEquals(2, appUpdate.getNumberOfFixes());
		//list of fixes returned is comma-separated
		
		assertEquals("[Addressed writing lag issues, Fixed a bug about dismissing menus]", appUpdate.getFixes());
		assertEquals("Version 5.7.31 contains 2 fixes [Addressed writing lag issues, Fixed a bug about dismissing menus]", app);
		
	}
 
}